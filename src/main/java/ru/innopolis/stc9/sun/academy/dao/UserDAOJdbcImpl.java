package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.connection.ConnectionManager;
import ru.innopolis.stc9.sun.academy.dao.mapper.JDBCMapper;
import ru.innopolis.stc9.sun.academy.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component("userDAOJDBC")
public class UserDAOJdbcImpl implements UserDAO {
    private final ConnectionManager connectionManager;
    private final JDBCMapper<User> userMapper;
    private static final Logger LOGGER = Logger.getLogger(UserDAOJdbcImpl.class);

    static final String INSERT_USER_SQL = "INSERT INTO \"user\" (firstname, lastname, patronymic, email, password, is_active) VALUES (?, ?, ?, ?, ?, ?) ";
    static final String SELECT_USER_SQL = "SELECT * FROM \"user\" WHERE id = ?";
    static final String SELECT_ALL_USERS_SQL = "SELECT * FROM \"user\" ORDER BY id";
    static final String UPDATE_USER_SQL = "UPDATE \"user\" SET firstname = ?, lastname = ?, patronymic = ?, email = ?, password = ?, is_active = ? WHERE id = ?";
    static final String DELETE_USER_SQL = "DELETE FROM \"user\" WHERE id = ?";
    static final String SELECT_USERS_BY_GROUP_SQL = "SELECT * FROM \"user\" LEFT JOIN \"members\" ON \"user\".id=user_id WHERE group_id=?";
    static final String SELECT_USER_SQL_EMAIL = "SELECT * FROM \"user\" WHERE email = ?";

    @Autowired
    public UserDAOJdbcImpl(ConnectionManager connectionManager, JDBCMapper<User> userMapper) {
        this.connectionManager = connectionManager;
        this.userMapper = userMapper;
    }

    //TODO: нужен рефакторинг. Много повторяющегося кода.
    // Наверное неплохо было бы переписать все даошки в шаблонный метод с делегированием подготовки statement'а и маппинга наследникам

    @Override
    public boolean add(User user) {
        try (Connection connection = connectionManager.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL)) {
                userMapper.toStatement(statement, user);
                statement.execute();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public User getById(int id) {
        User user = null;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        user = userMapper.toEntity(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public Set<User> getAll() {
        Set<User> users = new HashSet<>();
        try (Connection connection = connectionManager.getConnection();) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_SQL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        users.add(userMapper.toEntity(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public boolean update(User user) {
        int count = 0;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
                userMapper.toStatement(statement, user);
                statement.setInt(7, user.getId());
                count = statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return count > 0;
    }

    @Override
    public boolean delete(User user) {
        int count = 0;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
                statement.setInt(1, user.getId());
                count = statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return count > 0;
    }

    @Override
    public Set<User> getByGroup(Integer groupId) {
        LOGGER.info(String.format("Try to get user list from DB by group with id=%d", groupId));
        Set<User> users = new HashSet<>();
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_USERS_BY_GROUP_SQL)) {
                statement.setInt(1, groupId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        users.add(userMapper.toEntity(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public User getByEmail(String email) {
        Connection connection = connectionManager.getConnection();
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = userMapper.toEntity(resultSet);
                }
            }
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return user;
    }
}
