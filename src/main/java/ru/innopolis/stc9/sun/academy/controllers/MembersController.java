package ru.innopolis.stc9.sun.academy.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.innopolis.stc9.sun.academy.service.GroupService;
import ru.innopolis.stc9.sun.academy.service.UserService;

@Controller
public class MembersController {

    private static final String TITLE_ATTRIBUTE_NAME = "title";
    private static final String TITLE = "Управление составом группы";
    private static final String TO_MEMBERS_REDIRECT = "redirect:/group/%d/members";
    private static final String MEMBERS_VIEW_NAME = "members";
    private static final String MEMBERS_ATTRIBUTE_NAME = "members";
    private static final String USERS_ATTRIBUTE_NAME = "users";

    private final GroupService groupService;
    private final UserService userService;

    private static final Logger LOGGER = Logger.getLogger(MembersController.class);

    @Autowired
    public MembersController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping("/group/{groupId}/members")
    public String showMembers(@PathVariable Integer groupId, ModelMap model) {
        LOGGER.info(String.format("Received GET request to show members in group with id = %d.", groupId));
        model.addAttribute(MEMBERS_ATTRIBUTE_NAME, userService.getUsersByGroup(groupId));
        model.addAttribute(TITLE_ATTRIBUTE_NAME, TITLE);
        model.addAttribute("groupId", groupId);
        model.addAttribute(USERS_ATTRIBUTE_NAME, userService.getUsers());
        LOGGER.info(String.format("Show %s view.", MEMBERS_VIEW_NAME));
        return MEMBERS_VIEW_NAME;
    }


    @GetMapping("/group/{groupId}/members/delete/{userId}")
    public String deleteMember(@PathVariable Integer groupId,
                               @PathVariable Integer userId,
                               ModelMap model){
        LOGGER.info(String.format("Received GET request to delete user from group. Params: [groupId: %d; userId: %d]", groupId, userId));
        groupService.deleteMemberFromGroup(groupId, userId);
        LOGGER.info(String.format("Redirect to [%s]", String.format(TO_MEMBERS_REDIRECT, groupId)));
        return String.format(TO_MEMBERS_REDIRECT, groupId);
    }

    @GetMapping("/group/{groupId}/members/add/{userId}")
    public String addMember(@PathVariable Integer groupId,
                            @PathVariable Integer userId,
                            ModelMap model){
        LOGGER.info(String.format("Received GET request to add new user to group. Params: [groupId: %d; userId: %d]", groupId, userId));
        groupService.addNewMemberToGroup(groupId, userId);
        LOGGER.info(String.format("Redirect to [%s]", String.format(TO_MEMBERS_REDIRECT, groupId)));
        return String.format(TO_MEMBERS_REDIRECT, groupId);
    }
}
