package carsharing;

import carsharing.db.Database;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String dbFileName = "test";
        if (args.length > 1) {
            if ("-databaseFileName".equalsIgnoreCase(args[0])) {
                dbFileName = args[1];
            }
        }
        Database.initialize(dbFileName);
    }
}