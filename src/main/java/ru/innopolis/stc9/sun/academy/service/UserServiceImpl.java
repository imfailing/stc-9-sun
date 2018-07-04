package ru.innopolis.stc9.sun.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.sun.academy.authentication.roles.Role;
import ru.innopolis.stc9.sun.academy.dao.UserDAO;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.dto.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAOHibernate") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Set<UserDTO> getUsers() {
        return userDAO.getAll().stream().map(UserMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_USER"));
        userDTO.setRoles(roles);
        return userDAO.add(UserMapper.toEntity(userDTO));
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return UserMapper.toDto(userDAO.getById(id));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.update(UserMapper.toEntity(userDTO));
    }

    @Override
    public Set<UserDTO> getUsersByGroup(Integer groupId) {
        return userDAO.getByGroup(groupId).stream().map(UserMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public UserDTO getUserByEmail(String email) {return UserMapper.toDto(userDAO.getByEmail(email));}
}
