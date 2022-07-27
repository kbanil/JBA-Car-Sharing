package carsharing;

import java.util.List;

public interface CompanyService {
    void add(String companyName);

    List<Company> getAll();

    void addCar(Company company, String carName);

    List<Car> getAllCars(Company company);

    List<Car> getAllAvailableCars(Company company);
}
