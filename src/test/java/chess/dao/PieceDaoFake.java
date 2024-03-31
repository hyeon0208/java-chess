package chess.dao;

import chess.dto.PieceResponse;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class PieceDaoFake implements PieceRepository {
    private final Map<Long, PieceResponse> pieceRepository;
    private Long pieceId;

    public PieceDaoFake() {
        this.pieceRepository = new HashMap<>();
        this.pieceId = 0L;
    }

    @Override
    public Long save(final PieceResponse pieceResponse, final Long gameId, final Connection connection) {
        pieceId++;
        pieceRepository.put(gameId, pieceResponse);
        return pieceId;
    }
}
