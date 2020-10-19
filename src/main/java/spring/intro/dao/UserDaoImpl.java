package spring.intro.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.intro.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add user " + user + "to database :", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> getAllUsers = session.createQuery("from User", User.class);
            return getAllUsers.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't get users from DB", e);
        }
    }
}
