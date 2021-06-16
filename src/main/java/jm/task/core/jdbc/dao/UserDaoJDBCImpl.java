package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS User (\n" +
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
        try {
            Statement statement = connection.createStatement();
            String dropTable = "DROP TABLE IF EXISTS User";
            statement.execute(dropTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO USER VALUES(default, ?,?,?) ");
           // preparedStatement.setLong(1, 1);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
