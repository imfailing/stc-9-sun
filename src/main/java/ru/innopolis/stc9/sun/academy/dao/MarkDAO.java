package ru.innopolis.stc9.sun.academy.dao;

import ru.innopolis.stc9.sun.academy.entity.Mark;

import java.util.Set;

public interface MarkDAO {
    boolean add(Mark mark);
    boolean deleteById(Integer id);
    Set<Mark> getAllByUserId(Integer userId);
    Set<Mark> getAllByLessonId(Integer groupId);
}
