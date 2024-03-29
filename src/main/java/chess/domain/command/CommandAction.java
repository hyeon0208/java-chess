package chess.domain.command;

import chess.domain.game.ChessGame;

@FunctionalInterface
public interface CommandAction {

    CommandType execute(ChessGame chessGame, Command command);
}
