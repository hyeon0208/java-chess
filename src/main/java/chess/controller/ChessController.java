package chess.controller;

import chess.domain.command.CommandAction;
import chess.domain.game.ChessGame;
import chess.domain.command.CommandType;
import chess.domain.game.GameResult;
import chess.domain.piece.Color;
import chess.domain.square.Square;
import chess.view.InputView;
import chess.view.OutputView;
import chess.domain.command.Command;
import chess.view.dto.PieceResponses;
import chess.view.dto.ScoreResponse;
import java.util.EnumMap;
import java.util.Map;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;
    private final Map<CommandType, CommandAction> commandInvoker = new EnumMap<>(CommandType.class);

    public ChessController(final InputView inputView, final OutputView outputView, final ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
        init();
    }

    private void init() {
        commandInvoker.put(CommandType.START, this::start);
        commandInvoker.put(CommandType.MOVE, this::move);
        commandInvoker.put(CommandType.STATUS, this::status);
        commandInvoker.put(CommandType.END, (chessGame, command) -> CommandType.END);
    }

    public void run() {
        outputView.printStartMessage();
        CommandType commandType = CommandType.START;
        while (commandType != CommandType.END) {
            commandType = progress();
        }
    }

    private CommandType progress() {
        try {
            Command command = new Command(inputView.readGameCommand());
            CommandType commandType = command.getCommand();
            CommandAction commandAction = commandInvoker.get(commandType);
            return commandAction.execute(chessGame, command);
        } catch (IllegalArgumentException error) {
            outputView.printErrorMessage(error.getMessage());
            return progress();
        }
    }

    private CommandType start(final ChessGame chessGame, final Command command) {
        chessGame.start();
        outputView.printBoard(PieceResponses.toDto(chessGame.getBoard()));
        return CommandType.START;
    }

    private CommandType move(final ChessGame chessGame, final Command command) {
        Square source = Square.from(command.getSource());
        Square target = Square.from(command.getTarget());
        chessGame.move(source, target);
        outputView.printBoard(PieceResponses.toDto(chessGame.getBoard()));
        if (chessGame.end()) {
            outputView.printScores(responseScore(chessGame));
            return CommandType.END;
        }
        return CommandType.MOVE;
    }

    private CommandType status(final ChessGame chessGame, final Command command) {
        outputView.printScores(responseScore(chessGame));
        return CommandType.STATUS;
    }

    private ScoreResponse responseScore(final ChessGame chessGame) {
        GameResult gameResult = chessGame.createGameResult();
        double whiteScore = gameResult.calculateTotalScoreBy(Color.WHITE);
        double blackScore = gameResult.calculateTotalScoreBy(Color.BLACK);
        return new ScoreResponse(whiteScore, blackScore);
    }
}
