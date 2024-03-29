package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.square.Square;

public class ChessGame {
    private Board board;
    private Turn turn;

    public ChessGame() {
        this.board = new Board();
        this.turn = Turn.WHITE;
    }

    public void start() {
        board = BoardFactory.createBoard();
        turn = Turn.WHITE;
    }

    public boolean end() {
        return board.kingDead();
    }

    public void move(final Square source, final Square target) {
        board.move(source, target, turn);
        turn = turn.next();
    }


    public GameResult createGameResult() {
        return new GameResult(board.getPieces());
    }

    public Board getBoard() {
        return board;
    }
}
