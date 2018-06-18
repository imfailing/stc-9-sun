package ru.innopolis.stc9.sun.academy.service;

import ru.innopolis.stc9.sun.academy.dto.UserDTO;

import java.util.Set;

public interface UserService {
    Set<UserDTO> getUsers();

    boolean addUser(UserDTO userDTO);

    UserDTO getUserById(Integer id);

    boolean updateUser(UserDTO userDTO);

    Set<UserDTO> getUsersByGroup(Integer groupId);

    UserDTO getUserByEmail(String email);
}
