package chess.dao;

import chess.domain.game.ChessGame;
import chess.dto.ChessGameResponse;
import java.sql.Connection;
import java.util.List;

public interface GameDao {

    Long save(ChessGame chessGame, Connection connection);

    ChessGameResponse findById(Long gameId, Connection connection);

    List<Long> findIdAll(Connection connection);
}
