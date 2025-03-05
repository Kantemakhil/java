package net.syscon.s4.eoffender.beans;

public class EoffenderSqlParameter {
	private String parameterName;
	private String parameterType;
	private String parameterDataType;

	private String moduleBlockCode;
	
	private String field;

	public String getParameterName() {
		return parameterName;
	}



	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}



	public String getParameterType() {
		return parameterType;
	}



	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}



	public String getParameterDataType() {
		return parameterDataType;
	}



	public void setParameterDataType(String parameterDataType) {
		this.parameterDataType = parameterDataType;
	}



	public String getModuleBlockCode() {
		return moduleBlockCode;
	}



	public void setModuleBlockCode(String moduleBlockCode) {
		this.moduleBlockCode = moduleBlockCode;
	}



	public String getField() {
		return field;
	}



	public void setField(String field) {
		this.field = field;
	}



	@Override
	public String toString() {
		return "EoffenderSqlParameter [parameterName=" + parameterName + ", parameterType=" + parameterType
				+ ", parameterDataType=" + parameterDataType + ", moduleBlockCode=" + moduleBlockCode + ", field="
				+ field + "]";
	}



	
	
}
