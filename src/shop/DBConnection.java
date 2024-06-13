/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shop;


    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

/**
 *
 * @author mecatheclau
 */
public class DBConnection {
    
    public Connection connect() {
        String url = "jdbc:mysql://localhost:3306/ecommerce";
        String user = "root";
        String password = "root";
        Connection connection = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return connection;
    }
    
}
