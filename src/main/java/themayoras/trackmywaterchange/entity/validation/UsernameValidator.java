package themayoras.trackmywaterchange.entity.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {


	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {		
		// check if null
		if (value == null || value.trim().length() == 0) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("required").addConstraintViolation();
			return false;
		} 
		
		// the string must only be letters
		Pattern regexp = Pattern.compile("^[a-zA-Z0-9]+$");
		
		// check if regexp matches
		boolean isValid = regexp.matcher(value).matches();
		
		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("username must one or more letters or numbers").addConstraintViolation();
		}
		
		return isValid;
	}

}
