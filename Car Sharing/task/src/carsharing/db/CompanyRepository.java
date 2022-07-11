package carsharing.db;

import carsharing.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompanyRepository {
    void add(String companyName) throws SQLException;
    List<Company> getAll() throws SQLException;
}
