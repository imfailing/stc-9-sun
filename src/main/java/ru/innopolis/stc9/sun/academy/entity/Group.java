package ru.innopolis.stc9.sun.academy.entity;

public class Group {

    private int id;
    private String title;
    private String description;
    private String start_date;
    private String finished_date;
    private boolean is_active;

    public Group(int id) {
        this.id = id;
    }

    public Group(int id, String title, String decription, String start_date, String finished_date, boolean is_active) {
        this.id = id;
        this.title = title;
        this.description = decription;
        this.start_date = start_date;
        this.finished_date = finished_date;
        this.is_active = is_active;
    }

    public Group(String title, String decription, String start_date, String finished_date, boolean is_active) {
        this.title = title;
        this.description = decription;
        this.start_date = start_date;
        this.finished_date = finished_date;
        this.is_active = is_active;
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

        Group group = (Group) o;

        if (id != group.id) return false;
        if (is_active != group.is_active) return false;
        if (title != null ? !title.equals(group.title) : group.title != null) return false;
        if (description != null ? !description.equals(group.description) : group.description != null) return false;
        if (start_date != null ? !start_date.equals(group.start_date) : group.start_date != null) return false;
        return finished_date != null ? finished_date.equals(group.finished_date) : group.finished_date == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (start_date != null ? start_date.hashCode() : 0);
        result = 31 * result + (finished_date != null ? finished_date.hashCode() : 0);
        result = 31 * result + (is_active ? 1 : 0);
        return result;
    }
}
