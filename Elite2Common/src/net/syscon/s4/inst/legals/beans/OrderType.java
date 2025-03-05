package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("reportType")
	private String reportType;
	
	
	
	/***
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	/***
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/***
	 * 
	 * @return
	 */
	public String getReportType() {
		return reportType;
	}
	/***
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/***
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/***
	 * 
	 * @param reportType
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
}
