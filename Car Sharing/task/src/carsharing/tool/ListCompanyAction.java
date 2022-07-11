package carsharing.tool;

import carsharing.Company;
import carsharing.CompanyService;

import java.util.List;

public class ListCompanyAction implements Action {
    private final CompanyService companyService;

    public ListCompanyAction(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public void perform(DBMTool dbmTool) {
        final List<Company> companies = companyService.getAll();
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            System.out.println("Company list:");
            for (int i = 1; i <= companies.size(); i++) {
                Company company = companies.get(i - 1);
                System.out.printf("%d. %s%n", i, company.getName());
            }
            System.out.println();
        }
    }
}
