package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("턴")
class TurnTest {

    @DisplayName("현재와 타른 턴을 반환한다.")
    @Test
    void next() {
        // given
        Turn start = Turn.WHITE;

        // when
        Turn next = start.next();

        // then
        assertThat(next).isEqualTo(Turn.BLACK);
    }
}
