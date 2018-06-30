package ru.innopolis.stc9.sun.academy.dto.mapper;

import ru.innopolis.stc9.sun.academy.dto.GroupDTO;
import ru.innopolis.stc9.sun.academy.entity.Group;
import java.sql.Date;

public class GroupMapper {
    public GroupMapper() {
    }

    public static GroupDTO toDto(Group group) {
        if (group == null) return null;
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setTitle(group.getTitle());
        groupDTO.setDescription(group.getDescription());
        groupDTO.setStartDate(group.getStartDate());
        groupDTO.setFinishedDate(group.getFinishedDate());
        groupDTO.setActive(group.isActive());
        return groupDTO;
    }

    public static Group toEntity(GroupDTO groupDTO) {
        if (groupDTO == null) return null;
        Group group = new Group();
        group.setId(groupDTO.getId());
        group.setTitle(groupDTO.getTitle());
        group.setDescription(groupDTO.getDescription());
        group.setStartDate(new Date(groupDTO.getStartDate().getTime()));
        group.setFinishedDate(new Date(groupDTO.getFinishedDate().getTime()));
        group.setActive(groupDTO.isActive());
        return group;
    }
}
