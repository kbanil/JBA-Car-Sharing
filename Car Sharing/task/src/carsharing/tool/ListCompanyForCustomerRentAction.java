package carsharing.tool;

import carsharing.Company;
import carsharing.CompanyService;
import carsharing.Customer;
import carsharing.DependencyManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCompanyForCustomerRentAction implements Action {
    private final Customer customer;
    private final CompanyService companyService;

    public ListCompanyForCustomerRentAction(Customer customer) {
        this.customer = customer;
        this.companyService = DependencyManager.getInstance().getCompanyService();
    }

    @Override
    public void perform(DBMTool dbmTool) {
        final List<Company> companies = companyService.getAll();
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else if (customer.getRentedCar() != null) {
            System.out.println("You've already rented a car!");
        } else {
            System.out.println("Choose a company:");
            StringBuilder chooseCompanyMenuTextBuilder = new StringBuilder();
            final Map<Integer, Action> chooseCompanyMenuActionMap = new HashMap<>();
            for (int i = 1; i <= companies.size(); i++) {
                Company company = companies.get(i - 1);
                chooseCompanyMenuActionMap.put(i, new ListCarForCustomerRentAction(company,customer));
                chooseCompanyMenuTextBuilder.append(String.format("%d. %s%n", i, company.getName()));
            }
            chooseCompanyMenuTextBuilder.append(String.format("0. Back"));
            chooseCompanyMenuActionMap.put(0, new SwitchContextMenuAction(DependencyManager.getInstance().getCustomerMenu()));
            ContextMenu chooseCompanyMenu = new ContextMenuImpl(chooseCompanyMenuTextBuilder.toString(), chooseCompanyMenuActionMap);
            dbmTool.switchContext(chooseCompanyMenu);
        }
    }
}
