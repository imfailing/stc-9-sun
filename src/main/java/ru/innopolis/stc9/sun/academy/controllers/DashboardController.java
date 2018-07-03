package ru.innopolis.stc9.sun.academy.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.service.GroupService;
import ru.innopolis.stc9.sun.academy.service.LessonService;
import ru.innopolis.stc9.sun.academy.service.MarkService;
import ru.innopolis.stc9.sun.academy.service.UserService;

@Controller
public class DashboardController {
    private static final Logger LOGGER = Logger.getLogger(DashboardController.class);
    private static final String TITLE = "";
    private final LessonService lessonService;
    private final GroupService groupService;
    private final UserService userService;
    private final MarkService markService;

    @Autowired
    public DashboardController(LessonService lessonService, GroupService groupService, UserService userService, MarkService markService) {
        this.lessonService = lessonService;
        this.groupService = groupService;
        this.userService = userService;
        this.markService = markService;
    }

    @RequestMapping("/dashboard")
    public String dashboard(ModelMap model) {
        model.addAttribute("title", TITLE);
        model.addAttribute("lessons", lessonService.getLessonsNearest(5));
        model.addAttribute("lessonsById", lessonService.getLessonsNearestUser(5,currentUser().getId()));
        model.addAttribute("marks", markService.getAllMarksByUserId(currentUser().getId()));
        return "dashboard";
    }

    private UserDTO currentUser(){
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
