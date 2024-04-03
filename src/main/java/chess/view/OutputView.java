package chess.view;

import chess.domain.game.Winner;
import chess.domain.piece.Color;
import chess.domain.piece.Type;
import chess.dto.GameResultResponse;
import chess.dto.PieceResponse;
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
                > 점수 결과 : status
                > 게임 방 검색 : search
                > 게임 불러오기 : load 아이디 - 예. load 5
                > 게임 저장 : save
                """);
    }

    public void printBoard(final List<PieceResponse> pieceResponses) {
        char[][] board = setUpBoard();
        addPieceToBoard(pieceResponses, board);
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

    public void printSaveMessage(final Long gameId) {
        System.out.printf("게임 ID : %d로 저장되었습니다.%n", gameId);
    }

    public void printGameIds(final List<Long> gameIds) {
        StringBuilder builder = new StringBuilder();
        for (Long gameId : gameIds) {
            String message = String.format("게임 ID : %d %n", gameId);
            builder.append(message);
        }
        System.out.println(builder);
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
