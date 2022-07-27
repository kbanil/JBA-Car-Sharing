package carsharing.tool;

import carsharing.CustomerService;

import java.util.Scanner;

public class CreateCustomerAction implements Action {
    private final CustomerService customerService;

    public CreateCustomerAction(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void perform(DBMTool dbmTool) {
        System.out.println("Enter the customer name:");
        Scanner scanner = new Scanner(System.in);
        final String customerName = scanner.nextLine();
        customerService.add(customerName);
        System.out.println("The customer was added!");
    }
}
