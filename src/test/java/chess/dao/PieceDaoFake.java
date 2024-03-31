package chess.dao;

import chess.view.dto.PieceResponse;
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
    public Long save(final PieceResponse pieceResponse, final Long gameId) {
        pieceId++;
        pieceRepository.put(gameId, pieceResponse);
        return pieceId;
    }
}
