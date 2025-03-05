package net.syscon.s4.inst.workflow.maintenance.beans;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the STAFF_LOCATION_ROLES database table.
 * 
 */
public class StaffLocationRoles implements Serializable {
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

	// bi-directional many-to-one association to StaffMember

	private long sacStaffId;

	private String calAgyLocId;

	private String fromDate;

	private String position;

	private String role;
	private String lastName;
	private String firstName;
	private String dspDescription;
	private String caseloadId;
	private String agyLocId;
	private String dspName;
	private String dspDescription5;
	private String dspDescription4;
	private String dspDescription3;
	private String calCaseloadId;
	private String sacCaseloadId;
	private String sexCode;
	private String name;
	private String skillSubTypeFlag;
	private String skillSubType;
	private String staffId;
	private String noOffender;
	private boolean staffIdTemp;
	
	public String getStaffId() {
		return staffId;
	}

	public String getSkillSubTypeFlag() {
		return skillSubTypeFlag;
	}

	public void setSkillSubTypeFlag(String skillSubTypeFlag) {
		this.skillSubTypeFlag = skillSubTypeFlag;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getSkillSubType() {
		return skillSubType;
	}

	public void setSkillSubType(String skillSubType) {
		this.skillSubType = skillSubType;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	private BigDecimal staffWorkload;

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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


	public long getSacStaffId() {
		return sacStaffId;
	}

	public void setSacStaffId(long sacStaffId) {
		this.sacStaffId = sacStaffId;
	}

	public String getCalAgyLocId() {
		return calAgyLocId;
	}

	public void setCalAgyLocId(String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
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

	public BigDecimal getHoursPerWeek() {
		return this.hoursPerWeek;
	}

	public void setHoursPerWeek(BigDecimal hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
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

	public String getScheduleType() {
		return this.scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getStaffUnit() {
		return this.staffUnit;
	}

	public void setStaffUnit(String staffUnit) {
		this.staffUnit = staffUnit;
	}

	public String getSupervisorAgyLocId() {
		return this.supervisorAgyLocId;
	}

	public void setSupervisorAgyLocId(String supervisorAgyLocId) {
		this.supervisorAgyLocId = supervisorAgyLocId;
	}

	public Date getSupervisorFromDate() {
		return this.supervisorFromDate;
	}

	public void setSupervisorFromDate(Date supervisorFromDate) {
		this.supervisorFromDate = supervisorFromDate;
	}

	public String getSupervisorPosition() {
		return this.supervisorPosition;
	}

	public void setSupervisorPosition(String supervisorPosition) {
		this.supervisorPosition = supervisorPosition;
	}

	public String getSupervisorRole() {
		return this.supervisorRole;
	}

	public void setSupervisorRole(String supervisorRole) {
		this.supervisorRole = supervisorRole;
	}

	public BigDecimal getSupervisorStaffId() {
		return this.supervisorStaffId;
	}

	public void setSupervisorStaffId(BigDecimal supervisorStaffId) {
		this.supervisorStaffId = supervisorStaffId;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getDspDescription() {
		return dspDescription;
	}

	public void setDspDescription(String dspDescription) {
		this.dspDescription = dspDescription;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getDspName() {
		return dspName;
	}

	public void setDspName(String dspName) {
		this.dspName = dspName;
	}

	public String getDspDescription5() {
		return dspDescription5;
	}

	public void setDspDescription5(String dspDescription5) {
		this.dspDescription5 = dspDescription5;
	}

	public String getDspDescription4() {
		return dspDescription4;
	}

	public void setDspDescription4(String dspDescription4) {
		this.dspDescription4 = dspDescription4;
	}

	public String getDspDescription3() {
		return dspDescription3;
	}

	public void setDspDescription3(String dspDescription3) {
		this.dspDescription3 = dspDescription3;
	}

	public String getCalCaseloadId() {
		return calCaseloadId;
	}

	public void setCalCaseloadId(String calCaseloadId) {
		this.calCaseloadId = calCaseloadId;
	}

	public String getSacCaseloadId() {
		return sacCaseloadId;
	}

	public void setSacCaseloadId(String sacCaseloadId) {
		this.sacCaseloadId = sacCaseloadId;
	}

	public BigDecimal getStaffWorkload() {
		return staffWorkload;
	}

	public void setStaffWorkload(BigDecimal staffWorkload) {
		this.staffWorkload = staffWorkload;
	}
	
	public String getNoOffender() {
		return noOffender;
	}

	public void setNoOffender(String noOffender) {
		this.noOffender = noOffender;
	}

}
