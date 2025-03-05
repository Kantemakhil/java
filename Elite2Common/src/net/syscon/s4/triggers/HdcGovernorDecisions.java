package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class HdcGovernorDecisions extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer decisionSeq;
	private Long hdcRequestReferralId;
	private String commentText;
	private String createUserId;
	private Date createDatetime;
	private String governorsDecision;
	private Integer governorsStaffId;
	private String attachCommentFlag;
	private String referralTo;
	private Integer referredStaffId;
	private String modifyUserId;
	private Date modifyDatetime;
	private String messageId;
	private String sealFlag;

	public Integer getDecisionSeq() {
		return decisionSeq;
	}

	public void setDecisionSeq(Integer decisionSeq) {
		this.decisionSeq = decisionSeq;
	}

	public Long getHdcRequestReferralId() {
		return hdcRequestReferralId;
	}

	public void setHdcRequestReferralId(Long hdcRequestReferralId) {
		this.hdcRequestReferralId = hdcRequestReferralId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getGovernorsDecision() {
		return governorsDecision;
	}

	public void setGovernorsDecision(String governorsDecision) {
		this.governorsDecision = governorsDecision;
	}

	public Integer getGovernorsStaffId() {
		return governorsStaffId;
	}

	public void setGovernorsStaffId(Integer governorsStaffId) {
		this.governorsStaffId = governorsStaffId;
	}

	public String getAttachCommentFlag() {
		return attachCommentFlag;
	}

	public void setAttachCommentFlag(String attachCommentFlag) {
		this.attachCommentFlag = attachCommentFlag;
	}

	public String getReferralTo() {
		return referralTo;
	}

	public void setReferralTo(String referralTo) {
		this.referralTo = referralTo;
	}

	public Integer getReferredStaffId() {
		return referredStaffId;
	}

	public void setReferredStaffId(Integer referredStaffId) {
		this.referredStaffId = referredStaffId;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
