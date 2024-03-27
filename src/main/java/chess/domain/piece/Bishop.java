package chess.domain.piece;

import static chess.domain.Direction.DOWN_LEFT;
import static chess.domain.Direction.DOWN_RIGHT;
import static chess.domain.Direction.UP_LEFT;
import static chess.domain.Direction.UP_RIGHT;

import chess.domain.Direction;
import chess.domain.Movement;
import java.util.List;

public class Bishop extends Piece {
    private static final List<Direction> BISHOP_DIRECTION = List.of(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);
    private static final double SCORE = 3;

    public Bishop(final Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        return BISHOP_DIRECTION.contains(movement.direction());
    }

    @Override
    public double score() {
        return SCORE;
    }
}
