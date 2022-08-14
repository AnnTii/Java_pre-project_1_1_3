package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Statement statement;
    PreparedStatement preparedStatement;


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            String createUsersTable = "CREATE TABLE users(id INT AUTO_INCREMENT PRIMARY KEY,name CHAR(45),lastname CHAR(45), age INT)";
            preparedStatement = Util.getConnection().prepareStatement(createUsersTable);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            String dropUsersTable = "DROP TABLE users";
            preparedStatement = Util.getConnection().prepareStatement(dropUsersTable);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String saveUser = "INSERT INTO users(name,lastName,age) VALUES(?,?,?)";
            preparedStatement = Util.getConnection().prepareStatement(saveUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            String removeUserById = "DELETE FROM users WHERE ID = ?";
            preparedStatement = Util.getConnection().prepareStatement(removeUserById);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String getAllUsers = "SELECT * FROM users";
        try {
            statement = Util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getAllUsers);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            String cleanUsersTable = "DELETE FROM users";
            preparedStatement = Util.getConnection().prepareStatement(cleanUsersTable);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
