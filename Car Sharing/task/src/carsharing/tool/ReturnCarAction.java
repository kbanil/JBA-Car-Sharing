package carsharing.tool;

import carsharing.Customer;
import carsharing.CustomerService;
import carsharing.DependencyManager;

public class ReturnCarAction implements Action{
    private final Customer customer;
    private final CustomerService customerService;

    public ReturnCarAction(Customer customer) {
        this.customer = customer;
        this.customerService = DependencyManager.getInstance().getCustomerService();
    }

    @Override
    public void perform(DBMTool dbmTool) {
        if(customer.getRentedCar() == null) {
            System.out.println("You didn't rent a car!");
        } else {
           customerService.returnCar(customer);
            System.out.println("You've returned a rented car!");
        }
    }
}
