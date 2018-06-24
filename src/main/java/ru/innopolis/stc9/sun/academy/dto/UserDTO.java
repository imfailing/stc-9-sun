package ru.innopolis.stc9.sun.academy.dto;

import ru.innopolis.stc9.sun.academy.authentication.roles.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class UserDTO {
    private Integer id;

    @NotNull
    @Size(min=2, max=25)
    private String firstName;

    @NotNull
    @Size(min=2, max=25)
    private String lastName;

    @NotNull
    @Size(min=2, max=25)
    private String patronymic;

    @Email
    @NotNull
    @Size(min=5, max=25)
    private String email;

    @NotNull
    @Size(min=7, max=25)
    private String password;

    private Boolean isActive = true;

    private List<Role> roles;

    public UserDTO() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFullName() {
        return String.format("%s %s %s", lastName, firstName, patronymic);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Role> getRoles() { return roles;}

    public void setRoles(List<Role> roles) {this.roles = roles;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
                "UserDTO{id=%d, firstName='%s', lastName='%s', patronymic='%s', email='%s', password='*****', isActive=%s}",
                id, firstName, lastName, patronymic, email, isActive
        );
    }
}
