package net.syscon.s4.common.validators;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(FieldMatchValidator.class.getName());

	private String firstFieldName;
	private String secondFieldName;

	@Override
	public void initialize(final FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

			final Date date1 = (Date) firstObj;
			final Date date2 = (Date) secondObj;
			if (date1 == null || date2 == null) {
				return true;
			}
			return date1.compareTo(date2) == 1;
		} catch (final Exception ignore) {
			logger.error(ignore);
		}
		return true;
	}
}
