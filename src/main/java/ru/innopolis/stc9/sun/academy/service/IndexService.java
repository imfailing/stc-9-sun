package ru.innopolis.stc9.sun.academy.service;

import ru.innopolis.stc9.sun.academy.dto.UserDTO;

public interface IndexService {
    boolean checkAuth(UserDTO userDTO);
}
