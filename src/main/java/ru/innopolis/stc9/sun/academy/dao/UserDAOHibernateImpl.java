package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.sun.academy.entity.User;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Repository("userDAOHibernate")
@Transactional
public class UserDAOHibernateImpl implements UserDAO {

    private final SessionFactory sessionFactory;
    private static final Logger LOGGER = Logger.getLogger(UserDAOHibernateImpl.class);
    private static final String SELECT_USERS_BY_GROUP_SQL = "SELECT * FROM \"user\" LEFT JOIN \"members\" ON \"user\".id=user_id WHERE group_id=?";

    @Autowired
    public UserDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean add(User user) {
        try {
            sessionFactory.getCurrentSession().save(user);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public User getById(int id) {
        User user = null;
        try {
            user = sessionFactory.getCurrentSession().get(User.class, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public Set<User> getAll() {
        Set<User> users = new HashSet<>();
        try {
            users = new HashSet<User>(sessionFactory.getCurrentSession().createQuery("from User as u").list());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public boolean update(User user) {
        try {
            sessionFactory.getCurrentSession().update(user);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(User user) {
        try {
            sessionFactory.getCurrentSession().delete(user);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public Set<User> getByGroup(Integer groupId) {
        LOGGER.info(String.format("Try to get user list from DB by group with id=%d", groupId));
        Set<User> users = new HashSet<>();
        try {
            Query query = sessionFactory.getCurrentSession().createNativeQuery(SELECT_USERS_BY_GROUP_SQL, User.class);
            query.setParameter(1, groupId);
            users = new HashSet<User>(query.list());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public User getByEmail(String email) {
        User user = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from User where email = :email");
            query.setParameter("email", email);
            user = (User) query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }
}