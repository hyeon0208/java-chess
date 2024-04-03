package chess.domain.command;

import chess.controller.ChessController;
import chess.domain.game.ChessGame;

public interface CommandAction {

    void execute(ChessController chessController, ChessGame chessGame, Command command);

    CommandAction change(CommandType commandType);

    boolean isEnd();
}
