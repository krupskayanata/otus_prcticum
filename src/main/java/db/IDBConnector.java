package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBConnector {
    // void close();
    void executeUpdate(String sqlRequest);
    ResultSet executeQuery(String sqlRequest) throws SQLException;
    void open();

    void close();
}

