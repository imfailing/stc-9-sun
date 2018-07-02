package ru.innopolis.stc9.sun.academy.dao;

import ru.innopolis.stc9.sun.academy.entity.Lesson;

import java.util.Set;

public interface LessonDAO {
    boolean add(Lesson lesson);
    Lesson getById(Integer id);
    boolean update(Lesson lesson);
    boolean delete(Lesson lesson);
    Set<Lesson> getByGroup(Integer groupId);
}
