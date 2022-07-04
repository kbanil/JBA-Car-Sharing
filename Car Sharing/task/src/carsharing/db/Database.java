package carsharing.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./src/carsharing/db/";
    public static void initialize(String dbFileName) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);

        try (Connection connection =
                     DriverManager.getConnection(DB_URL + dbFileName);
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(true);
            final String sql = "CREATE TABLE IF NOT EXISTS COMPANY(ID INTEGER NOT NULL, NAME VARCHAR(30))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
