package tables;

import db.DBConnector;
import db.IDBConnector;

public abstract class AbsTable implements ITable {

    protected IDBConnector dbConnector = null;
    protected String tableName = "";

    public AbsTable(String tableName) {
        dbConnector = new DBConnector();
        this.tableName = tableName;
    }

    public abstract void create();

    @Override
    public void delete() {
        dbConnector.executeUpdate(String.format("drop table %s", this.tableName));
    }
}
