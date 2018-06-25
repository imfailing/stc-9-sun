package ru.innopolis.stc9.sun.academy.controllers;
import org.apache.log4j.Logger;
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
    private static final String PASS_RESULT_ATTRIBUTE_NAME = "passResult";

    private static final Logger logger = Logger.getLogger(GroupController.class);
    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;

    @GetMapping
    public String getUser(ModelMap model){
        model.addAttribute(USER_ATTRIBUTE_NAME, currentUser());
        Set<GroupDTO> groups = groupService.getGroupsByUserId(currentUser().getId());
        model.addAttribute(GROUPS_ATTRIBUTE_NAME, groups);
        return VIEW_NAME;
    }

    @PostMapping(params = {"passwordOld","passwordNew","passwordConfirm"})
    public String changePassword(@RequestParam("passwordOld")String passwordOld, @RequestParam("passwordNew")String passwordNew,@RequestParam("passwordConfirm")String passwordConfirm,ModelMap model){
        if(currentUser().getPassword().equals(passwordOld)){
            if(passwordNew.equals(passwordConfirm)){
                currentUser().setPassword(passwordNew);
                userService.updateUser(currentUser());
                model.addAttribute(PASS_RESULT_ATTRIBUTE_NAME,"Пароль изменен");
                logger.info("password has been changed by user");
            }
            else {
                model.addAttribute(PASS_RESULT_ATTRIBUTE_NAME,"Пароли не совпадают");
            }
        }
        else {
            model.addAttribute(PASS_RESULT_ATTRIBUTE_NAME,"Неправильный пароль");
        }
        return getUser(model);
    }


    private UserDTO currentUser(){
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
