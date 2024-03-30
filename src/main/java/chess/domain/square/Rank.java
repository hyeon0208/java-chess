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
                .filter(rank -> (rank.ordinal() + 1) == Integer.parseInt(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK));
    }


    public static Rank from(final int index) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.ordinal() == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK));
    }
}
