package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OidcoasiOffenderAssignments extends BaseModel {

	@JsonProperty("movementDate")
	private Date movementDate;

	@JsonProperty("movementTime")
	private Date movementTime;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("user")
	private String user;

	@JsonProperty("agyLocDescription")
	private String agyLocDescription;

	@JsonProperty("livingUnitCodeOne")
	private String livingUnitCodeOne;

	@JsonProperty("livingUnitCodeTwo")
	private String livingUnitCodeTwo;

	@JsonProperty("livingUnitCodeThree")
	private String livingUnitCodethree;

	@JsonProperty("livingUnitCodeFour")
	private String livingUnitCodeFour;

	@JsonProperty("currentOfficerLastName")
	private String currentOfficerLastName;

	@JsonProperty("currentOfficerFirstName")
	private String currentOfficerFirstName;

	@JsonProperty("currentOfficerStaffId")
	private Long currentOfficerStaffId;

	@JsonProperty("assignOfficerLastName")
	private String assignOfficerLastName;

	@JsonProperty("assignOfficerFirstName")
	private String assignOfficerFirstName;

	@JsonProperty("assignOfficerStaffId")
	private Long assignOfficerStaffId;

	@JsonProperty("unassignedFlag")
	private String unassignedFlag;

	@JsonProperty("confirmationAllFlag")
	private String confirmationAllFlag;

	@JsonProperty("confirmationFlag")
	private String confirmationFlag;

	@JsonProperty("offenderId")
	private String offenderId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBookingId")
	private Long offenderBookingId;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("livingUnitDesc")
	private String livingUnitDesc;

	@JsonProperty("staffLastName")
	private String staffLastName;

	@JsonProperty("staffFirstName")
	private String staffFirstName;

	@JsonProperty("caseOfficerId")
	private Integer caseOfficerId;

	@JsonProperty("caseAssignedDate")
	private Date caseAssignedDate;

	@JsonProperty("caseAssignedTime")
	private Date caseAssignedTime;

	@JsonProperty("vLivingUnitDesc")
	private String vLivingUnitDesc;
	
	@JsonProperty("vCaseOfficerId")
	private Long vCaseOfficerId;
	
	@JsonProperty("vLivingUnitId")
	private BigDecimal vLivingUnitId;

	public String getvLivingUnitDesc() {
		return vLivingUnitDesc;
	}

	public void setvLivingUnitDesc(String vLivingUnitDesc) {
		this.vLivingUnitDesc = vLivingUnitDesc;
	}

	public Long getvCaseOfficerId() {
		return vCaseOfficerId;
	}

	public void setvCaseOfficerId(Long vCaseOfficerId) {
		this.vCaseOfficerId = vCaseOfficerId;
	}

	public BigDecimal getvLivingUnitId() {
		return vLivingUnitId;
	}

	public void setvLivingUnitId(BigDecimal vLivingUnitId) {
		this.vLivingUnitId = vLivingUnitId;
	}

	public String getUnassignedFlag() {
		return unassignedFlag;
	}

	public void setUnassignedFlag(String unassignedFlag) {
		this.unassignedFlag = unassignedFlag;
	}

	public String getConfirmationAllFlag() {
		return confirmationAllFlag;
	}

	public void setConfirmationAllFlag(String confirmationAllFlag) {
		this.confirmationAllFlag = confirmationAllFlag;
	}

	public Date getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	public Date getMovementTime() {
		return movementTime;
	}

	public void setMovementTime(Date movementTime) {
		this.movementTime = movementTime;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getAgyLocDescription() {
		return agyLocDescription;
	}

	public void setAgyLocDescription(String agyLocDescription) {
		this.agyLocDescription = agyLocDescription;
	}

	public String getLivingUnitCodeOne() {
		return livingUnitCodeOne;
	}

	public void setLivingUnitCodeOne(String livingUnitCodeOne) {
		this.livingUnitCodeOne = livingUnitCodeOne;
	}

	public String getLivingUnitCodeTwo() {
		return livingUnitCodeTwo;
	}

	public void setLivingUnitCodeTwo(String livingUnitCodeTwo) {
		this.livingUnitCodeTwo = livingUnitCodeTwo;
	}

	public String getLivingUnitCodethree() {
		return livingUnitCodethree;
	}

	public void setLivingUnitCodethree(String livingUnitCodethree) {
		this.livingUnitCodethree = livingUnitCodethree;
	}

	public String getLivingUnitCodeFour() {
		return livingUnitCodeFour;
	}

	public void setLivingUnitCodeFour(String livingUnitCodeFour) {
		this.livingUnitCodeFour = livingUnitCodeFour;
	}

	public String getCurrentOfficerLastName() {
		return currentOfficerLastName;
	}

	public void setCurrentOfficerLastName(String currentOfficerLastName) {
		this.currentOfficerLastName = currentOfficerLastName;
	}

	public String getCurrentOfficerFirstName() {
		return currentOfficerFirstName;
	}

	public void setCurrentOfficerFirstName(String currentOfficerFirstName) {
		this.currentOfficerFirstName = currentOfficerFirstName;
	}

	public Long getCurrentOfficerStaffId() {
		return currentOfficerStaffId;
	}

	public void setCurrentOfficerStaffId(Long currentOfficerStaffId) {
		this.currentOfficerStaffId = currentOfficerStaffId;
	}

	public String getAssignOfficerLastName() {
		return assignOfficerLastName;
	}

	public void setAssignOfficerLastName(String assignOfficerLastName) {
		this.assignOfficerLastName = assignOfficerLastName;
	}

	public String getAssignOfficerFirstName() {
		return assignOfficerFirstName;
	}

	public void setAssignOfficerFirstName(String assignOfficerFirstName) {
		this.assignOfficerFirstName = assignOfficerFirstName;
	}

	public Long getAssignOfficerStaffId() {
		return assignOfficerStaffId;
	}

	public void setAssignOfficerStaffId(Long assignOfficerStaffId) {
		this.assignOfficerStaffId = assignOfficerStaffId;
	}

	public String getConfirmationFlag() {
		return confirmationFlag;
	}

	public void setConfirmationFlag(String confirmationFlag) {
		this.confirmationFlag = confirmationFlag;
	}

	public String getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(String offenderId) {
		this.offenderId = offenderId;
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

	public String getLivingUnitDesc() {
		return livingUnitDesc;
	}

	public void setLivingUnitDesc(String livingUnitDesc) {
		this.livingUnitDesc = livingUnitDesc;
	}

	public String getStaffLastName() {
		return staffLastName;
	}

	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}

	public String getStaffFirstName() {
		return staffFirstName;
	}

	public void setStaffFirstName(String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	public Integer getCaseOfficerId() {
		return caseOfficerId;
	}

	public void setCaseOfficerId(Integer caseOfficerId) {
		this.caseOfficerId = caseOfficerId;
	}

	public Date getCaseAssignedDate() {
		return caseAssignedDate;
	}

	public void setCaseAssignedDate(Date caseAssignedDate) {
		this.caseAssignedDate = caseAssignedDate;
	}

	public Date getCaseAssignedTime() {
		return caseAssignedTime;
	}

	public void setCaseAssignedTime(Date caseAssignedTime) {
		this.caseAssignedTime = caseAssignedTime;
	}

	public Long getOffenderBookingId() {
		return offenderBookingId;
	}

	public void setOffenderBookingId(Long offenderBookingId) {
		this.offenderBookingId = offenderBookingId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
