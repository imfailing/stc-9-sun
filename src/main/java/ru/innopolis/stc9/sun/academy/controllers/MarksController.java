package ru.innopolis.stc9.sun.academy.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.service.MarkService;


@Controller
@RequestMapping("/marks")
public class MarksController {
    private static final Logger LOGGER = Logger.getLogger(MarksController.class);
    private final MarkService markService;

    @Autowired
    public MarksController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping
    public String getCurrentUserMarks(ModelMap model){
        model.addAttribute("title", "Оценки");
        UserDTO userDetails = (UserDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.error("a12e: " + userDetails.getId());
        model.addAttribute("marks", markService.getAllMarksByUserId(userDetails.getId()));
        return "marks";
    }
}
