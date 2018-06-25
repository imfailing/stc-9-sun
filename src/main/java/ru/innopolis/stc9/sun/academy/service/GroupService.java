package ru.innopolis.stc9.sun.academy.service;

import ru.innopolis.stc9.sun.academy.dto.GroupDTO;

import java.util.Set;

public interface GroupService {
    boolean addGroup(GroupDTO groupDTO);
    boolean updateGroup(GroupDTO groupDTO);
    boolean deleteGroupById(int id);
    GroupDTO getGroupById(int id);
    Set<GroupDTO> getGroupsByUserId(int id);
    Set<GroupDTO> getAllGroups();
    boolean addNewMemberToGroup(Integer groupId, Integer userId);
    boolean deleteMemberFromGroup(Integer groupId, Integer userId);
}