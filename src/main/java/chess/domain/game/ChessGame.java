package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.square.Square;
import chess.view.dto.ChessGameResponse;

public class ChessGame {
    private Board board;
    private Turn turn;

    public ChessGame(final Board board) {
        this.board = board;
        this.turn = Turn.WHITE;
    }

    public void start() {
        board = BoardFactory.createBoard();
    }

    public void load(final ChessGameResponse chessGameResponse) {
        board = chessGameResponse.board();
        turn = chessGameResponse.turn();
    }

    public boolean end() {
        return board.kingDead();
    }

    public void move(final Square source, final Square target) {
        board.move(source, target, turn);
        turn = turn.next();
    }

    public Score createScore() {
        return new Score(board.getPieces());
    }

    public Turn getTurn() {
        return turn;
    }

    public Board getBoard() {
        return board;
    }
}
