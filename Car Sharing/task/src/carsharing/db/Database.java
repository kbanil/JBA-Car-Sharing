package carsharing.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./src/carsharing/db/";
    private static Database INSTANCE;
    private final String dbFileName;

    private Database(String dbFileName) {
        this.dbFileName = dbFileName;
    }

    public static Database getInstance(String dbFileName) {
        if (INSTANCE == null) {
            INSTANCE = new Database(dbFileName);
        }
        return INSTANCE;
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public void initialize() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection connection =
                     getConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(true);
            final String COMPANY_DDL = "CREATE TABLE IF NOT EXISTS " +
                    "COMPANY(" +
                    "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(30) UNIQUE NOT NULL )";
            statement.executeUpdate(COMPANY_DDL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL + dbFileName);
    }
}
