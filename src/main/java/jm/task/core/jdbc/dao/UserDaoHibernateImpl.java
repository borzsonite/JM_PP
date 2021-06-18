package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
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

    @Override
    public void dropUsersTable() {
        try (Connection connection = Util.getJdbcConnection()) {
            Statement statement = connection.createStatement();
            String dropTable = "DROP TABLE IF EXISTS users";
            statement.execute(dropTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction transaction = null;

        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User userToDelete = (User) session.load(User.class, id);
            session.delete(userToDelete);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = new ArrayList<>();
        try {
            Session session = Util.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(User.class);
            users = criteria.list();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;

        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }
}
