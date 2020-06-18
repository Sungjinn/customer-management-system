package app.DB;

import app.models.Customer;

import java.sql.*;

public class DbConnet {

    public DbConnet() {

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
    public boolean deleteCustomer(Customer customer){
        String deleteStatement = "DELETE FROM customer WHERE 상호 = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(deleteStatement);
            stmt.setString(1,customer.getCompanyName());
            int result = stmt.executeUpdate();
            if (result == 1){
                return true;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
