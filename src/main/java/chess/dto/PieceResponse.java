package chess.dto;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.square.Square;

public record PieceResponse(int fileIndex, int rankIndex, Type type, Color color) {

    public static PieceResponse of(final Square square, final Piece piece) {
        return new PieceResponse(
                square.getFileIndex(),
                square.getRankIndex(),
                piece.type(),
                piece.color()
        );
    }
}
