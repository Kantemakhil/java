package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class StaffDetails extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("position")
	private String position;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("teamMemberId")
	private String teamMemberId;
	
	private String staffName;
	
	private Integer staffId;
private String agencyLocationType;
private String agyLocId;
public String getAgencyLocationType() {
	return agencyLocationType;
}

public void setAgencyLocationType(String agencyLocationType) {
	this.agencyLocationType = agencyLocationType;
}

private String staffStatus;
private String areaCode;
private Date toDate;
private BigDecimal hoursPerWeek;	 
private String scheduleType;

	public BigDecimal getHoursPerWeek() {
	return hoursPerWeek;
}

public void setHoursPerWeek(BigDecimal hoursPerWeek) {
	this.hoursPerWeek = hoursPerWeek;
}

public String getScheduleType() {
	return scheduleType;
}

public void setScheduleType(String scheduleType) {
	this.scheduleType = scheduleType;
}

	public String getAgyLocId() {
	return agyLocId;
}

public void setAgyLocId(String agyLocId) {
	this.agyLocId = agyLocId;
}

public String getStaffStatus() {
	return staffStatus;
}

public void setStaffStatus(String staffStatus) {
	this.staffStatus = staffStatus;
}

public String getAreaCode() {
	return areaCode;
}

public void setAreaCode(String areaCode) {
	this.areaCode = areaCode;
}

public Date getToDate() {
	return toDate;
}

public void setToDate(Date toDate) {
	this.toDate = toDate;
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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(String teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	
	
	
	
	
}
