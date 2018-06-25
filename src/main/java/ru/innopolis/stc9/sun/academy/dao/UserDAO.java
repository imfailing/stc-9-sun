package ru.innopolis.stc9.sun.academy.dao;

import ru.innopolis.stc9.sun.academy.entity.User;

import java.util.Set;

public interface UserDAO {
    boolean add(User user);
    User getById(int id);
    Set<User> getAll();
    boolean update(User user);
    boolean delete(User user);
    Set<User> getByGroup(Integer groupId);
    User getByEmail(String email);
}
