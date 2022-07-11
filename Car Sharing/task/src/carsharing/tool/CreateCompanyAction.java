package carsharing.tool;

import carsharing.CompanyService;

import java.util.Scanner;

public class CreateCompanyAction implements Action {
    private final CompanyService companyService;

    public CreateCompanyAction(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public void perform(DBMTool dbmTool) {
        System.out.println("Enter the company name:");
        Scanner scanner = new Scanner(System.in);
        final String companyName = scanner.nextLine();
        companyService.add(companyName);
        System.out.println("The company was created!");
    }
}
