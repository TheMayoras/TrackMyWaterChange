package themayoras.trackmywaterchange.entity.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TankLocationValidator implements ConstraintValidator<TankLocation, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("required").addConstraintViolation();
			return false;
		}
		
		boolean isValid = Pattern.compile("^[a-zA-Z0-9].*$").matcher(value).matches();
	
		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("must start with a letter or number").addConstraintViolation();
		}
		
		return isValid;
	}
	
}
