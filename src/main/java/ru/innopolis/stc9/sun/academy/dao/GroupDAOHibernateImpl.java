package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.sun.academy.entity.Group;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Repository
@Transactional
public class GroupDAOHibernateImpl implements GroupDAO {

    private final SessionFactory sessionFactory;
    private static final String HQL_GET_ALL = "select a from " + Group.class.getSimpleName() + " a";
    private static final String SQL_GET_BY_USER_ID ="SELECT * FROM \"groups\" LEFT JOIN \"groups_users\" ON \"groups\".id=groups_id WHERE users_id=?";
    private static final String SQL_ADD_USER_TO_GROUP ="INSERT INTO groups_users VALUES (?,?)";
    private static final String SQL_DELETE_USER_FROM_GROUP ="DELETE FROM groups_users WHERE groups_id=? AND users_id=?";
    private static final Logger logger = Logger.getLogger(UserDAOHibernateImpl.class);

    @Autowired
    public GroupDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean add(Group group) {
        Session session = sessionFactory.getCurrentSession();
        try{
            session.save(group);
        }catch (HibernateError error){
            logger.error(error.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Group group) {
        Session session = sessionFactory.getCurrentSession();
        try{
            session.update(group);
        }catch (HibernateError error){
            logger.error(error.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        try{
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
        Session session = sessionFactory.getCurrentSession();
        try{
            group=session.get(Group.class,id);
        }catch (HibernateError error){
            logger.error(error.getMessage());
            return null;
        }
        return group;
    }

    @Override
    public Set<Group> getByUserId(int id) {
        Set<Group> groups = new HashSet<>();
        try {
            Query query = sessionFactory.getCurrentSession().createNativeQuery(SQL_GET_BY_USER_ID, Group.class);
            query.setParameter(1, id);
            groups = new HashSet<>(query.list());
        } catch (HibernateError e) {
            logger.error(e.getMessage());
        }
        return groups;
    }

    @Override
    public Set<Group> getAll() {
        Set<Group> groups=null;
        Session session = sessionFactory.getCurrentSession();
        try{
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
            Query query = sessionFactory.getCurrentSession().createNativeQuery(SQL_ADD_USER_TO_GROUP);
            query.setParameter(1, groupId);
            query.setParameter(2, userId);
            query.executeUpdate();
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
            Query query = sessionFactory.getCurrentSession().createNativeQuery(SQL_DELETE_USER_FROM_GROUP);
            query.setParameter(1, groupId);
            query.setParameter(2, userId);
            query.executeUpdate();
        } catch (HibernateError e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
