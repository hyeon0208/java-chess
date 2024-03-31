package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dao.GameDaoFake;
import chess.dao.GameRepository;
import chess.dao.PieceDaoFake;
import chess.dao.PieceRepository;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Type;
import chess.domain.square.Square;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ChessGameServiceTest {
    private static ChessGameService chessGameService;

    @BeforeAll
    static void init() {
        GameRepository gameRepository = new GameDaoFake();
        PieceRepository pieceRepository = new PieceDaoFake();
        chessGameService = new ChessGameService(gameRepository, pieceRepository);
    }

    @Test
    void saveGame() {
        // given
        Map<Square, Piece> fakeBoard = new HashMap<>();
        fakeBoard.put(Square.from("a2"), new Pawn(Color.WHITE));
        fakeBoard.put(Square.from("f1"), new King(Color.WHITE));
        fakeBoard.put(Square.from("e1"), new Queen(Color.WHITE));

        fakeBoard.put(Square.from("b8"), new Knight(Color.BLACK));
        fakeBoard.put(Square.from("c8"), new Bishop(Color.BLACK));
        fakeBoard.put(Square.from("f8"), new King(Color.BLACK));
        ChessGame chessGame = new ChessGame(new Board(fakeBoard));
        /*
        .NB..K..
        ........
        ........
        ........
        ........
        ........
        p.......
        ....qk..
         */

        Long expectedId = 1L;

        // when
        Long gameId = chessGameService.saveGame(chessGame);

        // then
        assertThat(gameId).isEqualTo(expectedId);
    }

    @Test
    void loadGame() {
        // given
        Board emptyBoard = new Board();
        ChessGame chessGame = new ChessGame(emptyBoard);
        chessGameService.loadGame(chessGame, 1L);

        Square pawnSquare = Square.from("a2");
        Piece pawn = chessGame.getBoard().getPieces().get(pawnSquare);
        Turn turn = chessGame.getTurn();

        // when && then
        assertAll(
                () -> assertThat(pawn.type()).isEqualTo(Type.PAWN),
                () -> assertThat(pawn.color()).isEqualTo(Color.WHITE),
                () -> assertThat(turn).isEqualTo(Turn.WHITE)
        );
    }

    @Test
    void findAllGame() {
        // given && when
        List<Long> gameIds = chessGameService.findAllGame();

        // then
        assertThat(gameIds).containsExactly(1L);
    }
}
