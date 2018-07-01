package ru.innopolis.stc9.sun.academy.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc9.sun.academy.dto.MarkDTO;
import ru.innopolis.stc9.sun.academy.dto.UserDTO;
import ru.innopolis.stc9.sun.academy.service.MarkService;
import javax.validation.Valid;

@Controller
@RequestMapping
public class MarksController {
    private static final Logger LOGGER = Logger.getLogger(MarksController.class);
    private final MarkService markService;

    @Autowired
    public MarksController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping("/marks")
    public String getCurrentUserMarks(ModelMap model){
        model.addAttribute("title", "Оценки");
        UserDTO userDetails = (UserDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.error("a12e: " + userDetails.getId());
        model.addAttribute("marks", markService.getAllMarksByUserId(userDetails.getId()));
        return "marks";
    }

    @GetMapping("/group/{groupId}/lessons/delete/{lessonId}/rating")
    public String getRating(@PathVariable Integer groupId,
                            @PathVariable Integer lessonId,
                            ModelMap model){
        model.addAttribute("title", "Оценки");
        model.addAttribute("marks", markService.getMarksWithUserByLessonIdAndGroupId(lessonId, groupId));
        model.addAttribute("mark", new MarkDTO());
        return "rating";
    }

    @PostMapping("/group/{groupId}/lessons/delete/{lessonId}/rating")
    public String addNewMark(@PathVariable Integer groupId,
                             @PathVariable Integer lessonId,
                             @Valid @ModelAttribute("mark") final MarkDTO mark,
                             BindingResult bindingResult,
                             ModelMap model){
        return String.format("redirect:/group/%d/lessons/delete/%d/rating", groupId, lessonId);
    }
}
