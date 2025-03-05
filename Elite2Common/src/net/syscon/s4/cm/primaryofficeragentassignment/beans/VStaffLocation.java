package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VStaffLocation extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("supervisorFromDate")
	private Date supervisorFromDate;
	
	@JsonProperty("supervisorRole")
	private String supervisorRole;
	
	@JsonProperty("supervisorPosition")
	private String supervisorPosition;
	
	@JsonProperty("supervisorStaffId")
	private Integer supervisorStaffId;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("hoursPerWeek")
	private BigDecimal hoursPerWeek;
	
	@JsonProperty("scheduleType")
	private String scheduleType;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("position")
	private String position;
	
	@JsonProperty("calAgyLocId")
	private String calAgyLocId;
	
	@JsonProperty("sexCode")
	private String sexCode;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("staffId")
	private Integer staffId;
	
	@JsonProperty("supervisorAgyLocId")
	private String supervisorAgyLocId;
	
	@JsonProperty("dspWorkFlowFlag")
	private String dspWorkFlowFlag;
	
	
	@JsonProperty("noOffender")
	private long noOffender;
	

	public Date getSupervisorFromDate() {
		return supervisorFromDate;
	}

	public void setSupervisorFromDate(final Date supervisorFromDate) {
		this.supervisorFromDate = supervisorFromDate;
	}

	public String getSupervisorRole() {
		return supervisorRole;
	}

	public void setSupervisorRole(final String supervisorRole) {
		this.supervisorRole = supervisorRole;
	}

	public String getSupervisorPosition() {
		return supervisorPosition;
	}

	public void setSupervisorPosition(final String supervisorPosition) {
		this.supervisorPosition = supervisorPosition;
	}

	public Integer getSupervisorStaffId() {
		return supervisorStaffId;
	}

	public void setSupervisorStaffId(final Integer supervisorStaffId) {
		this.supervisorStaffId = supervisorStaffId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	public BigDecimal getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(final BigDecimal hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(final String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(final String position) {
		this.position = position;
	}

	public String getCalAgyLocId() {
		return calAgyLocId;
	}

	public void setCalAgyLocId(final String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(final String sexCode) {
		this.sexCode = sexCode;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(final Integer staffId) {
		this.staffId = staffId;
	}

	public String getSupervisorAgyLocId() {
		return supervisorAgyLocId;
	}

	public void setSupervisorAgyLocId(final String supervisorAgyLocId) {
		this.supervisorAgyLocId = supervisorAgyLocId;
	}

	public long getNoOffender() {
		return noOffender;
	}

	public void setNoOffender( long noOffender) {
		this.noOffender = noOffender;
	}

	/**
	 * @return the dspWorkFlowFlag
	 */
	public String getDspWorkFlowFlag() {
		return dspWorkFlowFlag;
	}

	/**
	 * @param dspWorkFlowFlag the dspWorkFlowFlag to set
	 */
	public void setDspWorkFlowFlag(String dspWorkFlowFlag) {
		this.dspWorkFlowFlag = dspWorkFlowFlag;
	}

}
