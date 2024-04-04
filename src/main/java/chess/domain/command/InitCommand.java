package chess.domain.command;

import chess.controller.ChessController;
import chess.domain.game.ChessGame;

public class InitCommand implements CommandAction {

    @Override
    public void execute(final ChessController chessController, final ChessGame chessGame, final Command command) {
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
