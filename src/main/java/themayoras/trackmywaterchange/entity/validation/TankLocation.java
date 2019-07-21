package themayoras.trackmywaterchange.entity.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=TankLocationValidator.class)
public @interface TankLocation {
    public String message() default "{tank location specification error}";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
