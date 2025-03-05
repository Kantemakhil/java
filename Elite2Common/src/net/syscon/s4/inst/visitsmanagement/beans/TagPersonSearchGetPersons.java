package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class TagPersonSearchGetPersons
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagPersonSearchGetPersons extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("pSex")
	private String pSex;
	@JsonProperty("pBirthDate")
	private Date pBirthDate;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("pIdentifierType")
	private String pIdentifierType;
	@JsonProperty("pMiddleName")
	private String pMiddleName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("pBirthYear")
	private Integer pBirthYear;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("pSearchType")
	private String pSearchType;
	@JsonProperty("pLastName")
	private String pLastName;
	@JsonProperty("personId")
	private BigDecimal personId;
	@JsonProperty("birthDate")
	private Date birthDate;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("pBirthRange")
	private Integer pBirthRange;
	@JsonProperty("pPersonId")
	private Integer pPersonId;
	@JsonProperty("pIdentifierValue")
	private String pIdentifierValue;
	@JsonProperty("pFirstName")
	private String pFirstName;
	@JsonProperty("age")
	private Long age;
	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonProperty("secondMiddleName")
	private String secondMiddleName;
	@JsonProperty("sexDescription")
	private String sexDescription;
	
	@JsonProperty("pin")
	private String pin;
	
	@JsonProperty("intCorrelationId")
	private Long intCorrelationId;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("responseData")
	private byte[] responseData;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("pnin")
	private String pnin;

	/**
	 * @return the pSex
	 */
	public String getpSex() {
		return pSex;
	}

	/**
	 * @param pSex the pSex to set
	 */
	public void setpSex(String pSex) {
		this.pSex = pSex;
	}

	/**
	 * @return the pBirthDate
	 */
	public Date getpBirthDate() {
		return pBirthDate;
	}

	/**
	 * @param pBirthDate the pBirthDate to set
	 */
	public void setpBirthDate(Date pBirthDate) {
		this.pBirthDate = pBirthDate;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the pIdentifierType
	 */
	public String getpIdentifierType() {
		return pIdentifierType;
	}

	/**
	 * @param pIdentifierType the pIdentifierType to set
	 */
	public void setpIdentifierType(String pIdentifierType) {
		this.pIdentifierType = pIdentifierType;
	}

	/**
	 * @return the pMiddleName
	 */
	public String getpMiddleName() {
		return pMiddleName;
	}

	/**
	 * @param pMiddleName the pMiddleName to set
	 */
	public void setpMiddleName(String pMiddleName) {
		this.pMiddleName = pMiddleName;
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
	 * @return the pBirthYear
	 */
	public Integer getpBirthYear() {
		return pBirthYear;
	}

	/**
	 * @param pBirthYear the pBirthYear to set
	 */
	public void setpBirthYear(Integer pBirthYear) {
		this.pBirthYear = pBirthYear;
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
	 * @return the pSearchType
	 */
	public String getpSearchType() {
		return pSearchType;
	}

	/**
	 * @param pSearchType the pSearchType to set
	 */
	public void setpSearchType(String pSearchType) {
		this.pSearchType = pSearchType;
	}

	/**
	 * @return the pLastName
	 */
	public String getpLastName() {
		return pLastName;
	}

	/**
	 * @param pLastName the pLastName to set
	 */
	public void setpLastName(String pLastName) {
		this.pLastName = pLastName;
	}

	/**
	 * @return the personId
	 */
	public BigDecimal getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the pBirthRange
	 */
	public Integer getpBirthRange() {
		return pBirthRange;
	}

	/**
	 * @param pBirthRange the pBirthRange to set
	 */
	public void setpBirthRange(Integer pBirthRange) {
		this.pBirthRange = pBirthRange;
	}

	/**
	 * @return the pPersonId
	 */
	public Integer getpPersonId() {
		return pPersonId;
	}

	/**
	 * @param pPersonId the pPersonId to set
	 */
	public void setpPersonId(Integer pPersonId) {
		this.pPersonId = pPersonId;
	}

	/**
	 * @return the pIdentifierValue
	 */
	public String getpIdentifierValue() {
		return pIdentifierValue;
	}

	/**
	 * @param pIdentifierValue the pIdentifierValue to set
	 */
	public void setpIdentifierValue(String pIdentifierValue) {
		this.pIdentifierValue = pIdentifierValue;
	}

	/**
	 * @return the pFirstName
	 */
	public String getpFirstName() {
		return pFirstName;
	}

	/**
	 * @param pFirstName the pFirstName to set
	 */
	public void setpFirstName(String pFirstName) {
		this.pFirstName = pFirstName;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted the inserted to set
	 */
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the age
	 */
	public Long getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Long age) {
		this.age = age;
	}

	/**
	 * @return the secondMiddleName
	 */
	public String getsecondMiddleName() {
		return secondMiddleName;
	}

	/**
	 * @param secondMiddleName the secondMiddleName to set
	 */
	public void setsecondMiddleName(String secondMiddleName) {
		this.secondMiddleName = secondMiddleName;
	}
	
	public String getSexDescription() {
		return sexDescription;
	}

	public void setSexDescription(String sexDescription) {
		this.sexDescription = sexDescription;
	}


	public String getPin() {
		return pin;
	}

	public Long getIntCorrelationId() {
		return intCorrelationId;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}

	public void setIntCorrelationId(Long intCorrelationId) {
		this.intCorrelationId = intCorrelationId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public String getPnin() {
		return pnin;
	}

	public void setPnin(String pnin) {
		this.pnin = pnin;
	}
	
}