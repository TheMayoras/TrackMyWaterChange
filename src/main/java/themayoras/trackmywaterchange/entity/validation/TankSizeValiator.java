package themayoras.trackmywaterchange.entity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TankSizeValiator implements ConstraintValidator<TankSize, Double> {

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		if (value == null) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("number required").addConstraintViolation();
			return false;
		}

		boolean isValid = value > 0.0d;

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("must be a positive number").addConstraintViolation();
		}

		return isValid;
	}

}
