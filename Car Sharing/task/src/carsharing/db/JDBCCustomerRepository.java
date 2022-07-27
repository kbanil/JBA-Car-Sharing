package carsharing.db;

import carsharing.Car;
import carsharing.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCustomerRepository implements CustomerRepository {
    private static final CustomerRepository INSTANCE = new JDBCCustomerRepository();
    private static final CarRepository carRepository = JDBCCarRepository.getInstance();
    private static final String INSERT_CUSTOMER_STATEMENT = "INSERT INTO CUSTOMER(NAME) VALUES (?)";
    private static final String QUERY_FIND_ALL_CUSTOMER = "SELECT * from CUSTOMER";
    private static final String UPDATE_CAR_STATEMENT = "UPDATE CUSTOMER SET RENTED_CAR_ID = ? where ID = ?";
    private final Database database;

    private JDBCCustomerRepository() {
        database = Database.getInstance();
    }

    public static CustomerRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FIND_ALL_CUSTOMER);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                final int rentedCarId = rs.getInt("RENTED_CAR_ID");
                Car rentedCar = null;
                if (rentedCarId != 0) {
                    rentedCar = carRepository.findById(rentedCarId);
                }
                customers.add(new Customer(id, name, rentedCar));
            }
            return customers;
        }
    }

    @Override
    public void add(String customerName) throws SQLException {
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER_STATEMENT)) {
            statement.setString(1, customerName);
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Customer customer, Car rentedCar) throws SQLException {
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_STATEMENT)) {
            statement.setInt(2, customer.getId());
            if (rentedCar != null) {
                statement.setInt(1, rentedCar.getId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            statement.executeUpdate();
        }
    }
}
