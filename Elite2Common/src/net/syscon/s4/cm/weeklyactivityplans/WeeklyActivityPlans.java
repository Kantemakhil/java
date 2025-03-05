package net.syscon.s4.cm.weeklyactivityplans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class WeeklyActivityPlans extends BaseModel implements Serializable {

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
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("activityId")
	private String activityId;
	@JsonProperty("recordSource")
	private String recordSource;
	
	@JsonProperty("activityNew")
	private String activityNew;
	
	@JsonProperty("wapStartDate")
	private Date wapStartDate;
	
	@JsonProperty("wapEndDate")
	private Date wapEndDate;
	
	@JsonProperty("versionNo")
	private BigDecimal versionNo;
	
	@JsonProperty("wapUpdateStatus")
	private String wapUpdateStatus;
	
	@JsonProperty("notForOffenderFlag")
	private String notForOffenderFlag;
	@JsonProperty("finalized")
	private String finalized;
	
	@JsonProperty("htyVersionNo")
	private BigDecimal htyVersionNo;
	
	@JsonProperty("eventIdTemp")
	private String eventIdTemp;
	
	@JsonProperty("offPrgrefId")
	private BigDecimal offPrgrefId;
	
	public String getFinalized() {
		return finalized;
	}

	public void setFinalized(String finalized) {
		this.finalized = finalized;
	}

	public BigDecimal getHtyVersionNo() {
		return htyVersionNo;
	}

	public void setHtyVersionNo(BigDecimal htyVersionNo) {
		this.htyVersionNo = htyVersionNo;
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

	

	public String getRecordSource() {
		return recordSource;
	}

	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public String getActivityNew() {
		return activityNew;
	}

	public void setActivityNew(String activityNew) {
		this.activityNew = activityNew;
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

	public BigDecimal getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(BigDecimal versionNo) {
		this.versionNo = versionNo;
	}

	public String getWapUpdateStatus() {
		return wapUpdateStatus;
	}

	public void setWapUpdateStatus(String wapUpdateStatus) {
		this.wapUpdateStatus = wapUpdateStatus;
	}

	public String getNotForOffenderFlag() {
		return notForOffenderFlag;
	}

	public void setNotForOffenderFlag(String notForOffenderFlag) {
		this.notForOffenderFlag = notForOffenderFlag;
	}

	public String getEventIdTemp() {
		return eventIdTemp;
	}

	public void setEventIdTemp(String eventIdTemp) {
		this.eventIdTemp = eventIdTemp;
	}

	public BigDecimal getOffPrgrefId() {
		return offPrgrefId;
	}

	public void setOffPrgrefId(BigDecimal offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}	
	
	
}
