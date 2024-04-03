package chess.domain.command;

import chess.controller.ChessController;
import chess.domain.game.ChessGame;

public class LoadCommand implements CommandAction {

    @Override
    public void execute(final ChessController chessController, final ChessGame chessGame, final Command command) {
        Long gameId = command.getLoadId();
        chessController.load(chessGame, gameId);
        chessController.printBoard(chessGame);
        chessController.progress(chessGame);
    }

    @Override
    public CommandAction change(final CommandType commandType) {
        return commandType.action();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
