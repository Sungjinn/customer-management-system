package app.DB;

import app.models.Customer;

import javax.swing.*;
import java.sql.*;

public class DbConnet {

    public DbConnet() {

    }

    public static DbConnet getInstance() {
        return new DbConnet();
    }

    public Connection getConnection() {
//        String connect_string = "jdbc:sqlite:customer-management.db";
        Connection connection = null;
        try {
//            Class.forName("org.sqlite.JDBC");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/server?serverTimezone=UTC","root","Lastcall#8613");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"데이터베이스 접속 오류" + e.getMessage());
        }
        return connection;
    }

    public boolean deleteCustomer(Customer customer){
        String deleteStatement = "DELETE FROM customer WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(deleteStatement);
            stmt.setString(1,customer.getId());
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

    public void updateKey(){

    }
}
