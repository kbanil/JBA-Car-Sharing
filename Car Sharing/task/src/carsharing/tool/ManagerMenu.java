package carsharing.tool;

import java.util.Map;

public class ManagerMenu extends AbstractContextMenu implements ContextMenu {
    public ManagerMenu(String displayText, Map<Integer, Action> actionMap) {
        super(displayText, actionMap);
    }
}
