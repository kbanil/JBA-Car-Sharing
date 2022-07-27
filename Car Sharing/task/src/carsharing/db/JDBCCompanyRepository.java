package carsharing.db;

import carsharing.Car;
import carsharing.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCCompanyRepository implements CompanyRepository {
    private static final JDBCCompanyRepository INSTANCE = new JDBCCompanyRepository();
    private static final String INSERT_COMPANY_STATEMENT = "INSERT INTO COMPANY(NAME) VALUES (?)";
    private static final String QUERY_FIND_ALL_COMPANY = "SELECT * from COMPANY";
    private static final String INSERT_CAR_STATEMENT = "INSERT INTO CAR(NAME,COMPANY_ID) VALUES (?,?)";
    private static final String QUERY_FIND_ALL_CAR = "SELECT * FROM CAR WHERE COMPANY_ID = ? ORDER BY ID";
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
             final PreparedStatement statement = connection.prepareStatement(INSERT_COMPANY_STATEMENT)) {
            statement.setString(1, companyName);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Company> getAll() throws SQLException {
        List<Company> companies = new ArrayList<>();
        try (Connection connection = database.getConnection();
             final PreparedStatement statement = connection.prepareStatement(QUERY_FIND_ALL_COMPANY);
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

    @Override
    public void addCar(Company company, String carName) throws SQLException {
        try (Connection connection = database.getConnection();
             final PreparedStatement statement = connection.prepareStatement(INSERT_CAR_STATEMENT)) {
            statement.setString(1, carName);
            statement.setInt(2, company.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Car> getAllCars(Company company) throws SQLException {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = database.getConnection();
             final PreparedStatement statement = connection.prepareStatement(QUERY_FIND_ALL_CAR);
        ) {
            statement.setInt(1,company.getId());
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                cars.add(new Car(id, name));
            }
        }
        return cars;
    }
}
