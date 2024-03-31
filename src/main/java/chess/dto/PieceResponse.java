package chess.dto;

import chess.domain.piece.Color;
import chess.domain.piece.Type;

public record PieceResponse(int fileIndex, int rankIndex, Type type, Color color) {
}
