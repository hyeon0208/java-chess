package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    private JdbcConnection() {
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION,
                    USER_NAME, PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (final SQLException exception) {
            System.err.println("DB 연결 오류 : " + exception.getMessage());
            return null;
        }
    }
}
