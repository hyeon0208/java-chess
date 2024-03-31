package chess.domain.game;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Winner {
    WHITE((whiteTotalScore, blackTotalScore) -> whiteTotalScore > blackTotalScore),
    BLACK((whiteTotalScore, blackTotalScore) -> whiteTotalScore < blackTotalScore),
    TIE(Double::equals),
    ;

    private final BiPredicate<Double, Double> predicate;

    Winner(final BiPredicate<Double, Double> predicate) {
        this.predicate = predicate;
    }

    public static Winner of(final double whiteTotalScore, final double blackTotalScore) {
        return Arrays.stream(Winner.values())
                .filter(winner -> winner.predicate.test(whiteTotalScore, blackTotalScore))
                .findAny()
                .orElse(TIE);
    }

    public boolean whiteWin() {
        return this.equals(Winner.WHITE);
    }

    public boolean blackWin() {
        return this.equals(Winner.BLACK);
    }

    public boolean tie() {
        return this.equals(Winner.TIE);
    }
}
