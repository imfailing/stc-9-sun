package ru.innopolis.stc9.sun.academy.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.service.UserService;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UsersController {
    private static final Logger LOGGER = Logger.getLogger(UsersController.class);
    private static final String TITLE = "Пользователи";
    private static final String TITLE_ATTRIBUTE_NAME = "title";
    private static final String USERS_VIEW_NAME = "users";
    private static final String USER_VIEW_NAME = "user";
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String USERS_ATTRIBUTE_NAME = "users";
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(ModelMap model){
        model.addAttribute(TITLE_ATTRIBUTE_NAME, TITLE);
        model.addAttribute(USERS_ATTRIBUTE_NAME, userService.getUsers());
        model.addAttribute(USER_ATTRIBUTE_NAME, new UserDTO());
        return USERS_VIEW_NAME;
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute(USER_ATTRIBUTE_NAME) final UserDTO user,
                           BindingResult bindingResult,
                           ModelMap model){
        if (!bindingResult.hasErrors()) {
            userService.addUser(user);
            return "redirect:/users";
        } else {
            model.addAttribute(TITLE_ATTRIBUTE_NAME, TITLE);
            model.addAttribute(USERS_ATTRIBUTE_NAME, userService.getUsers());
            return USERS_VIEW_NAME;
        }
    }

    @GetMapping("/{id}/")
    public String getUser(@PathVariable Integer id, ModelMap model){
        model.addAttribute(TITLE_ATTRIBUTE_NAME, TITLE);
        model.addAttribute(USER_ATTRIBUTE_NAME, userService.getUserById(id));
        return USER_VIEW_NAME;
    }

    @PostMapping("/{id}/")
    public String updateUser(@PathVariable Integer id,
                             @Valid @ModelAttribute(USER_ATTRIBUTE_NAME) final UserDTO user,
                             BindingResult bindingResult,
                             ModelMap model){
        if (!bindingResult.hasErrors() && Objects.equals(id, user.getId())) {
            user.setId(id);
            userService.updateUser(user);
        } else {
            model.addAttribute(TITLE_ATTRIBUTE_NAME, user.getFullName());
        }
        return USER_VIEW_NAME;
    }

    @ModelAttribute(USER_ATTRIBUTE_NAME)
    public UserDTO getUserById(Integer id){
        if (id != null) return userService.getUserById(id);
        else return new UserDTO();
    }
}
