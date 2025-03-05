package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderProceedings extends BaseModel implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private BigDecimal offenderProceedingId	;
	private BigDecimal offenderBookId	;
	private String proceedingType	;
	private Date startDate	;
	private String proceedingAgyLocId	;
	private String commentText	;
	private String proceedingStatus	;
	private Date outcomeDate	;
	private Date createDatetime	;
	private String createUserId	;
	private Date modifyDatetime	;
	private String modifyUserId	;
	private String sealFlag	;
	private String teamResponsible;
	private BigDecimal staffResponsible;
	private String orderType;
	private BigDecimal sentenceSeq;
	private String staffUpdateFlag;
	
	private Date caseStartDate	;
	
	@JsonProperty("crtActionRecommendation")
	private String crtActionRecommendation;
	
	@JsonProperty("proceedingPursuantAct")
	private String proceedingPursuantAct;


	private BigDecimal teamId;
	
	private String staffId;
	public Date getCaseStartDate() {
		return caseStartDate;
	}
	public void setCaseStartDate(Date caseStartDate) {
		this.caseStartDate = caseStartDate;
	}
	public BigDecimal getOffenderProceedingId() {
		return offenderProceedingId;
	}
	public void setOffenderProceedingId(BigDecimal offenderProceedingId) {
		this.offenderProceedingId = offenderProceedingId;
	}
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getProceedingType() {
		return proceedingType;
	}
	public void setProceedingType(String proceedingType) {
		this.proceedingType = proceedingType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public String getProceedingAgyLocId() {
		return proceedingAgyLocId;
	}
	public void setProceedingAgyLocId(String proceedingAgyLocId) {
		this.proceedingAgyLocId = proceedingAgyLocId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getProceedingStatus() {
		return proceedingStatus;
	}
	public void setProceedingStatus(String proceedingStatus) {
		this.proceedingStatus = proceedingStatus;
	}
	public Date getOutcomeDate() {
		return outcomeDate;
	}
	public void setOutcomeDate(Date outcomeDate) {
		this.outcomeDate = outcomeDate;
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
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	public String getTeamResponsible() {
		return teamResponsible;
	}
	public void setTeamResponsible(String teamResponsible) {
		this.teamResponsible = teamResponsible;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getStaffUpdateFlag() {
		return staffUpdateFlag;
	}
	public void setStaffUpdateFlag(String staffUpdateFlag) {
		this.staffUpdateFlag = staffUpdateFlag;
	}
	public String getCrtActionRecommendation() {
		return crtActionRecommendation;
	}
	public String getProceedingPursuantAct() {
		return proceedingPursuantAct;
	}
	public void setCrtActionRecommendation(String crtActionRecommendation) {
		this.crtActionRecommendation = crtActionRecommendation;
	}
	public void setProceedingPursuantAct(String proceedingPursuantAct) {
		this.proceedingPursuantAct = proceedingPursuantAct;
	}
	public BigDecimal getStaffResponsible() {
		return staffResponsible;
	}
	
	public String getStaffId() {
		return staffId;
	}
	public void setStaffResponsible(BigDecimal staffResponsible) {
		this.staffResponsible = staffResponsible;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public BigDecimal getTeamId() {
		return teamId;
	}
	public void setTeamId(BigDecimal teamId) {
		this.teamId = teamId;
	}
	public BigDecimal getSentenceSeq() {
		return sentenceSeq;
	}
	public void setSentenceSeq(BigDecimal sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	
}
