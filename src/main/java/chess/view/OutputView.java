package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.Type;
import chess.dto.PieceResponse;
import chess.dto.ScoreResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final char EMPTY_SQUARE = '.';
    private static final int BOARD_SIZE = 8;

    public void printBoard(final List<PieceResponse> pieces) {
        char[][] board = setUpBoard();
        addPieceToBoard(pieces, board);
        printBoard(board);
        System.out.println();
    }

    private char[][] setUpBoard() {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        for (char[] line : board) {
            Arrays.fill(line, EMPTY_SQUARE);
        }
        return board;
    }

    private void addPieceToBoard(final List<PieceResponse> pieces, final char[][] board) {
        for (PieceResponse response : pieces) {
            int y = response.rankIndex();
            int x = response.fileIndex();
            board[y][x] = getPieceDisplay(response.type(), response.color());
        }
    }

    private char getPieceDisplay(final Type type, final Color color) {
        return PieceView.findByType(type).changeToView(color);
    }

    private void printBoard(final char[][] board) {
        IntStream.range(0, board.length)
                .mapToObj(lineCount -> board[board.length - 1 - lineCount])
                .forEach(System.out::println);
    }

    public void printScores(final ScoreResponse scoreResponse) {
        System.out.println(scoreResponse.whiteScore() + "점");
        System.out.println(scoreResponse.blackScore() + "점");
    }

    public void printMoveCommandMessage() {
        System.out.println("이동 명령어를 입력해주세요");
    }
}
