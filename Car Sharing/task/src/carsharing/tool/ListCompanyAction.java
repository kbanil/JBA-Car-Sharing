package carsharing.tool;

import carsharing.Company;
import carsharing.CompanyService;
import carsharing.DependencyManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            System.out.println("Choose the company:");
            StringBuilder chooseCompanyMenuTextBuilder = new StringBuilder();
            final Map<Integer, Action> chooseCompanyMenuActionMap = new HashMap<>();
            for (int i = 1; i <= companies.size(); i++) {
                Company company = companies.get(i - 1);
                chooseCompanyMenuActionMap.put(i, new ChooseCompanyAction(company, companyService));
                chooseCompanyMenuTextBuilder.append(String.format("%d. %s%n", i, company.getName()));
            }
            chooseCompanyMenuTextBuilder.append(String.format("0. Back"));
            chooseCompanyMenuActionMap.put(0, new SwitchContextMenuAction(DependencyManager.getInstance().getManagerMenu()));
            ContextMenu chooseCompanyMenu = new ContextMenuImpl(chooseCompanyMenuTextBuilder.toString(), chooseCompanyMenuActionMap);
            dbmTool.switchContext(chooseCompanyMenu);
        }
    }
}
