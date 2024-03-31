package chess.dao;

import chess.dto.PieceResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PieceDao implements PieceRepository {

    @Override
    public Long save(final PieceResponse pieceResponse, final Long gameId) {
        try (Connection connection = JdbcConnection.getConnection()) {
            String query = "INSERT INTO piece (game_id, x, y, color, type) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(1, gameId);
                preparedStatement.setInt(2, pieceResponse.rankIndex());
                preparedStatement.setInt(3, pieceResponse.fileIndex());
                preparedStatement.setString(4, pieceResponse.color().name());
                preparedStatement.setString(5, pieceResponse.type().name());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                connection.commit();
                return resultSet.getLong(1);
            } catch (SQLException exception) {
                connection.rollback();
                throw new RuntimeException(exception);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
