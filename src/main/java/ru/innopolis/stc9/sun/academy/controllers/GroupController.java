package ru.innopolis.stc9.sun.academy.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc9.sun.academy.dto.GroupDTO;
import ru.innopolis.stc9.sun.academy.service.GroupService;
import ru.innopolis.stc9.sun.academy.service.UserService;
import javax.validation.Valid;
import java.util.Set;

@Controller
public class GroupController {

    private static final String TITLE_ATTRIBUTE_NAME = "title";
    private static final String TITLE = "Управление группами";
    private static final String GROUPS_ATTRIBUTE_NAME = "groups";
    private static final String GROUPS_VIEW_NAME = "groups";
    private static final String GROUP_ATTRIBUTE_NAME = "group";
    private static final String GROUP_VIEW_NAME = "group";
    private static final String TO_GROUP_REDIRECT = "redirect:/group/";
    private static final String TO_GROUPS_REDIRECT = "redirect:/groups";

    private final GroupService groupService;
    private final UserService userService;

    private static final Logger LOGGER = Logger.getLogger(GroupController.class);

    @Autowired
    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping("/groups")
    public String groups(ModelMap model) {
        LOGGER.info("Received GET request to show all groups");
        Set<GroupDTO> groups = groupService.getAllGroups();
        model.addAttribute(TITLE_ATTRIBUTE_NAME, TITLE);
        model.addAttribute(GROUPS_ATTRIBUTE_NAME, groups);
        LOGGER.info(String.format("Show %s view.", GROUPS_VIEW_NAME));
        return GROUPS_VIEW_NAME;

    }

    @GetMapping("/group")
    public String newGroup(ModelMap model) {
        LOGGER.info("Received GET request to add new group");
        model.addAttribute(GROUP_ATTRIBUTE_NAME, new GroupDTO(0));
        model.addAttribute(TITLE_ATTRIBUTE_NAME, TITLE);
        LOGGER.info(String.format("Show %s view.", GROUP_VIEW_NAME));
        return GROUP_VIEW_NAME;
    }

    @GetMapping("/group/{id}")
    public String updateGroup(@PathVariable Integer id, ModelMap model) {
        LOGGER.info(String.format("Received GET request to show the group with id = %d.", id));
        model.addAttribute("members", userService.getUsersByGroup(id));
        model.addAttribute(GROUP_ATTRIBUTE_NAME, groupService.getGroupById(id));
        model.addAttribute(TITLE_ATTRIBUTE_NAME, TITLE);
        model.addAttribute("users", userService.getUsers());
        LOGGER.info(String.format("Show %s view.", GROUP_VIEW_NAME));
        return GROUP_VIEW_NAME;
    }

    @PostMapping("/group")
    public String newGroup(@Valid @ModelAttribute(GROUP_ATTRIBUTE_NAME) final GroupDTO group,
                        BindingResult bindingResult,
                        ModelMap model) {
        LOGGER.info(String.format("Received POST request to add the group. Group params: [ %s ]", group.toString()));
        if (!bindingResult.hasErrors()) {
            groupService.addGroup(group);
            LOGGER.info(String.format("Redirect to [%s]", TO_GROUPS_REDIRECT));
            return TO_GROUPS_REDIRECT;
        }
        LOGGER.warn("Invalid group params.");
        LOGGER.info(String.format("Show %s view.", GROUP_VIEW_NAME));
        return GROUP_VIEW_NAME;
    }

    @PostMapping("/group/{id}")
    public String updateGroup(@PathVariable Integer id,
                            @Valid @ModelAttribute(GROUP_ATTRIBUTE_NAME) final GroupDTO group,
                            BindingResult bindingResult,
                            ModelMap model) {
        LOGGER.info(String.format("Received POST request to update the group. Group params: [ %s ]", group.toString()));
        if (!bindingResult.hasErrors()) {
            groupService.updateGroup(group);
        }
        LOGGER.info(String.format("Redirect to [%s%d]", TO_GROUP_REDIRECT, id));
        return TO_GROUP_REDIRECT + id;
    }

    @GetMapping("/group/del/{id}")
    public String group(@PathVariable Integer id) {
        LOGGER.info(String.format("Received GET request to delete the group with id = %d", id));
        groupService.deleteGroupById(id);
        LOGGER.info(String.format("Redirect to [%s]", TO_GROUPS_REDIRECT));
        return TO_GROUPS_REDIRECT;
    }

    @GetMapping("/group/{groupId}/members/delete/{userId}")
    public String deleteMember(@PathVariable Integer groupId,
                               @PathVariable Integer userId,
                               ModelMap model){
        LOGGER.info(String.format("Received GET request to delete user from group. Params: [groupId: %d; userId: %d]", groupId, userId));
        groupService.deleteMemberFromGroup(groupId, userId);
        LOGGER.info(String.format("Redirect to [%s%d]", TO_GROUP_REDIRECT, groupId));
        return TO_GROUP_REDIRECT + groupId;
    }

    @GetMapping("/group/{groupId}/members/add/{userId}")
    public String addMember(@PathVariable Integer groupId,
                               @PathVariable Integer userId,
                               ModelMap model){
        LOGGER.info(String.format("Received GET request to add new user to group. Params: [groupId: %d; userId: %d]", groupId, userId));
        groupService.addNewMemberToGroup(groupId, userId);
        LOGGER.info(String.format("Redirect to [%s%d]", TO_GROUP_REDIRECT, groupId));
        return TO_GROUP_REDIRECT + groupId;
    }
}
