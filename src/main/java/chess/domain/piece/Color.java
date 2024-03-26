package chess.domain.piece;

public enum Color {
    WHITE,
    BLACK,
    ;

    public boolean isSame(final Color color) {
        return this == color;
    }

    public Color opposite() {
        if (this.equals(Color.WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
