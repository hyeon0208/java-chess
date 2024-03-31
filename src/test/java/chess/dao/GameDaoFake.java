package chess.dao;

import chess.domain.game.ChessGame;
import chess.view.dto.ChessGameResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameDaoFake implements GameRepository {
    private final Map<Long, ChessGame> gameRepository;
    private Long gameId;

    public GameDaoFake() {
        this.gameRepository = new HashMap<>();
        this.gameId = 0L;
    }

    @Override
    public Long save(final ChessGame chessGame) {
        gameId += 1;
        gameRepository.put(gameId, chessGame);
        return gameId;
    }

    @Override
    public ChessGameResponse findById(final Long gameId) {
        ChessGame chessGame = gameRepository.get(gameId);
        return new ChessGameResponse(chessGame.getBoard(), chessGame.getTurn());
    }

    @Override
    public List<Long> findIdAll() {
        List<Long> gameIds = new ArrayList<>();
        gameIds.addAll(gameRepository.keySet());
        return gameIds;
    }
}
