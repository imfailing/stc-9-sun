package ru.innopolis.stc9.sun.academy.dao.mapper;

import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.sun.academy.entity.Group;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GroupJdbcMapperTest {

    private GroupJdbcMapper groupJdbcMapper;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Group group;

    @Before
    public void before() throws SQLException {
        groupJdbcMapper = new GroupJdbcMapper();
        resultSet = mock(ResultSet.class);
        group = new Group(1, "Title", "Description",
                Date.valueOf("2018-07-31"), Date.valueOf("2018-08-31"), true);
        when(resultSet.getInt("id")).thenReturn(group.getId());
        when(resultSet.getString("title")).thenReturn(group.getTitle());
        when(resultSet.getString("description")).thenReturn(group.getDescription());
        when(resultSet.getDate("start_date")).thenReturn(new Date(group.getStartDate().getTime()));
        when(resultSet.getDate("finished_date")).thenReturn(new Date(group.getFinishedDate().getTime()));
        when(resultSet.getBoolean("is_active")).thenReturn(group.isActive());
        preparedStatement = mock(PreparedStatement.class);
    }

    @Test
    public void toEntityTest() throws SQLException {
        Group resultGroup = groupJdbcMapper.toEntity(resultSet);
        assertEquals(resultGroup, group);
    }

    @Test
    public void toStatementTest() throws SQLException {
        groupJdbcMapper.toStatement(preparedStatement, group);
        verify(preparedStatement).setString(1, group.getTitle());
        verify(preparedStatement).setString(2, group.getDescription());
        verify(preparedStatement).setDate(3, new Date(group.getStartDate().getTime()));
        verify(preparedStatement).setDate(4, new Date(group.getFinishedDate().getTime()));
        verify(preparedStatement).setBoolean(5, group.isActive());
    }
}
