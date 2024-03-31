package chess.view;

import chess.domain.game.Winner;
import chess.domain.piece.Color;
import chess.domain.piece.Type;
import chess.view.dto.PieceResponse;
import chess.view.dto.PieceResponses;
import chess.view.dto.GameResultResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final char EMPTY_SQUARE = '.';
    private static final int BOARD_SIZE = 8;

    public void printErrorMessage(final String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public void printStartMessage() {
        System.out.print("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3
                """);
    }

    public void printBoard(final PieceResponses pieceResponses) {
        char[][] board = setUpBoard();
        addPieceToBoard(pieceResponses.pieces(), board);
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

    public void printGameResult(final GameResultResponse gameResultResponse) {
        printScores(gameResultResponse.whiteScore(), gameResultResponse.blackScore());
        printWinner(gameResultResponse.winner());
    }

    private void printScores(final double whiteScore, final double blackScore) {
        System.out.printf("하얀색 : %.1f점 %n", whiteScore);
        System.out.printf("검은색 : %.1f점 %n", blackScore);
    }

    private void printWinner(final Winner winner) {
        if (winner.tie()) {
            System.out.println("무승부");
            return;
        }
        if (winner.whiteWin()) {
            System.out.printf("우승자 : %s %n", winner);
            return;
        }
        if (winner.blackWin()) {
            System.out.printf("우승자 : %s %n", winner);
        }
    }
}
