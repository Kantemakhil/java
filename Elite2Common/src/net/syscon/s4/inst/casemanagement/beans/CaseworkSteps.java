package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.Date;

public class CaseworkSteps implements Serializable {
	private static final long serialVersionUID = 1L;

	private String casSteComment;

	private Date completionDate;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String plannedStep;

	private String sealFlag;

	private Date startDate;

	private Date targetDate;
	
	private long offenderBookId;

	private long casePlanId;

	private long detailSeq;

	private long caseworkSeq;

	public CaseworkSteps() {
		// CaseworkSteps
	}

	public String getCasSteComment() {
		return this.casSteComment;
	}

	public void setCasSteComment(String casSteComment) {
		this.casSteComment = casSteComment;
	}

	public Date getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
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

	public String getPlannedStep() {
		return this.plannedStep;
	}

	public void setPlannedStep(String plannedStep) {
		this.plannedStep = plannedStep;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getTargetDate() {
		return this.targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public long getOffenderBookId() {
		return this.offenderBookId;
	}
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public long getCasePlanId() {
		return this.casePlanId;
	}
	public void setCasePlanId(long casePlanId) {
		this.casePlanId = casePlanId;
	}
	public long getDetailSeq() {
		return this.detailSeq;
	}
	public void setDetailSeq(long detailSeq) {
		this.detailSeq = detailSeq;
	}
	public long getCaseworkSeq() {
		return this.caseworkSeq;
	}
	public void setCaseworkSeq(long caseworkSeq) {
		this.caseworkSeq = caseworkSeq;
	}

}
