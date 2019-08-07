package themayoras.trackmywaterchange.entity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuantityValiator implements ConstraintValidator<Quantity, Object> {

    @Override
    public boolean isValid(Object objectValue, ConstraintValidatorContext context) {
        Double value = -1d;
        
        try {
            value = (Double) objectValue;
        } catch (Exception e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("number required").addConstraintViolation();
        }

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
