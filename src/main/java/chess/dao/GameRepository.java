package chess.dao;

import chess.domain.game.ChessGame;
import java.util.List;
import java.util.Optional;

public interface GameRepository {

    Long save(ChessGame chessGame);

    Optional<ChessGame> findById(Long roomId);

    List<Long> findIdAll();
}
