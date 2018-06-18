package ru.innopolis.stc9.sun.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.sun.academy.dao.UserDAO;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;

@Service
public class IndexServiceImpl implements IndexService{
    private final UserDAO userDAO;

    @Autowired
    public IndexServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean checkAuth(UserDTO userDTO) {
        UserDAO userDAO = null;
        return (userDAO.getByEmail(userDTO.getEmail()) != null) && (userDAO.getByEmail(userDTO.getEmail()).getPassword().equals(userDTO.getPassword()));
    }
}
