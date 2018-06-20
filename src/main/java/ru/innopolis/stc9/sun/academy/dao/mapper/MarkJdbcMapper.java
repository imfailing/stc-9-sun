package ru.innopolis.stc9.sun.academy.dao.mapper;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.entity.Mark;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MarkJdbcMapper implements JDBCMapper<Mark> {
    @Override
    public Mark toEntity(ResultSet resultSet) throws SQLException {
        return new Mark(
                resultSet.getInt("id"),
                resultSet.getInt("lesson_id"),
                resultSet.getInt("user_id"),
                resultSet.getInt("value")
        );
    }

    public void toStatement(PreparedStatement statement, Mark mark) throws SQLException {
        statement.setInt(1, mark.getLessonId());
        statement.setInt(2, mark.getUserId());
        statement.setInt(3, mark.getValue());
    }
}
