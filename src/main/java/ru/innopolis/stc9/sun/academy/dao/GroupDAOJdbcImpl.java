package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.connection.ConnectionManager;
import ru.innopolis.stc9.sun.academy.dao.mapper.JDBCMapper;
import ru.innopolis.stc9.sun.academy.entity.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component
public class GroupDAOJdbcImpl implements GroupDAO {

    static final String INSERT_GROUP_SQL = "INSERT INTO groups (title, description, start_date, finished_date, is_active) VALUES (?, ?, to_date(?,'YYYY-MM-DD'), to_date(?,'YYYY-MM-DD'), ?)";
    static final String UPDATE_GROUP_SQL = "UPDATE groups SET title = ?, description = ?, start_date = to_date(?,'YYYY-MM-DD'), finished_date = to_date(?,'YYYY-MM-DD'), is_active = ? WHERE id = ?";
    static final String DELETE_GROUP_SQL = "DELETE FROM groups WHERE id = ?";
    static final String SELECT_ALL_GROUPS_SQL = "SELECT id, title, description, start_date, finished_date, is_active FROM groups ORDER BY id";
    static final String GET_GROUP_SQL = "SELECT id, title, description, start_date, finished_date, is_active FROM groups WHERE id = ?";
    static final String INSERT_NEW_MEMBER_SQL = "INSERT INTO members (group_id, user_id) VALUES (?, ?)";
    static final String DELETE_NEW_MEMBER_SQL = "DELETE FROM members WHERE group_id = ? AND user_id=?";

    private final ConnectionManager connectionManager;
    private final JDBCMapper<Group> groupMapper;
    private static final Logger LOGGER = Logger.getLogger(UserDAOJdbcImpl.class);

    @Autowired
    public GroupDAOJdbcImpl(ConnectionManager connectionManager, JDBCMapper<Group> groupMapper) {
        this.connectionManager = connectionManager;
        this.groupMapper = groupMapper;
    }

    //TODO: нужен рефакторинг. Много повторяющегося кода.
    // Наверное неплохо было бы переписать все даошки в шаблонный метод с делегированием подготовки statement'а и маппинга наследникам

    @Override
    public boolean add(Group group) {
        try(Connection connection = connectionManager.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(INSERT_GROUP_SQL)) {
                groupMapper.toStatement(statement, group);
                statement.execute();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Group group) {
        int count = 0;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_GROUP_SQL)) {
                groupMapper.toStatement(statement, group);
                statement.setInt(6, group.getId());
                count = statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return count > 0;
    }

    @Override
    public boolean deleteById(int id) {
        int count = 0;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_GROUP_SQL)) {
                statement.setInt(1, id);
                count = statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return count > 0;
    }

    @Override
    public Group getById(int id) {
        Group group = null;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(GET_GROUP_SQL)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        group = groupMapper.toEntity(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return group;
    }

    @Override
    public Set<Group> getAll() {
        Set<Group> groups = new HashSet<>();
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GROUPS_SQL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        groups.add(groupMapper.toEntity(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return groups;
    }

    @Override
    public boolean addNewMember(Integer groupId, Integer userId) {
        try(Connection connection = connectionManager.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_MEMBER_SQL)) {
                statement.setInt(1, groupId);
                statement.setInt(2, userId);
                statement.execute();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteGroupMember(Integer groupId, Integer userId) {
        int count = 0;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_NEW_MEMBER_SQL)) {
                statement.setInt(1, groupId);
                statement.setInt(2, userId);
                count = statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return count > 0;
    }
}