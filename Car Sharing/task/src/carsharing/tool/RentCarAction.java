package carsharing.tool;

import carsharing.Car;
import carsharing.Customer;
import carsharing.CustomerService;
import carsharing.DependencyManager;

public class RentCarAction implements Action {
    private final Car car;
    private final Customer customer;
    private final CustomerService customerService;

    public RentCarAction(Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
        this.customerService = DependencyManager.getInstance().getCustomerService();
    }

    @Override
    public void perform(DBMTool dbmTool) {
        customerService.rentCar(customer, car);
        System.out.printf("You rented '%s'%n", car.getName());
        dbmTool.switchContext(DependencyManager.getInstance().getCustomerMenu());
    }
}
