package ru.innopolis.stc9.sun.academy.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class GroupDTO {
    private int id;

    @NotNull
    @Size(min=2, max=25)
    private String title;

    @Size(min=0, max=240)
    private String description;

    @NotNull
    @Size(min=2, max=25)
    private String start_date;

    @NotNull
    @Size(min=2, max=25)
    private String finished_date;

    private boolean is_active = true;

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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getFinished_date() {
        return finished_date;
    }

    public void setFinished_date(String finished_date) {
        this.finished_date = finished_date;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO groupDTO = (GroupDTO) o;
        return id == groupDTO.id &&
                is_active == groupDTO.is_active &&
                Objects.equals(title, groupDTO.title) &&
                Objects.equals(description, groupDTO.description) &&
                Objects.equals(start_date, groupDTO.start_date) &&
                Objects.equals(finished_date, groupDTO.finished_date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, start_date, finished_date, is_active);
    }

    @Override
    public String toString() {
        return String.format(
                "GroupDTO{id=%d, title='%s', description='%s', start_date='%s', finished_date='%s', is_active=%s}",
                id, title, description, start_date, finished_date, is_active
        );
    }
}