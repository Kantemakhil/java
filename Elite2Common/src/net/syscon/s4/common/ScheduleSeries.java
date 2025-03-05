package net.syscon.s4.common;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class ScheduleSeries extends BaseModel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8590873904456543967L;
	private long seriesId;
	private String repeatType;
	private Date endDate;
	private Integer repeatFrequency;
	private String repeatOn;
	private Integer totalcount;
	private Date createDate;
	private String createdBy;
	private Date modifyDate;
	private Date modifiedBy;
	private String excludeHoliday;
	private Date startDate;
	private String active;
	private String sealFlag;
	private Date startTime;
	private Date endTime;
	private String uiRules;
	private List<String> days;
	
	
	public long getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(long seriesId) {
		this.seriesId = seriesId;
	}
	public String getRepeatType() {
		return repeatType;
	}
	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getRepeatFrequency() {
		return repeatFrequency;
	}
	public void setRepeatFrequency(Integer repeatFrequency) {
		this.repeatFrequency = repeatFrequency;
	}
	public String getRepeatOn() {
		return repeatOn;
	}
	public void setRepeatOn(String repeatOn) {
		this.repeatOn = repeatOn;
	}
	
	public Integer getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Date getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Date modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getExcludeHoliday() {
		return excludeHoliday;
	}
	public void setExcludeHoliday(String excludeHoliday) {
		this.excludeHoliday = excludeHoliday;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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
	public String getUiRules() {
		return uiRules;
	}
	public void setUiRules(String uiRules) {
		this.uiRules = uiRules;
	}
	public List<String> getDays() {
		return days;
	}
	public void setDays(List<String> days) {
		this.days = days;
	}
	
	
	
	

}
