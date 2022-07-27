package carsharing.tool;

import carsharing.Customer;
import carsharing.CustomerService;
import carsharing.DependencyManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCustomerAction implements Action {
    private final CustomerService customerService;

    public ListCustomerAction(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void perform(DBMTool dbmTool) {
        final List<Customer> customers = customerService.getAll();
        if (customers.isEmpty()) {
            System.out.println("The customer list is empty!");
        } else {
            System.out.println("Customer list:");
            StringBuilder chooseCustomerMenuTextBuilder = new StringBuilder();
            final Map<Integer, Action> chooseCustomerMenuActionMap = new HashMap<>();
            for (int i = 1; i <= customers.size(); i++) {
                Customer customer = customers.get(i - 1);
                chooseCustomerMenuActionMap.put(i, new ChooseCustomerAction(customer));
                chooseCustomerMenuTextBuilder.append(String.format("%d. %s%n", i, customer.getName()));
            }
            chooseCustomerMenuTextBuilder.append(String.format("0. Back"));
            chooseCustomerMenuActionMap.put(0, new SwitchContextMenuAction(DependencyManager.getInstance().getMainMenu()));
            ContextMenu chooseCustomerMenu = new ContextMenuImpl(chooseCustomerMenuTextBuilder.toString(), chooseCustomerMenuActionMap);
            dbmTool.switchContext(chooseCustomerMenu);
        }
    }
}
