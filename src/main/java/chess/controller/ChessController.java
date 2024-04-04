package chess.controller;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.CommandAction;
import chess.domain.command.CommandType;
import chess.domain.command.EndCommand;
import chess.domain.command.InitCommand;
import chess.domain.command.LoadCommand;
import chess.domain.command.MoveCommand;
import chess.domain.command.SaveCommand;
import chess.domain.command.SearchCommand;
import chess.domain.command.StartCommand;
import chess.domain.command.StatusCommand;
import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.Map;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;
    private final Map<CommandType, CommandAction> commandInvoker = new EnumMap<>(CommandType.class);
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
        init();
    }

    private void init() {
        commandInvoker.put(CommandType.START, new StartCommand(outputView));
        commandInvoker.put(CommandType.SAVE, new SaveCommand(outputView));
        commandInvoker.put(CommandType.STATUS, new StatusCommand(outputView));
        commandInvoker.put(CommandType.MOVE, new MoveCommand(outputView));
        commandInvoker.put(CommandType.SEARCH, new SearchCommand(outputView));
        commandInvoker.put(CommandType.LOAD, new LoadCommand(outputView));
        commandInvoker.put(CommandType.END, new EndCommand());
    }

    public void run() {
        outputView.printStartMessage();
        ChessGame chessGame = new ChessGame(new Board());
        retry(() -> progress(chessGame));
    }

    public void progress(final ChessGame chessGame) {
        do {
            Command command = new Command(inputView.readGameCommand());
            commandAction = commandInvoker.get(command.type());
            commandAction.execute(chessGameService, chessGame, command);
        } while (!commandAction.isEnd() && !chessGame.end());
    }

    private void retry(final Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            retry(runnable);
        }
    }
}
