package chess;

import chess.controller.ChessController;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChessGame chessGame = new ChessGame();
        ChessController chessController = new ChessController(inputView, outputView, chessGame);
        chessController.run();
    }
}
