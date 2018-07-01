package ru.innopolis.stc9.sun.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.sun.academy.dao.GroupDAO;
import ru.innopolis.stc9.sun.academy.dao.LessonDAO;
import ru.innopolis.stc9.sun.academy.dao.MarkDAO;
import ru.innopolis.stc9.sun.academy.dao.UserDAO;
import ru.innopolis.stc9.sun.academy.dto.MarkDTO;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.dto.mapper.MarkMapper;
import ru.innopolis.stc9.sun.academy.dto.mapper.UserMapper;
import ru.innopolis.stc9.sun.academy.entity.Lesson;
import ru.innopolis.stc9.sun.academy.entity.Mark;
import ru.innopolis.stc9.sun.academy.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    private MarkDAO markDAO;

    @Autowired
    private LessonDAO lessonDAO;

    @Qualifier("groupDAOHibernateImpl")
    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    @Qualifier("userDAOHibernate")
    private UserDAO userDAO;

    @Override
    public boolean addMark(MarkDTO markDTO) {
        return markDAO.add(MarkMapper.toEntity(markDTO));
    }

    @Override
    public boolean deleteMarkById(Integer id) {
        return markDAO.deleteById(id);
    }

    @Override
    public Set<MarkDTO> getAllMarksByUserId(Integer userId) {
        Set<Mark> marks = markDAO.getAllByUserId(userId);
        for (Mark mark : marks) {
            Lesson lesson = lessonDAO.getById(mark.getLessonId());
            lesson.setGroup(groupDAO.getById(lesson.getGroupId()));
            mark.setLesson(lesson);
            mark.setUser(userDAO.getById(mark.getUserId()));
        }
        return marks.stream().map(MarkMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public Map<UserDTO, MarkDTO> getMarksWithUserByLessonIdAndGroupId(Integer lessonId, Integer groupId) {
        Map<UserDTO, MarkDTO> map = new HashMap<>();
        Set<UserDTO> users = userDAO
                .getByGroup(groupId)
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toSet());
        Set<MarkDTO> marks = markDAO
                .getAllByLessonId(lessonId)
                .stream()
                .map(MarkMapper::toDto)
                .collect(Collectors.toSet());
        users.forEach(
                (user) -> {
                    map.put(user, null);
                    marks.forEach(
                            (mark) -> {
                                if (user.getId().equals(mark.getUser().getId())) map.put(user, mark);
                            });
                }
        );
        return map;
    }
}
