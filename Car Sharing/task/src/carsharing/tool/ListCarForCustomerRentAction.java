package carsharing.tool;

import carsharing.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCarForCustomerRentAction implements Action {
    private final Company company;
    private final Customer customer;
    private final CompanyService companyService;

    public ListCarForCustomerRentAction(Company company, Customer customer) {
        this.company = company;
        this.customer = customer;
        this.companyService = DependencyManager.getInstance().getCompanyService();
    }

    @Override
    public void perform(DBMTool dbmTool) {
        final List<Car> cars = companyService.getAllAvailableCars(company);
        if (cars.isEmpty()) {
            System.out.printf("No available cars in the '%s' company%n", company.getName());
        } else {
            System.out.println("Choose a car:");
            StringBuilder chooseCarMenuTextBuilder = new StringBuilder();
            final Map<Integer, Action> chooseCarMenuActionMap = new HashMap<>();
            for (int i = 1; i <= cars.size(); i++) {
                Car car = cars.get(i - 1);
                chooseCarMenuActionMap.put(i, new RentCarAction(car, customer));
                chooseCarMenuTextBuilder.append(String.format("%d. %s%n", i, car.getName()));
            }
            chooseCarMenuTextBuilder.append(String.format("0. Back"));
            chooseCarMenuActionMap.put(0, new SwitchContextMenuAction(DependencyManager.getInstance().getCustomerMenu()));
            ContextMenu chooseCompanyMenu = new ContextMenuImpl(chooseCarMenuTextBuilder.toString(), chooseCarMenuActionMap);
            dbmTool.switchContext(chooseCompanyMenu);
        }
    }
}
