package net.syscon.s4.community_supervision_tiers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MaintainTierDefaultEvents implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("tierLevelcode")
	private String tierLevelcode;
	
	@JsonProperty("scheduleType")
	private String scheduleType;
	
	@JsonProperty("scheduleSubType")
	private String scheduleSubType;
	
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
	
	@JsonProperty("uiRules")
	private String uiRules;
	
	@JsonProperty("caseLoadId")
	private String caseLoadId;
	
	@JsonProperty("startDate")
	private Date startDate;
	
	@JsonProperty("startTime")
	private Date startTime;
	
	@JsonProperty("endTime")
	private Date endTime;
	
	@JsonProperty("emailFlag")
	private String emailFlag;
	
	@JsonProperty("smsFlag")
	private String smsFlag;
	
	@JsonProperty("emailSchHoursBefore")
	private Long emailSchHoursBefore;
	
	@JsonProperty("smsSchHoursBefore")
	private Long smsSchHoursBefore;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("isSeriesFalg")
	private String isSeriesFalg;
	
	@JsonProperty("excludeFlag")
	private String excludeFlag;
	
	@JsonProperty("staffName")
	private String staffName;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("emailAddressCount")
	private Integer emailAddressCount;
	
	@JsonProperty("phoneNumberCount")
	private Integer phoneNumberCount;
	
	@JsonProperty("eventId")
	private Integer eventId;
	
	@JsonProperty("eventStatus")
	private String eventStatus;
	
	@JsonProperty("offenderTierLevelId")
	private BigDecimal offenderTierLevelId;
	
	@JsonProperty("versionNo")
	private Long versionNo;
	
	@JsonProperty("tierEventSchVersionId")
	private Long tierEventSchVersionId;
	
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	public String getScheduleSubType() {
		return scheduleSubType;
	}
	public void setScheduleSubType(String scheduleSubType) {
		this.scheduleSubType = scheduleSubType;
	}
	public String getTierLevelcode() {
		return tierLevelcode;
	}
	public void setTierLevelcode(String tierLevelcode) {
		this.tierLevelcode = tierLevelcode;
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
	public String getUiRules() {
		return uiRules;
	}
	public void setUiRules(String uiRules) {
		this.uiRules = uiRules;
	}
	public String getCaseLoadId() {
		return caseLoadId;
	}
	public void setCaseLoadId(String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getEmailFlag() {
		return emailFlag;
	}
	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}
	public String getSmsFlag() {
		return smsFlag;
	}
	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}
	public Long getEmailSchHoursBefore() {
		return emailSchHoursBefore;
	}
	public void setEmailSchHoursBefore(Long emailSchHoursBefore) {
		this.emailSchHoursBefore = emailSchHoursBefore;
	}
	public Long getSmsSchHoursBefore() {
		return smsSchHoursBefore;
	}
	public void setSmsSchHoursBefore(Long smsSchHoursBefore) {
		this.smsSchHoursBefore = smsSchHoursBefore;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getIsSeriesFalg() {
		return isSeriesFalg;
	}
	public void setIsSeriesFalg(String isSeriesFalg) {
		this.isSeriesFalg = isSeriesFalg;
	}
	public String getExcludeFlag() {
		return excludeFlag;
	}
	public void setExcludeFlag(String excludeFlag) {
		this.excludeFlag = excludeFlag;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Long getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public Integer getEmailAddressCount() {
		return emailAddressCount;
	}
	public void setEmailAddressCount(Integer emailAddressCount) {
		this.emailAddressCount = emailAddressCount;
	}
	public Integer getPhoneNumberCount() {
		return phoneNumberCount;
	}
	public void setPhoneNumberCount(Integer phoneNumberCount) {
		this.phoneNumberCount = phoneNumberCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	public BigDecimal getOffenderTierLevelId() {
		return offenderTierLevelId;
	}
	public void setOffenderTierLevelId(BigDecimal offenderTierLevelId) {
		this.offenderTierLevelId = offenderTierLevelId;
	}
	public Long getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}
	public Long getTierEventSchVersionId() {
		return tierEventSchVersionId;
	}
	public void setTierEventSchVersionId(Long tierEventSchVersionId) {
		this.tierEventSchVersionId = tierEventSchVersionId;
	}
	
	
}
