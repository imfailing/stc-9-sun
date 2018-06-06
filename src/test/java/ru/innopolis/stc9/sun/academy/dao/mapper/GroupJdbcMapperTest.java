package ru.innopolis.stc9.sun.academy.dao.mapper;

import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.sun.academy.entity.Group;
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
                "Start Date", "Finish Date", true);
        when(resultSet.getInt("id")).thenReturn(group.getId());
        when(resultSet.getString("title")).thenReturn(group.getTitle());
        when(resultSet.getString("description")).thenReturn(group.getDescription());
        when(resultSet.getString("start_date")).thenReturn(group.getStart_date());
        when(resultSet.getString("finished_date")).thenReturn(group.getFinished_date());
        when(resultSet.getBoolean("is_active")).thenReturn(group.isIs_active());
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
        verify(preparedStatement).setString(3, group.getStart_date());
        verify(preparedStatement).setString(4, group.getFinished_date());
        verify(preparedStatement).setBoolean(5, group.isIs_active());
    }
}
