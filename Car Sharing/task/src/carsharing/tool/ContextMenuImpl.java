package carsharing.tool;

import java.util.Map;
import java.util.Scanner;

public class ContextMenuImpl implements ContextMenu{
    protected final String displayText;
    protected final Map<Integer, Action> actionMap;
    protected Action currentAction;

    public ContextMenuImpl(String displayText, Map<Integer, Action> actionMap) {
        this.displayText = displayText;
        this.actionMap = actionMap;
    }

    public void display() {
        System.out.println(displayText);
    }

    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        final int actionCode = scanner.nextInt();
        currentAction = actionMap.get(actionCode);
    }

    public void performAction(DBMTool dbmTool) {
        currentAction.perform(dbmTool);
    }
}
