package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.square.Square;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임 점수")
class ScoreTest {

    @DisplayName("남아있는 기물의 점수를 출력한다.")
    @Test
    void calculateTotalScoreBy() {
        //given
        Map<Square, Piece> pieces = new HashMap<>();
        pieces.put(Square.from("a2"), new Pawn(Color.WHITE));
        pieces.put(Square.from("a3"), new Pawn(Color.WHITE));
        pieces.put(Square.from("b3"), new Pawn(Color.WHITE));
        pieces.put(Square.from("f1"), new King(Color.WHITE));
        pieces.put(Square.from("e1"), new Queen(Color.WHITE));

        pieces.put(Square.from("a7"), new Pawn(Color.BLACK));
        pieces.put(Square.from("b7"), new Pawn(Color.BLACK));
        pieces.put(Square.from("a1"), new Rook(Color.BLACK));
        pieces.put(Square.from("b1"), new Knight(Color.BLACK));
        pieces.put(Square.from("c1"), new Bishop(Color.BLACK));

        Score score = new Score(pieces);

        //when
        double whiteScore = score.calculateTotalScoreBy(Color.WHITE);
        double blackScore = score.calculateTotalScoreBy(Color.BLACK);

        //then
        assertAll(
                () -> assertThat(whiteScore).isEqualTo(11),
                () -> assertThat(blackScore).isEqualTo(12.5)
        );
    }
}
