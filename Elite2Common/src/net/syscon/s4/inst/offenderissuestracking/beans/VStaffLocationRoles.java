package net.syscon.s4.inst.offenderissuestracking.beans;

import java.sql.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VStaffLocationRoles extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String staffName;
	private String lastName;
	private String firstName;
	private Date birthdate;
	private String status;
	private String calAgyLocId;
	private Integer sacStaffId;
	private Date fromDate;
	private String position;
	private String role;
	private Date dateTo;
	private String scheduleType;
	private Double hoursPerWeek;
	private Long offenderBookId;
	private String agyLocId;
	private String description;
	private Integer code;
	private String caseloadId;

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCalAgyLocId() {
		return calAgyLocId;
	}

	public void setCalAgyLocId(String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	public Integer getSacStaffId() {
		return sacStaffId;
	}

	public void setSacStaffId(Integer sacStaffId) {
		this.sacStaffId = sacStaffId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public Double getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(Double hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

}
