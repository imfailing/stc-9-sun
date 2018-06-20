package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.connection.ConnectionManager;
import ru.innopolis.stc9.sun.academy.dao.mapper.JDBCMapper;
import ru.innopolis.stc9.sun.academy.entity.Mark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component
public class MarkDAOJdbcImpl implements MarkDAO {
    private final ConnectionManager connectionManager;
    private final JDBCMapper<Mark> markMapper;
    private static final Logger LOGGER = Logger.getLogger(MarkDAOJdbcImpl.class);

    static final String INSERT_MARK_SQL = "INSERT INTO marks (lesson_id, user_id, value) VALUES (?, ?, ?) ";
    static final String SELECT_ALL_MARK_BY_USER_ID_SQL = "SELECT * FROM marks m WHERE user_id = ? ORDER BY id DESC";
    static final String DELETE_MARK_SQL = "DELETE FROM marks WHERE id = ?";

    @Autowired
    public MarkDAOJdbcImpl(ConnectionManager connectionManager, JDBCMapper<Mark> markMapper) {
        this.connectionManager = connectionManager;
        this.markMapper = markMapper;
    }

    @Override
    public boolean add(Mark mark) {
        try (Connection connection = connectionManager.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(INSERT_MARK_SQL)) {
                markMapper.toStatement(statement, mark);
                statement.execute();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public Set<Mark> getAllByUserId(Integer userId) {
        Set<Mark> marks = new HashSet<>();
        try (Connection connection = connectionManager.getConnection();) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MARK_BY_USER_ID_SQL)) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        marks.add(markMapper.toEntity(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return marks;
    }

    @Override
    public boolean deleteById(Integer id) {
        int count = 0;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_MARK_SQL)) {
                statement.setInt(1, id);
                count = statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return count > 0;
    }
}
