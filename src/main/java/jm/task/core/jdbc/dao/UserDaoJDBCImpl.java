package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        try (Connection connection = Util.getJdbcConnection()) {
            Statement statement = connection.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS users (\n" +
                    "id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "name varchar(30),\n" +
                    "last_name varchar(30),\n" +
                    "age tinyint,\n" +
                    "PRIMARY KEY (id));";
            statement.execute(createTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getJdbcConnection()) {
            Statement statement = connection.createStatement();
            String dropTable = "DROP TABLE IF EXISTS users";
            statement.execute(dropTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getJdbcConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO users VALUES(default, ?,?,?) ");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getJdbcConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getJdbcConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getJdbcConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE users ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
