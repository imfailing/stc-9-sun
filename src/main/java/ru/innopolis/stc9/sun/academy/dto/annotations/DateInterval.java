package ru.innopolis.stc9.sun.academy.dto.annotations;

import ru.innopolis.stc9.sun.academy.dto.validators.DateIntervalValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateIntervalValidator.class)
public @interface DateInterval {
    String message() default "Выбранная дата находится за пределами проведения курса";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
