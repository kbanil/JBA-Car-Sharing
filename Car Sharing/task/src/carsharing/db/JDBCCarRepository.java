package carsharing.db;

import carsharing.Car;
import carsharing.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCCarRepository implements CarRepository {
    private static final CarRepository INSTANCE = new JDBCCarRepository();
    private static final CompanyRepository companyRepository = JDBCCompanyRepository.getInstance();
    private final String FIND_BY_ID = "SELECT * FROM CAR WHERE ID = ?";
    private final Database database;

    private JDBCCarRepository() {
        this.database = Database.getInstance();
    }

    public static CarRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Car findById(int carId) throws SQLException {
        try (Connection connection = database.getConnection();
             final PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
        ) {
            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                final int id = resultSet.getInt("ID");
                final String name = resultSet.getString("NAME");
                final int companyId = resultSet.getInt("COMPANY_ID");
                final Company company = companyRepository.findById(companyId);
                return new Car(id, name, company);
            }
        }
        return null;
    }
}
