package ru.innopolis.stc9.sun.academy.entity;

import java.sql.Date;
import java.util.Objects;

public class Lesson {

    private Integer id;
    private Date date;
    private String title;
    private String description;
    private Integer groupId;
    private Group group;

    public Lesson() {
    }

    public Lesson(Integer id, Date date, String title, String description, Integer groupId) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        this.groupId = groupId;
    }

    public Lesson(Integer id, Date date, String title, String description, Group group) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        this.group = group;
    }

    public Lesson(Date date, String title, String description, Integer groupId) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.groupId = groupId;
    }

    public Lesson(Date date, String title, String description, Group group) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
        if (group != null) {
            groupId = group.getId();
        } else {
            groupId = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id) &&
                Objects.equals(date, lesson.date) &&
                Objects.equals(title, lesson.title) &&
                Objects.equals(description, lesson.description) &&
                Objects.equals(groupId, lesson.groupId) &&
                Objects.equals(group, lesson.group);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, title, description, groupId, group);
    }
}
