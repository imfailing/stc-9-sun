package ru.innopolis.stc9.sun.academy.service;

import ru.innopolis.stc9.sun.academy.dto.HashDTO;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;

public interface RecoverService {
    boolean sendMail(UserDTO userDTO, HashDTO hashDTOsh);
    boolean makeHash(UserDTO userDTO);
    UserDTO passRecovery(String hash);
    HashDTO getHashByHash(String hash);
    boolean setPassword(UserDTO userDTO);
}
