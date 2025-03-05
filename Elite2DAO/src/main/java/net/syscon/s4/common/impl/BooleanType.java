package net.syscon.s4.common.impl;

import java.sql.CallableStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.SqlReturnType;

public class BooleanType implements SqlReturnType {

	@Override
	public Object getTypeValue(CallableStatement cs, int paramIndex, int sqlType, String typeName) throws SQLException {
		Object value = cs.getObject(paramIndex);
		if (value == null) {
			return false;
		}
		String s = value.toString().toLowerCase();
		return ("true".equals(s) || "0".equals(s));
	}

}
