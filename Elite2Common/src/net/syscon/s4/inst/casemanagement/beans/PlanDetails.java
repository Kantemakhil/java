package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PlanDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date completionDate;

	private String conditionCodes;

	private Date createDatetime;

	private String createUserId;

	private String goalSubType;

	private String goalType;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal offCcId;

	private String plaDetComment;

	private String sealFlag;

	private String status;

	private Date targetDate;

	private long offenderBookId;

	private long casePlanId;

	private long detailSeq;

	public PlanDetails() {
		// PlanDetails
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getCasePlanId() {
		return casePlanId;
	}

	public void setCasePlanId(long casePlanId) {
		this.casePlanId = casePlanId;
	}

	public long getDetailSeq() {
		return detailSeq;
	}

	public void setDetailSeq(long detailSeq) {
		this.detailSeq = detailSeq;
	}

	public Date getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public String getConditionCodes() {
		return this.conditionCodes;
	}

	public void setConditionCodes(String conditionCodes) {
		this.conditionCodes = conditionCodes;
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

	public String getGoalSubType() {
		return this.goalSubType;
	}

	public void setGoalSubType(String goalSubType) {
		this.goalSubType = goalSubType;
	}

	public String getGoalType() {
		return this.goalType;
	}

	public void setGoalType(String goalType) {
		this.goalType = goalType;
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

	public BigDecimal getOffCcId() {
		return this.offCcId;
	}

	public void setOffCcId(BigDecimal offCcId) {
		this.offCcId = offCcId;
	}

	public String getPlaDetComment() {
		return this.plaDetComment;
	}

	public void setPlaDetComment(String plaDetComment) {
		this.plaDetComment = plaDetComment;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTargetDate() {
		return this.targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

}
