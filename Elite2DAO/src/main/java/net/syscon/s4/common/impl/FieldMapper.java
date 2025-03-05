package net.syscon.s4.common.impl;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ReflectionUtils;

import oracle.sql.TIMESTAMP;

public class FieldMapper {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(FieldMapper.class.getName());
	public static final String ADDITIONAL_PROPERTIES = "additionalProperties";

	private String name;
	private Function<Field, Void> setterFunction;
	private Function<Object, Object> decodeFunction;

	// encode value for query
	private Function<String, String> encodeFunction;

	public FieldMapper(final String name) {
		this.name = name;
	}

	public FieldMapper(final String name,  final Function<Object, Object> decodeFunction) {
		this.name = name;
		this.decodeFunction = decodeFunction;
	}

	public FieldMapper(final String name,  final Function<Object, Object> decodeFunction, final Function<Field, Void> setterFunction) {
		this.name = name;
		this.decodeFunction = decodeFunction;
		this.setterFunction = setterFunction;
	}

	public FieldMapper(final String name,  final Function<Object, Object> decodeFunction, final Function<Field, Void> setterFunction,
			final Function<String, String> encodeFunction) {
		this.name = name;
		this.decodeFunction = decodeFunction;
		this.setterFunction = setterFunction;
		this.encodeFunction = encodeFunction;
	}

	public void setSetterFunction(final Function<Field, Void> setterFunction) {
		this.setterFunction = setterFunction;
	}

	public void setDecodeFunction(final Function<Object, Object> decodeFunction) {
		this.decodeFunction = decodeFunction;
	}

	private Object getCompatibleValue(final Field field, final Object value) throws SQLException {
		final Class<?> fieldType = field.getType();
		if (decodeFunction != null) {
			return decodeFunction.apply(value);
		} else if (fieldType.isInstance(value)) {
			return value;
		} else if (value instanceof Number) {
			return getNumberValue(value, fieldType);
		} else if (value instanceof java.util.Date) {
			return getDateValue(value, fieldType);
		} else if (value instanceof  Blob && fieldType == byte[].class) {
			return getBlobValue(value);
		} else if (value instanceof oracle.sql.TIMESTAMP){
			return getTimeStampToDate(value, fieldType);
		}
		return value;
	}

	private Object getBlobValue(Object value) throws SQLException {
		final Blob blob = (Blob)value;
		final int length = (int) blob.length();
		final byte[] result  = blob.getBytes(1, length);
		blob.free();
		return result;
	}

	private Object getDateValue(final Object value, final Class<?> fieldType) {
		if (fieldType.equals(java.sql.Date.class)) {
			return new Date(((Date) value).getTime());
		} else if (fieldType.equals(Time.class)) {
			return new Time(((Date) value).getTime());
		} else if (fieldType.equals(Timestamp.class)) {
			return new Timestamp(((Date) value).getTime());
		}
		return null;
	}
	
	public Object getTimeStampToDate(final Object value, final Class<?> fieldType){
		if (fieldType.equals(Date.class)) {
			final Calendar start = Calendar.getInstance();
			try {
				start.setTime(((TIMESTAMP) value).dateValue());
			} catch (SQLException e) {
				logger.error(e);
			}
			return start.getTime();
		}
		return null;
	}

	@SuppressWarnings({"squid:S3776", "squid:MethodCyclomaticComplexity"})
	private Object getNumberValue(final Object value, final Class<?> fieldType) {
		final Number numberValue = (Number) value;
		if (Long.TYPE.equals(fieldType) || Long.class.equals(fieldType))
			return numberValue.longValue();
		if (Integer.TYPE.equals(fieldType) || Integer.class.equals(fieldType))
			return numberValue.intValue();
		if (Short.TYPE.equals(fieldType) || Short.class.equals(fieldType))
			return numberValue.shortValue();
		if (Byte.TYPE.equals(fieldType) || Byte.class.equals(fieldType))
			return numberValue.byteValue();
		if (Character.TYPE.equals(fieldType) || Character.class.equals(fieldType))
			return (char) numberValue.shortValue();
		if (Boolean.TYPE.equals(fieldType) || Boolean.class.equals(fieldType))
			return numberValue.byteValue() == 0 ? false : true;
		if (BigDecimal.class.equals(fieldType))
			return new BigDecimal(numberValue.toString());
		if (BigInteger.class.equals(fieldType))
			return new BigInteger(numberValue.toString());
		if (Float.TYPE.equals(fieldType) || Float.class.equals(fieldType))
			return numberValue.floatValue();
		if (Double.TYPE.equals(fieldType) || Double.class.equals(fieldType))
			return numberValue.doubleValue();
		return null;
	}


	public void setValue(final Object target, final Object value) {
		try {
			final Field beanField = target.getClass().getDeclaredField(name);
			if (beanField != null) {
				final boolean accessible = beanField.isAccessible();
				beanField.setAccessible(true);
				if (setterFunction != null) {
					setterFunction.apply(beanField);
				} else {
					final Object compatibleValue = getCompatibleValue(beanField, value);
					ReflectionUtils.setField(beanField, target, compatibleValue);
				}
				beanField.setAccessible(accessible);
			}
		} catch(NoSuchFieldException ex) {
			// no-op
		} catch (SQLException ex) {
			logger.error(ex);
			/*logger.warn("Failure setting the field \"" + name + "\" on " + target.getClass().getName());*/
		}
	}

	public String getEncodedValue(final String value) {
		String encodedValue;

		if (encodeFunction == null) {
			encodedValue = value;
		} else {
			encodedValue = encodeFunction.apply(value);
		}

		return encodedValue;
	}

	public String getName() {
		return name;
	}
}
