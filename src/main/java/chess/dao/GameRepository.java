package chess.dao;

import chess.domain.game.ChessGame;
import chess.dto.ChessGameResponse;
import java.util.List;

public interface GameRepository {

    Long save(ChessGame chessGame);

    ChessGameResponse findById(Long gameId);

    List<Long> findIdAll();
}
