package chess.domain.command;

import chess.controller.ChessController;
import chess.domain.game.ChessGame;

public class SaveCommand implements CommandAction {

    @Override
    public void execute(final ChessController chessController, final ChessGame chessGame, final Command command) {
        chessController.save(chessGame);
        chessController.progress(chessGame);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
