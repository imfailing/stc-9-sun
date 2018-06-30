package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.connection.ConnectionManager;
import ru.innopolis.stc9.sun.academy.dao.mapper.JDBCMapper;
import ru.innopolis.stc9.sun.academy.entity.Lesson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component("lessonDAOJDBC")
public class LessonDAOJDBCImpl implements LessonDAO {
    private final ConnectionManager connectionManager;
    private final JDBCMapper<Lesson> lessonMapper;
    private static final Logger LOGGER = Logger.getLogger(LessonDAOJDBCImpl.class);

    static final String INSERT_LESSON_SQL = "INSERT INTO \"lessons\" (date, title, description, group_id) VALUES (?, ?, ?, ?)";
    static final String SELECT_LESSON_SQL = "SELECT * FROM \"lessons\" WHERE id = ?";
    static final String UPDATE_LESSON_SQL = "UPDATE \"lessons\" SET date = ?, title = ?, description = ?, group_id = ? WHERE id = ?";
    static final String DELETE_LESSON_SQL = "DELETE FROM \"lessons\" WHERE id = ?";
    static final String SELECT_LESSON_BY_GROUP_SQL = "SELECT * FROM \"lessons\" WHERE group_id=?";

    @Autowired
    public LessonDAOJDBCImpl(ConnectionManager connectionManager, JDBCMapper<Lesson> lessonMapper) {
        this.connectionManager = connectionManager;
        this.lessonMapper = lessonMapper;
    }

    @Override
    public boolean add(Lesson lesson) {
        try (Connection connection = connectionManager.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(INSERT_LESSON_SQL)) {
                lessonMapper.toStatement(statement, lesson);
                statement.execute();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public Lesson getById(Integer id) {
        Lesson lesson = null;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_LESSON_SQL)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        lesson = lessonMapper.toEntity(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return lesson;
    }

    @Override
    public boolean update(Lesson lesson) {
        int count = 0;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_LESSON_SQL)) {
                lessonMapper.toStatement(statement, lesson);
                statement.setInt(5, lesson.getId());
                count = statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return count > 0;
    }

    @Override
    public boolean delete(Lesson lesson) {
        int count = 0;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_LESSON_SQL)) {
                statement.setInt(1, lesson.getId());
                count = statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return count > 0;
    }

    @Override
    public Set<Lesson> getByGroup(Integer groupId) {
        LOGGER.info(String.format("Try to get lesson list from DB by group with id=%d", groupId));
        Set<Lesson> lessons = new HashSet<>();
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_LESSON_BY_GROUP_SQL)) {
                statement.setInt(1, groupId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        lessons.add(lessonMapper.toEntity(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return lessons;
    }
}
