package carsharing.tool;

public class SwitchContextMenuAction implements Action {
    private final ContextMenu nextContextMenu;

    public SwitchContextMenuAction(ContextMenu nextContextMenu) {
        this.nextContextMenu = nextContextMenu;
    }

    @Override
    public void perform(DBMTool dbmTool) {
        dbmTool.switchContext(nextContextMenu);
    }
}
