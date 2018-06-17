package ru.innopolis.stc9.sun.academy.service;

import ru.innopolis.stc9.sun.academy.dto.LessonDTO;
import java.util.Set;

public interface LessonService {
    boolean addLesson(LessonDTO lessonDTO);
    LessonDTO getLessonById(Integer id);
    boolean updateLesson(LessonDTO lessonDTO);
    boolean deleteLesson(Integer id);
    Set<LessonDTO> getLessonsByGroup(Integer groupId);
}
