package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
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
            sessionFactory.getCurrentSession().update(hash);
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
