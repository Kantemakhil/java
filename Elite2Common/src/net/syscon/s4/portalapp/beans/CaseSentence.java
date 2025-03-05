package net.syscon.s4.portalapp.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public class CaseSentence {

	private Long requestId;
	private Long caseId;
	private Long personId;
	
	private Long sentenceId;
	private String category;
	private String sentenceType;
	private BigDecimal fineAmount;
	private Integer consecutiveToLine;
	private Date sentenceDate;
	private Date startDate;
	private String status;
	private String sentenceCalculateReason;
	private List<BigDecimal> offenseIds;
	private List<Term> sentenceTerms;
	
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSentenceType() {
		return sentenceType;
	}
	public void setSentenceType(String sentenceType) {
		this.sentenceType = sentenceType;
	}
	public BigDecimal getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(BigDecimal fineAmount) {
		this.fineAmount = fineAmount;
	}
	public Integer getConsecutiveToLine() {
		return consecutiveToLine;
	}
	public void setConsecutiveToLine(Integer consecutiveToLine) {
		this.consecutiveToLine = consecutiveToLine;
	}
	public Date getSentenceDate() {
		return sentenceDate;
	}
	public void setSentenceDate(Date sentenceDate) {
		this.sentenceDate = sentenceDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<BigDecimal> getOffenseIds() {
		return offenseIds;
	}
	public void setOffenseIds(List<BigDecimal> offenseIds) {
		this.offenseIds = offenseIds;
	}
	public List<Term> getSentenceTerms() {
		return sentenceTerms;
	}
	public void setSentenceTerms(List<Term> sentenceTerms) {
		this.sentenceTerms = sentenceTerms;
	}
	public Long getSentenceId() {
		return sentenceId;
	}
	public void setSentenceId(Long sentenceId) {
		this.sentenceId = sentenceId;
	}
	public String getSentenceCalculateReason() {
		return sentenceCalculateReason;
	}
	public void setSentenceCalculateReason(String sentenceCalculateReason) {
		this.sentenceCalculateReason = sentenceCalculateReason;
	}
	
}
