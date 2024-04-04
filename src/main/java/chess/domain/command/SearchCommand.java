package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;
import java.util.List;

public class SearchCommand implements CommandAction {
    private final OutputView outputView;

    public SearchCommand(final OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public void execute(final ChessGameService chessGameService, final ChessGame chessGame, final Command command) {
        List<Long> gameIds = chessGameService.findAllGame();
        outputView.printGameIds(gameIds);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
