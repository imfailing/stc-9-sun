package ru.innopolis.stc9.sun.academy.dto.mapper;

import ru.innopolis.stc9.sun.academy.dto.MarkDTO;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.entity.Mark;

public class MarkMapper {
    MarkMapper() {
    }

    public static MarkDTO toDto(Mark mark) {
        if (mark != null) {
            MarkDTO markDTO = new MarkDTO();
            markDTO.setId(mark.getId());
            markDTO.setLessonId(mark.getLessonId());
            markDTO.setLesson(LessonMapper.toDto(mark.getLesson()));
            markDTO.setUserId(mark.getUserId());
            markDTO.setUser(mark.getUser()==null?new UserDTO(mark.getUserId()):UserMapper.toDto(mark.getUser()));
            markDTO.setValue(mark.getValue());
            return markDTO;
        } else {
            return null;
        }
    }

    public static Mark toEntity(MarkDTO markDTO) {
        if (markDTO != null) {
            Mark mark = new Mark(markDTO.getId());
            mark.setLesson(LessonMapper.toEntity(markDTO.getLesson()));
            mark.setLessonId(markDTO.getLessonId());
            mark.setUser(UserMapper.toEntity(markDTO.getUser()));
            mark.setUserId(markDTO.getUserId());
            mark.setValue(markDTO.getValue());
            return mark;
        } else {
            return null;
        }
    }
}
