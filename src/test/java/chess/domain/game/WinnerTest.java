package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("우승자")
class WinnerTest {

    @DisplayName("점수가 더 높은 진영이 우승한다.")
    @Test
    void winner() {
        // given
        Winner winner = Winner.of(30, 29.5);

        // when
        boolean whiteWin = winner.whiteWin();
        boolean blackWin = winner.blackWin();

        // then
        assertThat(whiteWin).isTrue();
        assertThat(blackWin).isFalse();
    }

    @DisplayName("점수가 같으면 무승부한다.")
    @Test
    void tie() {
        // given
        Winner winner = Winner.of(30.5, 30.5);

        // when
        boolean tie = winner.tie();

        // then
        assertThat(tie).isTrue();
    }
}
