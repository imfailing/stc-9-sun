package ru.innopolis.stc9.sun.academy.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innopolis.stc9.sun.academy.dto.LessonDTO;
import ru.innopolis.stc9.sun.academy.service.LessonService;

import javax.validation.Valid;

@Controller
public class LessonsController {

    private static final String TITLE_ATTRIBUTE_NAME = "title";
    private static final String TITLE = "Управление занятием";
    private static final String TO_GROUP_REDIRECT = "redirect:/group/";
    private static final String LESSON_ATTRIBUTE_NAME = "lesson";
    private static final String LESSON_VIEW_NAME = "lesson";
    private final LessonService lessonService;
    private static final Logger LOGGER = Logger.getLogger(LessonsController.class);

    @Autowired
    public LessonsController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/group/{groupId}/lessons/{lessonId}")
    public String showLesson(@PathVariable Integer groupId,
                              @PathVariable Integer lessonId,
                              ModelMap model) {
        LOGGER.info(String.format("Received GET request to show lesson with id = %d, from group with id = %d", lessonId, groupId));
        model.addAttribute(TITLE_ATTRIBUTE_NAME, TITLE);
        model.addAttribute(LESSON_ATTRIBUTE_NAME, lessonService.getLessonById(lessonId));
        model.addAttribute("groupId", groupId);
        LOGGER.info(String.format("Show %s view.", LESSON_VIEW_NAME));
        return LESSON_VIEW_NAME;
    }

    @PostMapping("/group/{groupId}/lessons/{lessonId}")
    public String addLesson(@Valid @ModelAttribute(LESSON_ATTRIBUTE_NAME) final LessonDTO lesson,
                            BindingResult bindingResult,
                            @PathVariable Integer groupId,
                            @PathVariable Integer lessonId,
                            ModelMap model){
        LOGGER.info(String.format("Received POST request to update lesson. Lesson's params: [%s]", lesson.toString()));
        if (!bindingResult.hasErrors()) {
            lessonService.updateLesson(lesson);
            LOGGER.info(String.format("Redirect to [%s]", TO_GROUP_REDIRECT + groupId));
            return TO_GROUP_REDIRECT + groupId;
        }
        LOGGER.warn("Invalid group params.");
        LOGGER.info(String.format("Show %s view.", LESSON_VIEW_NAME));
        return LESSON_VIEW_NAME;
    }

    @GetMapping("/group/{groupId}/lessons/delete/{lessonId}")
    public String deleteLesson(@PathVariable Integer groupId,
                               @PathVariable Integer lessonId,
                               ModelMap model){
        LOGGER.info(String.format("Received GET request to delete lesson from group. Params: [groupId: %d; lessonId: %d]", groupId, lessonId));
        lessonService.deleteLesson(lessonId);
        LOGGER.info(String.format("Redirect to [%s]", TO_GROUP_REDIRECT + groupId));
        return TO_GROUP_REDIRECT + groupId;
    }

    @GetMapping("/group/{groupId}/lessons/add")
    public String addLesson(@PathVariable Integer groupId, ModelMap model){
        LOGGER.info(String.format("Received GET request to show add lesson form. Params: [groupId: %d]", groupId));
        model.addAttribute("groupId", groupId);
        LessonDTO lesson = new LessonDTO();
        lesson.setId(0);
        model.addAttribute(LESSON_ATTRIBUTE_NAME, lesson);
        LOGGER.info(String.format("Show %s view.", LESSON_VIEW_NAME));
        return LESSON_VIEW_NAME;
    }

    @PostMapping("/group/{groupId}/lessons/add")
    public String addLesson(@Valid @ModelAttribute(LESSON_ATTRIBUTE_NAME) final LessonDTO lesson,
                            BindingResult bindingResult,
                            @PathVariable Integer groupId,
                            ModelMap model){
        LOGGER.info(String.format("Received POST request to add new lesson. Lesson's params: [%s]", lesson.toString()));
        if (!bindingResult.hasErrors()) {
            lessonService.addLesson(lesson);
            LOGGER.info(String.format("Redirect to [%s]", TO_GROUP_REDIRECT + groupId));
            return TO_GROUP_REDIRECT + groupId;
        }
        LOGGER.warn("Invalid group params.");
        LOGGER.info(String.format("Show %s view.", LESSON_VIEW_NAME));
        return LESSON_VIEW_NAME;
    }
}
