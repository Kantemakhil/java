package net.syscon.s4.portalapp.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseOffense implements Serializable{
	
	private static final long serialVersionUID = -880147319876674843L;
	
	private String personId;
	@JsonProperty("scheduleId")
	private Long scheduleId; 
	@JsonProperty("caseId")
	private Long caseId;
	@JsonProperty("offenseId")
	private Long offenseId;
	
	@JsonProperty("offenceCode")
	private String offenceCode;
	@JsonProperty("offenseType")
	private String offenseType;
	@JsonProperty("offenseDate")
	private Date offenseDate;
	private String plea;
	@JsonProperty("range")
	private Date range;
	private String result;
	@JsonProperty("statuteCode")
	private String statuteCode;
	
	private BigDecimal requestId;
	private String requestStatus;
	private String rejectReason;
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public Long getOffenseId() {
		return offenseId;
	}
	public void setOffenseId(Long offenseId) {
		this.offenseId = offenseId;
	}
	public String getOffenceCode() {
		return offenceCode;
	}
	public void setOffenceCode(String offenceCode) {
		this.offenceCode = offenceCode;
	}
	public String getOffenseType() {
		return offenseType;
	}
	public void setOffenseType(String offenseType) {
		this.offenseType = offenseType;
	}
	public String getPlea() {
		return plea;
	}
	public void setPlea(String plea) {
		this.plea = plea;
	}
	public Date getOffenseDate() {
		return offenseDate;
	}
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}
	public Date getRange() {
		return range;
	}
	public void setRange(Date range) {
		this.range = range;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getStatuteCode() {
		return statuteCode;
	}
	public void setStatuteCode(String statuteCode) {
		this.statuteCode = statuteCode;
	}
	public BigDecimal getRequestId() {
		return requestId;
	}
	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

}
