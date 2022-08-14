package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String userName = "root";
    private static final String password = "1234";
    private static final String url = "jdbc:mysql://localhost:3306/preproject";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    public static Connection connection;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(url,userName,password);
            Class.forName(driver);
            if (!connection.isClosed()) {
                System.out.println("Connected");
            }
            else {
                System.out.println("Connection failed");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void main(String[] args) {

        Util.getConnection();

    }
}
