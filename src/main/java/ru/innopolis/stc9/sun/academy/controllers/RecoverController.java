package ru.innopolis.stc9.sun.academy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/recover")
    public String recover(ModelMap model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("title", TITLE);
        return "recover";
    }


    @GetMapping("/recover?hash={hash}")
    public String recover(@PathVariable String hash, ModelMap model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("title", TITLE);
        if (recoverService.passRecovery(hash))
        {
            return "changepass";
        } else {
            return "recover";
        }
    }

    @PostMapping
    public String recover(@ModelAttribute("user") final UserDTO user,
                         BindingResult bindingResult,
                         ModelMap model) {
        if (!bindingResult.hasErrors()) {
            recoverService.makeHash(user);
            model.addAttribute("title", TITLE);
            return "redirect:/recover?send=1";
        } else {
            model.addAttribute("title", TITLE);
            return "/recover";
        }
    }
}
