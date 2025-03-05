package net.syscon.s4.portalapp.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourtCaseInfo {
	
	private static final long serialVersionUID = 1L;
	private Long caseId;
	private Long personId;
	private String caseInfoNumber;
	private String caseType;
	private Date casestartDate;
	private String toCourt;
	private BigDecimal requestId;
	private String status;
	
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}
	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public Date getCasestartDate() {
		return casestartDate;
	}
	public void setCasestartDate(Date casestartDate) {
		this.casestartDate = casestartDate;
	}
	public String getToCourt() {
		return toCourt;
	}
	public void setToCourt(String toCourt) {
		this.toCourt = toCourt;
	}
	public BigDecimal getRequestId() {
		return requestId;
	}
	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
