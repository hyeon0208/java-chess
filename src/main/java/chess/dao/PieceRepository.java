package chess.dao;

import chess.view.dto.PieceResponse;

public interface PieceRepository {

    Long save(PieceResponse pieceResponse, Long gameId);
}
