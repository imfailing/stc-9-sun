package ru.innopolis.stc9.sun.academy.dao.mapper;

import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.sun.academy.entity.Role;
import ru.innopolis.stc9.sun.academy.entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserJdbcMapperTest {
    private UserJdbcMapper userJdbcMapper;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private User user;

    @Before
    public void before() throws SQLException {
        userJdbcMapper = new UserJdbcMapper();
        resultSet = mock(ResultSet.class);
        String roleName = "Role";
        Role role = new Role();
        role.setName(roleName);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user = new User(1, "First Name", "Last Name", "Patronymic",
                "E-Mail", "Password", true, roles);
        when(resultSet.getInt("id")).thenReturn(user.getId());
        when(resultSet.getString("firstname")).thenReturn(user.getFirstName());
        when(resultSet.getString("lastname")).thenReturn(user.getLastName());
        when(resultSet.getString("patronymic")).thenReturn(user.getPatronymic());
        when(resultSet.getString("email")).thenReturn(user.getEmail());
        when(resultSet.getString("password")).thenReturn(user.getPassword());
        when(resultSet.getBoolean("is_active")).thenReturn(user.getActive());
        when(resultSet.getString("roles")).thenReturn(roleName);
        preparedStatement = mock(PreparedStatement.class);
    }

    @Test
    public void toEntityTest() throws SQLException {
        User resultUser = userJdbcMapper.toEntity(resultSet);
        assertEquals(resultUser.getId(), user.getId());
        assertEquals(resultUser.getPassword(), user.getPassword());
        assertEquals(resultUser.getEmail(), user.getEmail());
        assertEquals(resultUser.getFirstName(), user.getFirstName());
        assertEquals(resultUser.getLastName(), user.getLastName());
        assertEquals(resultUser.getActive(), user.getActive());
        assertEquals(resultUser.getPatronymic(), user.getPatronymic());
        assertEquals(resultUser.getRoles(), user.getRoles());
    }

    @Test
    public void toStatementTest() throws SQLException {
        userJdbcMapper.toStatement(preparedStatement, user);
        verify(preparedStatement).setString(1, user.getFirstName());
        verify(preparedStatement).setString(2, user.getLastName());
        verify(preparedStatement).setString(3, user.getPatronymic());
        verify(preparedStatement).setString(4, user.getEmail());
        verify(preparedStatement).setString(5, user.getPassword());
        verify(preparedStatement).setBoolean(6, user.getActive());
    }
}
