package carsharing.db;

import carsharing.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCCompanyRepository implements CompanyRepository {
    private static final JDBCCompanyRepository INSTANCE = new JDBCCompanyRepository();
    private static final String INSERT_STATEMENT = "INSERT INTO COMPANY(NAME) VALUES (?)";
    private static final String QUERY_FIND_ALL = "SELECT * from COMPANY";
    private final Database database;

    private JDBCCompanyRepository() {
        database = Database.getInstance();
    }

    public static CompanyRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(String companyName) throws SQLException {
        try (Connection connection = database.getConnection();
             final PreparedStatement statement = connection.prepareStatement(INSERT_STATEMENT)) {
            statement.setString(1, companyName);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Company> getAll() throws SQLException {
        List<Company> companies = new ArrayList<>();
        try (Connection connection = database.getConnection();
             final PreparedStatement statement = connection.prepareStatement(QUERY_FIND_ALL);
             final ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                companies.add(new Company(id, name));
            }
        }
        return companies;
    }
}
