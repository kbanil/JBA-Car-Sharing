package carsharing.tool;

public class DBMTool implements Runnable {
    private ContextMenu contextMenu;
    private boolean shouldTerminate;

    public DBMTool(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }

    @Override
    public void run() {
        while (!shouldTerminate) {
            contextMenu.display();
            contextMenu.readInput();
            contextMenu.performAction(this);
        }
    }

    void switchContext(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }

    void terminate() {
        shouldTerminate = true;
    }
}
