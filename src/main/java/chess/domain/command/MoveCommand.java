package chess.domain.command;

import chess.domain.game.ChessGame;
import chess.domain.square.Square;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class MoveCommand implements CommandAction {
    private final OutputView outputView;

    public MoveCommand(final OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public void execute(final ChessGameService chessGameService, final ChessGame chessGame, final Command command) {
        Square source = Square.from(command.getSource());
        Square target = Square.from(command.getTarget());
        chessGame.move(source, target);
        outputView.printBoard(chessGameService.getPieceResponses(chessGame.getBoard()));
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
