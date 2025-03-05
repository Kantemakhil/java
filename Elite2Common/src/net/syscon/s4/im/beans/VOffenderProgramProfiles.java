package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VOffenderProgramProfiles extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer age;

	private Long crsActyId;

	private String firstName;

	private String lastName;

	private Long offPrgrefId;

	private String offenceTypes;

	private String offenderAlert;
	
	private String pGenderCode;
	
	private String pEthnicityCode;
	
	private Long offenderBookId;

	private Date offenderEndDate;

	private Long offenderId;

	private String offenderIdDisplay;

	private String offenderName;

	private String offenderProgramStatus;

	private Date offenderStartDate;

	private Long programId;

	private String raceCode;

	private String sexCode;

	private String suspendedFlag;
	
	private List<VOffenderProgramProfiles> prgServiceList;

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	public List<VOffenderProgramProfiles> getPrgServiceList() {
		return prgServiceList;
	}

	public void setPrgServiceList(List<VOffenderProgramProfiles> prgServiceList) {
		this.prgServiceList = prgServiceList;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(final Integer age) {
		this.age = age;
	}

	/**
	 * @return the crsActyId
	 */
	public Long getCrsActyId() {
		return crsActyId;
	}

	/**
	 * @param crsActyId the crsActyId to set
	 */
	public void setCrsActyId(final Long crsActyId) {
		this.crsActyId = crsActyId;
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
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
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
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the offPrgrefId
	 */
	public Long getOffPrgrefId() {
		return offPrgrefId;
	}

	/**
	 * @param offPrgrefId the offPrgrefId to set
	 */
	public void setOffPrgrefId(final Long offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	/**
	 * @return the offenceTypes
	 */
	public String getOffenceTypes() {
		return offenceTypes;
	}

	/**
	 * @param offenceTypes the offenceTypes to set
	 */
	public void setOffenceTypes(final String offenceTypes) {
		this.offenceTypes = offenceTypes;
	}

	/**
	 * @return the offenderAlert
	 */
	public String getOffenderAlert() {
		return offenderAlert;
	}

	/**
	 * @param offenderAlert the offenderAlert to set
	 */
	public void setOffenderAlert(final String offenderAlert) {
		this.offenderAlert = offenderAlert;
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
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the pGenderCode
	 */
	public String getpGenderCode() {
		return pGenderCode;
	}

	/**
	 * @return the pEthnicityCode
	 */
	public String getpEthnicityCode() {
		return pEthnicityCode;
	}

	/**
	 * @param pGenderCode the pGenderCode to set
	 */
	public void setpGenderCode(final String pGenderCode) {
		this.pGenderCode = pGenderCode;
	}

	/**
	 * @param pEthnicityCode the pEthnicityCode to set
	 */
	public void setpEthnicityCode(final String pEthnicityCode) {
		this.pEthnicityCode = pEthnicityCode;
	}

	/**
	 * @return the offenderEndDate
	 */
	public Date getOffenderEndDate() {
		return offenderEndDate;
	}

	/**
	 * @param offenderEndDate the offenderEndDate to set
	 */
	public void setOffenderEndDate(final Date offenderEndDate) {
		this.offenderEndDate = offenderEndDate;
	}

	/**
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId the offenderId to set
	 */
	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the offenderName
	 */
	public String getOffenderName() {
		return offenderName;
	}

	/**
	 * @param offenderName the offenderName to set
	 */
	public void setOffenderName(final String offenderName) {
		this.offenderName = offenderName;
	}

	/**
	 * @return the offenderProgramStatus
	 */
	public String getOffenderProgramStatus() {
		return offenderProgramStatus;
	}

	/**
	 * @param offenderProgramStatus the offenderProgramStatus to set
	 */
	public void setOffenderProgramStatus(final String offenderProgramStatus) {
		this.offenderProgramStatus = offenderProgramStatus;
	}

	/**
	 * @return the offenderStartDate
	 */
	public Date getOffenderStartDate() {
		return offenderStartDate;
	}

	/**
	 * @param offenderStartDate the offenderStartDate to set
	 */
	public void setOffenderStartDate(final Date offenderStartDate) {
		this.offenderStartDate = offenderStartDate;
	}

	/**
	 * @return the programId
	 */
	public Long getProgramId() {
		return programId;
	}

	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(final Long programId) {
		this.programId = programId;
	}

	/**
	 * @return the raceCode
	 */
	public String getRaceCode() {
		return raceCode;
	}

	/**
	 * @param raceCode the raceCode to set
	 */
	public void setRaceCode(final String raceCode) {
		this.raceCode = raceCode;
	}

	/**
	 * @return the sexCode
	 */
	public String getSexCode() {
		return sexCode;
	}

	/**
	 * @param sexCode the sexCode to set
	 */
	public void setSexCode(final String sexCode) {
		this.sexCode = sexCode;
	}

	/**
	 * @return the suspendedFlag
	 */
	public String getSuspendedFlag() {
		return suspendedFlag;
	}

	/**
	 * @param suspendedFlag the suspendedFlag to set
	 */
	public void setSuspendedFlag(final String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	} 
	
	

}
