package chess.dto;

import chess.domain.board.Board;
import chess.domain.game.Turn;

public record ChessGameResponse(Board board, Turn turn) {
}
