package net.syscon.s4.portalapp.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseScheduleInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7937654629691693595L;
	private Long caseId;
	private Long personId;
	private String caseInfoNumber;
	private String caseType;
	private Date casestartDate;
	private String toCourt;
	private BigDecimal requestId;
	private String status;
	private CourtScheduleInfo schedule;
	
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
	public CourtScheduleInfo getSchedule() {
		return schedule;
	}
	public void setSchedule(CourtScheduleInfo schedule) {
		this.schedule = schedule;
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
