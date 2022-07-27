package carsharing.db;

import carsharing.Car;
import carsharing.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {
    List<Customer> getAll() throws SQLException;

    void add(String customerName) throws SQLException;

    void update(Customer customer, Car rentedCar) throws SQLException;
}
