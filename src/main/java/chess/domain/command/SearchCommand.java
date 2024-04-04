package chess.domain.command;

import chess.controller.ChessController;
import chess.domain.game.ChessGame;

public class SearchCommand implements CommandAction {

    @Override
    public void execute(final ChessController chessController, final ChessGame chessGame, final Command command) {
        chessController.search();
        chessController.progress(chessGame);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
