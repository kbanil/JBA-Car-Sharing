package carsharing.tool;

import carsharing.Company;
import carsharing.CompanyService;

import java.util.Scanner;

public class CreateCarAction implements Action {
    private final Company company;
    private final CompanyService companyService;

    public CreateCarAction(Company company, CompanyService companyService) {
        this.company = company;
        this.companyService = companyService;
    }

    @Override
    public void perform(DBMTool dbmTool) {
        System.out.println("Enter the car name:");
        Scanner scanner = new Scanner(System.in);
        final String carName = scanner.nextLine();
        companyService.addCar(company, carName);
        System.out.println("The car was added!");
    }
}
