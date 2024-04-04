package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class StartCommand implements CommandAction {
    private final OutputView outputView;

    public StartCommand(final OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public void execute(final ChessGameService chessGameService, final ChessGame chessGame, final Command command) {
        chessGame.start();
        outputView.printBoard(chessGameService.getPieceResponses(chessGame.getBoard()));
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
