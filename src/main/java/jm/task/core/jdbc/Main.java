package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Tom", "Johns", (byte) 45);
        userDaoJDBC.saveUser("Bob", "Smirnov", (byte) 23);
        userDaoJDBC.saveUser("Mike", "Petrov", (byte) 34);
        userDaoJDBC.saveUser("Maria", "Ivanova", (byte) 30);

        System.out.println(userDaoJDBC.getAllUsers());

        userDaoJDBC.cleanUsersTable();

//      userDaoJDBC.removeUserById(2);



        //userDaoJDBC.dropUsersTable();

    }
}
