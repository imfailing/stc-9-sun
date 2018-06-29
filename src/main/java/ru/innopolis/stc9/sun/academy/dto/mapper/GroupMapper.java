package ru.innopolis.stc9.sun.academy.dto.mapper;

import ru.innopolis.stc9.sun.academy.dto.GroupDTO;
import ru.innopolis.stc9.sun.academy.entity.Group;

public class GroupMapper {
    public GroupMapper() {
    }

    public static GroupDTO toDto(Group group) {
        if (group == null) return null;
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setTitle(group.getTitle());
        groupDTO.setDescription(group.getDescription());
        groupDTO.setStart_date(group.getStartDate());
        groupDTO.setFinished_date(group.getFinishedDate());
        groupDTO.setIs_active(group.isActive());
        return groupDTO;
    }

    public static Group toEntity(GroupDTO groupDTO) {
        if (groupDTO == null) return null;
        return new Group(groupDTO.getId(), groupDTO.getTitle(), groupDTO.getDescription(), groupDTO.getStart_date(), groupDTO.getFinished_date(), groupDTO.isIs_active());
    }
}
