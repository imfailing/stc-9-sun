package ru.innopolis.stc9.sun.academy.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDeniedController {
    private static final Logger LOGGER = Logger.getLogger(AccessDeniedController.class);
    private static final String TITLE = "Доступ запрещен";

    @RequestMapping("/accessdenied")
    public String accessdenied(ModelMap model) {
        model.addAttribute("title", TITLE);
        return "accessdenied";
    }
}
