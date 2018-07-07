package ru.innopolis.stc9.sun.academy.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.innopolis.stc9.sun.academy.dto.annotations.DateInterval;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@DateInterval
public class LessonDTO {

    private Integer id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @NotNull
    @Size(min = 1, max = 250)
    private String title;

    private String description;

    @NotNull
    private Integer groupId;

    private GroupDTO group;

    public LessonDTO() {

    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
        if (group == null){
            group = new GroupDTO(groupId);
        }
    }
}
