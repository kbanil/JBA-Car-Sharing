package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./src/carsharing/db/";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        String dbFileName = "test";
        if (args.length > 1) {
            if ("-databaseFileName".equalsIgnoreCase(args[0])) {
                dbFileName = args[1];
            }
        }
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