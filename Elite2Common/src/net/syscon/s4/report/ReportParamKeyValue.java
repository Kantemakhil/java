package net.syscon.s4.report;

import java.io.Serializable;
import java.util.Objects;

public class ReportParamKeyValue implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7548897447575705699L;
	private String paramKey;
	private String paramType;
	private Object paramValue;
	
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	public Object getParamValue() {
		return paramValue;
	}
	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}
	
	
	public String getParamType() {
        return paramType;
    }
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(paramKey, paramType, paramValue);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReportParamKeyValue other = (ReportParamKeyValue) obj;
        return Objects.equals(paramKey, other.paramKey) && Objects.equals(paramType, other.paramType)
                && Objects.equals(paramValue, other.paramValue);
    }
   
	
	

}
