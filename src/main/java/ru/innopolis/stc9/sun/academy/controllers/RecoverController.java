package ru.innopolis.stc9.sun.academy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.service.RecoverService;

import javax.validation.Valid;

@Controller
public class RecoverController {
    private static final String TITLE = "Восстановить пароль";
    private final RecoverService recoverService;

    public RecoverController(RecoverService recoverService) {
        this.recoverService = recoverService;
    }

    @RequestMapping(value = "/recover", method = RequestMethod.GET)
    public String recover(ModelMap model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("title", TITLE);
        return "recover";
    }

    @PostMapping
    public String recover(@Valid @ModelAttribute("user") final UserDTO user,
                         BindingResult bindingResult,
                         ModelMap model) {
        if (!bindingResult.hasErrors()) {
            recoverService.makeHash(user);
            return "redirect:/";
        } else {
            model.addAttribute("title", TITLE);
            return "recover";
        }
    }
}
