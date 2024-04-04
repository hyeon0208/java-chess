package chess.domain.command;

import chess.controller.ChessController;
import chess.domain.game.ChessGame;

public class StartCommand implements CommandAction {

    @Override
    public void execute(final ChessController chessController, final ChessGame chessGame, final Command command) {
        chessGame.start();
        chessController.printBoard(chessGame);
        chessController.progress(chessGame);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
