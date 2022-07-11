package carsharing.tool;

import java.util.Map;

public class MainMenu extends AbstractContextMenu implements ContextMenu{

    public MainMenu(String displayText, Map<Integer, Action> actionMap) {
        super(displayText,actionMap);
    }
}
