package net.syscon.s4.common.validators;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GlobalValidator implements ConstraintValidator<GlobalValidation, Object> {
	private static Logger logger = LogManager.getLogger(GlobalValidator.class.getName());
	@Override
	public void initialize(final GlobalValidation constraintAnnotation) {
		// initialize
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		boolean flag = false;
		// Get all instance variables
		final Field[] fields = value.getClass().getDeclaredFields();
		for (Field field : fields) {
			if ("serialVersionUID".equals(field.getName())) {
				// ignore serialVersionUID variable.
				continue;
			}
			try {
				// check field value against null.
				if (PropertyUtils.getSimpleProperty(value, field.getName()) != null) {
					flag = true;
					break;
				}
			} catch (Exception e) {
				logger.error(e);
				flag = false;
			}
		}
		return flag;
	}
}
