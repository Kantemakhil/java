package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CourseScheduleRules extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("crsActyId")
	private Long crsActyId;
	
	@JsonProperty("courseScheduleRuleId")
	private long courseScheduleRuleId;

	@JsonProperty("capacity")
	private Long capacity;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("endTime")
	private Date endTime;

	@JsonProperty("fridayFlag")
	private String fridayFlag;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("mondayFlag")
	private String mondayFlag;

	@JsonProperty("saturdayFlag")
	private String saturdayFlag;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("startTime")
	private Date startTime;

	@JsonProperty("sundayFlag")
	private String sundayFlag;

	@JsonProperty("thursdayFlag")
	private String thursdayFlag;

	@JsonProperty("tuesdayFlag")
	private String tuesdayFlag;

	@JsonProperty("wednesdayFlag")
	private String wednesdayFlag;

	@JsonProperty("weekNo")
	private Long weekNo;
	
	@JsonProperty("sunday")
	private boolean sunday;
	
	@JsonProperty("monday")
	private boolean monday;
	
	@JsonProperty("tuesday")
	private boolean tuesday;
	
	@JsonProperty("wednesday")
	private boolean wednesday;
	
	@JsonProperty("thursday")
	private boolean thursday;
	
	@JsonProperty("friday")
	private boolean friday;
	
	@JsonProperty("saturday")
	private boolean saturday;
	
	@JsonProperty("noOfDays")
	private Long noOfDays;
	
	@JsonProperty("noBuilt")
	private Long noBuilt;
	
	@JsonProperty("lastDate")
	private Date lastDate;
	
	@JsonProperty("lireturn")
	private Integer lireturn;

	@JsonProperty("totalRecords")
	private Integer totalRecords;
	
	
	public CourseScheduleRules() {
		
	}

	public Long getCrsActyId() {
		return crsActyId;
	}

	public void setCrsActyId(final Long crsActyId) {
		this.crsActyId = crsActyId;
	}

	public long getCourseScheduleRuleId() {
		return courseScheduleRuleId;
	}

	public void setCourseScheduleRuleId(final long courseScheduleRuleId) {
		this.courseScheduleRuleId = courseScheduleRuleId;
	}

	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(final Long capacity) {
		this.capacity = capacity;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public String getFridayFlag() {
		return fridayFlag;
	}

	public void setFridayFlag(final String fridayFlag) {
		this.fridayFlag = fridayFlag;
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

	public String getMondayFlag() {
		return mondayFlag;
	}

	public void setMondayFlag(final String mondayFlag) {
		this.mondayFlag = mondayFlag;
	}

	public String getSaturdayFlag() {
		return saturdayFlag;
	}

	public void setSaturdayFlag(final String saturdayFlag) {
		this.saturdayFlag = saturdayFlag;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public String getSundayFlag() {
		return sundayFlag;
	}

	public void setSundayFlag(final String sundayFlag) {
		this.sundayFlag = sundayFlag;
	}

	public String getThursdayFlag() {
		return thursdayFlag;
	}

	public void setThursdayFlag(final String thursdayFlag) {
		this.thursdayFlag = thursdayFlag;
	}

	public String getTuesdayFlag() {
		return tuesdayFlag;
	}

	public void setTuesdayFlag(final String tuesdayFlag) {
		this.tuesdayFlag = tuesdayFlag;
	}

	public String getWednesdayFlag() {
		return wednesdayFlag;
	}

	public void setWednesdayFlag(final String wednesdayFlag) {
		this.wednesdayFlag = wednesdayFlag;
	}

	public Long getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(final Long weekNo) {
		this.weekNo = weekNo;
	}

	public boolean isSunday() {
		return sunday;
	}

	public void setSunday(final boolean sunday) {
		this.sunday = sunday;
	}

	public boolean isMonday() {
		return monday;
	}

	public void setMonday(final boolean monday) {
		this.monday = monday;
	}

	public boolean isTuesday() {
		return tuesday;
	}

	public void setTuesday(final boolean tuesday) {
		this.tuesday = tuesday;
	}

	public boolean isWednesday() {
		return wednesday;
	}

	public void setWednesday(final boolean wednesday) {
		this.wednesday = wednesday;
	}

	public boolean isThursday() {
		return thursday;
	}

	public void setThursday(final boolean thursday) {
		this.thursday = thursday;
	}

	public boolean isFriday() {
		return friday;
	}

	public void setFriday(final boolean friday) {
		this.friday = friday;
	}

	public boolean isSaturday() {
		return saturday;
	}

	public void setSaturday(final boolean saturday) {
		this.saturday = saturday;
	}

	public Long getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(final Long noOfDays) {
		this.noOfDays = noOfDays;
	}

	public Long getNoBuilt() {
		return noBuilt;
	}

	public void setNoBuilt(final Long noBuilt) {
		this.noBuilt = noBuilt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(final Date lastDate) {
		this.lastDate = lastDate;
	}

	public Integer getLireturn() {
		return lireturn;
	}

	public void setLireturn(final Integer lireturn) {
		this.lireturn = lireturn;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(final Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	

}
