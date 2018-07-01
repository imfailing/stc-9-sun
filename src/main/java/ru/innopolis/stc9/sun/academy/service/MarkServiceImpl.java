package ru.innopolis.stc9.sun.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.sun.academy.dao.GroupDAO;
import ru.innopolis.stc9.sun.academy.dao.LessonDAO;
import ru.innopolis.stc9.sun.academy.dao.MarkDAO;
import ru.innopolis.stc9.sun.academy.dao.UserDAO;
import ru.innopolis.stc9.sun.academy.dto.MarkDTO;
import ru.innopolis.stc9.sun.academy.dto.mapper.MarkMapper;
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
    public Map<User, Mark> getMarksWithUserByLessonIdAndGroupId(Integer lessonId, Integer groupId) {
        Map<User, Mark> map = new HashMap<>();
        Set<User> users = userDAO.getByGroup(groupId);
        Set<Mark> marks = markDAO.getAllByLessonId(lessonId);
        users.forEach(
                (user) -> marks.forEach(
                        (mark) -> map.put(user, user.getId().equals(mark.getUserId()) ? mark : null)
                )
        );
        return map;
    }
}
