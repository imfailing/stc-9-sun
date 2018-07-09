package ru.innopolis.stc9.sun.academy.dto.validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.innopolis.stc9.sun.academy.dao.GroupDAO;
import ru.innopolis.stc9.sun.academy.dto.LessonDTO;
import ru.innopolis.stc9.sun.academy.dto.annotations.DateInterval;
import ru.innopolis.stc9.sun.academy.entity.Group;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DateIntervalValidator implements ConstraintValidator<DateInterval, LessonDTO> {
    @Autowired
    @Qualifier("groupDAOHibernateImpl")
    GroupDAO groupDAO;
    @Override
    public final void initialize(final DateInterval annotation) {
        //not used
    }
    @Override
    public final boolean isValid(final LessonDTO value,
                                 final ConstraintValidatorContext context) {
        Date date = value.getDate();
        if (date != null) {
            Group group = groupDAO.getById(value.getGroupId());
            Date startDate=group.getStartDate();
            Date finishedDate=group.getFinishedDate();
            boolean isValid= date.after(startDate) && date.before(finishedDate);
            if ( !isValid ) {
                context.disableDefaultConstraintViolation();
                context
                        .buildConstraintViolationWithTemplate( "{message}" )
                        .addPropertyNode( "date" ).addConstraintViolation();
            }

            return isValid;
        }
        return false;
        }
    }


