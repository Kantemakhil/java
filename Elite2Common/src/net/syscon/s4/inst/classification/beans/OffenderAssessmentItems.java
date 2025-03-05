package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the OFFENDER_ASSESSMENT_ITEMS database table.
 * 
 */
public class OffenderAssessmentItems implements Serializable {
	private static final long serialVersionUID = 1L;

	private String answeredFlag;

	private BigDecimal assessmentId;

	private String commentText;

	private Date createDate;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal parentAssessmentId;

	private BigDecimal rank;

	private BigDecimal score;

	private String sealFlag;
	
	private long offenderBookId;

	private long assessmentSeq;

	private long itemSeq;
	
	private String requiredFlag;
	

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getAssessmentSeq() {
		return assessmentSeq;
	}

	public void setAssessmentSeq(long assessmentSeq) {
		this.assessmentSeq = assessmentSeq;
	}

	public long getItemSeq() {
		return itemSeq;
	}

	public void setItemSeq(long itemSeq) {
		this.itemSeq = itemSeq;
	}

	public OffenderAssessmentItems() {
	}

	public String getAnsweredFlag() {
		return this.answeredFlag;
	}

	public void setAnsweredFlag(String answeredFlag) {
		this.answeredFlag = answeredFlag;
	}

	public BigDecimal getAssessmentId() {
		return this.assessmentId;
	}

	public void setAssessmentId(BigDecimal assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public BigDecimal getParentAssessmentId() {
		return this.parentAssessmentId;
	}

	public void setParentAssessmentId(BigDecimal parentAssessmentId) {
		this.parentAssessmentId = parentAssessmentId;
	}

	public BigDecimal getRank() {
		return this.rank;
	}

	public void setRank(BigDecimal rank) {
		this.rank = rank;
	}

	public BigDecimal getScore() {
		return this.score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getRequiredFlag() {
		return requiredFlag;
	}

	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}

}
