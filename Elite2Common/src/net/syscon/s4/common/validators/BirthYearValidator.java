package net.syscon.s4.common.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BirthYearValidator implements ConstraintValidator<ValidBirthYear, String> {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(BirthYearValidator.class.getName());

	@Override
	public void initialize(final ValidBirthYear constraintAnnotation) {
		// initialize
	}

	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		try {
			if (value == null) {
				return true;
			}
			final int result = Integer.parseInt(value);
			if (result > 0 && value.length() <= 4) {
				return true;
			}
		} catch (final Exception ignore) {
			logger.error(ignore);
		}
		return false;
	}

}
