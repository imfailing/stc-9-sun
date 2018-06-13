package ru.innopolis.stc9.sun.academy.dto.mapper;

import ru.innopolis.stc9.sun.academy.dto.GroupDTO;
import ru.innopolis.stc9.sun.academy.entity.Group;

public class GroupMapper {
    public GroupMapper() {
    }

    public static GroupDTO toDto(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setTitle(group.getTitle());
        groupDTO.setDescription(group.getDescription());
        groupDTO.setStart_date(group.getStart_date());
        groupDTO.setFinished_date(group.getFinished_date());
        groupDTO.setIs_active(group.isIs_active());
        return groupDTO;
    }

    public static Group toEntity(GroupDTO groupDTO) {
        return new Group(groupDTO.getId(), groupDTO.getTitle(), groupDTO.getDescription(), groupDTO.getStart_date(), groupDTO.getFinished_date(), groupDTO.isIs_active());
    }
}
