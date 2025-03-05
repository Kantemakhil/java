package net.syscon.s4.common.validators;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BirthDateValidator implements ConstraintValidator<ValidBirthDate, Date> {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(BirthDateValidator.class.getName());

	private String pattern;

	@Override
	public void initialize(final ValidBirthDate constraintAnnotation) {
		pattern = constraintAnnotation.pattern();
	}

	@Override
	public boolean isValid(final Date value, final ConstraintValidatorContext context) {
		try {
			if (value == null) {
				return true;
			}
			final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			dateFormat.format(value);
			return true;
		} catch (final Exception ignore) {
			logger.error(ignore);
		}
		return false;
	}

}
