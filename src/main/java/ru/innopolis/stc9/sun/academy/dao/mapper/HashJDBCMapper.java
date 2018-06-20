package ru.innopolis.stc9.sun.academy.dao.mapper;


import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.entity.Hash;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HashJDBCMapper implements JDBCMapper<Hash> {

    public HashJDBCMapper() {
    }


    @Override
    public Hash toEntity(ResultSet resultSet) throws SQLException {
        return new Hash(
                resultSet.getString("hash"),
                resultSet.getInt("userid"),
                resultSet.getInt("recovered")
        );
    }

    @Override
    public void toStatement(PreparedStatement statement, Hash hash) throws SQLException {
        statement.setInt(1,hash.getRecovered());
        statement.setInt(2,hash.getUserid());
        statement.setString(3,hash.getHash());
    }
}
