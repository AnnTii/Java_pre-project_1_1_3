package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    private static final String userName = "root";
    private static final String password = "1234";
    private static final String url = "jdbc:mysql://localhost:3306/preproject";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    public static void getConnection() {

        try (Connection connection = DriverManager.getConnection(url,userName,password)) {
            connection.setAutoCommit(false);
            Class.forName(driver);
            try (Statement statement = connection.createStatement()) {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
            connection.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

    }
}
