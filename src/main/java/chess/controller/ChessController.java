package chess.controller;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.CommandAction;
import chess.domain.command.InitCommand;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.game.Winner;
import chess.domain.piece.Color;
import chess.dto.GameResultResponse;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;
    private CommandAction commandAction;

    public ChessController(
            final InputView inputView,
            final OutputView outputView,
            final ChessGameService chessGameService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = chessGameService;
        this.commandAction = new InitCommand();
    }

    public void run() {
        outputView.printStartMessage();
        ChessGame chessGame = new ChessGame(new Board());
        retry(() -> progress(chessGame));

    }

    public void progress(final ChessGame chessGame) {
        if (!commandAction.isEnd() || !chessGame.end()) {
            Command command = new Command(inputView.readGameCommand());
            commandAction = commandAction.change(command.type());
            commandAction.execute(this, chessGame, command);
        }
    }

    public void search() {
        List<Long> gameIds = chessGameService.findAllGame();
        outputView.printGameIds(gameIds);
    }

    public void load(final ChessGame chessGame, final Long gameId) {
        chessGameService.loadGame(chessGame, gameId);
    }

    public void save(final ChessGame chessGame) {
        Long gameId = chessGameService.saveGame(chessGame);
        outputView.printSaveMessage(gameId);
    }

    public void printBoard(final ChessGame chessGame) {
        outputView.printBoard(chessGameService.getPieceResponses(chessGame.getBoard()));
    }

    public void printGameResult(final ChessGame chessGame) {
        outputView.printGameResult(responseGameResult(chessGame));
    }

    private void retry(final Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            retry(runnable);
        }
    }

    private GameResultResponse responseGameResult(final ChessGame chessGame) {
        Score score = chessGame.createScore();
        double whiteScore = score.calculateTotalScoreBy(Color.WHITE);
        double blackScore = score.calculateTotalScoreBy(Color.BLACK);
        Winner winner = Winner.of(whiteScore, blackScore);
        return new GameResultResponse(whiteScore, blackScore, winner);
    }
}
