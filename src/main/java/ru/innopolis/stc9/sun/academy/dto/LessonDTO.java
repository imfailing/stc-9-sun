package ru.innopolis.stc9.sun.academy.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

public class LessonDTO {

    private Integer id;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @NotNull
    @Size(min = 1, max = 250)
    private String title;

    private String description;

    @NotNull
    private Integer groupId;

    private GroupDTO group;

    public LessonDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonDTO lessonDTO = (LessonDTO) o;
        return Objects.equals(id, lessonDTO.id) &&
                Objects.equals(date, lessonDTO.date) &&
                Objects.equals(title, lessonDTO.title) &&
                Objects.equals(description, lessonDTO.description) &&
                Objects.equals(groupId, lessonDTO.groupId) &&
                Objects.equals(group, lessonDTO.group);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, title, description, groupId, group);
    }

    @Override
    public String toString() {
        return "LessonDTO{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", groupId=" + groupId +
                ", group=" + group +
                '}';
    }
}
