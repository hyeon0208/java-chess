package chess;

import chess.controller.ChessController;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChessGameService chessGameService = new ChessGameService(new GameDao(), new PieceDao());
        ChessController chessController = new ChessController(inputView, outputView, chessGameService);
        chessController.run();
    }
}
