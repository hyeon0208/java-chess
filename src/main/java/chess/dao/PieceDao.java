package chess.dao;

import chess.dto.PieceResponse;
import java.sql.Connection;

public interface PieceDao {

    Long save(PieceResponse pieceResponse, Long gameId, Connection connection);
}
