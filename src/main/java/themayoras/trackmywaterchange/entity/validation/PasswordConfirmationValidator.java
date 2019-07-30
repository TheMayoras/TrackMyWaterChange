package themayoras.trackmywaterchange.entity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import themayoras.trackmywaterchange.entity.UserCommand;

public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, Object> {

    
    private PasswordConfirmation annotation;
    
    @Override
    public void initialize(PasswordConfirmation constraintAnnotation) {
	annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
	UserCommand user = (UserCommand) value;

	return user.getPassword().equals(user.getConfirmationPassword());
    }

    
}
