package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class Adjustments extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("keyDatesAdjustId")
	private Long keyDatesAdjustId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("sentenceAdjustCode")
	private String sentenceAdjustCode;
	
	@JsonProperty("adjustFromDate")
	private Date adjustFromDate;
	
	@JsonProperty("adjustToDate")
	private Date adjustToDate;
	
	@JsonProperty("adjustDays")
	private Integer adjustDays;
	
	@JsonProperty("adjustStatus")
	private String adjustStatus;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("adjustDate")
	private Date adjustDate;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;

	public Long getKeyDatesAdjustId() {
		return keyDatesAdjustId;
	}

	public void setKeyDatesAdjustId(Long keyDatesAdjustId) {
		this.keyDatesAdjustId = keyDatesAdjustId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getSentenceAdjustCode() {
		return sentenceAdjustCode;
	}

	public void setSentenceAdjustCode(String sentenceAdjustCode) {
		this.sentenceAdjustCode = sentenceAdjustCode;
	}

	public Date getAdjustFromDate() {
		return adjustFromDate;
	}

	public void setAdjustFromDate(Date adjustFromDate) {
		this.adjustFromDate = adjustFromDate;
	}

	public Date getAdjustToDate() {
		return adjustToDate;
	}

	public void setAdjustToDate(Date adjustToDate) {
		this.adjustToDate = adjustToDate;
	}

	public Integer getAdjustDays() {
		return adjustDays;
	}

	public void setAdjustDays(Integer adjustDays) {
		this.adjustDays = adjustDays;
	}

	public String getAdjustStatus() {
		return adjustStatus;
	}

	public void setAdjustStatus(String adjustStatus) {
		this.adjustStatus = adjustStatus;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
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
	
	
}
