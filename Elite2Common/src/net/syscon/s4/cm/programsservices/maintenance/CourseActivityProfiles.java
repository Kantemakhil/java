package net.syscon.s4.cm.programsservices.maintenance;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the COURSE_ACTIVITY_PROFILES database table.
 * 
 */
public class CourseActivityProfiles implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private long crsActyId;

	private String programProfileType;

	private String programProfileCode;

	public long getCrsActyId() {
		return crsActyId;
	}

	public void setCrsActyId(long crsActyId) {
		this.crsActyId = crsActyId;
	}

	public String getProgramProfileType() {
		return programProfileType;
	}

	public void setProgramProfileType(String programProfileType) {
		this.programProfileType = programProfileType;
	}

	public String getProgramProfileCode() {
		return programProfileCode;
	}

	public void setProgramProfileCode(String programProfileCode) {
		this.programProfileCode = programProfileCode;
	}

	public CourseActivityProfiles() {
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

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
