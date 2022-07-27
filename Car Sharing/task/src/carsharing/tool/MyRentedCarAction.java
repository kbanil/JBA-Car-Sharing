package carsharing.tool;

import carsharing.Car;
import carsharing.Company;
import carsharing.Customer;

public class MyRentedCarAction implements Action {
    private final Customer customer;

    public MyRentedCarAction(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void perform(DBMTool dbmTool) {
        final Car rentedCar = customer.getRentedCar();
        if (rentedCar == null) {
            System.out.println("You didn't rent a car!");
        } else {
            System.out.println("Your rented car:");
            System.out.println(rentedCar.getName());
            final Company company = rentedCar.getCompany();
            System.out.println("Company:");
            System.out.println(company.getName());
        }
    }
}
