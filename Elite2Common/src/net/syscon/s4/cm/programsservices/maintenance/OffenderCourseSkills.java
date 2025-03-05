package net.syscon.s4.cm.programsservices.maintenance;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderCourseSkills extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long eventId;
	private String skillCode;
	private Double noOfHours;
	private Long staffId;
	private String staffDesc;
	private String commentText;
	private String staffRole;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String sealFlag;
	private String rowId;
	private int returnValue;
	
	private String firstName;
	
	private String lastName;
	private Long rowIdOne;

	public Long getRowIdOne() {
		return rowIdOne;
	}

	public void setRowIdOne(Long rowIdOne) {
		this.rowIdOne = rowIdOne;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(final Long eventId) {
		this.eventId = eventId;
	}

	public String getSkillCode() {
		return skillCode;
	}

	public void setSkillCode(final String skillCode) {
		this.skillCode = skillCode;
	}

	public Double getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(final Double noOfHours) {
		this.noOfHours = noOfHours;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(final Long staffId) {
		this.staffId = staffId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public String getStaffRole() {
		return staffRole;
	}

	public void setStaffRole(final String staffRole) {
		this.staffRole = staffRole;
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getStaffDesc() {
		return staffDesc;
	}

	public void setStaffDesc(final String staffDesc) {
		this.staffDesc = staffDesc;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}

}
