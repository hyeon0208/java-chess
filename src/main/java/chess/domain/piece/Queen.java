package chess.domain.piece;

import static chess.domain.Direction.DOWN;
import static chess.domain.Direction.DOWN_LEFT;
import static chess.domain.Direction.DOWN_RIGHT;
import static chess.domain.Direction.LEFT;
import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;
import static chess.domain.Direction.UP_LEFT;
import static chess.domain.Direction.UP_RIGHT;

import chess.domain.Direction;
import chess.domain.Movement;
import java.util.List;

public class Queen extends Piece {
    private static final List<Direction> QUEEN_DIRECTION;
    private static final double SCORE = 9;

    static {
        QUEEN_DIRECTION = List.of(
                UP, DOWN, LEFT, RIGHT,
                UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
        );
    }

    public Queen(final Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        return QUEEN_DIRECTION.contains(movement.direction());
    }

    @Override
    public double score() {
        return SCORE;
    }
}
