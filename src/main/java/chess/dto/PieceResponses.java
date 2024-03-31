package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import java.util.List;
import java.util.Map;

public record PieceResponses(List<PieceResponse> pieces) {

    public static PieceResponses toDto(final Board board) {
        Map<Square, Piece> pieces = board.getPieces();
        List<PieceResponse> pieceResponses = pieces.entrySet().stream()
                .map(entry -> new PieceResponse(
                        entry.getKey().getFileIndex(),
                        entry.getKey().getRankIndex(),
                        entry.getValue().type(),
                        entry.getValue().color())
                ).toList();

        return new PieceResponses(pieceResponses);
    }
}
