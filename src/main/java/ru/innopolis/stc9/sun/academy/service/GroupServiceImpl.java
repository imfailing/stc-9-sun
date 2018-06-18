package ru.innopolis.stc9.sun.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.sun.academy.dao.GroupDAO;
import ru.innopolis.stc9.sun.academy.dto.GroupDTO;
import ru.innopolis.stc9.sun.academy.dto.mapper.GroupMapper;
import ru.innopolis.stc9.sun.academy.entity.Group;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDAO groupDAO;

    @Autowired
    public GroupServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public boolean addGroup(GroupDTO groupDTO) {
        return groupDAO.add(GroupMapper.toEntity(groupDTO));
    }

    @Override
    public boolean updateGroup(GroupDTO groupDTO) { return groupDAO.update(GroupMapper.toEntity(groupDTO)); }

    @Override
    public boolean deleteGroupById(int id) {
        return groupDAO.deleteById(id);
    }

    @Override
    public GroupDTO getGroupById(int id) { return GroupMapper.toDto(groupDAO.getById(id)); }

    @Override
    public GroupDTO getGroupByUserId(int id) {
        GroupDTO groupDTO=null;
        Group group = groupDAO.getByUserId(id);
        if(group!=null){
            groupDTO=GroupMapper.toDto(group);
        }
        return groupDTO;
    }

    @Override
    public Set<GroupDTO> getAllGroups() {
        return groupDAO.getAll().stream().map(GroupMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public boolean addNewMemberToGroup(Integer groupId, Integer userId) {
        return groupDAO.addNewMember(groupId, userId);
    }

    @Override
    public boolean deleteMemberFromGroup(Integer groupId, Integer userId) {
        return groupDAO.deleteGroupMember(groupId, userId);
    }
}
