package ru.innopolis.stc9.sun.academy.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MarkDTO {
    private Integer id;

    @NotNull
    private Integer lessonId;

    private LessonDTO lesson;

    @NotNull
    private Integer userId;

    private UserDTO user;

    private Integer value;
}
