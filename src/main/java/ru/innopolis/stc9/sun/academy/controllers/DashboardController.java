package ru.innopolis.stc9.sun.academy.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
    private static final Logger LOGGER = Logger.getLogger(DashboardController.class);
    private static final String TITLE = "";

    @RequestMapping("/dashboard")
    public String dashboard(ModelMap model) {
        model.addAttribute("title", TITLE);
        return "dashboard";
    }
}
