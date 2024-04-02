package chess.dao;

import chess.dto.PieceResponse;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class PieceDaoFake implements PieceDao {
    private final Map<Long, PieceResponse> pieces;
    private Long pieceId;

    public PieceDaoFake() {
        this.pieces = new HashMap<>();
        this.pieceId = 0L;
    }

    @Override
    public Long save(final PieceResponse pieceResponse, final Long gameId, final Connection connection) {
        pieceId++;
        pieces.put(gameId, pieceResponse);
        return pieceId;
    }
}
