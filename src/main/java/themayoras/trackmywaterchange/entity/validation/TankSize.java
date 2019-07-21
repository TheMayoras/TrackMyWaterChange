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
@Constraint(validatedBy=TankSizeValiator.class)
public @interface TankSize {
    public String message() default "{invalid tank size error}";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
