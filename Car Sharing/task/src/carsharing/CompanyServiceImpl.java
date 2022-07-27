package carsharing;

import carsharing.db.CompanyRepository;

import java.sql.SQLException;
import java.util.List;

public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void add(String companyName) {
        try {
            companyRepository.add(companyName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Company> getAll() {
        try {
            return companyRepository.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCar(Company company, String carName) {
        try {
            companyRepository.addCar(company, carName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> getAllCars(Company company) {
        try {
            return companyRepository.getAllCars(company);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> getAllAvailableCars(Company company) {
        try {
            return companyRepository.getAllAvailableCars(company);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
