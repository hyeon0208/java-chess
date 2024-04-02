package chess.dao;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMapper;
import chess.domain.piece.Type;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.dto.ChessGameResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameDaoImpl implements GameDao {

    @Override
    public Long save(final ChessGame chessGame, final Connection connection) {
        String query = "INSERT INTO game (turn) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, chessGame.getTurn().name());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public ChessGameResponse findById(final Long gameId, final Connection connection) {
        String query = "SELECT turn, x, y, type, color FROM game g "
                + "JOIN piece p ON g.game_id = p.game_id WHERE p.game_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            Turn turn = null;
            preparedStatement.setLong(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Square, Piece> board = new HashMap<>();
            PieceMapper pieceMapper = new PieceMapper();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("해당 ID의 게임을 찾을 수 없습니다 : " + gameId);
            }
            while (resultSet.next()) {
                turn = Turn.valueOf(resultSet.getString("turn"));
                Rank rank = Rank.from(resultSet.getInt("x"));
                File file = File.from(resultSet.getInt("y"));
                Type type = Type.valueOf(resultSet.getString("type"));
                Color color = Color.valueOf(resultSet.getString("color"));
                board.put(Square.of(rank, file), pieceMapper.map(type, color));
            }
            return new ChessGameResponse(new Board(board), turn);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    @Override
    public List<Long> findIdAll(final Connection connection) {
        String query = "SELECT * FROM game";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Long> gameIds = new ArrayList<>();
            while (resultSet.next()) {
                gameIds.add(resultSet.getLong(1));
            }
            return gameIds;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
