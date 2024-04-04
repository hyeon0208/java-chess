package chess.domain.command;

import chess.controller.ChessController;
import chess.domain.game.ChessGame;
import chess.domain.square.Square;

public class MoveCommand implements CommandAction {

    @Override
    public void execute(final ChessController chessController, final ChessGame chessGame, final Command command) {
        Square source = Square.from(command.getSource());
        Square target = Square.from(command.getTarget());
        chessGame.move(source, target);
        chessController.printBoard(chessGame);
        chessController.progress(chessGame);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
