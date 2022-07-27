package carsharing.tool;

import carsharing.Customer;
import carsharing.DependencyManager;

import java.util.HashMap;
import java.util.Map;

public class ChooseCustomerAction implements Action {
    private final Customer customer;

    public ChooseCustomerAction(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void perform(DBMTool dbmTool) {
        final String customerMenuText = "1. Rent a car\n" +
                "2. Return a rented car\n" +
                "3. My rented car\n" +
                "0. Back";
        Action backAction = new SwitchContextMenuAction(DependencyManager.getInstance().getMainMenu());
        Map<Integer, Action> customerMenuActionMap = new HashMap<>();
        Action listCompanyForRentAction = new ListCompanyForCustomerRentAction(customer);
        Action returnCarAction = new ReturnCarAction(customer);
        Action myRentedCarAction = new MyRentedCarAction(customer);
        customerMenuActionMap.put(1, listCompanyForRentAction);
        customerMenuActionMap.put(2, returnCarAction);
        customerMenuActionMap.put(3, myRentedCarAction);
        customerMenuActionMap.put(0, backAction);
        ContextMenu customerMenu = new ContextMenuImpl(customerMenuText, customerMenuActionMap);
        DependencyManager.getInstance().setCustomerMenu(customerMenu);
        dbmTool.switchContext(customerMenu);
    }
}
