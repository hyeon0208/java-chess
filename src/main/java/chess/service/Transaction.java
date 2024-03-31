package chess.service;

import chess.dao.JdbcConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public class Transaction {

    private Transaction() {
    }

    public static  <T> T start(Function<Connection, T> function) {
        try (Connection connection = JdbcConnection.getConnection()) {
            try {
                T result = function.apply(connection);
                connection.commit();
                return result;
            } catch (SQLException exception) {
                connection.rollback();
                throw new RuntimeException(exception);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
