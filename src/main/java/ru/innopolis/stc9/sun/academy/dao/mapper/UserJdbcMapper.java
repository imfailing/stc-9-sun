package ru.innopolis.stc9.sun.academy.dao.mapper;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserJdbcMapper implements JDBCMapper<User> {
    UserJdbcMapper () {
    }

    @Override
    public User toEntity(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getString("patronymic"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getBoolean("is_active"),
                resultSet.getString("roles")
        );
    }

    public void toStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getPatronymic());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        statement.setBoolean(6, user.getActive());
    }
}
