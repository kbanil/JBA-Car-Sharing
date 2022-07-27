package carsharing;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    void add(String customerName);

    void rentCar(Customer customer, Car rentedCar);

    void returnCar(Customer customer);
}
