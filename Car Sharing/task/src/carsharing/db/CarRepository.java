package carsharing.db;

import carsharing.Car;

import java.sql.SQLException;

public interface CarRepository {
    Car findById(int carId) throws SQLException;
}
