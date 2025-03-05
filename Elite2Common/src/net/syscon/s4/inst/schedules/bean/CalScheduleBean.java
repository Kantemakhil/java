package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import net.syscon.s4.common.beans.BaseModel;

public class CalScheduleBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date startTime;
	private Date endTime;
	private String startTimezone;
	private String endTimezone;
	private String subject;
	private String isAllDay;
	private String recurrenceRule;
	private String recurrenceID;
	private String recurrenceException;
	private BigDecimal offenderBookId;
	private Date eventDate;
	private String location;
	private String description;
	private String categoryColor;
	private String caseLoadId;
	private Map<String, Object> profileMap;
	private String eventType;
	private String eventSubType;
	private String inChargeStaffName;
	private String eventSubTypeDesc;
	private String toAgyLocId;
	private Integer seriesId;
	private String emailFlag;
	private String smsFlag;
	private Integer emailScheduleHoursBefore;
	private Integer smsScheduleHoursBefore;
	private BigDecimal eventId;
	private String eventOutCome;
	private Date actualEndTime;
	private String eventClass;
	private Integer offPrgrefId;
	private String sealFlag;
	private String tierLevelCode;
	private BigDecimal offenderTierLevelId;
	private String toIntLocLevel1Code;
	private String recordSource;
	private String eventStatus;
	private String appearanceLocation;
	private String appearanceType;
	private String appearanceLocationCode;
	private String cancelReason;
	private String comment;
	private String matter;	
	private Integer linkData;
	private Long versionNo;
	private String currentSelectedViewClass;
	
	private Date departStartTime;
	
	private Date returnTime;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getAppearanceLocationCode() {
		return appearanceLocationCode;
	}

	public void setAppearanceLocationCode(String appearanceLocationCode) {
		this.appearanceLocationCode = appearanceLocationCode;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getRecordSource() {
		return recordSource;
	}

	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
	}

	public String getToIntLocLevel1Code() {
		return toIntLocLevel1Code;
	}

	public void setToIntLocLevel1Code(String toIntLocLevel1Code) {
		this.toIntLocLevel1Code = toIntLocLevel1Code;
	}

	public Integer getOffPrgrefId() {
		return offPrgrefId;
	}

	public void setOffPrgrefId(Integer offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
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

	public String getStartTimezone() {
		return startTimezone;
	}

	public void setStartTimezone(String startTimezone) {
		this.startTimezone = startTimezone;
	}

	public String getEndTimezone() {
		return endTimezone;
	}

	public void setEndTimezone(String endTimezone) {
		this.endTimezone = endTimezone;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getIsAllDay() {
		return isAllDay;
	}

	public void setIsAllDay(String isAllDay) {
		this.isAllDay = isAllDay;
	}

	public String getRecurrenceRule() {
		return recurrenceRule;
	}

	public void setRecurrenceRule(String recurrenceRule) {
		this.recurrenceRule = recurrenceRule;
	}

	public String getRecurrenceID() {
		return recurrenceID;
	}

	public void setRecurrenceID(String recurrenceID) {
		this.recurrenceID = recurrenceID;
	}

	public String getRecurrenceException() {
		return recurrenceException;
	}

	public void setRecurrenceException(String recurrenceException) {
		this.recurrenceException = recurrenceException;
	}

	public String getCategoryColor() {
		return categoryColor;
	}

	public void setCategoryColor(String categoryColor) {
		this.categoryColor = categoryColor;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getCaseLoadId() {
		return caseLoadId;
	}

	
	public void setCaseLoadId(String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	public Map<String, Object> getProfileMap() {
		return profileMap;
	}

	public void setProfileMap(Map<String, Object> profileMap) {
		this.profileMap = profileMap;
	}

	public String getInChargeStaffName() {
		return inChargeStaffName;
	}

	public void setInChargeStaffName(String inChargeStaffName) {
		this.inChargeStaffName = inChargeStaffName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventSubType() {
		return eventSubType;
	}

	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public String getEventSubTypeDesc() {
		return eventSubTypeDesc;
	}

	public void setEventSubTypeDesc(String eventSubTypeDesc) {
		this.eventSubTypeDesc = eventSubTypeDesc;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public Integer getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public Integer getEmailScheduleHoursBefore() {
		return emailScheduleHoursBefore;
	}

	public void setEmailScheduleHoursBefore(Integer emailScheduleHoursBefore) {
		this.emailScheduleHoursBefore = emailScheduleHoursBefore;
	}

	public Integer getSmsScheduleHoursBefore() {
		return smsScheduleHoursBefore;
	}

	public void setSmsScheduleHoursBefore(Integer smsScheduleHoursBefore) {
		this.smsScheduleHoursBefore = smsScheduleHoursBefore;
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

	public BigDecimal getEventId() {
		return eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public String getEventOutCome() {
		return eventOutCome;
	}

	public void setEventOutCome(String eventOutCome) {
		this.eventOutCome = eventOutCome;
	}

	public Date getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	public String getEventClass() {
		return eventClass;
	}

	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getTierLevelCode() {
		return tierLevelCode;
	}

	public void setTierLevelCode(String tierLevelCode) {
		this.tierLevelCode = tierLevelCode;
	}

	public BigDecimal getOffenderTierLevelId() {
		return offenderTierLevelId;
	}

	public void setOffenderTierLevelId(BigDecimal offenderTierLevelId) {
		this.offenderTierLevelId = offenderTierLevelId;
	}
	
	public String getAppearanceType() {
		return appearanceType;
	}
	
	public void setAppearanceType(String appearanceType) {
		this.appearanceType = appearanceType;
	}
	
	public String getAppearanceLocation() {
		return appearanceLocation;
	}
	
	public void setAppearanceLocation(String appearanceLocation) {
		this.appearanceLocation = appearanceLocation;
	}

	public Integer getLinkData() {
		return linkData;
	}

	public void setLinkData(Integer linkData) {
		this.linkData = linkData;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public String getCurrentSelectedViewClass() {
		return currentSelectedViewClass;
	}

	public void setCurrentSelectedViewClass(String currentSelectedViewClass) {
		this.currentSelectedViewClass = currentSelectedViewClass;
	}

	public Date getDepartStartTime() {
		return departStartTime;
	}

	public void setDepartStartTime(Date departStartTime) {
		this.departStartTime = departStartTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	
	
	
}
