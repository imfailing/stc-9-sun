package ru.innopolis.stc9.sun.academy.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class Role implements GrantedAuthority {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
