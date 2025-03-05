package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class Allowances extends BaseModel implements Serializable{
	
	@JsonProperty("allowanceType")
	private String allowanceType;
	@JsonProperty("unit")
    private String unit;
	@JsonProperty("maxUnit")
    private Integer maxUnit;
	@JsonProperty("rate")
    private Integer rate;
	@JsonProperty("sundayFlag")
    private String sundayFlag;
	@JsonProperty("mondayFlag")
    private String mondayFlag;
	@JsonProperty("tuesdayFlag")
    private String tuesdayFlag;
	@JsonProperty("wednesdayFlag")
    private String wednesdayFlag;
	@JsonProperty("thursdayFlag")
    private String thursdayFlag;
	@JsonProperty("fridayFlag")
    private String fridayFlag;
	@JsonProperty("saturdayFlag")
    private String saturdayFlag;
	@JsonProperty("activeFlag")
    private String activeFlag;
	@JsonProperty("expiryDate")
    private Date expiryDate;
	@JsonProperty("versionNo")
    private Integer versionNo;
	@JsonProperty("versionStartDate")
    private Date versionStartDate;
	@JsonProperty("versionStartTime")
    private Date versionStartTime;
	@JsonProperty("allowanceModifiedDate")
    private Date allowanceModifiedDate;
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
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;
	
	public String getAllowanceType() {
		return allowanceType;
	}
	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getMaxUnit() {
		return maxUnit;
	}
	public void setMaxUnit(Integer maxUnit) {
		this.maxUnit = maxUnit;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public String getSundayFlag() {
		return sundayFlag;
	}
	public void setSundayFlag(String sundayFlag) {
		this.sundayFlag = sundayFlag;
	}
	public String getMondayFlag() {
		return mondayFlag;
	}
	public void setMondayFlag(String mondayFlag) {
		this.mondayFlag = mondayFlag;
	}
	public String getTuesdayFlag() {
		return tuesdayFlag;
	}
	public void setTuesdayFlag(String tuesdayFlag) {
		this.tuesdayFlag = tuesdayFlag;
	}
	public String getWednesdayFlag() {
		return wednesdayFlag;
	}
	public void setWednesdayFlag(String wednesdayFlag) {
		this.wednesdayFlag = wednesdayFlag;
	}
	public String getThursdayFlag() {
		return thursdayFlag;
	}
	public void setThursdayFlag(String thursdayFlag) {
		this.thursdayFlag = thursdayFlag;
	}
	public String getFridayFlag() {
		return fridayFlag;
	}
	public void setFridayFlag(String fridayFlag) {
		this.fridayFlag = fridayFlag;
	}
	public String getSaturdayFlag() {
		return saturdayFlag;
	}
	public void setSaturdayFlag(String saturdayFlag) {
		this.saturdayFlag = saturdayFlag;
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
	public Integer getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	public Date getVersionStartDate() {
		return versionStartDate;
	}
	public void setVersionStartDate(Date versionStartDate) {
		this.versionStartDate = versionStartDate;
	}
	public Date getVersionStartTime() {
		return versionStartTime;
	}
	public void setVersionStartTime(Date versionStartTime) {
		this.versionStartTime = versionStartTime;
	}
	public Date getAllowanceModifiedDate() {
		return allowanceModifiedDate;
	}
	public void setAllowanceModifiedDate(Date allowanceModifiedDate) {
		this.allowanceModifiedDate = allowanceModifiedDate;
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
	
	
	public String getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getCanDisplay() {
		return canDisplay;
	}
	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}
	@Override
	public String toString() {
		return "Allowances [allowanceType=" + allowanceType + ", unit=" + unit + ", maxUnit=" + maxUnit + ", rate="
				+ rate + ", sundayFlag=" + sundayFlag + ", mondayFlag=" + mondayFlag + ", tuesdayFlag=" + tuesdayFlag
				+ ", wednesdayFlag=" + wednesdayFlag + ", thursdayFlag=" + thursdayFlag + ", fridayFlag=" + fridayFlag
				+ ", saturdayFlag=" + saturdayFlag + ", activeFlag=" + activeFlag + ", expiryDate=" + expiryDate
				+ ", versionNo=" + versionNo + ", versionStartDate=" + versionStartDate + ", versionStartTime="
				+ versionStartTime + ", allowanceModifiedDate=" + allowanceModifiedDate + ", createDatetime="
				+ createDatetime + ", createUserId=" + createUserId + ", modifyDatetime=" + modifyDatetime
				+ ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag + "]";
	}
	
}
