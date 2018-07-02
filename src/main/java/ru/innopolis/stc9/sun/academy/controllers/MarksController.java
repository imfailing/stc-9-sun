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
import ru.innopolis.stc9.sun.academy.entity.Lesson;
import ru.innopolis.stc9.sun.academy.service.LessonService;
import ru.innopolis.stc9.sun.academy.service.MarkService;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping
public class MarksController {
    private static final Logger LOGGER = Logger.getLogger(MarksController.class);
    private final MarkService markService;
    private final LessonService lessonService;

    @Autowired
    public MarksController(MarkService markService, LessonService lessonService) {
        this.lessonService = lessonService;
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

    @GetMapping("/group/{groupId}/lessons/{lessonId}/rating")
    public String getRating(@PathVariable Integer groupId,
                            @PathVariable Integer lessonId,
                            ModelMap model){
        model.addAttribute("title", "Оценки");
        model.addAttribute("marks", markService.getMarksWithUserByLessonIdAndGroupId(lessonId, groupId));
        model.addAttribute("mark", new MarkDTO());
        model.addAttribute("lesson", lessonService.getLessonById(lessonId));
        return "rating";
    }

    @PostMapping("/group/{groupId}/lessons/{lessonId}/rating")
    public String addNewMark(@PathVariable Integer groupId,
                             @PathVariable Integer lessonId,
                             @Valid @ModelAttribute("mark") final MarkDTO mark,
                             BindingResult bindingResult,
                             ModelMap model){
        LOGGER.info(mark);
        markService.addMark(mark);
        return String.format("redirect:/group/%d/lessons/%d/rating", groupId, lessonId);
    }
}
