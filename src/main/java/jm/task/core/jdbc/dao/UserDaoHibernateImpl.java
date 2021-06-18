package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String createTable = "CREATE TABLE IF NOT EXISTS users(id BIGINT NOT NULL AUTO_INCREMENT, name varchar(30), last_name varchar(30), age tinyint, PRIMARY KEY (id))";

        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(createTable).executeUpdate();
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String dropTable = "DROP TABLE IF EXISTS users";

        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(dropTable).executeUpdate();
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
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
        List<User> users = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
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
