package chess.domain.command;

import chess.controller.ChessController;
import chess.domain.game.ChessGame;

public class EndCommand implements CommandAction {

    @Override
    public void execute(final ChessController chessController, final ChessGame chessGame, final Command command) {
    }

    @Override
    public CommandAction change(final CommandType commandType) {
        return commandType.action();
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
