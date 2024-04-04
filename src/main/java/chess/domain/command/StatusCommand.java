package chess.domain.command;

import chess.controller.ChessController;
import chess.domain.game.ChessGame;

public class StatusCommand implements CommandAction {

    @Override
    public void execute(final ChessController chessController, final ChessGame chessGame, final Command command) {
        chessController.printGameResult(chessGame);
        chessController.progress(chessGame);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
