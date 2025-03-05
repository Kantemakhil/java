package net.syscon.s4.inst.transportation.maintenance.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ScheduledTripParameters extends BaseModel {
	
	@JsonProperty("tripCode")
	private String tripCode;
	@JsonProperty("weekNo")
	private long weekNo;
	@JsonProperty("sunday")
	private String sunday;
	@JsonProperty("monday")
	private String monday;
	@JsonProperty("tuesday")
	private String tuesday;
	@JsonProperty("wednesday")
	private String wednesday;
	@JsonProperty("thursday")
	private String thursday;
	@JsonProperty("friday")
	private String friday;
	@JsonProperty("saturday")
	private String saturday;
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
	@JsonProperty("estDepartureTime")
	private Date estDepartureTime;
	@JsonProperty("vMdate")
	private Date vMdate;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("weekNoTemp")
	private long weekNoTemp;

	public long getWeekNoTemp() {
		return weekNoTemp;
	}

	public void setWeekNoTemp(long weekNoTemp) {
		this.weekNoTemp = weekNoTemp;
	}

	public Date getvMdate() {
		return vMdate;
	}

	public void setvMdate(Date vMdate) {
		this.vMdate = vMdate;
	}

	public Date getEstDepartureTime() {
		return estDepartureTime;
	}

	public void setEstDepartureTime(Date estDepartureTime) {
		this.estDepartureTime = estDepartureTime;
	}

	public String getTripCode() {
		return tripCode;
	}

	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}

	public long getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(long weekNo) {
		this.weekNo = weekNo;
	}

	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}

	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
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
	public void setErrorMessage(String message) {
		// TODO Auto-generated method stub

	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
}
