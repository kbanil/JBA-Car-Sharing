package carsharing;

import java.util.List;

public interface CompanyService {
    void add(String companyName);
    List<Company> getAll();
}
