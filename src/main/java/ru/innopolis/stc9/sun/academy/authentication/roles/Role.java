package ru.innopolis.stc9.sun.academy.authentication.roles;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Role implements GrantedAuthority {

    private String name;

    public Role() { }

    public Role(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return this.name;
    }
}
