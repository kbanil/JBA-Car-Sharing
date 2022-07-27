package carsharing.db;

import carsharing.Car;
import carsharing.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompanyRepository {
    void add(String companyName) throws SQLException;

    List<Company> getAll() throws SQLException;

    void addCar(Company company, String carName) throws SQLException;

    List<Car> getAllCars(Company company) throws SQLException;
    List<Car> getAllAvailableCars(Company company) throws SQLException;

    Company findById(int id) throws SQLException;
}
