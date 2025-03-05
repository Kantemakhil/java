package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenseResultCodes extends BaseModel implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("disposition")
	private String disposition;
	
	@JsonProperty("dispositionDescription")
	private String dispositionDescription;
	
	@JsonProperty("offenseStatus")
	private String offenseStatus;
	
	@JsonProperty("conviction")
	private String conviction;
	
	@JsonProperty("resultCode")
	private String resultCode;
	
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode
	 *            the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param resultCode
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public String getDisposition() {
		return disposition;
	}

	/**
	 * 
	 * @param disposition
	 */
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	/**
	 * 
	 * @return
	 */
	public String getDispositionDescription() {
		return dispositionDescription;
	}

	/**
	 * 
	 * @param code
	 */
	public void setDispositionDescription(String dispositionDescription) {
		this.dispositionDescription = dispositionDescription;
	}

	/**
	 * 
	 * @return
	 */
	public String getOffenseStatus() {
		return offenseStatus;
	}

	/**
	 * 
	 * @param offenseStatus
	 */
	public void setOffenseStatus(String offenseStatus) {
		this.offenseStatus = offenseStatus;
	}

	/**
	 * 
	 * @return
	 */
	public String getConviction() {
		return conviction;
	}

	/**
	 * 
	 * @param conviction
	 */
	public void setConviction(String conviction) {
		this.conviction = conviction;
	}
	
}
