package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class Appeals extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("adjustRequiredFlag")
	private String adjustRequiredFlag;
	
	@JsonProperty("appealId")
	private Long appealId;
	
	@JsonProperty("appealLodgedDate")
	private Date appealLodgedDate;
	
	@JsonProperty("appealPartyCode")
	private String appealPartyCode;
	
	@JsonProperty("appealResultCode")
	private String appealResultCode;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("courtAgyLocId")
	private String courtAgyLocId;
	
	@JsonProperty("courtType")
	private String courtType;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("judgeText")
	private String judgeText;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("upheldDate")
	private Date upheldDate;
	
	@JsonProperty("workflowTempData")
	private String workflowTempData;
	
	@JsonProperty("eventId")
	private Long eventId;
	
	@JsonProperty("offenderChargeId")
	private Long offenderChargeId;
	
	@JsonProperty("applyFlag")
	private String applyFlag;

	public String getAdjustRequiredFlag() {
		return adjustRequiredFlag;
	}

	public void setAdjustRequiredFlag(String adjustRequiredFlag) {
		this.adjustRequiredFlag = adjustRequiredFlag;
	}

	public Long getAppealId() {
		return appealId;
	}

	public void setAppealId(Long appealId) {
		this.appealId = appealId;
	}

	public Date getAppealLodgedDate() {
		return appealLodgedDate;
	}

	public void setAppealLodgedDate(Date appealLodgedDate) {
		this.appealLodgedDate = appealLodgedDate;
	}

	public String getAppealPartyCode() {
		return appealPartyCode;
	}

	public void setAppealPartyCode(String appealPartyCode) {
		this.appealPartyCode = appealPartyCode;
	}

	public String getAppealResultCode() {
		return appealResultCode;
	}

	public void setAppealResultCode(String appealResultCode) {
		this.appealResultCode = appealResultCode;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCourtAgyLocId() {
		return courtAgyLocId;
	}

	public void setCourtAgyLocId(String courtAgyLocId) {
		this.courtAgyLocId = courtAgyLocId;
	}

	public String getCourtType() {
		return courtType;
	}

	public void setCourtType(String courtType) {
		this.courtType = courtType;
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

	public String getJudgeText() {
		return judgeText;
	}

	public void setJudgeText(String judgeText) {
		this.judgeText = judgeText;
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

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getUpheldDate() {
		return upheldDate;
	}

	public void setUpheldDate(Date upheldDate) {
		this.upheldDate = upheldDate;
	}

	public String getWorkflowTempData() {
		return workflowTempData;
	}

	public void setWorkflowTempData(String workflowTempData) {
		this.workflowTempData = workflowTempData;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getOffenderChargeId() {
		return offenderChargeId;
	}

	public void setOffenderChargeId(Long offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
	}

	public String getApplyFlag() {
		return applyFlag;
	}

	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}
	
	
	
}
