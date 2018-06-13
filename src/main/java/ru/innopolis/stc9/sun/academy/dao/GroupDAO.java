package ru.innopolis.stc9.sun.academy.dao;

import ru.innopolis.stc9.sun.academy.entity.Group;

import java.util.Set;

public interface GroupDAO {
    boolean add(Group group);
    boolean update(Group group);
    boolean deleteById(int id);
    Group getById(int id);
    Set<Group> getAll();
    boolean addNewMember(Integer groupId, Integer userId);
    boolean deleteGroupMember(Integer groupId, Integer userId);
}