package chess.dao;

import chess.domain.game.ChessGame;
import chess.dto.ChessGameResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameDaoFake implements GameDao {
    private final Map<Long, ChessGame> games;
    private Long gameId;

    public GameDaoFake() {
        this.games = new HashMap<>();
        this.gameId = 0L;
    }

    @Override
    public Long save(final ChessGame chessGame, final Connection connection) {
        gameId += 1;
        games.put(gameId, chessGame);
        return gameId;
    }

    @Override
    public ChessGameResponse findById(final Long gameId, final Connection connection) {
        ChessGame chessGame = games.get(gameId);
        return new ChessGameResponse(chessGame.getBoard(), chessGame.getTurn());
    }

    @Override
    public List<Long> findIdAll(final Connection connection) {
        List<Long> gameIds = new ArrayList<>();
        gameIds.addAll(games.keySet());
        return gameIds;
    }
}
