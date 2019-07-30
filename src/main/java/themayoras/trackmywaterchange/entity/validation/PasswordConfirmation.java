package themayoras.trackmywaterchange.entity.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy=PasswordConfirmationValidator.class)
public @interface PasswordConfirmation {
    public String message() default "{invalid passwords error}";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
