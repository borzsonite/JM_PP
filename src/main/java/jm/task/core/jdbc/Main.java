package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserDao userDao = new UserDaoHibernateImpl();

//      1. Создание таблицы User(ов)
        userDao.createUsersTable();

//      2.  Добавление 4 User(ов) в таблицу с данными на свой выбор.
//      После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userDao.saveUser("Tom", "Johns", (byte) 45);
        userDao.saveUser("Bob", "Smirnov", (byte) 23);
        userDao.saveUser("Mike", "Petrov", (byte) 34);
        userDao.saveUser("Maria", "Ivanova", (byte) 30);

//        userDao.removeUserById(1);
//        userDao.removeUserById(3);
        System.out.println(userDao.getAllUsers());

//      3. Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
//        System.out.println(userDaoJDBC.getAllUsers());

//      4. Очистка таблицы User(ов)
//        userDaoJDBC.cleanUsersTable();

//      5.  Удаление таблицы
//        userDaoJDBC.dropUsersTable();

    }
}
