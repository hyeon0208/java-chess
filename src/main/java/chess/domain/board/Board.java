package chess.domain.board;

import chess.domain.Movement;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.square.Square;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
    private static final String NO_PIECE_EXCEPTION = "해당 위치에 기물이 없습니다.";
    private static final String INVALID_TURN = "헤당 색의 턴이 아닙니다.";
    private static final String INVALID_PIECE_MOVEMENT = "해당 기물은 위치로 이동할 수 없습니다.";

    private final Map<Square, Piece> pieces;

    public Board(final Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final Square source, final Square target, final Color turn) {
        Piece sourcePiece = pieces.get(source);
        validateEmptyPiece(sourcePiece);
        validateTurn(sourcePiece, turn);

        Movement movement = new Movement(source, target);
        validatePieceMovement(sourcePiece, pieces.get(target), movement);

        pieces.remove(source);
        pieces.put(target, sourcePiece);
    }

    private void validateEmptyPiece(final Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException(NO_PIECE_EXCEPTION);
        }
    }

    private void validateTurn(final Piece piece, final Color turn) {
        if (!piece.isSameColor(turn)) {
            throw new IllegalArgumentException(INVALID_TURN);
        }
    }

    private void validatePieceMovement(final Piece piece, final Piece targetPiece, final Movement movement) {
        piece.validateArrival(movement, targetPiece);
        validateMoveRoute(movement);
    }

    private void validateMoveRoute(final Movement movement) {
        Set<Square> squares = movement.findRoute();
        for (Square square : squares) {
            checkPieceExist(square);
        }
    }

    private void checkPieceExist(final Square square) {
        if (pieces.containsKey(square)) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVEMENT);
        }
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
                .filter(entry -> entry.getValue().type() == Type.PAWN && entry.getValue().color() == color)
                .collect(Collectors.groupingBy(entry -> entry.getKey().getFileIndex(), Collectors.counting()))
                .values().stream()
                .filter(count -> count > 1)
                .mapToDouble(count -> count * 0.5)
                .sum();
    }

    public Map<Square, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
