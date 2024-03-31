package chess.domain.piece;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class PieceMapper {
    private final Map<Type, Function<Color, Piece>> pieces = new EnumMap<>(Type.class);

    public PieceMapper() {
        pieces.put(Type.PAWN, Pawn::new);
        pieces.put(Type.KNIGHT, Knight::new);
        pieces.put(Type.KING, King::new);
        pieces.put(Type.QUEEN, Queen::new);
        pieces.put(Type.ROOK, Rook::new);
        pieces.put(Type.BISHOP, Bishop::new);
    }

    public Piece map(Type type, Color color) {
        Function<Color, Piece> function = pieces.get(type);
        return function.apply(color);
    }
}

