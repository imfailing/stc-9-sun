package ru.innopolis.stc9.sun.academy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc9.sun.academy.dto.GroupDTO;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.service.GroupService;
import ru.innopolis.stc9.sun.academy.service.UserService;
import java.util.Set;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private static final String VIEW_NAME = "profile";
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String GROUPS_ATTRIBUTE_NAME = "groups";

    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;

    @GetMapping
    public String getUser(ModelMap model){
        UserDTO userCurrent = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute(USER_ATTRIBUTE_NAME, userCurrent);
        Set<GroupDTO> groups = groupService.getGroupsByUserId(userCurrent.getId());
        model.addAttribute(GROUPS_ATTRIBUTE_NAME, groups);
        return VIEW_NAME;
    }

}
