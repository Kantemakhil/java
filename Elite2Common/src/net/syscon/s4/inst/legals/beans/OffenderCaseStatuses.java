package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.OffenderCas;


/**
 * The persistent class for the OFFENDER_CASE_STATUSES database table.
 * 
 */
public class OffenderCaseStatuses implements Serializable {
	private static final long serialVersionUID = 1L;

	private long offenderCaseStatusId;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private String statusUpdateComment;

	private Date statusUpdateDate;

	private String statusUpdateReason;

	private BigDecimal statusUpdateStaffId;
	private String staffName;

	@JsonProperty("caseId")
	private Long caseId;
	//bi-directional many-to-one association to OffenderCas
	private OffenderCas offenderCas;
	
	@JsonProperty("offenderSentCondId")
	private Long offenderSentCondId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("sentenceSeq")
	private Long sentenceSeq;
	
	@JsonProperty("previousStatus")
	private String previousStatus;
	
	@JsonProperty("previousStatusDesc")
	private String previousStatusDesc;
	
	public String getPreviousStatusDesc() {
		return previousStatusDesc;
	}

	public void setPreviousStatusDesc(final String previousStatusDesc) {
		this.previousStatusDesc = previousStatusDesc;
	}
	
	public String getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(final String previousStatus) {
		this.previousStatus = previousStatus;
	}
	
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(final Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}
	
	public Long getOffenderSentCondId() {
		return offenderSentCondId;
	}

	public void setOffenderSentCondId(final Long offenderSentCondId) {
		this.offenderSentCondId = offenderSentCondId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public OffenderCaseStatuses() {
	}

	public long getOffenderCaseStatusId() {
		return this.offenderCaseStatusId;
	}

	public void setOffenderCaseStatusId(long offenderCaseStatusId) {
		this.offenderCaseStatusId = offenderCaseStatusId;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getStatusUpdateComment() {
		return this.statusUpdateComment;
	}

	public void setStatusUpdateComment(String statusUpdateComment) {
		this.statusUpdateComment = statusUpdateComment;
	}

	public Date getStatusUpdateDate() {
		return this.statusUpdateDate;
	}

	public void setStatusUpdateDate(Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public String getStatusUpdateReason() {
		return this.statusUpdateReason;
	}

	public void setStatusUpdateReason(String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	public BigDecimal getStatusUpdateStaffId() {
		return this.statusUpdateStaffId;
	}

	public void setStatusUpdateStaffId(BigDecimal statusUpdateStaffId) {
		this.statusUpdateStaffId = statusUpdateStaffId;
	}

	public OffenderCas getOffenderCas() {
		return this.offenderCas;
	}

	public void setOffenderCas(OffenderCas offenderCas) {
		this.offenderCas = offenderCas;
	}

}
