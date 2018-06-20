package ru.innopolis.stc9.sun.academy.service;

import ru.innopolis.stc9.sun.academy.dto.MarkDTO;

import java.util.Set;

public interface MarkService {
    boolean addMark(MarkDTO markDTO);
    boolean deleteMarkById(Integer id);
    Set<MarkDTO> getAllMarksByUserId(Integer userId);
}
