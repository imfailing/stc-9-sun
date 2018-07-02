package ru.innopolis.stc9.sun.academy.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class GroupDTO {
    private int id;

    @NotNull
    @Size(min=2, max=25)
    private String title;

    @Size(min=0, max=240)
    private String description;

    @NotNull
    private Date startDate;

    @NotNull
    private Date finishedDate;

    private boolean active = true;

    public GroupDTO() {
    }

    public GroupDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDescription(String decription) {
        this.description = decription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO groupDTO = (GroupDTO) o;
        return id == groupDTO.id &&
                active == groupDTO.active &&
                Objects.equals(title, groupDTO.title) &&
                Objects.equals(description, groupDTO.description) &&
                Objects.equals(startDate, groupDTO.startDate) &&
                Objects.equals(finishedDate, groupDTO.finishedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, startDate, finishedDate, active);
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}