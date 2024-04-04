package chess.domain.command;

import chess.controller.ChessController;
import chess.domain.game.ChessGame;

public interface CommandAction {

    void execute(ChessController chessController, ChessGame chessGame, Command command);

    default CommandAction change(CommandType commandType) {
        return commandType.action();
    }

    boolean isEnd();
}
