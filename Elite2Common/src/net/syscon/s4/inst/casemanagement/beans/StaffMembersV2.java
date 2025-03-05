package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StaffMembersV2 implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date birthdate;

	private String calAgyLocId;

	private Date dateTo;

	private String firstName;

	private Date fromDate;

	private BigDecimal hoursPerWeek;

	private String lastName;

	private String position;

	private String role;

	private BigDecimal sacStaffId;

	private String scheduleType;

	private String staffName;

	private String suspendedFlag;

	private String userId;

	public StaffMembersV2() {
		// StaffMembersV2
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCalAgyLocId() {
		return this.calAgyLocId;
	}

	public void setCalAgyLocId(String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public BigDecimal getHoursPerWeek() {
		return this.hoursPerWeek;
	}

	public void setHoursPerWeek(BigDecimal hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public BigDecimal getSacStaffId() {
		return this.sacStaffId;
	}

	public void setSacStaffId(BigDecimal sacStaffId) {
		this.sacStaffId = sacStaffId;
	}

	public String getScheduleType() {
		return this.scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getSuspendedFlag() {
		return this.suspendedFlag;
	}

	public void setSuspendedFlag(String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
