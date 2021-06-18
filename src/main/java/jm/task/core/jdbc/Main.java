package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserDao userDaoJDBC = new UserDaoJDBCImpl();

//      1. Создание таблицы User(ов)
        userDaoJDBC.createUsersTable();

//      2.  Добавление 4 User(ов) в таблицу с данными на свой выбор.
//      После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
//        userDaoJDBC.saveUser("Tom", "Johns", (byte) 45);
//        userDaoJDBC.saveUser("Bob", "Smirnov", (byte) 23);
//        userDaoJDBC.saveUser("Mike", "Petrov", (byte) 34);
//        userDaoJDBC.saveUser("Maria", "Ivanova", (byte) 30);

//      3. Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
//        System.out.println(userDaoJDBC.getAllUsers());

//      4. Очистка таблицы User(ов)
//        userDaoJDBC.cleanUsersTable();

//      5.  Удаление таблицы
        userDaoJDBC.dropUsersTable();

    }
}
