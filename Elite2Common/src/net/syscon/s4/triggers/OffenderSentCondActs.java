package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentCondActs extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long offenderSentConditionId;
	private String activityCode;
	private String commentText;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private Long offenderSentCondActId;
	private String condActType;
	private String details;
	private BigDecimal age;
	private String activityStatus;
	private Long programId;
	private String sealFlag;

	public Long getOffenderSentConditionId() {
		return offenderSentConditionId;
	}

	public void setOffenderSentConditionId(Long offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Long getOffenderSentCondActId() {
		return offenderSentCondActId;
	}

	public void setOffenderSentCondActId(Long offenderSentCondActId) {
		this.offenderSentCondActId = offenderSentCondActId;
	}

	public String getCondActType() {
		return condActType;
	}

	public void setCondActType(String condActType) {
		this.condActType = condActType;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public BigDecimal getAge() {
		return age;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
