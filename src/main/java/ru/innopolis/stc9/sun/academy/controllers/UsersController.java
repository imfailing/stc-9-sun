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
        LOGGER.info("Received GET request to show all users.");
        model.addAttribute(TITLE_ATTRIBUTE_NAME, TITLE);
        model.addAttribute(USERS_ATTRIBUTE_NAME, userService.getUsers());
        model.addAttribute(USER_ATTRIBUTE_NAME, new UserDTO());
        LOGGER.info(String.format("Show %s view", USERS_VIEW_NAME));
        return USERS_VIEW_NAME;
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute(USER_ATTRIBUTE_NAME) final UserDTO user,
                           BindingResult bindingResult,
                           ModelMap model){
        LOGGER.info(String.format("Received POST request to add new user. User's params: [%s]", user.toString()));
        if (!bindingResult.hasErrors()) {
            LOGGER.info("Params is valid. Try to add user and redirect to /users");
            userService.addUser(user);
            return "redirect:/users";
        } else {
            LOGGER.warn("Invalid user's params");
            model.addAttribute(TITLE_ATTRIBUTE_NAME, TITLE);
            model.addAttribute(USERS_ATTRIBUTE_NAME, userService.getUsers());
            LOGGER.info(String.format("Show %s view", USERS_VIEW_NAME));
            return USERS_VIEW_NAME;
        }
    }

    @GetMapping("/{id}/")
    public String getUser(@PathVariable Integer id, ModelMap model){
        LOGGER.info(String.format("Received GET request to show user with id=%d", id));
        model.addAttribute(TITLE_ATTRIBUTE_NAME, TITLE);
        model.addAttribute(USER_ATTRIBUTE_NAME, userService.getUserById(id));
        LOGGER.info(String.format("Show %s view", USER_VIEW_NAME));
        return USER_VIEW_NAME;
    }

    @PostMapping("/{id}/")
    public String updateUser(@PathVariable Integer id,
                             @Valid @ModelAttribute(USER_ATTRIBUTE_NAME) final UserDTO user,
                             BindingResult bindingResult,
                             ModelMap model){
        LOGGER.info(String.format("Received POST request to update user with id=%d. User's params: [ %s ]", id, user.toString()));
        if (!bindingResult.hasErrors() && Objects.equals(id, user.getId())) {
            LOGGER.info("Params is valid. Try to update user");
            user.setId(id);
            userService.updateUser(user);
        } else {
            LOGGER.warn("Invalid user's params");
            model.addAttribute(TITLE_ATTRIBUTE_NAME, user.getFullName());
        }
        LOGGER.info(String.format("Show %s view", USER_VIEW_NAME));
        return USER_VIEW_NAME;
    }

    @ModelAttribute(USER_ATTRIBUTE_NAME)
    public UserDTO getUserById(Integer id){
        LOGGER.info("Prepare user model");
        if (id != null) {
            LOGGER.info(String.format("Request contain user ID = %d. Try to get user DTO.", id));
            return userService.getUserById(id);
        }
        else {
            LOGGER.info("Request doesn't contain the user ID. Use empty DTO.");
            return new UserDTO();
        }
    }
}
