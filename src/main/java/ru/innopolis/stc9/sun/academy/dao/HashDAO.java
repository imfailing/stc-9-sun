package ru.innopolis.stc9.sun.academy.dao;

import ru.innopolis.stc9.sun.academy.entity.Hash;

public interface HashDAO {
    boolean add (Hash hash);
    boolean update(Hash hash);
    Hash getByHash(String hash);
}
