package net.syscon.s4.cm.programsservices;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderCourseApptRule implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderCourseApptRuleId") 
	  private long offenderCourseApptRuleId;
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
	@JsonProperty( "saturdayFlag") 
	  private String saturdayFlag;
	@JsonProperty( "sealFlag") 
	  private String sealFlag;
	@JsonProperty( "startTime") 
	  private Date startTime;
	@JsonProperty( "sundayFlag") 
	  private String sundayFlag;
	@JsonProperty( "thursdayFlag") 
	  private String thursdayFlag;
	@JsonProperty( "tuesdayFlag") 
	  private String tuesdayFlag;
	@JsonProperty( "wednesdayFlag") 
	  private String wednesdayFlag;
	//bi-directional many-to-one association to OffenderCourseApptGrp
	@JsonProperty("offenderCourseApptGrpId")
	private long offenderCourseApptGrpId;



	public long getOffenderCourseApptGrpId() {
		return offenderCourseApptGrpId;
	}

	public void setOffenderCourseApptGrpId(long offenderCourseApptGrpId) {
		this.offenderCourseApptGrpId = offenderCourseApptGrpId;
	}

	public OffenderCourseApptRule() {
		
	}

	public long getOffenderCourseApptRuleId() {
		return this.offenderCourseApptRuleId;
	}

	public void setOffenderCourseApptRuleId(long offenderCourseApptRuleId) {
		this.offenderCourseApptRuleId = offenderCourseApptRuleId;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getFridayFlag() {
		return this.fridayFlag;
	}

	public void setFridayFlag(String fridayFlag) {
		this.fridayFlag = fridayFlag;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getMondayFlag() {
		return this.mondayFlag;
	}

	public void setMondayFlag(String mondayFlag) {
		this.mondayFlag = mondayFlag;
	}

	public String getSaturdayFlag() {
		return this.saturdayFlag;
	}

	public void setSaturdayFlag(String saturdayFlag) {
		this.saturdayFlag = saturdayFlag;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getSundayFlag() {
		return this.sundayFlag;
	}

	public void setSundayFlag(String sundayFlag) {
		this.sundayFlag = sundayFlag;
	}

	public String getThursdayFlag() {
		return this.thursdayFlag;
	}

	public void setThursdayFlag(String thursdayFlag) {
		this.thursdayFlag = thursdayFlag;
	}

	public String getTuesdayFlag() {
		return this.tuesdayFlag;
	}

	public void setTuesdayFlag(String tuesdayFlag) {
		this.tuesdayFlag = tuesdayFlag;
	}

	public String getWednesdayFlag() {
		return this.wednesdayFlag;
	}

	public void setWednesdayFlag(String wednesdayFlag) {
		this.wednesdayFlag = wednesdayFlag;
	}

	



}

