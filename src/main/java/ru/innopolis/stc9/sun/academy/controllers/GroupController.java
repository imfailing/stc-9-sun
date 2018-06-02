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

    private static final String TITLE = "Управление группами";
    private final GroupService groupService;
    private final UserService userService;

    private static final Logger LOGGER = Logger.getLogger(SignUpController.class);

    @Autowired
    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping("/groups")
    public String groups(ModelMap model) {
        Set<GroupDTO> groups = groupService.getAllGroups();

        model.addAttribute("title", TITLE);
        model.addAttribute("groups", groups);

        return "groups";
    }

    @GetMapping("/group")
    public String newGroup(ModelMap model) {
        model.addAttribute("group", new GroupDTO(0));
        model.addAttribute("title", TITLE);

        return "group";
    }

    @GetMapping("/group/{id}")
    public String updateGroup(@PathVariable Integer id, ModelMap model) {
        model.addAttribute("members", userService.getUsersByGroup(id));
        model.addAttribute("group", groupService.getGroupById(id));
        model.addAttribute("title", TITLE);
        model.addAttribute("users", userService.getUsers());
        return "group";
    }

    @PostMapping("/group")
    public String newGroup(@Valid @ModelAttribute("group") final GroupDTO group,
                        BindingResult bindingResult,
                        ModelMap model) {
        if (!bindingResult.hasErrors()) {
            groupService.addGroup(group);
            return "redirect:/groups";
        }
        return "group";
    }

    @PostMapping("/group/{id}")
    public String updateGroup(@Valid @ModelAttribute("group") final GroupDTO group,
                           BindingResult bindingResult,
                           ModelMap model) {
        if (!bindingResult.hasErrors()) {
            groupService.updateGroup(group);
        }
        return "group";
    }

    @GetMapping("/group/del/{id}")
    public String group(@PathVariable Integer id) {
        groupService.deleteGroupById(id);
        return "redirect:/groups";
    }

    @GetMapping("/group/{groupId}/members/delete/{userId}")
    public String deleteMember(@PathVariable Integer groupId,
                               @PathVariable Integer userId,
                               ModelMap model){
        groupService.deleteMemberFromGroup(groupId, userId);
        return "redirect:/group/" + groupId;
    }

    @GetMapping("/group/{groupId}/members/add/{userId}")
    public String addMember(@PathVariable Integer groupId,
                               @PathVariable Integer userId,
                               ModelMap model){
        groupService.addNewMemberToGroup(groupId, userId);
        return "redirect:/group/" + groupId;
    }
}
