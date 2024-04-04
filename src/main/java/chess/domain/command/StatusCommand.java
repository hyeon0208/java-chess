package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class StatusCommand implements CommandAction {
    private final OutputView outputView;

    public StatusCommand(final OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public void execute(final ChessGameService chessGameService, final ChessGame chessGame, final Command command) {
        outputView.printGameResult(chessGameService.getGameResultResponse(chessGame));
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
