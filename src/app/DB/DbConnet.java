package app.DB;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnet {

    private DbConnet() {

    }

    public static DbConnet getInstance() {
        return new DbConnet();
    }

    public Connection getConnection() {
        String connect_string = "jdbc:sqlite:customer-management.db";
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(connect_string);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
