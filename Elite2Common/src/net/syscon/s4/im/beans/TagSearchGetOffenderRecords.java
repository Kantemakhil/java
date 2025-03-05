package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.common.validators.ValidBirthDate;
import net.syscon.s4.common.validators.ValidBirthYear;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class TagSearchGetOffenderRecords extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;	

	@JsonProperty("pSearchType")
	@NotNull
	private String pSearchType;

	@JsonProperty("pLastName")
	private String pLastName;

	@JsonProperty("pFirstName")
	private String pFirstName;

	@JsonProperty("pMiddleName")
	private String pMiddleName;

	@JsonProperty("pIdentifierType")
	private String pIdentifierType;

	@JsonProperty("pIdentifierValue")
	private String pIdentifierValue;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("pSexCode")
	private String pSexCode;

	@JsonProperty("pBirthDate")
	@ValidBirthDate(message = "date.format.not.valid", pattern = Utilities.DATE_PATTEN)
	private Date pBirthDate;

	@JsonProperty("pBirthYear")
	// @Digits(fraction = 0, integer = 4, message =
	// "Birth.year.should.be.4.digit.number")
	// @Size(max = 4, min = 4, message = "Birth.year.must.be.4.digit.number")
	@ValidBirthYear(message = "Birth.year.must.be.4.digit.number")
	private String pBirthYear;

	@JsonProperty("pBirthRange")
	@Digits(fraction = 0, integer = 2, message = "Birth.range.must.be.a.number")
	private BigDecimal pBirthRange;

	@JsonProperty("pAgedate")
	private Date pAgedate;

	@JsonProperty("pAgeRange")
	private BigDecimal pAgeRange;

	@JsonProperty("pEthnicity")
	private String pEthnicity;

	@JsonProperty("pNameVariation")
	private String pNameVariation;

	@JsonProperty("pSwitchNames")
	private String pSwitchNames;

	@JsonProperty("offenderId")
	private String offenderId;

	@JsonProperty("rootOffenderId")
	private String rootOffenderId;

	@JsonProperty("workingNameFlag")
	private String workingNameFlag;

	@JsonProperty("nbtBkgNo")
	private String nbtBkgNo;

	@JsonProperty("nameType")
	private String nameType;
	
	@JsonProperty("pBookNo")
	private String pBookNo;
	
	@JsonProperty("pGenderCode")
	private String pGenderCode;
	
	@JsonProperty("secondMiddleName")
	private String secondMiddleName;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("pin")
	private String pin;
	
	@JsonProperty("intCorrelationId")
	private Long intCorrelationId;
	
	@JsonProperty("responseData")
	private byte[] responseData;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("parentForm")
	private String parentForm;
	
	@JsonProperty("pnin")
	private String pnin;

	public String getpBookNo() {
		return pBookNo;
	}

	public void setpBookNo(final String pBookNo) {
		this.pBookNo = pBookNo;
	}

	/**
	 * Creates new ReferenceCodes class Object
	 */
	public TagSearchGetOffenderRecords() {
		// TagSearchGetOffenderRecords
	}
	/**
	 * @return the nameType
	 */
	public String getNameType() {
		return nameType;
	}

	/**
	 * @param nameType the nameType to set
	 */
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	/**
	 *
	 * @return
	 */
	public String getpSearchType() {
		return pSearchType;
	}

	/**
	 *
	 * @param pSearchType
	 */
	public void setpSearchType(final String pSearchType) {
		this.pSearchType = pSearchType;
	}

	/**
	 *
	 * @return
	 */
	public String getpLastName() {
		return pLastName;
	}

	/**
	 *
	 * @param pLastName
	 */
	public void setpLastName(final String pLastName) {
		this.pLastName = pLastName;
	}

	/**
	 *
	 * @return
	 */
	public String getpFirstName() {
		return pFirstName;
	}

	/**
	 *
	 * @param pFirstName
	 */
	public void setpFirstName(final String pFirstName) {
		this.pFirstName = pFirstName;
	}

	/**
	 *
	 * @return
	 */
	public String getpMiddleName() {
		return pMiddleName;
	}

	/**
	 *
	 * @param pMiddleName
	 */
	public void setpMiddleName(final String pMiddleName) {
		this.pMiddleName = pMiddleName;
	}

	/**
	 *
	 * @return
	 */
	public String getpIdentifierType() {
		return pIdentifierType;
	}

	/**
	 *
	 * @param pIdentifierType
	 */
	public void setpIdentifierType(final String pIdentifierType) {
		this.pIdentifierType = pIdentifierType;
	}

	/**
	 *
	 * @return
	 */
	public String getpIdentifierValue() {
		return pIdentifierValue;
	}

	/**
	 *
	 * @param pIdentifierValue
	 */
	public void setpIdentifierValue(final String pIdentifierValue) {
		this.pIdentifierValue = pIdentifierValue;
	}

	/**
	 *
	 * @return
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 *
	 * @param offenderIdDisplay
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 *
	 * @return
	 */
	public String getpSexCode() {
		return pSexCode;
	}

	/**
	 *
	 * @param pSexCode
	 */
	public void setpSexCode(final String pSexCode) {
		this.pSexCode = pSexCode;
	}

	/**
	 *
	 * @return
	 */
	public Date getpBirthDate() {
		return pBirthDate;
	}

	/**
	 *
	 * @param pBirthDate
	 */
	public void setpBirthDate(final Date pBirthDate) {
		this.pBirthDate = pBirthDate;
	}

	/**
	 *
	 * @return
	 */
	public String getpBirthYear() {
		return pBirthYear;
	}

	/**
	 *
	 * @param pBirthYear
	 */
	public void setpBirthYear(final String pBirthYear) {
		this.pBirthYear = pBirthYear;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getpBirthRange() {
		return pBirthRange;
	}

	/**
	 *
	 * @param pBirthRange
	 */
	public void setpBirthRange(final BigDecimal pBirthRange) {
		this.pBirthRange = pBirthRange;
	}

	/**
	 *
	 * @return
	 */
	public Date getpAgedate() {
		return pAgedate;
	}

	/**
	 *
	 * @param pAgedate
	 */
	public void setpAgedate(final Date pAgedate) {
		this.pAgedate = pAgedate;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getpAgeRange() {
		return pAgeRange;
	}

	/**
	 *
	 * @param pAgeRange
	 */
	public void setpAgeRange(final BigDecimal pAgeRange) {
		this.pAgeRange = pAgeRange;
	}

	/**
	 *
	 * @return
	 */
	public String getpEthnicity() {
		return pEthnicity;
	}

	/**
	 *
	 * @param pEthnicity
	 */
	public void setpEthnicity(final String pEthnicity) {
		this.pEthnicity = pEthnicity;
	}

	/**
	 *
	 * @return
	 */
	public String getpNameVariation() {
		return pNameVariation;
	}

	/**
	 *
	 * @param pNameVariation
	 */
	public void setpNameVariation(final String pNameVariation) {
		this.pNameVariation = pNameVariation;
	}

	/**
	 *
	 * @return
	 */
	public String getpSwitchNames() {
		return pSwitchNames;
	}

	/**
	 *
	 * @param pSwitchNames
	 */
	public void setpSwitchNames(final String pSwitchNames) {
		this.pSwitchNames = pSwitchNames;
	}

	/**
	 *
	 * @return
	 */
	public String getOffenderId() {
		return offenderId;
	}

	/**
	 *
	 * @param offenderId
	 */
	public void setOffenderId(final String offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 *
	 * @param rootOffenderId
	 */
	public void setRootOffenderId(final String rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getWorkingNameFlag() {
		return workingNameFlag;
	}

	/**
	 *
	 * @param workingNameFlag
	 */
	public void setWorkingNameFlag(final String workingNameFlag) {
		this.workingNameFlag = workingNameFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getNbtBkgNo() {
		return nbtBkgNo;
	}

	/**
	 *
	 * @param nbtBkgNo
	 */
	public void setNbtBkgNo(final String nbtBkgNo) {
		this.nbtBkgNo = nbtBkgNo;
	}

	/**
	 * To validate search type and last name properties
	 * 
	 * @return
	 */
	@AssertTrue(message = "search.type.and.lastname.mandatory")
	private boolean isValid() {
		if ("N".equals(this.pSearchType) || "P".equals(this.pSearchType) || "S".equals(this.pSearchType)) {
			return this.pLastName != null;
		}
		return true;
	}

	/**
	 * To validate search type and last name properties
	 * 
	 * @return
	 */
	@AssertTrue(message = "atlease.one.ORCA.BKG..Identifier.mandatory")
	private boolean isValidIdentifier() {
		if ("I".equals(this.pSearchType)) {
			return this.offenderIdDisplay != null || this.nbtBkgNo != null
					|| (this.pIdentifierType != null && this.pIdentifierValue != null);
		}
		return true;
	}

	public String getpGenderCode() {
		return pGenderCode;
	}

	public void setpGenderCode(String pGenderCode) {
		this.pGenderCode = pGenderCode;
	}
	
	/**
	 *
	 * @return
	 */
	public String getsecondMiddleName() {
		return secondMiddleName;
	}

	/**
	 *
	 * @param pMiddleName
	 */
	public void setsecondMiddleName(final String secondMiddleName) {
		this.secondMiddleName = secondMiddleName;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getPin() {
		return pin;
	}

	public Long getIntCorrelationId() {
		return intCorrelationId;
	}

	public void setIntCorrelationId(Long intCorrelationId) {
		this.intCorrelationId = intCorrelationId;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public byte[] getResponseData() {
		return responseData;
	}

	public void setResponseData(byte[] responseData) {
		this.responseData = responseData;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getParentForm() {
		return parentForm;
	}

	public void setParentForm(String parentForm) {
		this.parentForm = parentForm;
	}

	public String getPnin() {
		return pnin;
	}

	public void setPnin(String pnin) {
		this.pnin = pnin;
	}

}