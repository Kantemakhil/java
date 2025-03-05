package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VAssignedOffenders extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty( "calAgyLocId") 
	  private String calAgyLocId;
	@JsonProperty( "endDate") 
	  private Date endDate;
	@JsonProperty( "casePlanStatus") 
	  private String casePlanStatus;
	@JsonProperty( "sacStaffId") 
	  private Integer sacStaffId;
	@JsonProperty( "role") 
	  private String role;
	@JsonProperty( "position") 
	  private String position;
	@JsonProperty( "startDate") 
	  private Date startDate;
	@JsonProperty( "offenderStatus") 
	  private String offenderStatus;
	@JsonProperty( "supervisionLevel") 
	  private String supervisionLevel;
	@JsonProperty( "sexCode") 
	  private String sexCode;
	@JsonProperty( "middleName") 
	  private String middleName;
	@JsonProperty( "firstName") 
	  private String firstName;
	@JsonProperty( "lastName") 
	  private String lastName;
	@JsonProperty( "offenderBookId") 
	  private Integer offenderBookId;
	@JsonProperty( "offenderIdDisplay") 
	  private String offenderIdDisplay;
	@JsonProperty( "imageData") 
	private byte[] imageData;
	
	@JsonProperty("dspCaseType")
	private String dspCaseType;
	@JsonProperty("caseType")
	private String caseType;
	
	
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getDspCaseType() {
		return dspCaseType;
	}
	public void setDspCaseType(String string) {
		this.dspCaseType = string;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	public String getCalAgyLocId() {
		return calAgyLocId;
	}
	public void setCalAgyLocId(String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCasePlanStatus() {
		return casePlanStatus;
	}
	public void setCasePlanStatus(String casePlanStatus) {
		this.casePlanStatus = casePlanStatus;
	}
	public Integer getSacStaffId() {
		return sacStaffId;
	}
	public void setSacStaffId(Integer sacStaffId) {
		this.sacStaffId = sacStaffId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getOffenderStatus() {
		return offenderStatus;
	}
	public void setOffenderStatus(String offenderStatus) {
		this.offenderStatus = offenderStatus;
	}
	public String getSupervisionLevel() {
		return supervisionLevel;
	}
	public void setSupervisionLevel(String supervisionLevel) {
		this.supervisionLevel = supervisionLevel;
	}
	public String getSexCode() {
		return sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
	public Integer getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
