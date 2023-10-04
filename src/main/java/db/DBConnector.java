package db;
import settings.ISettings;
import settings.Settings;

import java.sql.*;
import java.util.Map;

public class DBConnector implements IDBConnector {

    private static Connection connection = null;
    private static Statement statement = null;
    //private final Settings settings;

//    public DBConnector() {
//        this.settings = new Settings().getDbSettings();
//    }

    public void open() {
        Map<String, String> settings = new Settings().read();
        try {
            if(connection == null) {
                connection = DriverManager.getConnection(settings.get("url"), settings.get("db_username"), settings.get("db_password"));
            }
            if(statement == null) {
            statement = connection.createStatement();
        }
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        if(statement != null) {
           try {
               statement.close();
               statement = null;
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void executeUpdate(String sqlRequest) {
        this.open();
        try {
            statement.executeUpdate(sqlRequest);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public ResultSet executeQuery(String sqlRequest) throws SQLException {
        return statement.executeQuery(sqlRequest);
    }

}


