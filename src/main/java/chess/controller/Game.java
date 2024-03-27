package chess.controller;

import chess.domain.Command;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import chess.dto.PieceResponse;
import chess.dto.ScoreResponse;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class Game {
    private final InputView inputView;
    private final OutputView outputView;

    public Game(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Board board = BoardFactory.createBoard();
        String command = inputView.readStartCommand();

        if (command.equals(Command.START_COMMAND)) {
            progress(board);
        }
    }

    private void progress(final Board board) {
        Color startColor = Color.WHITE;
        outputView.printBoard(createBoardStatus(board.getPieces()));
        Command command = new Command(inputView.readMovement());
        while (!command.isEnd()) {
            moveByCommand(board, startColor, command);
            outputView.printBoard(createBoardStatus(board.getPieces()));
            showScore(board);
            startColor = startColor.opposite();
            command = new Command(inputView.readMovement());
        }
    }

    private void showScore(final Board board) {
        boolean scoreStatus = inputView.readScoreStatus();
        if (scoreStatus) {
            double whiteScore = board.calculateTotalScoreBy(Color.WHITE);
            double blackScore = board.calculateTotalScoreBy(Color.BLACK);
            ScoreResponse scoreResponse = new ScoreResponse(whiteScore, blackScore);
            outputView.printScores(scoreResponse);
        }
        outputView.printMoveCommandMessage();
    }

    private void moveByCommand(final Board board, final Color startColor, final Command command) {
        Square source = Square.from(command.sourceSquare());
        Square target = Square.from(command.targetSquare());
        board.move(source, target, startColor);
    }

    private List<PieceResponse> createBoardStatus(final Map<Square, Piece> pieces) {
        return pieces.entrySet().stream()
                .map(entry -> new PieceResponse(
                        entry.getKey().getFileIndex(),
                        entry.getKey().getRankIndex(),
                        entry.getValue().type(),
                        entry.getValue().color())
                )
                .toList();
    }
}
