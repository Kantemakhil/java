package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportBean extends BaseModel implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("report")
	private byte[] report;
	@JsonProperty("fields")
	private List<?> fields;
	
	public ReportBean() {
		// ReportBean
	}

	/**
	 * @return the report
	 */
	public byte[] getReport() {
		return report;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(final byte[] report) {
		this.report = report;
	}

	/**
	 * @return the fields
	 */
	public List<?> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(final List<?> fields) {
		this.fields = fields;
	}

}
