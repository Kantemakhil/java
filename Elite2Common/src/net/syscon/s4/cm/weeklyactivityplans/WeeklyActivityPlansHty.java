package net.syscon.s4.cm.weeklyactivityplans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class WeeklyActivityPlansHty extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("serialNumber")
	private Integer serialNumber;
	@JsonProperty("systemGenerated")
	private String systemGenerated;
	@JsonProperty("day")
	private String day;
	@JsonProperty("activityDate")
	private Date activityDate;
	@JsonProperty("activity")
	private String activity;
	@JsonProperty("activityAddress")
	private String activityAddress;
	@JsonProperty("departStartTime")
	private Date departStartTime;
	@JsonProperty("activityStart")
	private Date activityStart;
	@JsonProperty("activityFinish")
	private Date activityFinish;
	@JsonProperty("returnTime")
	private Date returnTime;
	@JsonProperty("modeOfTransport")
	private String modeOfTransport;
	@JsonProperty("updateIndicator")
	private String updateIndicator;
	@JsonProperty("notForOffender")
	private String notForOffender;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("fromDate")
	private Date fromDate;
	@JsonProperty("toDate")
	private Date toDate;
	@JsonProperty("eventId")
	private BigDecimal eventId;
	
	@JsonProperty("liReturn")
	private BigDecimal  liReturn;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("createDatetime")
    private Date createDatetime;
	@JsonProperty("createUserId")
    private String createUserId;
	@JsonProperty("modifyUserId")
    private String modifyUserId;
    
	@JsonProperty("weeklyActivityPlanId")
	private BigDecimal weeklyActivityPlanId;
	
	@JsonProperty("versionNo")
	private BigDecimal versionNo;
	
	@JsonProperty("weeklyActivityPlanHtyId")
	private BigDecimal weeklyActivityPlanHtyId;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("activityId")
	private BigDecimal activityId;
	
	@JsonProperty("wapStartDate")
	private Date wapStartDate;
	
	@JsonProperty("wapEndDate")
	private Date wapEndDate;
	
	@JsonProperty("comment")
	private String comment;
	
	@JsonProperty("notForOffenderFlag")
	private String notForOffenderFlag;
	
	
	private String activityStartTimeString;
	
	private String activityFinishTimeString;
	private String departStartTimeString;
	private String returnTimeString;


	@JsonProperty("modeOfTransportDesc")
	private String modeOfTransportDesc;
	
	@JsonProperty("htyVersionNo")
	private BigDecimal htyVersionNo;
	@JsonProperty("finalized")
	private String finalized;
	@JsonProperty("activityNew")
	private String activityNew;
	
	@JsonProperty("serialLabel")
	private String serialLabel;
	
	@JsonProperty("dateLabel")
	private String dateLabel;
	
	@JsonProperty("wapactivityLabel")
	private String wapactivityLabel;
	
	@JsonProperty("activityAddressLabel")
	private String activityAddressLabel;
	
	@JsonProperty("departTimeLabel")
	private String departTimeLabel;
	
	@JsonProperty("activityStartLabel")
	private String activityStartLabel;
	
	@JsonProperty("activityFinishLabel")
	private String activityFinishLabel;
	
	@JsonProperty("returnTimeLabel")
	private String returnTimeLabel;
	
	@JsonProperty("modeOfTransportLabel")
	private String modeOfTransportLabel;
	

	public String getSerialLabel() {
		return serialLabel;
	}

	public void setSerialLabel(String serialLabel) {
		this.serialLabel = serialLabel;
	}

	public String getDateLabel() {
		return dateLabel;
	}

	public void setDateLabel(String dateLabel) {
		this.dateLabel = dateLabel;
	}

	public String getWapactivityLabel() {
		return wapactivityLabel;
	}

	public void setWapactivityLabel(String wapactivityLabel) {
		this.wapactivityLabel = wapactivityLabel;
	}

	public String getActivityAddressLabel() {
		return activityAddressLabel;
	}

	public void setActivityAddressLabel(String activityAddressLabel) {
		this.activityAddressLabel = activityAddressLabel;
	}

	public String getDepartTimeLabel() {
		return departTimeLabel;
	}

	public void setDepartTimeLabel(String departTimeLabel) {
		this.departTimeLabel = departTimeLabel;
	}

	public String getActivityStartLabel() {
		return activityStartLabel;
	}

	public void setActivityStartLabel(String activityStartLabel) {
		this.activityStartLabel = activityStartLabel;
	}

	public String getActivityFinishLabel() {
		return activityFinishLabel;
	}

	public void setActivityFinishLabel(String activityFinishLabel) {
		this.activityFinishLabel = activityFinishLabel;
	}

	public String getReturnTimeLabel() {
		return returnTimeLabel;
	}

	public void setReturnTimeLabel(String returnTimeLabel) {
		this.returnTimeLabel = returnTimeLabel;
	}

	public String getModeOfTransportLabel() {
		return modeOfTransportLabel;
	}

	public void setModeOfTransportLabel(String modeOfTransportLabel) {
		this.modeOfTransportLabel = modeOfTransportLabel;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSystemGenerated() {
		return systemGenerated;
	}

	public void setSystemGenerated(String systemGenerated) {
		this.systemGenerated = systemGenerated;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getActivityAddress() {
		return activityAddress;
	}

	public void setActivityAddress(String activityAddress) {
		this.activityAddress = activityAddress;
	}

	public Date getActivityStart() {
		return activityStart;
	}

	public void setActivityStart(Date activityStart) {
		this.activityStart = activityStart;
	}

	public Date getActivityFinish() {
		return activityFinish;
	}

	public void setActivityFinish(Date activityFinish) {
		this.activityFinish = activityFinish;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	
	public String getUpdateIndicator() {
		return updateIndicator;
	}

	public void setUpdateIndicator(String updateIndicator) {
		this.updateIndicator = updateIndicator;
	}

	public String getNotForOffender() {
		return notForOffender;
	}

	public void setNotForOffender(String notForOffender) {
		this.notForOffender = notForOffender;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public BigDecimal getEventId() {
		return eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public BigDecimal getLiReturn() {
		return liReturn;
	}

	public void setLiReturn(BigDecimal liReturn) {
		this.liReturn = liReturn;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
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

	public BigDecimal getWeeklyActivityPlanId() {
		return weeklyActivityPlanId;
	}

	public void setWeeklyActivityPlanId(BigDecimal weeklyActivityPlanId) {
		this.weeklyActivityPlanId = weeklyActivityPlanId;
	}

	public BigDecimal getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(BigDecimal versionNo) {
		this.versionNo = versionNo;
	}

	public BigDecimal getWeeklyActivityPlanHtyId() {
		return weeklyActivityPlanHtyId;
	}

	public void setWeeklyActivityPlanHtyId(BigDecimal weeklyActivityPlanHtyId) {
		this.weeklyActivityPlanHtyId = weeklyActivityPlanHtyId;
	}

	public Date getDepartStartTime() {
		return departStartTime;
	}

	public void setDepartStartTime(Date departStartTime) {
		this.departStartTime = departStartTime;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public BigDecimal getActivityId() {
		return activityId;
	}

	public void setActivityId(BigDecimal activityId) {
		this.activityId = activityId;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getActivityStartTimeString() {
		return activityStartTimeString;
	}

	public void setActivityStartTimeString(String activityStartTimeString) {
		this.activityStartTimeString = activityStartTimeString;
	}

	public String getActivityFinishTimeString() {
		return activityFinishTimeString;
	}

	public void setActivityFinishTimeString(String activityFinishTimeString) {
		this.activityFinishTimeString = activityFinishTimeString;
	}

	public String getDepartStartTimeString() {
		return departStartTimeString;
	}

	public void setDepartStartTimeString(String departStartTimeString) {
		this.departStartTimeString = departStartTimeString;
	}

	public String getReturnTimeString() {
		return returnTimeString;
	}

	public void setReturnTimeString(String returnTimeString) {
		this.returnTimeString = returnTimeString;
	}

	public String getNotForOffenderFlag() {
		return notForOffenderFlag;
	}

	public void setNotForOffenderFlag(String notForOffenderFlag) {
		this.notForOffenderFlag = notForOffenderFlag;
	}

	public String getModeOfTransportDesc() {
		return modeOfTransportDesc;
	}

	public void setModeOfTransportDesc(String modeOfTransportDesc) {
		this.modeOfTransportDesc = modeOfTransportDesc;
	}

	public BigDecimal getHtyVersionNo() {
		return htyVersionNo;
	}

	public void setHtyVersionNo(BigDecimal htyVersionNo) {
		this.htyVersionNo = htyVersionNo;
	}

	public String getFinalized() {
		return finalized;
	}

	public void setFinalized(String finalized) {
		this.finalized = finalized;
	}

	public String getActivityNew() {
		return activityNew;
	}

	public void setActivityNew(String activityNew) {
		this.activityNew = activityNew;
	}
	
	
}
