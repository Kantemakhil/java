package net.syscon.s4.common;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public final class ExceptionUtilities {

	private static Logger logger = LogManager.getLogger(ExceptionUtilities.class.getName());

	private ExceptionUtilities() {
		throw new IllegalAccessError("Utility class");
	}

	public static void logException(Exception e) {
		logger.error(e);
		throw new RuntimeException(e);
	}

}
