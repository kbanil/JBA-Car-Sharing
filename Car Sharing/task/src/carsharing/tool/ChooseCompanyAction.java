package carsharing.tool;

import carsharing.Company;
import carsharing.CompanyService;
import carsharing.DependencyManager;

import java.util.HashMap;
import java.util.Map;

public class ChooseCompanyAction implements Action {
    private final Company company;
    private final CompanyService companyService;

    public ChooseCompanyAction(Company company, CompanyService companyService) {
        this.company = company;
        this.companyService = companyService;
    }

    @Override
    public void perform(DBMTool dbmTool) {
        System.out.printf("'%s' company%n", company.getName());
        String companyMenuText = "1. Car list\n" +
                "2. Create a car\n" +
                "0. Back";
        Action backAction = new SwitchContextMenuAction(DependencyManager.getInstance().getManagerMenu());
        Action listCarAction = new ListCarAction(company, companyService);
        Action createCarAction = new CreateCarAction(company, companyService);
        final Map<Integer, Action> companyMenuActionMap = new HashMap<>();
        companyMenuActionMap.put(1, listCarAction);
        companyMenuActionMap.put(2, createCarAction);
        companyMenuActionMap.put(0, backAction);
        ContextMenu companyMenu = new ContextMenuImpl(companyMenuText, companyMenuActionMap);
        dbmTool.switchContext(companyMenu);
    }
}
