package ru.innopolis.stc9.sun.academy.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private String patronymic;

    private String roles;

    private String email;

    private String password;

    @Column(name = "is_active")
    private Boolean active = true;

    public User() { }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String firstName, String lastName, String patronymic, String email, String password,
                Boolean isActive, String roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
        this.roles = roles;
        active = isActive;
    }

    public User(Integer id, String firstName, String lastName, String patronymic, String email, String password,
                Boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
        active = isActive;
    }

    public User(String firstName, String lastName, String patronymic, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
    }
}
