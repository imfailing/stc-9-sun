package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.sun.academy.connection.ConnectionManager;
import ru.innopolis.stc9.sun.academy.dao.mapper.JDBCMapper;
import ru.innopolis.stc9.sun.academy.entity.Hash;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("hashDAOHibernate")
@Transactional
public class HashDAOHibernateImpl implements HashDAO {
    private static final String UPDATE_BY_HASH = "update Hash set recovered=:recovered where userid=:userid";

    private final SessionFactory sessionFactory;
    private static final Logger LOGGER = Logger.getLogger(HashDAOHibernateImpl.class);

    @Autowired
    public HashDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }


    @Override
    public boolean add(Hash hash) {
        try{
            sessionFactory.getCurrentSession().save(hash);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Hash hash) {
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(UPDATE_BY_HASH);
            query.setParameter("userid",hash.getUserid());
            query.setParameter("recovered",hash.getRecovered());
            query.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public Hash getByHash(String hashString) {
        Hash hash = null;
        try {
            hash = sessionFactory.getCurrentSession().get(Hash.class,hashString);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return hash;
    }
}
