package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class HdcBoardDecisions extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer decisionSeq;
	private Long hdcRequestReferralId;
	private String commentText;
	private String boardAttendees;
	private Long chairStaffId;
	private String boardRecommendation;
	private String attachCommentFlag;
	private String successfulRotlFlag;
	private String offendingBehaviourFlag;
	private String homeCircumstancesFlag;
	private String medicalOfficerCommentFlag;
	private String probationCommentFlag;
	private String throughcareTeamCommentFlag;
	private String wingOfficerCommentFlag;
	private String likelyToFailCurfewFlag;
	private String reoffendingProbabilityFlag;
	private String noSuitableAccommodationFlag;
	private String threatToPublicSafetyFlag;
	private String otherFlag;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
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

	public String getBoardAttendees() {
		return boardAttendees;
	}

	public void setBoardAttendees(String boardAttendees) {
		this.boardAttendees = boardAttendees;
	}

	public Long getChairStaffId() {
		return chairStaffId;
	}

	public void setChairStaffId(Long chairStaffId) {
		this.chairStaffId = chairStaffId;
	}

	public String getBoardRecommendation() {
		return boardRecommendation;
	}

	public void setBoardRecommendation(String boardRecommendation) {
		this.boardRecommendation = boardRecommendation;
	}

	public String getAttachCommentFlag() {
		return attachCommentFlag;
	}

	public void setAttachCommentFlag(String attachCommentFlag) {
		this.attachCommentFlag = attachCommentFlag;
	}

	public String getSuccessfulRotlFlag() {
		return successfulRotlFlag;
	}

	public void setSuccessfulRotlFlag(String successfulRotlFlag) {
		this.successfulRotlFlag = successfulRotlFlag;
	}

	public String getOffendingBehaviourFlag() {
		return offendingBehaviourFlag;
	}

	public void setOffendingBehaviourFlag(String offendingBehaviourFlag) {
		this.offendingBehaviourFlag = offendingBehaviourFlag;
	}

	public String getHomeCircumstancesFlag() {
		return homeCircumstancesFlag;
	}

	public void setHomeCircumstancesFlag(String homeCircumstancesFlag) {
		this.homeCircumstancesFlag = homeCircumstancesFlag;
	}

	public String getMedicalOfficerCommentFlag() {
		return medicalOfficerCommentFlag;
	}

	public void setMedicalOfficerCommentFlag(String medicalOfficerCommentFlag) {
		this.medicalOfficerCommentFlag = medicalOfficerCommentFlag;
	}

	public String getProbationCommentFlag() {
		return probationCommentFlag;
	}

	public void setProbationCommentFlag(String probationCommentFlag) {
		this.probationCommentFlag = probationCommentFlag;
	}

	public String getThroughcareTeamCommentFlag() {
		return throughcareTeamCommentFlag;
	}

	public void setThroughcareTeamCommentFlag(String throughcareTeamCommentFlag) {
		this.throughcareTeamCommentFlag = throughcareTeamCommentFlag;
	}

	public String getWingOfficerCommentFlag() {
		return wingOfficerCommentFlag;
	}

	public void setWingOfficerCommentFlag(String wingOfficerCommentFlag) {
		this.wingOfficerCommentFlag = wingOfficerCommentFlag;
	}

	public String getLikelyToFailCurfewFlag() {
		return likelyToFailCurfewFlag;
	}

	public void setLikelyToFailCurfewFlag(String likelyToFailCurfewFlag) {
		this.likelyToFailCurfewFlag = likelyToFailCurfewFlag;
	}

	public String getReoffendingProbabilityFlag() {
		return reoffendingProbabilityFlag;
	}

	public void setReoffendingProbabilityFlag(String reoffendingProbabilityFlag) {
		this.reoffendingProbabilityFlag = reoffendingProbabilityFlag;
	}

	public String getNoSuitableAccommodationFlag() {
		return noSuitableAccommodationFlag;
	}

	public void setNoSuitableAccommodationFlag(String noSuitableAccommodationFlag) {
		this.noSuitableAccommodationFlag = noSuitableAccommodationFlag;
	}

	public String getThreatToPublicSafetyFlag() {
		return threatToPublicSafetyFlag;
	}

	public void setThreatToPublicSafetyFlag(String threatToPublicSafetyFlag) {
		this.threatToPublicSafetyFlag = threatToPublicSafetyFlag;
	}

	public String getOtherFlag() {
		return otherFlag;
	}

	public void setOtherFlag(String otherFlag) {
		this.otherFlag = otherFlag;
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
