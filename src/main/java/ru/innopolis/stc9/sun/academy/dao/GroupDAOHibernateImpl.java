package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.sun.academy.entity.Group;
import ru.innopolis.stc9.sun.academy.entity.User;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Repository
@Transactional
public class GroupDAOHibernateImpl implements GroupDAO {

    private final SessionFactory sessionFactory;
    private static final String HQL_GET_ALL = "from Group";
    private static final String HQL_GET_BY_USER_ID = "select groups from User as u where u.id=:userId";
    private static final Logger logger = Logger.getLogger(UserDAOHibernateImpl.class);

    @Autowired
    public GroupDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean add(Group group) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.save(group);
        }catch (HibernateError error){
            logger.error(error.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Group group) {
        try{
            Session session = sessionFactory.getCurrentSession();
            group.setUsers(session.get(Group.class,group.getId()).getUsers());
            session.merge(group);
        }catch (HibernateError error){
            logger.error(error.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.remove(getById(id));
        }catch (HibernateError error){
            logger.error(error.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Group getById(int id) {
        Group group=null;
        try{
            Session session = sessionFactory.getCurrentSession();
            group=session.get(Group.class,id);
        }catch (HibernateError error){
            logger.error(error.getMessage());
            return null;
        }
        return group;
    }

    @Override
    public Set<Group> getByUserId(int userId) {
        Set<Group> groups=null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(HQL_GET_BY_USER_ID);
            query.setParameter("userId",userId);
            groups = new HashSet<>(query.list());
        } catch (HibernateError e) {
            logger.error(e.getMessage());
        }
        return groups;
    }

    @Override
    public Set<Group> getAll() {
        Set<Group> groups=null;
        try{
            Session session = sessionFactory.getCurrentSession();
            groups=new HashSet<>(session.createQuery(HQL_GET_ALL).list());
        }catch (HibernateError error){
            logger.error(error.getMessage());
            return Collections.emptySet();
        }
        return groups;
    }

    @Override
    public boolean addNewMember(Integer groupId, Integer userId) {
        logger.info("user_id="+userId+ "added to "+groupId+"group");
        try {
            Session session = sessionFactory.getCurrentSession();
            Group group=session.get(Group.class,groupId);
            User user = session.get(User.class,userId);
            group.getUsers().add(user);
            session.update(group);
        } catch (HibernateError e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteGroupMember(Integer groupId, Integer userId) {
        logger.info("user_id="+userId+ "was deleted from "+groupId+"group");
        try {
            Session session = sessionFactory.getCurrentSession();
            Group group=session.get(Group.class,groupId);
            User user = session.get(User.class,userId);
            group.getUsers().remove(user);
            session.update(group);
        } catch (HibernateError e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
