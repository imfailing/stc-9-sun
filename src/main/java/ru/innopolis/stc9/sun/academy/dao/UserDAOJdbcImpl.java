package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.connection.ConnectionManager;
import ru.innopolis.stc9.sun.academy.dao.mapper.UserJdbcMapper;
import ru.innopolis.stc9.sun.academy.entity.Role;
import ru.innopolis.stc9.sun.academy.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDAOJdbcImpl implements UserDAO {
    private final ConnectionManager connectionManager;
    private static final Logger LOGGER = Logger.getLogger(UserDAOJdbcImpl.class);

    static final String INSERT_USER_SQL = "INSERT INTO \"user\" (firstname, lastname, patronymic, email, password, is_active) VALUES (?, ?, ?, ?, ?, ?) ";
    static final String SELECT_USER_SQL = "SELECT * FROM \"user\" WHERE id = ?";
    static final String SELECT_ALL_USERS_SQL = "SELECT id, firstname, lastname, patronymic FROM \"user\" ORDER BY id";
    static final String UPDATE_USER_SQL = "UPDATE \"user\" SET firstname = ?, lastname = ?, patronymic = ?, email = ?, password = ?, is_active = ? WHERE id = ?";
    static final String DELETE_USER_SQL = "DELETE FROM \"user\" WHERE id = ?";
    static final String SELECT_USER_SQL_EMAIL = "SELECT * FROM \"user\" WHERE email = ?";

    @Autowired
    public UserDAOJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean add(User user) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL)) {
            UserJdbcMapper.toStatement(statement, user);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return true;
    }

    @Override
    public User getById(int id) {
        Connection connection = connectionManager.getConnection();
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = UserJdbcMapper.toUser(resultSet);
                }
            }
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return user;
    }

    @Override
    public Set<User> getAll() {
        Set<User> users = new HashSet<>();
        Connection connection = connectionManager.getConnection();
        try {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_SQL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        users.add(UserJdbcMapper.toUser(resultSet));
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return users;
    }

    @Override
    public boolean update(User user) {
        Connection connection = connectionManager.getConnection();
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            UserJdbcMapper.toStatement(statement, user);
            statement.setInt(7, user.getId());
            count = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return count > 0;
    }

    @Override
    public boolean deleteById(int id) {
        Connection connection = connectionManager.getConnection();
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setInt(1, id);
            count = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return count > 0;
    }

    @Override
    public User getByEmail(String email) {
        Connection connection = connectionManager.getConnection();
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = UserJdbcMapper.toUser(resultSet);
                    List<Role> roles = new ArrayList<>();
                    Role role = new Role();
                    role.setName("ROLE_ADMIN");
                    roles.add(role);
                    user.setRoles(roles);
                }
            }
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return user;
    }
}
