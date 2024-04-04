package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class SaveCommand implements CommandAction {
    private final OutputView outputView;

    public SaveCommand(final OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public void execute(final ChessGameService chessGameService, final ChessGame chessGame, final Command command) {
        Long gameId = chessGameService.saveGame(chessGame);
        outputView.printSaveMessage(gameId);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
