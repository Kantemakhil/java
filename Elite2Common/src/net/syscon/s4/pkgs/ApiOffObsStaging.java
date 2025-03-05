package net.syscon.s4.pkgs;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class ApiOffObsStaging extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long logId;
	private Long offenderBookId;
	private String observationType;
	private Long obsPeriodId;
	private Long frequency;
	private String statusCode;
	private Date modifyDatetime;
	private String logMessage;
	private Date createDatetime;
	private String createUserId;
	private String modifyUserId;
	private String sealFlag;
	private String tableName;
	private String trigEvent;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getObservationType() {
		return observationType;
	}

	public void setObservationType(String observationType) {
		this.observationType = observationType;
	}

	public Long getObsPeriodId() {
		return obsPeriodId;
	}

	public void setObsPeriodId(Long obsPeriodId) {
		this.obsPeriodId = obsPeriodId;
	}

	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTrigEvent() {
		return trigEvent;
	}

	public void setTrigEvent(String trigEvent) {
		this.trigEvent = trigEvent;
	}

}
