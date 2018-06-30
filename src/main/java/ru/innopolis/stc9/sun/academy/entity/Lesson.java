package ru.innopolis.stc9.sun.academy.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @SequenceGenerator(name = "lessonSeq", sequenceName = "lessons_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessonSeq")
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String title;

    private String description;

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "group_id")
    private Group group;

    public Lesson() { }

    public Lesson(Integer id){
        this.id = id;
    }

    public Lesson(Integer id, Date date, String title, String description, Group group) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        this.group = group;
    }

    public Lesson(Date date, String title, String description, Group group) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.group = group;
    }
}