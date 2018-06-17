package ru.innopolis.stc9.sun.academy.dao.mapper;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.entity.Lesson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LessonJDBCMapper implements JDBCMapper<Lesson> {

    @Override
    public Lesson toEntity(ResultSet resultSet) throws SQLException {
        if(resultSet == null) return null;
        Lesson lesson = new Lesson();
        lesson.setId(resultSet.getInt("id"));
        lesson.setTitle(resultSet.getString("title"));
        lesson.setDescription(resultSet.getString("description"));
        lesson.setDate(resultSet.getDate("date"));
        lesson.setGroupId(resultSet.getInt("group_id"));
        return lesson;
    }

    @Override
    public void toStatement(PreparedStatement statement, Lesson lesson) throws SQLException {
        statement.setDate(1, lesson.getDate());
        statement.setString(2, lesson.getTitle());
        statement.setString(3, lesson.getDescription());
        statement.setInt(4, lesson.getGroupId());
    }
}
