package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCourseSkill extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("noOfHours")
	private BigDecimal noOfHours;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("staffId")
	private BigDecimal staffId;

	@JsonProperty("staffRole")
	private String staffRole;

	@JsonProperty("offenderCourseAttendance")
	private OffenderCourseAttendance offenderCourseAttendance;

	@JsonProperty("eventId")
	private long eventId;

	@JsonProperty("skillCode")
	private String skillCode;

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getNoOfHours() {
		return noOfHours;
	}

	/**
	 *
	 * @param noOfHours
	 */
	public void setNoOfHours(BigDecimal noOfHours) {
		this.noOfHours = noOfHours;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getStaffId() {
		return staffId;
	}

	/**
	 *
	 * @param staffId
	 */
	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	/**
	 *
	 * @return
	 */
	public String getStaffRole() {
		return staffRole;
	}

	/**
	 *
	 * @param staffRole
	 */
	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}

	/**
	 *
	 * @return
	 */
	public OffenderCourseAttendance getOffenderCourseAttendance() {
		return offenderCourseAttendance;
	}

	/**
	 *
	 * @param offenderCourseAttendance
	 */
	public void setOffenderCourseAttendance(OffenderCourseAttendance offenderCourseAttendance) {
		this.offenderCourseAttendance = offenderCourseAttendance;
	}

	/**
	 *
	 * @return
	 */
	public long getEventId() {
		return eventId;
	}

	/**
	 *
	 * @param eventId
	 */
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	/**
	 *
	 * @return
	 */
	public String getSkillCode() {
		return skillCode;
	}

	/**
	 *
	 * @param skillCode
	 */
	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}

}