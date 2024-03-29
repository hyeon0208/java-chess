package chess.domain.game;

public enum Turn {
    WHITE,
    BLACK,
    ;

    public Turn next() {
    if (this.equals(Turn.WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
