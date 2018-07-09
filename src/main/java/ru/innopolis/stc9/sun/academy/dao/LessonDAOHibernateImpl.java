package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.entity.Lesson;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Repository("lessonDAOHibernate")
@Transactional
public class LessonDAOHibernateImpl implements LessonDAO {

    private final SessionFactory sessionFactory;
    private static final Logger LOGGER = Logger.getLogger(UserDAOHibernateImpl.class);
    private static final String SELECT_LESSONS_BY_USERID_SQL = "SELECT * FROM \"lessons\" LEFT JOIN \"groups_users\" on \"lessons\".group_id=groups_id WHERE users_id=? and date>=? and date<=?";

    @Autowired
    public LessonDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean add(Lesson lesson) {
        try {
            sessionFactory.getCurrentSession().save(lesson);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public Lesson getById(Integer id) {
        Lesson lesson = null;
        try {
            lesson = sessionFactory.getCurrentSession().get(Lesson.class, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return lesson;
    }

    @Override
    public boolean update(Lesson lesson) {
        try {
            sessionFactory.getCurrentSession().update(lesson);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Lesson lesson) {
        try {
            sessionFactory.getCurrentSession().delete(lesson);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public Set<Lesson> getByGroup(Integer groupId) {
        LOGGER.info(String.format("Try to get lesson list from DB by group with id=%d", groupId));
        Set<Lesson> lessons = new HashSet<>();
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from Lesson where group.id = :groupId");
            query.setParameter("groupId", groupId);
            lessons = new HashSet<>(query.list());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return lessons;
    }

    @Override
    public Set<Lesson> getNearestUser(Integer days, Integer userid) {
        Set<Lesson> lessons = new HashSet<>();
        try {
            Query query = sessionFactory.getCurrentSession().createNativeQuery(SELECT_LESSONS_BY_USERID_SQL, Lesson.class);
            query.setParameter(1, userid);
            query.setParameter(2, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
            query.setParameter(3, java.sql.Date.valueOf(LocalDateTime.now().plusDays(days).toLocalDate()));
            lessons = new HashSet<Lesson>(query.list());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return lessons;
    }

    @Override
    public Set<Lesson> getNearest(Integer days) {
        Set<Lesson> lessons = new HashSet<>();
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from Lesson where date >= :startDate and date <= :endDate");
            query.setParameter("startDate",  java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
            query.setParameter("endDate", java.sql.Date.valueOf(LocalDateTime.now().plusDays(days).toLocalDate()));
            lessons = new HashSet<>(query.list());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return lessons;
    }
}
