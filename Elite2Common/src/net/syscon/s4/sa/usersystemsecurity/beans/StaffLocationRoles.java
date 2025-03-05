package net.syscon.s4.sa.usersystemsecurity.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class StaffLocationRoles extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date createDatetime;

	private String createUserId;

	private BigDecimal hoursPerWeek;

	private Date modifyDatetime;

	private String modifyUserId;

	private String scheduleType;

	private String sealFlag;

	private String staffUnit;

	private String supervisorAgyLocId;

	private Date supervisorFromDate;

	private String supervisorPosition;

	private String supervisorRole;

	private BigDecimal supervisorStaffId;

	private Date toDate;

	private Long sacStaffId;

	private String calAgyLocId;

	private Date fromDate;

	private String position;

	private String role;
	
	private String firstName;
	
	private Date birthdate;
	
	private String lastName;

	private BigDecimal listSeq;
	
	private BigDecimal fteStatus;
	
	private Long staffLocRoleId;

	@JsonProperty("rowId")
	private Integer rowId;

	/**
	 * @return the createDatetime
	 */
	public Object getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @return the hoursPerWeek
	 */
	public BigDecimal getHoursPerWeek() {
		return hoursPerWeek;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Object getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @return the scheduleType
	 */
	public String getScheduleType() {
		return scheduleType;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @return the staffUnit
	 */
	public String getStaffUnit() {
		return staffUnit;
	}

	/**
	 * @return the supervisorAgyLocId
	 */
	public String getSupervisorAgyLocId() {
		return supervisorAgyLocId;
	}

	/**
	 * @return the supervisorFromDate
	 */
	public Object getSupervisorFromDate() {
		return supervisorFromDate;
	}

	/**
	 * @return the supervisorPosition
	 */
	public String getSupervisorPosition() {
		return supervisorPosition;
	}

	/**
	 * @return the supervisorRole
	 */
	public String getSupervisorRole() {
		return supervisorRole;
	}

	/**
	 * @return the supervisorStaffId
	 */
	public BigDecimal getSupervisorStaffId() {
		return supervisorStaffId;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @return the sacStaffId
	 */
	public Long getSacStaffId() {
		return sacStaffId;
	}

	/**
	 * @return the calAgyLocId
	 */
	public String getCalAgyLocId() {
		return calAgyLocId;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @param createUserId
	 *        the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @param hoursPerWeek
	 *            the hoursPerWeek to set
	 */
	public void setHoursPerWeek(final BigDecimal hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @param scheduleType
	 *            the scheduleType to set
	 */
	public void setScheduleType(final String scheduleType) {
		this.scheduleType = scheduleType;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @param staffUnit
	 *            the staffUnit to set
	 */
	public void setStaffUnit(final String staffUnit) {
		this.staffUnit = staffUnit;
	}

	/**
	 * @param supervisorAgyLocId
	 *            the supervisorAgyLocId to set
	 */
	public void setSupervisorAgyLocId(final String supervisorAgyLocId) {
		this.supervisorAgyLocId = supervisorAgyLocId;
	}

	/**
	 * @param supervisorFromDate
	 *            the supervisorFromDate to set
	 */
	public void setSupervisorFromDate(final Date supervisorFromDate) {
		this.supervisorFromDate = supervisorFromDate;
	}

	/**
	 * @param supervisorPosition
	 *            the supervisorPosition to set
	 */
	public void setSupervisorPosition(final String supervisorPosition) {
		this.supervisorPosition = supervisorPosition;
	}

	/**
	 * @param supervisorRole
	 *            the supervisorRole to set
	 */
	public void setSupervisorRole(final String supervisorRole) {
		this.supervisorRole = supervisorRole;
	}

	/**
	 * @param supervisorStaffId
	 *            the supervisorStaffId to set
	 */
	public void setSupervisorStaffId(final BigDecimal supervisorStaffId) {
		this.supervisorStaffId = supervisorStaffId;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @param sacStaffId
	 *            the sacStaffId to set
	 */
	public void setSacStaffId(final Long sacStaffId) {
		this.sacStaffId = sacStaffId;
	}

	/**
	 * @param calAgyLocId
	 *            the calAgyLocId to set
	 */
	public void setCalAgyLocId(final String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(final String position) {
		this.position = position;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(final String role) {
		this.role = role;
	}

	/**
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the rowId
	 */
	public Integer getRowId() {
		return rowId;
	}

	/**
	 * @param rowId
	 *            the rowId to set
	 */
	public void setRowId(final Integer rowId) {
		this.rowId = rowId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigDecimal getFteStatus() {
		return fteStatus;
	}

	public void setFteStatus(BigDecimal fteStatus) {
		this.fteStatus = fteStatus;
	}

	public Long getStaffLocRoleId() {
		return staffLocRoleId;
	}

	public void setStaffLocRoleId(Long staffLocRoleId) {
		this.staffLocRoleId = staffLocRoleId;
	}

}
