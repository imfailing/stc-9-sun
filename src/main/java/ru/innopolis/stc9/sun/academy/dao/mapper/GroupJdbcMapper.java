package ru.innopolis.stc9.sun.academy.dao.mapper;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.entity.Group;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GroupJdbcMapper implements JDBCMapper<Group> {

    public GroupJdbcMapper() {
    }

    @Override
    public Group toEntity(ResultSet resultSet) throws SQLException {
        return new Group(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getDate("start_date"),
                resultSet.getDate("finished_date"),
                resultSet.getBoolean("is_active")
        );
    }

    @Override
    public void toStatement(PreparedStatement statement, Group group) throws SQLException {
        statement.setString(1, group.getTitle());
        statement.setString(2, group.getDescription());
        statement.setDate(3, new Date(group.getStartDate().getTime()));
        statement.setDate(4, new Date(group.getFinishedDate().getTime()));
        statement.setBoolean(5, group.isActive());
    }
}
