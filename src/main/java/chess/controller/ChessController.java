package chess.controller;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.CommandAction;
import chess.domain.command.CommandType;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.game.Winner;
import chess.domain.piece.Color;
import chess.domain.square.Square;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.GameResultResponse;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;
    private final Map<CommandType, CommandAction> commandInvoker = new EnumMap<>(CommandType.class);

    public ChessController(
            final InputView inputView,
            final OutputView outputView,
            final ChessGameService chessGameService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = chessGameService;
        init();
    }

    private void init() {
        commandInvoker.put(CommandType.START, this::start);
        commandInvoker.put(CommandType.MOVE, this::move);
        commandInvoker.put(CommandType.STATUS, this::status);
        commandInvoker.put(CommandType.END, (chessGame, command) -> CommandType.END);
        commandInvoker.put(CommandType.SEARCH, this::searchGame);
        commandInvoker.put(CommandType.LOAD, this::loadGame);
        commandInvoker.put(CommandType.SAVE, this::saveGame);
    }

    public void run() {
        outputView.printStartMessage();
        CommandType commandType = CommandType.START;
        ChessGame chessGame = new ChessGame(new Board());
        while (commandType != CommandType.END) {
            commandType = progress(chessGame);
        }
    }

    private CommandType progress(final ChessGame chessGame) {
        try {
            Command command = new Command(inputView.readGameCommand());
            CommandType commandType = command.getCommand();
            CommandAction commandAction = commandInvoker.get(commandType);
            return commandAction.execute(chessGame, command);
        } catch (IllegalArgumentException error) {
            outputView.printErrorMessage(error.getMessage());
            return progress(chessGame);
        }
    }

    private CommandType start(final ChessGame chessGame, final Command command) {
        chessGame.start();
        outputView.printBoard(chessGameService.getPieceResponses(chessGame.getBoard()));
        return CommandType.START;
    }

    private CommandType searchGame(final ChessGame chessGame, final Command command) {
        List<Long> gameIds = chessGameService.findAllGame();
        outputView.printGameIds(gameIds);
        return CommandType.SEARCH;
    }

    private CommandType loadGame(final ChessGame chessGame, final Command command) {
        Long gameId = command.getLoadId();
        chessGameService.loadGame(chessGame, gameId);
        outputView.printBoard(chessGameService.getPieceResponses(chessGame.getBoard()));
        return CommandType.LOAD;
    }

    private CommandType saveGame(final ChessGame chessGame, final Command command) {
        Long gameId = chessGameService.saveGame(chessGame);
        outputView.printSaveMessage(gameId);
        return CommandType.SAVE;
    }

    private CommandType move(final ChessGame chessGame, final Command command) {
        Square source = Square.from(command.getSource());
        Square target = Square.from(command.getTarget());
        chessGame.move(source, target);
        outputView.printBoard(chessGameService.getPieceResponses(chessGame.getBoard()));
        if (chessGame.end()) {
            outputView.printGameResult(responseGameResult(chessGame));
            return CommandType.END;
        }
        return CommandType.MOVE;
    }

    private CommandType status(final ChessGame chessGame, final Command command) {
        outputView.printGameResult(responseGameResult(chessGame));
        return CommandType.STATUS;
    }

    private GameResultResponse responseGameResult(final ChessGame chessGame) {
        Score score = chessGame.createScore();
        double whiteScore = score.calculateTotalScoreBy(Color.WHITE);
        double blackScore = score.calculateTotalScoreBy(Color.BLACK);
        Winner winner = Winner.of(whiteScore, blackScore);
        return new GameResultResponse(whiteScore, blackScore, winner);
    }
}
