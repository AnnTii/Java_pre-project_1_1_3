package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.*;

public class Main {

    private static final String userName = "root";
    private static final String password = "1234";
    private static final String url = "jdbc:mysql://localhost:3306/preproject";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(url,userName,password)) {
            Class.forName(driver);
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                UserService userService = new UserServiceImpl();

                userService.createUsersTable();
                userService.saveUser("Name1", "LastName1", (byte) 20);
                System.out.println("User с именем – Name1 добавлен в базу данных");
                userService.saveUser("Name2", "LastName2", (byte) 25);
                System.out.println("User с именем – Name2 добавлен в базу данных");
                userService.saveUser("Name3", "LastName3", (byte) 31);
                System.out.println("User с именем – Name3 добавлен в базу данных");
                userService.saveUser("Name4", "LastName4", (byte) 38);
                System.out.println("User с именем – Name4 добавлен в базу данных");
                userService.getAllUsers();
                System.out.println(userService.getAllUsers());
                userService.cleanUsersTable();
                userService.dropUsersTable();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
            connection.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
