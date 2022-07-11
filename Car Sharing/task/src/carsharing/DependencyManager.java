package carsharing;

import carsharing.db.CompanyRepository;
import carsharing.db.JDBCCompanyRepository;
import carsharing.tool.*;

import java.util.HashMap;
import java.util.Map;

public class DependencyManager {
    private DBMTool dbmTool;
    private ContextMenu mainMenu;
    private ContextMenu managerMenu;
    private CompanyRepository companyRepository;
    private CompanyService companyService;

    public void wireDependencies() {
        companyRepository = JDBCCompanyRepository.getInstance();
        companyService = new CompanyServiceImpl(companyRepository);
        Action createCompanyAction = new CreateCompanyAction(companyService);
        Action listCompanyAction = new ListCompanyAction(companyService);
        final String mainMenuDisplayText = "1. Log in as a manager\n" +
                "0. Exit";
        final Map<Integer, Action> mainMenuActionMap = new HashMap<>();
        mainMenu = new MainMenu(mainMenuDisplayText, mainMenuActionMap);

        Action backAction = new SwitchContextMenuAction(mainMenu);
        final Map<Integer, Action> managerMenuActionMap = new HashMap<>();
        managerMenuActionMap.put(1, listCompanyAction);
        managerMenuActionMap.put(2, createCompanyAction);
        managerMenuActionMap.put(0, backAction);

        final String managerMenuDisplayText = "1. Company list\n" +
                "2. Create a company\n" +
                "0. Back";
        managerMenu = new ManagerMenu(managerMenuDisplayText, managerMenuActionMap);
        Action managerLoginAction = new SwitchContextMenuAction(managerMenu);
        Action terminateAction = new TerminateAction();
        mainMenuActionMap.put(1, managerLoginAction);
        mainMenuActionMap.put(0, terminateAction);
        dbmTool = new DBMTool(mainMenu);
    }

    public DBMTool getDbmTool() {
        return dbmTool;
    }

    public ContextMenu getMainMenu() {
        return mainMenu;
    }

    public ContextMenu getManagerMenu() {
        return managerMenu;
    }

    public CompanyRepository getCompanyRepository() {
        return companyRepository;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }
}
