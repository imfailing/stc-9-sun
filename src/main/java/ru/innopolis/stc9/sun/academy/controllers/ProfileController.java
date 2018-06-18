package ru.innopolis.stc9.sun.academy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc9.sun.academy.dto.GroupDTO;
import ru.innopolis.stc9.sun.academy.service.GroupService;
import ru.innopolis.stc9.sun.academy.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private static final String VIEW_NAME = "profile";
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String GROUP_ATTRIBUTE_NAME = "group";

    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;

    @GetMapping
    public String getUser(ModelMap model){
        //брать текущий user id
        model.addAttribute(USER_ATTRIBUTE_NAME, userService.getUserById(1));
        GroupDTO group=groupService.getGroupByUserId(1);
        if(group!=null)model.addAttribute(GROUP_ATTRIBUTE_NAME, group.getTitle());
        else model.addAttribute(GROUP_ATTRIBUTE_NAME, "-");
        return VIEW_NAME;
    }

}
