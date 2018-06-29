package ru.innopolis.stc9.sun.academy.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@DynamicUpdate
@Table(name = "groups")
public class Group {
    @Id
    @SequenceGenerator(name = "groupSeq", sequenceName = "group_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupSeq")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name="description")
    private String description;
    @Column (name = "start_date")
    private String startDate;
    @Column (name = "finished_date")
    private String finishedDate;
    @Column (name = "is_active")
    private boolean isActive;
    @ManyToMany
    @JoinTable(name="groups_users")
    private Set<User> users=new HashSet<>();

    public Group() {
    }

    public Group(int id) {
        this.id = id;
    }

    public Group(int id, String title, String description, String startDate, String finishedDate, boolean isActive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.finishedDate = finishedDate;
        this.isActive = isActive;
    }

    public Group(String title, String description, String startDate, String finishedDate, boolean isActive) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.finishedDate = finishedDate;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return id == group.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
