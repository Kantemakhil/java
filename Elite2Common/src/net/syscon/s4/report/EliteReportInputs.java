package net.syscon.s4.report;

import java.io.Serializable;
import java.util.Set;

public class EliteReportInputs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8820102259766064333L;
	
	private String moduleName;
	private String reportType;
	private Set<ReportParamKeyValue> paramValues;
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Set<ReportParamKeyValue> getParamValues() {
		return paramValues;
	}
	public void setParamValues(Set<ReportParamKeyValue> paramValues) {
		this.paramValues = paramValues;
	}
    public String getReportType() {
        return reportType;
    }
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

}
