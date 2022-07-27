package carsharing;

import carsharing.db.Database;
import carsharing.tool.DBMTool;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String dbFileName = "test";
        if (args.length > 1) {
            if ("-databaseFileName".equalsIgnoreCase(args[0])) {
                dbFileName = args[1];
            }
        }
        Database database = Database.getInstance(dbFileName);
        database.initialize();
        DependencyManager dependencyManager = DependencyManager.getInstance();
        DBMTool dbmTool = dependencyManager.getDbmTool();
        dbmTool.run();
    }
}