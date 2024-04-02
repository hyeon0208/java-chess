package chess;

import chess.controller.ChessController;
import chess.dao.GameDaoImpl;
import chess.dao.PieceDaoImpl;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChessGameService chessGameService = new ChessGameService(new GameDaoImpl(), new PieceDaoImpl());
        ChessController chessController = new ChessController(inputView, outputView, chessGameService);
        chessController.run();
    }
}
