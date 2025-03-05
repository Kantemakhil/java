package net.syscon.s4.cm.weeklyactivityplans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OcrwapstReportBean extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("userName")
	private String userName;

	@JsonProperty("offenderName")
	private String offenderName;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("emTagId")
	private BigDecimal emTagId;

	@JsonProperty("comment")
	private String comment;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("emStrapSize")
	private String emStrapSize;

	@JsonProperty("address")
	private String address;

	@JsonProperty("wapVersion")
	private String wapVersion;

	@JsonProperty("emDailyChargingPeriod")
	private String emDailyChargingPeriod;
	
	@JsonProperty("scheduledActivitiesList")
	private List<WeeklyActivityPlansHty> scheduledActivitiesList;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("week")
	private String week;
	
	@JsonProperty("wapStartDate")
	private Date wapStartDate;
	
	@JsonProperty("wapEndDate")
	private Date wapEndDate;
	
	@JsonProperty("caseloadDesc")
	private String caseloadDesc;
	
	@JsonProperty("report")
	private byte[] report;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("emTagData")
	private String emTagData;
	
	@JsonProperty("emTagStartTime")
	private Date emTagStartTime;
	
	@JsonProperty("emTagEndTime")
	private Date emTagEndTime;
	
	@JsonProperty("confSytPrfValue")
	private String confSytPrfValue; 
	
	@JsonProperty("pidLabel")
	private String pidLabel;
	
	@JsonProperty("emtagLabel")
	private String emtagLabel;

	@JsonProperty("emtagStrapSizeLabel")
	private String emtagStrapSizeLabel;
	
	@JsonProperty("commentLabel")
	private String commentLabel;
	
	@JsonProperty("titleLabel")
	private String titleLabel; 
	
	@JsonProperty("reportCreatedLabel")
	private String reportCreatedLabel; 
	
	@JsonProperty("createdLabel")
	private String createdLabel; 
	
	@JsonProperty("offenderNameLabel")
	private String offenderNameLabel; 
	
	@JsonProperty("offenderPhoneLabel")
	private String offenderPhoneLabel; 
	
	@JsonProperty("offenderAddressLabel")
	private String offenderAddressLabel; 
	
	@JsonProperty("weekLabel")
	private String weekLabel; 
	
	@JsonProperty("wapVersionLabel")
	private String wapVersionLabel; 
	
	@JsonProperty("emDailyChargingLabel")
	private String emDailyChargingLabel; 
	
	@JsonProperty("scheduledActivitiesLabel")
	private String scheduledActivitiesLabel; 	
	
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getOffenderName() {
		return offenderName;
	}

	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public BigDecimal getEmTagId() {
		return emTagId;
	}

	public void setEmTagId(BigDecimal emTagId) {
		this.emTagId = emTagId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWapVersion() {
		return wapVersion;
	}

	public void setWapVersion(String wapVersion) {
		this.wapVersion = wapVersion;
	}

	public List<WeeklyActivityPlansHty> getScheduledActivitiesList() {
		return scheduledActivitiesList;
	}

	public void setScheduledActivitiesList(List<WeeklyActivityPlansHty> scheduledActivitiesList) {
		this.scheduledActivitiesList = scheduledActivitiesList;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public Date getWapStartDate() {
		return wapStartDate;
	}

	public void setWapStartDate(Date wapStartDate) {
		this.wapStartDate = wapStartDate;
	}

	public Date getWapEndDate() {
		return wapEndDate;
	}

	public void setWapEndDate(Date wapEndDate) {
		this.wapEndDate = wapEndDate;
	}

	public String getCaseloadDesc() {
		return caseloadDesc;
	}

	public void setCaseloadDesc(String caseloadDesc) {
		this.caseloadDesc = caseloadDesc;
	}

	public byte[] getReport() {
		return report;
	}

	public void setReport(byte[] report) {
		this.report = report;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getEmStrapSize() {
		return emStrapSize;
	}

	public void setEmStrapSize(String emStrapSize) {
		this.emStrapSize = emStrapSize;
	}

	public String getEmDailyChargingPeriod() {
		return emDailyChargingPeriod;
	}

	public void setEmDailyChargingPeriod(String emDailyChargingPeriod) {
		this.emDailyChargingPeriod = emDailyChargingPeriod;
	}

	public String getEmTagData() {
		return emTagData;
	}

	public void setEmTagData(String emTagData) {
		this.emTagData = emTagData;
	}

	public Date getEmTagStartTime() {
		return emTagStartTime;
	}

	public void setEmTagStartTime(Date emTagStartTime) {
		this.emTagStartTime = emTagStartTime;
	}

	public Date getEmTagEndTime() {
		return emTagEndTime;
	}

	public void setEmTagEndTime(Date emTagEndTime) {
		this.emTagEndTime = emTagEndTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getConfSytPrfValue() {
		return confSytPrfValue;
	}

	public void setConfSytPrfValue(String confSytPrfValue) {
		this.confSytPrfValue = confSytPrfValue;
	}
	
	public String getPidLabel() {
		return pidLabel;
	}

	public void setPidLabel(String pidLabel) {
		this.pidLabel = pidLabel;
	}

	public String getEmtagLabel() {
		return emtagLabel;
	}

	public void setEmtagLabel(String emtagLabel) {
		this.emtagLabel = emtagLabel;
	}

	public String getEmtagStrapSizeLabel() {
		return emtagStrapSizeLabel;
	}

	public void setEmtagStrapSizeLabel(String emtagStrapSizeLabel) {
		this.emtagStrapSizeLabel = emtagStrapSizeLabel;
	}

	public String getCommentLabel() {
		return commentLabel;
	}

	public void setCommentLabel(String commentLabel) {
		this.commentLabel = commentLabel;
	}
	
	public String getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}

	public String getReportCreatedLabel() {
		return reportCreatedLabel;
	}

	public void setReportCreatedLabel(String reportCreatedLabel) {
		this.reportCreatedLabel = reportCreatedLabel;
	}

	public String getCreatedLabel() {
		return createdLabel;
	}

	public void setCreatedLabel(String createdLabel) {
		this.createdLabel = createdLabel;
	}

	public String getOffenderNameLabel() {
		return offenderNameLabel;
	}

	public void setOffenderNameLabel(String offenderNameLabel) {
		this.offenderNameLabel = offenderNameLabel;
	}

	public String getOffenderPhoneLabel() {
		return offenderPhoneLabel;
	}

	public void setOffenderPhoneLabel(String offenderPhoneLabel) {
		this.offenderPhoneLabel = offenderPhoneLabel;
	}

	public String getOffenderAddressLabel() {
		return offenderAddressLabel;
	}

	public void setOffenderAddressLabel(String offenderAddressLabel) {
		this.offenderAddressLabel = offenderAddressLabel;
	}

	public String getWeekLabel() {
		return weekLabel;
	}

	public void setWeekLabel(String weekLabel) {
		this.weekLabel = weekLabel;
	}

	public String getWapVersionLabel() {
		return wapVersionLabel;
	}

	public void setWapVersionLabel(String wapVersionLabel) {
		this.wapVersionLabel = wapVersionLabel;
	}

	public String getEmDailyChargingLabel() {
		return emDailyChargingLabel;
	}

	public void setEmDailyChargingLabel(String emDailyChargingLabel) {
		this.emDailyChargingLabel = emDailyChargingLabel;
	}
	
	public String getScheduledActivitiesLabel() {
		return scheduledActivitiesLabel;
	}

	public void setScheduledActivitiesLabel(String scheduledActivitiesLabel) {
		this.scheduledActivitiesLabel = scheduledActivitiesLabel;
	}

	
}
