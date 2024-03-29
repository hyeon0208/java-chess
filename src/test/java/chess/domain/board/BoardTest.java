package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.game.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.square.Square;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임판")
class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createBoard();
    }

    @DisplayName("초기화에 성공한다.")
    @Test
    void initialize() {
        //given
        int expectedSize = 32;

        //when & then
        assertThat(board.getPieces().size()).isEqualTo(expectedSize);
    }

    @DisplayName("기물이 없는 위치를 이동시킬 경우 예외가 발생한다")
    @Test
    void noPiece() {
        //given
        Square from = Square.from("c4");
        Square to = Square.from("c5");

        //when & then
        assertThatThrownBy(() -> board.move(from, to, Turn.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("해당 턴이 아닌 경우 예외가 발생한다")
    @Test
    void invalidTurn() {
        //given
        Square from = Square.from("c2");
        Square to = Square.from("c3");

        //when & then
        assertThatThrownBy(() -> board.move(from, to, Turn.BLACK))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동이 불가능할 경우 예외가 발생한다")
    @Test
    void invalidMovable() {
        //given
        Square from = Square.from("c2");
        Square to = Square.from("c5");

        //when & then
        assertThatThrownBy(() -> board.move(from, to, Turn.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 경로에 다른 기물이 있으면 예외가 발생한다")
    @Test
    void checkRoute() {
        //given
        Square from = Square.from("c1");
        Square to = Square.from("f4");

        //when & then
        assertThatThrownBy(() -> board.move(from, to, Turn.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("나이트는 이동 경로에 다른 기물이 있어도 예외가 발생하지 않는다")
    @Test
    void checkKnightRoute() {
        //given
        Square from = Square.from("b1");
        Square to = Square.from("c3");

        //when & then
        assertThatCode(() -> board.move(from, to, Turn.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("목적지에 있는 기물이 현재 기물과 같은 색이라면 예외가 발생한다")
    @Test
    void invalidDestination() {
        //given
        Square from = Square.from("a1");
        Square to = Square.from("a2");

        //when & then
        assertThatThrownBy(() -> board.move(from, to, Turn.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("목적지로 이동한다")
    @Test
    void move() {
        //given
        Square from = Square.from("a2");
        Square to = Square.from("a3");

        //when
        board.move(from, to, Turn.WHITE);
        Map<Square, Piece> pieces = board.getPieces();

        //then
        assertAll(
                () -> assertThat(pieces.get(to).type()).isEqualTo(Type.PAWN),
                () -> assertThat(pieces.get(to).color()).isEqualTo(Color.WHITE)
        );
    }
}
