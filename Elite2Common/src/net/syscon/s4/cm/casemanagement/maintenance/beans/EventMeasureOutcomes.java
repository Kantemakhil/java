package net.syscon.s4.cm.casemanagement.maintenance.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class EventMeasureOutcomes extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("eventMeasureId")
	private Long eventMeasureId;
	
	@JsonProperty("outcomeCode")
	private String outcomeCode;
	
	@JsonProperty("setCounterFlag")
	private String setCounterFlag;  
	
	@JsonProperty("promptUserFlag")
	private String promptUserFlag;
	
	@JsonProperty("listSeq")
	private Long listSeq; 
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;
	
	@JsonProperty("createDate")
	private Date createDate; 
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("updateOnContactLog")
	private String updateOnContactLog;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("rowId")
	String rowId;
	
	private Long rowIdNo;
	
	@JsonProperty("cancelFlag")
	private String cancelFlag;

	public Long getEventMeasureId() {
		return eventMeasureId;
	}

	public void setEventMeasureId(final Long eventMeasureId) {
		this.eventMeasureId = eventMeasureId;
	}

	public String getOutcomeCode() {
		return outcomeCode;
	}

	public void setOutcomeCode(final String outcomeCode) {
		this.outcomeCode = outcomeCode;
	}

	public String getSetCounterFlag() {
		return setCounterFlag;
	}

	public void setSetCounterFlag(final String setCounterFlag) {
		this.setCounterFlag = setCounterFlag;
	}

	public String getPromptUserFlag() {
		return promptUserFlag;
	}

	public void setPromptUserFlag(final String promptUserFlag) {
		this.promptUserFlag = promptUserFlag;
	}

	public Long getListSeq() {
		return listSeq;
	}

	public void setListSeq(final Long listSeq) {
		this.listSeq = listSeq;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getUpdateOnContactLog() {
		return updateOnContactLog;
	}

	public void setUpdateOnContactLog(final String updateOnContactLog) {
		this.updateOnContactLog = updateOnContactLog;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	public String getRowId() {
		return rowId;
	}

	public void setRowId(final String rowId) {
		this.rowId = rowId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getRowIdNo() {
		return rowIdNo;
	}

	public void setRowIdNo(Long rowIdNo) {
		this.rowIdNo = rowIdNo;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	
	
}
