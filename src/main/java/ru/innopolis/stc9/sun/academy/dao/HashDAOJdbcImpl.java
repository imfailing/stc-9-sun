package ru.innopolis.stc9.sun.academy.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.sun.academy.connection.ConnectionManager;
import ru.innopolis.stc9.sun.academy.dao.mapper.JDBCMapper;
import ru.innopolis.stc9.sun.academy.entity.Hash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component("hashDAOJDBC")
public class HashDAOJdbcImpl implements HashDAO {
    static final String INSERT_HASH_SQL = "INSERT into recover (hash, userid, recovered) VALUES (?, ?, ?)";
    static final String UPDATE_HASH_SQL = "UPDATE recover set recovered=? where userid=?";
    static final String SELECT_HASH_SQL = "SELECT hash,userid,recovered from recover where hash = ? and recovered = 0 order by date desc";

    private final ConnectionManager connectionManager;
    private final JDBCMapper<Hash> hashMapper;
    private static final Logger LOGGER = Logger.getLogger(HashDAOJdbcImpl.class);

    @Autowired
    public HashDAOJdbcImpl(ConnectionManager connectionManager, JDBCMapper<Hash> hashMapper) {
        this.connectionManager = connectionManager;
        this.hashMapper = hashMapper;
    }


    @Override
    public boolean add(Hash hash) {
        try(Connection connection = connectionManager.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(INSERT_HASH_SQL)) {
                hashMapper.toStatement(statement, hash);
                statement.execute();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Hash hash) {
        try(Connection connection = connectionManager.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_HASH_SQL)) {
                statement.setInt(1,hash.getRecovered());
                statement.setInt(2,hash.getUserid());
                statement.execute();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public Hash getByHash(String hashString) {
        Hash hash = null;
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_HASH_SQL)) {
                statement.setString(1, hashString);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        hash = hashMapper.toEntity(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return hash;
    }
}
