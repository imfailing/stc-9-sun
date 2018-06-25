package ru.innopolis.stc9.sun.academy.dto.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.innopolis.stc9.sun.academy.authentication.roles.Role;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.entity.User;
import java.util.List;

public class UserMapper {
    UserMapper() { }

    public static UserDTO toDto(User user) {
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setPatronymic(user.getPatronymic());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTO.setActive(user.getActive());
            Gson gson = new GsonBuilder().create();
            List<Role> roles = gson.fromJson(user.getRoles(), new TypeToken<List<Role>>(){}.getType());
            userDTO.setRoles(roles);
            return userDTO;
        } else {
            return null;
        }
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO != null) {
            User user = new User(userDTO.getId());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPatronymic(userDTO.getPatronymic());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setActive(userDTO.getActive());
            Gson gson = new GsonBuilder().create();
            user.setRoles(gson.toJson(userDTO.getRoles()));
            return user;
        } else {
            return null;
        }
    }
}
