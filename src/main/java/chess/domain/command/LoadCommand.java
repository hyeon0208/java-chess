package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class LoadCommand implements CommandAction {
    private final OutputView outputView;

    public LoadCommand(final OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public void execute(final ChessGameService chessGameService, final ChessGame chessGame, final Command command) {
        Long gameId = command.getLoadId();
        chessGameService.loadGame(chessGame, gameId);
        outputView.printBoard(chessGameService.getPieceResponses(chessGame.getBoard()));
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
