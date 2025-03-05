package net.syscon.s4.cm.programsservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the COURSE_SCHEDULE_STAFFS database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseScheduleStaff extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("courseScheduleStaffId")
	private long courseScheduleStaffId;

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
	
	@JsonProperty("staffId")
	private java.math.BigDecimal staffId;
	
	@JsonProperty("staffRole")
	private String staffRole;
	
	@JsonProperty("crsSchId")
	private BigDecimal crsSchId;

	public BigDecimal getCrsSchId() {
		return crsSchId;
	}

	public void setCrsSchId(BigDecimal crsSchId) {
		this.crsSchId = crsSchId;
	}

	public CourseScheduleStaff() {
	}

	public long getCourseScheduleStaffId() {
		return this.courseScheduleStaffId;
	}

	public void setCourseScheduleStaffId(long courseScheduleStaffId) {
		this.courseScheduleStaffId = courseScheduleStaffId;
	}


	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
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

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public java.math.BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(java.math.BigDecimal staffId) {
		this.staffId = staffId;
	}

	public String getStaffRole() {
		return this.staffRole;
	}

	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}

	

}
