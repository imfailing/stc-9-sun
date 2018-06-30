package ru.innopolis.stc9.sun.academy.dao;

import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.sun.academy.connection.ConnectionManager;
import ru.innopolis.stc9.sun.academy.dao.mapper.GroupJdbcMapper;
import ru.innopolis.stc9.sun.academy.dao.mapper.JDBCMapper;
import ru.innopolis.stc9.sun.academy.entity.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GroupDAOJdbcImplTest {

    private GroupDAOJdbcImpl groupDAO;
    private JDBCMapper<Group> groupMapper;
    private PreparedStatement preparedStatement;
    private Group group;
    private Group groupForExceptionCall;
    private Integer idForException;
    private List<Integer> statementArguments;

    @Before
    public void before() throws SQLException {
        idForException = 23451346;
        group = new Group(1, "Title", "Description",
                Date.valueOf("2018-07-31"), Date.valueOf("2018-08-31"), true);
        groupForExceptionCall = new Group(0);
        statementArguments = new ArrayList<>();
        ConnectionManager connectionManager = mock(ConnectionManager.class);
        groupMapper = mock(GroupJdbcMapper.class);
        when(groupMapper.toEntity(any())).thenReturn(group);
        Connection connection = mock(Connection.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        doAnswer(invocation -> {
            statementArguments.add((Integer) invocation.getArguments()[1]);
            return null;
        }).when(preparedStatement).setInt(anyInt(), anyInt());
        doThrow(new SQLException()).when(preparedStatement).setInt(1, idForException);
        doThrow(new SQLException()).when(preparedStatement).setInt(2, idForException);
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        groupDAO = new GroupDAOJdbcImpl(connectionManager, groupMapper);
        doThrow(new SQLException()).when(groupMapper).toStatement(preparedStatement, groupForExceptionCall);
    }

    @Test
    public void addTestPositive() throws SQLException {
        Boolean result = groupDAO.add(group);
        verify(groupMapper).toStatement(preparedStatement, group);
        verify(preparedStatement).execute();
        assertTrue(result);
    }

    @Test
    public void addTestNegative(){
        assertFalse(groupDAO.add(groupForExceptionCall));
    }

    @Test
    public void updateTestPositive() throws SQLException {
        Boolean result = groupDAO.update(group);
        verify(groupMapper).toStatement(preparedStatement, group);
        verify(preparedStatement).executeUpdate();
        assertTrue(result);
    }

    @Test
    public void updateTestNegative(){
        assertFalse(groupDAO.update(groupForExceptionCall));
    }

    @Test
    public void deleteByIdTestPositive() throws SQLException {
        Boolean result = groupDAO.deleteById(1);
        verify(preparedStatement).executeUpdate();
        assertTrue(result);
    }

    @Test
    public void deleteByIdTestNegative(){
        assertFalse(groupDAO.deleteById(idForException));
    }

    @Test
    public void getByIdTest() throws SQLException {
        Group resultGroup = groupDAO.getById(1);
        assertEquals(resultGroup, group);
    }

    @Test
    public void getByIdTestWithException(){
        Group resultGroup = groupDAO.getById(idForException);
        assertNull(resultGroup);
    }

    @Test
    public void getAllTest() throws SQLException {
        Set<Group> groups = groupDAO.getAll();
        assertTrue(groups.size() > 0);
        assertTrue(groups.contains(group));
    }

    @Test
    public void addNewMemberTestPositive() throws SQLException {
        Integer groupId = 23;
        Integer userId = 32;
        Boolean result = groupDAO.addNewMember(groupId, userId);
        assertTrue(statementArguments.contains(groupId));
        assertTrue(statementArguments.contains(userId));
        assertTrue(statementArguments.size() == 2);
        verify(preparedStatement).execute();
        assertTrue(result);
    }

    @Test
    public void addNewMemberTestNegative(){
        assertFalse(groupDAO.addNewMember(idForException, idForException));
    }

    @Test
    public void deleteGroupMemberTestPositive() throws SQLException {
        Integer groupId = 23;
        Integer userId = 32;
        Boolean result = groupDAO.deleteGroupMember(groupId, userId);
        assertTrue(statementArguments.contains(groupId));
        assertTrue(statementArguments.contains(userId));
        assertTrue(statementArguments.size() == 2);
        verify(preparedStatement).executeUpdate();
        assertTrue(result);
    }

    @Test
    public void deleteGroupMemberNegative(){
        assertFalse(groupDAO.deleteGroupMember(idForException, idForException));
    }
}
