package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the AGENCY_VISIT_TIMES database table.
 * 
 */
public class AgencyVisitTimes extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty( "activeFlag") 
	private String activeFlag;

	@JsonProperty( "createDatetime") 
	private Date createDatetime;

	@JsonProperty( "createUserId") 
	private String createUserId;

	@JsonProperty( "endTime") 
	private Date endTime;

	@JsonProperty( "expiryDate") 
	private Date expiryDate;

	@JsonProperty( "modifyDatetime") 
	private Date modifyDatetime;

	@JsonProperty( "modifyUserId") 
	private String modifyUserId;

	@JsonProperty( "sealFlag") 
	private String sealFlag;

	@JsonProperty( "startTime") 
	private Date startTime;

	@JsonProperty( "agyLocId") 
	private String agyLocId;

	@JsonProperty( "weekDay") 
	private String weekDay;

	@JsonProperty( "timeSlotSeq") 
	private String timeSlotSeq;


	public AgencyVisitTimes() {
		
	}


	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}


	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}


	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}


	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}


	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}


	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}


	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}


	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}


	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}


	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}


	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}


	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}


	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}


	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}


	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}


	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}


	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}


	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}


	/**
	 * @return the weekDay
	 */
	public String getWeekDay() {
		return weekDay;
	}


	/**
	 * @param weekDay the weekDay to set
	 */
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}


	/**
	 * @return the timeSlotSeq
	 */
	public String getTimeSlotSeq() {
		return timeSlotSeq;
	}


	/**
	 * @param timeSlotSeq the timeSlotSeq to set
	 */
	public void setTimeSlotSeq(String timeSlotSeq) {
		this.timeSlotSeq = timeSlotSeq;
	}
	
	

	
}
