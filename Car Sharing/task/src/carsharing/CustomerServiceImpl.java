package carsharing;

import carsharing.db.CustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAll() {
        try {
            return customerRepository.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String customerName) {
        try {
            customerRepository.add(customerName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rentCar(Customer customer, Car rentedCar) {
        try {
            customerRepository.update(customer, rentedCar);
            customer.setRentedCar(rentedCar);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void returnCar(Customer customer) {
        try {
            customerRepository.update(customer, null);
            customer.setRentedCar(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
