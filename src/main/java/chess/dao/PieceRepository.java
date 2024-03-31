package chess.dao;

import chess.dto.PieceResponse;

public interface PieceRepository {

    Long save(PieceResponse pieceResponse, Long gameId);
}
