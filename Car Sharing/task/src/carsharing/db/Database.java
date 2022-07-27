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
            final String CAR_DDL = "CREATE TABLE IF NOT EXISTS " +
                    "CAR (" +
                    "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(30) UNIQUE NOT NULL, " +
                    "COMPANY_ID INTEGER NOT NULL , " +
                    "CONSTRAINT fk_company FOREIGN KEY(COMPANY_ID) REFERENCES COMPANY(ID))";
            statement.executeUpdate(CAR_DDL);
            final String CUSTOMER_DDL = "CREATE TABLE IF NOT EXISTS " +
                    "CUSTOMER(" +
                    "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(30) UNIQUE NOT NULL, " +
                    "RENTED_CAR_ID INTEGER default NULL, " +
                    "CONSTRAINT fk_car FOREIGN KEY(RENTED_CAR_ID) REFERENCES CAR(ID))";
            statement.executeUpdate(CUSTOMER_DDL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL + dbFileName);
    }
}
