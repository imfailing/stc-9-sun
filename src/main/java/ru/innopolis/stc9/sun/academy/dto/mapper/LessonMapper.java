package ru.innopolis.stc9.sun.academy.dto.mapper;

import ru.innopolis.stc9.sun.academy.dto.LessonDTO;
import ru.innopolis.stc9.sun.academy.entity.Lesson;

public class LessonMapper {

    public LessonMapper() {
    }

    public static LessonDTO toDto(Lesson lesson){
        if (lesson == null) return null;
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(lesson.getId());
        lessonDTO.setDate(lesson.getDate());
        lessonDTO.setTitle(lesson.getTitle());
        lessonDTO.setDescription(lesson.getDescription());
        lessonDTO.setGroupId(lesson.getGroup().getId());
        lessonDTO.setGroup(GroupMapper.toDto(lesson.getGroup()));
        return lessonDTO;
    }

    public static Lesson toEntity(LessonDTO lessonDTO){
        if (lessonDTO == null) return null;
        Lesson lesson = new Lesson();
        lesson.setId(lessonDTO.getId());
        lesson.setDate(lessonDTO.getDate());
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setGroup(GroupMapper.toEntity(lessonDTO.getGroup()));
        return lesson;
    }
}
