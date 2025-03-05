package net.syscon.s4.portalapp.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CaseOffenseResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7456553751998016312L;
	private String personId;
	private String notProcessingReasonCode;
	private String notProcessingReasonDescription;
	private BigDecimal requestId;
	private BigDecimal eventScheduleId;
	private BigDecimal offednerChargeId;
	private BigDecimal offenderBookId;
	private String status;
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getNotProcessingReasonCode() {
		return notProcessingReasonCode;
	}
	public void setNotProcessingReasonCode(String notProcessingReasonCode) {
		this.notProcessingReasonCode = notProcessingReasonCode;
	}
	public String getNotProcessingReasonDescription() {
		return notProcessingReasonDescription;
	}
	public void setNotProcessingReasonDescription(String notProcessingReasonDescription) {
		this.notProcessingReasonDescription = notProcessingReasonDescription;
	}
	public BigDecimal getRequestId() {
		return requestId;
	}
	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
	}
	public BigDecimal getEventScheduleId() {
		return eventScheduleId;
	}
	public void setEventScheduleId(BigDecimal eventScheduleId) {
		this.eventScheduleId = eventScheduleId;
	}
	public BigDecimal getOffednerChargeId() {
		return offednerChargeId;
	}
	public void setOffednerChargeId(BigDecimal offednerChargeId) {
		this.offednerChargeId = offednerChargeId;
	}
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
