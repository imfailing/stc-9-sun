package ru.innopolis.stc9.sun.academy.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    private static final String TITLE = "Регистрация";
    private final UserService userService;

    private static final Logger LOGGER = Logger.getLogger(SignUpController.class);

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signUp(ModelMap model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("title", TITLE);
        return "signup";
    }

    @PostMapping
    public String signUp(@Valid @ModelAttribute("user") final UserDTO user,
                         BindingResult bindingResult,
                         ModelMap model) {
        if(userService.getUserByEmail(user.getEmail())!=null)
        {
            return "redirect:/signup?error=1";
        }
        if (!bindingResult.hasErrors()) {
            userService.addUser(user);
            return "redirect:/";
        } else {
            model.addAttribute("title", TITLE);
            return "signup";
        }
    }
}
