package ru.innopolis.stc9.sun.academy.dto;

import javax.validation.constraints.NotNull;

public class MarkDTO {
    private Integer id;

    @NotNull
    private LessonDTO lesson;

    @NotNull
    private UserDTO user;

    private Integer value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LessonDTO getLesson() {
        return lesson;
    }

    public void setLesson(LessonDTO lesson) {
        this.lesson = lesson;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MarkDTO{" +
                "id=" + id +
                ", lesson=" + lesson +
                ", user=" + user +
                ", value=" + value +
                '}';
    }
}
