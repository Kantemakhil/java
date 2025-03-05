package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class GOffenderAssignmentRecBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("livingUnitDesc")
	private String livingUnitDesc;

	@JsonProperty("caseOfficerId")
	private Long caseOfficerId;

	@JsonProperty("staffLastName")
	private String staffLastName;

	@JsonProperty("staffFirstName")
	private String staffFirstName;

	@JsonProperty("caseAssignedDate")
	private Date caseAssignedDate;

	@JsonProperty("caseAssignedTime")
	private Date caseAssignedTime;

	@JsonProperty("confirmFlag")
	private String confirmFlag;

	@JsonProperty("caseAgyLocId")
	private String caseAgyLocId;

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the livingUnitDesc
	 */
	public String getLivingUnitDesc() {
		return livingUnitDesc;
	}

	/**
	 * @param livingUnitDesc the livingUnitDesc to set
	 */
	public void setLivingUnitDesc(String livingUnitDesc) {
		this.livingUnitDesc = livingUnitDesc;
	}

	/**
	 * @return the caseOfficerId
	 */
	public Long getCaseOfficerId() {
		return caseOfficerId;
	}

	/**
	 * @param caseOfficerId the caseOfficerId to set
	 */
	public void setCaseOfficerId(Long caseOfficerId) {
		this.caseOfficerId = caseOfficerId;
	}

	/**
	 * @return the staffLastName
	 */
	public String getStaffLastName() {
		return staffLastName;
	}

	/**
	 * @param staffLastName the staffLastName to set
	 */
	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}

	/**
	 * @return the staffFirstName
	 */
	public String getStaffFirstName() {
		return staffFirstName;
	}

	/**
	 * @param staffFirstName the staffFirstName to set
	 */
	public void setStaffFirstName(String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	/**
	 * @return the caseAssignedDate
	 */
	public Date getCaseAssignedDate() {
		return caseAssignedDate;
	}

	/**
	 * @param caseAssignedDate the caseAssignedDate to set
	 */
	public void setCaseAssignedDate(Date caseAssignedDate) {
		this.caseAssignedDate = caseAssignedDate;
	}

	/**
	 * @return the caseAssignedTime
	 */
	public Date getCaseAssignedTime() {
		return caseAssignedTime;
	}

	/**
	 * @param caseAssignedTime the caseAssignedTime to set
	 */
	public void setCaseAssignedTime(Date caseAssignedTime) {
		this.caseAssignedTime = caseAssignedTime;
	}

	/**
	 * @return the confirmFlag
	 */
	public String getConfirmFlag() {
		return confirmFlag;
	}

	/**
	 * @param confirmFlag the confirmFlag to set
	 */
	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	/**
	 * @return the caseAgyLocId
	 */
	public String getCaseAgyLocId() {
		return caseAgyLocId;
	}

	/**
	 * @param caseAgyLocId the caseAgyLocId to set
	 */
	public void setCaseAgyLocId(String caseAgyLocId) {
		this.caseAgyLocId = caseAgyLocId;
	}

}