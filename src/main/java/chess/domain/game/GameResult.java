package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.square.Square;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    private static final double PAWN_PENALTY = 0.5;

    private final Map<Square, Piece> pieces;

    public GameResult(final Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public double calculateTotalScoreBy(final Color color) {
        double totalScore = pieces.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();

        return totalScore - calculateTotalPenalty(color);
    }

    private double calculateTotalPenalty(final Color color) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().type().equals(Type.PAWN) && entry.getValue().isSameColor(color))
                .collect(Collectors.groupingBy(entry -> entry.getKey().getFileIndex(), Collectors.counting()))
                .values().stream()
                .filter(count -> count > 1)
                .mapToDouble(count -> count * PAWN_PENALTY)
                .sum();
    }
}
