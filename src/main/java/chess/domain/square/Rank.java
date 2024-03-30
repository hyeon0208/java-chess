package chess.domain.square;

import java.util.Arrays;

public enum Rank {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    ;

    public static final String INVALID_RANK = "유효하지 않는 랭크입니다.";

    public static Rank from(final String input) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.ordinal() == Integer.parseInt(input) - 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK));
    }

    public static Rank from(final int index) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.ordinal() == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK));
    }

    public static Rank of(final int sourceIndex, final int nextIndex) {
        return Arrays.stream(values())
                .filter(rank -> rank.ordinal() == sourceIndex + nextIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK));
    }
}
