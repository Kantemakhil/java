package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentConditionsHty extends BaseModel implements Serializable{
	
	@JsonProperty("commConditionType")
	private String commConditionType; 
	@JsonProperty("description")
	private String description;
	@JsonProperty("commConditionCode")
	private String commConditionCode;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("statusDate")
	private Date statusDate;
	@JsonProperty("longCommentText")
	private String longCommentText; 
	@JsonProperty("provision")
	private String provision; 
	
	private long offenderBookId;

	private long sentenceSeq;

	private long sentenceEventId;
	
	private String conditionStatus; 
	private Date createDatetime;

	private String createUserId;
	private Date modifyDatetime;

	private String modifyUserId;
	private String categoryType;
	
	public String getCommConditionType() {
		return commConditionType;
	}
	public void setCommConditionType(String commConditionType) {
		this.commConditionType = commConditionType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCommConditionCode() {
		return commConditionCode;
	}
	public void setCommConditionCode(String commConditionCode) {
		this.commConditionCode = commConditionCode;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLongCommentText() {
		return longCommentText;
	}
	public void setLongCommentText(String longCommentText) {
		this.longCommentText = longCommentText;
	}
	
	public long getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public long getSentenceSeq() {
		return sentenceSeq;
	}
	public void setSentenceSeq(long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}
	public String getConditionStatus() {
		return conditionStatus;
	}
	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}
	public long getSentenceEventId() {
		return sentenceEventId;
	}
	public void setSentenceEventId(long sentenceEventId) {
		this.sentenceEventId = sentenceEventId;
	}
	@JsonProperty("status")
	private String status;

	public String getProvision() {
		return provision;
	}
	public void setProvision(String provision) {
		this.provision = provision;
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
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	
}
