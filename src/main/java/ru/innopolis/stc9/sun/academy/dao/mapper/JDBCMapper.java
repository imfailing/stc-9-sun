package ru.innopolis.stc9.sun.academy.dao.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JDBCMapper<T> {
    T toEntity(ResultSet resultSet) throws SQLException;
    void toStatement(PreparedStatement statement, T entity) throws SQLException;
}
