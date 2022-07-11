package carsharing.tool;

public class TerminateAction implements Action {
    @Override
    public void perform(DBMTool dbmTool) {
        dbmTool.terminate();
    }
}
