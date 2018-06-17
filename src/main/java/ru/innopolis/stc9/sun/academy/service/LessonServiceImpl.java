package ru.innopolis.stc9.sun.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.sun.academy.dao.LessonDAO;
import ru.innopolis.stc9.sun.academy.dto.LessonDTO;
import ru.innopolis.stc9.sun.academy.dto.mapper.LessonMapper;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService{

    private final LessonDAO lessonDAO;

    @Autowired
    public LessonServiceImpl(LessonDAO lessonDAO) {
        this.lessonDAO = lessonDAO;
    }

    @Override
    public boolean addLesson(LessonDTO lessonDTO) {
        return lessonDAO.add(LessonMapper.toEntity(lessonDTO));
    }

    @Override
    public LessonDTO getLessonById(Integer id) {
        return LessonMapper.toDto(lessonDAO.getById(id));
    }

    @Override
    public boolean updateLesson(LessonDTO lessonDTO) {
        return lessonDAO.update(LessonMapper.toEntity(lessonDTO));
    }

    @Override
    public boolean deleteLesson(Integer id) {
        return lessonDAO.deleteById(id);
    }

    @Override
    public Set<LessonDTO> getLessonsByGroup(Integer groupId) {
        return lessonDAO.getByGroup(groupId).stream().map(LessonMapper::toDto).collect(Collectors.toSet());
    }
}
