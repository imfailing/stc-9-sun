package ru.innopolis.stc9.sun.academy.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @SequenceGenerator(name = "groupSeq", sequenceName = "group_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupSeq")
    private int id;

    private String title;

    private String description;
    @Temporal(TemporalType.DATE)
    @Column (name = "start_date")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column (name = "finished_date")
    private Date finishedDate;

    @Column (name = "is_active")
    private boolean isActive;

    @ManyToMany
    @JoinTable(name="groups_users")
    private Set<User> users=new HashSet<>();

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private Set<Lesson> lessons;

    public Group() {
    }

    public Group(int id) {
        this.id = id;
    }

    public Group(int id, String title, String description, Date startDate, Date finishedDate, boolean isActive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.finishedDate = finishedDate;
        this.isActive = isActive;
    }

    public Group(String title, String description, Date startDate, Date finishedDate, boolean isActive) {
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
