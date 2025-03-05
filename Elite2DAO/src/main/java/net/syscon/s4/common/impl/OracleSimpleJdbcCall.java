package net.syscon.s4.common.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.CallableStatementCreatorFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import oracle.jdbc.OracleTypes;


public class OracleSimpleJdbcCall extends SimpleJdbcCall {
	
	private static final String BOOL_TO_INT_FUNCTION = "SYS.DIUTIL.BOOL_TO_INT";
	private static final String INT_TO_BOOL_FUNCTION = "SYS.DIUTIL.INT_TO_BOOL";

	private List<SqlParameter> modifiedCallParams = new ArrayList<>();

	public OracleSimpleJdbcCall(DataSource dataSource) {
		super(dataSource);
	}

	public OracleSimpleJdbcCall(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		this.setFunction(true);
	}

	private String callString;
	private boolean booleanResult;

	public boolean isBooleanResult() {
		return booleanResult;
	}

	public SimpleJdbcCall withBooleanResult(boolean booleanResult) {
		this.booleanResult = booleanResult;
		return this;
	}

	public String getCallString() {
		return this.callString;
	}

	protected CallableStatementCreatorFactory getCallableStatementFactory() {
		return new CallableStatementCreatorFactory(this.callString, this.modifiedCallParams);
	}

	private SqlParameter translateIfBoolean(SqlParameter param) {
		if (param.getSqlType() != Types.BOOLEAN) {
			return param;
		}
		if (param instanceof SqlOutParameter) {
			return new SqlOutParameter(param.getName(), OracleTypes.NUMBER);
		} else if (param instanceof SqlInOutParameter) {
			return new SqlInOutParameter(param.getName(), OracleTypes.NUMBER);
		} else {
			return new SqlParameter(param.getName(), OracleTypes.NUMBER);
		}
	}

	@Override
	protected void compileInternal() {
		
		super.compileInternal();

		final String[] parts = super.getCallString().split("call ");
		final String sqlCall = parts[1].substring(0, parts[1].length() - 1).trim();

		modifiedCallParams.clear();
		
		
		StringBuilder newSqlCall = new StringBuilder("");
		Iterator<SqlParameter> iterParams= super.getCallParameters().iterator();
		
		boolean functionResultChanged = false;
		if (isFunction()) {
			final SqlParameter param = iterParams.next();
			final SqlParameter translatedParam = translateIfBoolean(param);
			modifiedCallParams.add(translatedParam);
			if (param != translatedParam) {
				functionResultChanged = true;
			}
		}
		
		for (int i = 0; i < sqlCall.length(); i++) {
			char ch = sqlCall.charAt(i);
			boolean consumed = false;
			if (ch == '?') {
				final SqlParameter param = iterParams.next();
				final SqlParameter translatedParam = translateIfBoolean(param);
				modifiedCallParams.add(translatedParam);
				if (param != translatedParam) {
					newSqlCall.append(INT_TO_BOOL_FUNCTION).append("(?)");
					consumed = true;
				}
			}
			if (!consumed) {
				newSqlCall.append(ch);
			}
		}

		this.callString = newSqlCall.toString();
		if (isFunction()) {
			StringBuilder sbBegin = new StringBuilder("? := ");
			if (functionResultChanged) {
				sbBegin.append(BOOL_TO_INT_FUNCTION).append("(");
				newSqlCall.insert(0, sbBegin).append(")");
			} 
			newSqlCall.append(";");
		}
		newSqlCall.insert(0, "BEGIN ");
		newSqlCall.append(" END;");
		this.callString = newSqlCall.toString();
	}

	@Override
	protected Map<String, Object> doExecute(SqlParameterSource parameterSource) {
		
		checkCompiled();
		Map<String, Object> params = matchInParameterValuesWithCallParameters(parameterSource);
		
		// converting eventual boolean values to number
		for (String key: params.keySet()) {
			Object value = params.get(key);
			if (value instanceof Boolean) {
				Boolean booleanValue = (Boolean) value;
				value = booleanValue? new BigDecimal(1): new BigDecimal(0);
				params.put(key,  value);
			}
		}

		// invoke the procedure / function
		Map<String, Object> result = super.doExecute(params);

		// convert the eventual number values to boolean
		for (SqlParameter param : super.getCallParameters()) {
			if (param.getSqlType() == Types.BOOLEAN && (param.getClass().equals(SqlOutParameter.class) || (param.getClass().equals(SqlInOutParameter.class)))) {
				Boolean booleanValue = Boolean.FALSE;
				String value = result.get(param.getName()).toString();
				if (value != null) {
					BigDecimal decimalValue = new BigDecimal(value); 
					booleanValue = decimalValue.intValue() == 1;
				}
				result.put(param.getName(), booleanValue);
			}
		}
		return result;
	}
}


