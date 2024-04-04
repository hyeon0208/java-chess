package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.service.ChessGameService;

public interface CommandAction {

    void execute(ChessGameService chessGameService, ChessGame chessGame, Command command);

    boolean isEnd();
}
