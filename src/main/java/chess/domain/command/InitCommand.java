package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;

public class InitCommand implements CommandAction {

    @Override
    public void execute(final ChessGameService chessGameService, final ChessGame chessGame, final Command command) {
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
