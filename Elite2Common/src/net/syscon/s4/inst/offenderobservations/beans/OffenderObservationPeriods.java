package net.syscon.s4.inst.offenderobservations.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderObservationPeriods extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("observationType")
	private String observationType;
	@JsonProperty("frequency")
	private BigDecimal frequency;
	@JsonProperty("notificationFlag")
	private String notificationFlag;
	@JsonProperty("notificationTiming")
	private BigDecimal notificationTiming;
	@JsonProperty("linkAssessFlag")
	private String linkAssessFlag;
	@JsonProperty("linkSegDiFlag")
	private String linkSegDiFlag;
	@JsonProperty("linkIncidentFlag")
	private String linkIncidentFlag;
	@JsonProperty("linkOicFlag")
	private String linkOicFlag;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("returnedOutput")
	private BigDecimal returnedOutput;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("obsPeriodId")
	private BigDecimal obsPeriodId;
	@JsonProperty("startDatetime")
    private Date startDatetime;
	@JsonProperty("endReasonCode")
    private String endReasonCode;
	@JsonProperty("endDatetime")
    private Date endDatetime;
	@JsonProperty("statusCode")
    private String statusCode;    
	@JsonProperty("scheduleDate")
	private Date scheduleDate;
	@JsonProperty("obsTypeVersionId")
	private BigDecimal obsTypeVersionId;
	@JsonProperty("obsPeriodSeq")
	private BigDecimal obsPeriodSeq;
	@JsonProperty("obsTypeVersionIdTemp")
	private String obsTypeVersionIdTemp;
	@JsonProperty("checkRecordCount")
	private Integer checkRecordCount;
	
	public String getObservationType() {
		return observationType;
	}
	public void setObservationType(String observationType) {
		this.observationType = observationType;
	}
	public BigDecimal getFrequency() {
		return frequency;
	}
	public void setFrequency(BigDecimal frequency) {
		this.frequency = frequency;
	}
	public String getNotificationFlag() {
		return notificationFlag;
	}
	public void setNotificationFlag(String notificationFlag) {
		this.notificationFlag = notificationFlag;
	}
	public BigDecimal getNotificationTiming() {
		return notificationTiming;
	}
	public void setNotificationTiming(BigDecimal notificationTiming) {
		this.notificationTiming = notificationTiming;
	}
	public String getLinkAssessFlag() {
		return linkAssessFlag;
	}
	public void setLinkAssessFlag(String linkAssessFlag) {
		this.linkAssessFlag = linkAssessFlag;
	}
	public String getLinkSegDiFlag() {
		return linkSegDiFlag;
	}
	public void setLinkSegDiFlag(String linkSegDiFlag) {
		this.linkSegDiFlag = linkSegDiFlag;
	}
	public String getLinkIncidentFlag() {
		return linkIncidentFlag;
	}
	public void setLinkIncidentFlag(String linkIncidentFlag) {
		this.linkIncidentFlag = linkIncidentFlag;
	}
	public String getLinkOicFlag() {
		return linkOicFlag;
	}
	public void setLinkOicFlag(String linkOicFlag) {
		this.linkOicFlag = linkOicFlag;
	}
	public BigDecimal getListSeq() {
		return listSeq;
	}
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
	public BigDecimal getReturnedOutput() {
		return returnedOutput;
	}
	public void setReturnedOutput(BigDecimal returnedOutput) {
		this.returnedOutput = returnedOutput;
	}
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public BigDecimal getObsPeriodId() {
		return obsPeriodId;
	}
	public void setObsPeriodId(BigDecimal obsPeriodId) {
		this.obsPeriodId = obsPeriodId;
	}
	public Date getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}
	public String getEndReasonCode() {
		return endReasonCode;
	}
	public void setEndReasonCode(String endReasonCode) {
		this.endReasonCode = endReasonCode;
	}
	public Date getEndDatetime() {
		return endDatetime;
	}
	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public BigDecimal getObsTypeVersionId() {
		return obsTypeVersionId;
	}
	public void setObsTypeVersionId(BigDecimal obsTypeVersionId) {
		this.obsTypeVersionId = obsTypeVersionId;
	}
	public BigDecimal getObsPeriodSeq() {
		return obsPeriodSeq;
	}
	public void setObsPeriodSeq(BigDecimal obsPeriodSeq) {
		this.obsPeriodSeq = obsPeriodSeq;
	}
	public String getObsTypeVersionIdTemp() {
		return obsTypeVersionIdTemp;
	}
	public void setObsTypeVersionIdTemp(String obsTypeVersionIdTemp) {
		this.obsTypeVersionIdTemp = obsTypeVersionIdTemp;
	}

	public Integer getCheckRecordCount() {
		return checkRecordCount;
	}
	public void setCheckRecordCount(Integer checkRecordCount) {
		this.checkRecordCount = checkRecordCount;
	}
}
