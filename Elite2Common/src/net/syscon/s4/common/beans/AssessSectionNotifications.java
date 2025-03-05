package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import net.syscon.s4.inst.classification.beans.Assessments;


/**
 * The persistent class for the ASSESS_SECTION_NOTIFICATIONS database table.
 * 
 */
public class AssessSectionNotifications extends BaseModel {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private Date createDatetime;

	private String nextAssCode;

	public String getNextAssCode() {
		return nextAssCode;
	}

	public void setNextAssCode(String nextAssCode) {
		this.nextAssCode = nextAssCode;
	}

	private String createUserId;

	private Date expiryDate;

	private BigDecimal maxScore;

	private String messageText;

	private BigDecimal minScore;

	private Date modifyDatetime;

	private String modifyUserId;

	private String nextSectionFlag;

	private String scoreType;

	private String sealFlag;

	
	private Long assessmentId;
	

	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}

	

	private BigDecimal nextAssessmentId;
	

	public BigDecimal getNextAssessmentId() {
		return nextAssessmentId;
	}

	public void setNextAssessmentId(BigDecimal nextAssessmentId) {
		this.nextAssessmentId = nextAssessmentId;
	}

	private long scoreSeq;

	private String assCodeTemp;
	

	public long getScoreSeq() {
		return scoreSeq;
	}

	public void setScoreSeq(long scoreSeq) {
		this.scoreSeq = scoreSeq;
	}

	@Override
	public String toString() {
		return "AssessSectionNotifications [activeFlag=" + activeFlag + ", createDatetime=" + createDatetime
				+ ", createUserId=" + createUserId + ", expiryDate=" + expiryDate + ", maxScore=" + maxScore
				+ ", messageText=" + messageText + ", minScore=" + minScore + ", modifyDatetime=" + modifyDatetime
				+ ", modifyUserId=" + modifyUserId + ", nextSectionFlag=" + nextSectionFlag + ", scoreType=" + scoreType
				+ ", sealFlag=" + sealFlag + ", assessmentId=" + assessmentId + ", nextAssessmentId=" + nextAssessmentId
				+ ", scoreSeq=" + scoreSeq + "]";
	}

	public AssessSectionNotifications() {
	}
	
	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	

	
	
public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.assessmentId ^ (this.assessmentId >>> 32)));
		hash = hash * prime + ((int) (this.scoreSeq ^ (this.scoreSeq >>> 32)));
		
		return hash;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	
	
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getMaxScore() {
		return this.maxScore;
	}

	public void setMaxScore(BigDecimal maxScore) {
		this.maxScore = maxScore;
	}

	public String getMessageText() {
		return this.messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public BigDecimal getMinScore() {
		return this.minScore;
	}

	public void setMinScore(BigDecimal minScore) {
		this.minScore = minScore;
	}

	

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getNextSectionFlag() {
		return this.nextSectionFlag;
	}

	public void setNextSectionFlag(String nextSectionFlag) {
		this.nextSectionFlag = nextSectionFlag;
	}

	public String getScoreType() {
		return this.scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getAssCodeTemp() {
		return assCodeTemp;
	}

	public void setAssCodeTemp(String assCodeTemp) {
		this.assCodeTemp = assCodeTemp;
	}


}
