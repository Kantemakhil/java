package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class Persons extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("personId")
	private Long personId;

	@JsonProperty("rootPersonId")
	private BigDecimal rootPersonId;

	@JsonProperty("aliasPersonId")
	private BigDecimal aliasPersonId;

	@JsonProperty("attention")
	private String attention;

	@JsonProperty("birthPlace")
	private String birthPlace;

	@JsonProperty("birthdate")
	private Date birthdate;

	@JsonProperty("careOf")
	private String careOf;

	@JsonProperty("citizenship")
	private String citizenship;

	@JsonProperty("comprehendEnglishFlag")
	private String comprehendEnglishFlag;

	@JsonProperty("coronerNumber")
	private String coronerNumber;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("criminalHistoryText")
	private String criminalHistoryText;

	@JsonProperty("deceasedDate")
	private Date deceasedDate;

	@JsonProperty("employer")
	private String employer;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("firstNameKey")
	private String firstNameKey;

	@JsonProperty("interpreterRequired")
	private String interpreterRequired;

	@JsonProperty("languageCode")
	private String languageCode;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("lastNameKey")
	private String lastNameKey;

	@JsonProperty("lastNameSoundex")
	private String lastNameSoundex;

	@JsonProperty("maritalStatus")
	private String maritalStatus;

	@JsonProperty("memoText")
	private String memoText;

	@JsonProperty("middleName")
	private String middleName;

	@JsonProperty("middleNameKey")
	private String middleNameKey;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("nameType")
	private String nameType;

	@JsonProperty("occupationCode")
	private String occupationCode;

	@JsonProperty("primaryLanguageCode")
	private String primaryLanguageCode;

	@JsonProperty("profileCode")
	private String profileCode;

	@JsonProperty("remitterFlag")
	private String remitterFlag;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("sex")
	private String sex;

	@JsonProperty("staffFlag")
	private String staffFlag;

	@JsonProperty("suspendedDate")
	private Date suspendedDate;

	@JsonProperty("suspendedFlag")
	private String suspendedFlag;

	@JsonProperty("globalCaseloadId")
	private String globalCaseloadId;

	@JsonProperty("streetInformation")
	private String streetInformation;

	@JsonProperty("suiteNumber")
	private String suiteNumber;

	@JsonProperty("zipPostalCode")
	private String zipPostalCode;

	@JsonProperty("cityDesc")
	private String cityDesc;

	@JsonProperty("stateDesc")
	private String stateDesc;

	@JsonProperty("countryDesc")
	private String countryDesc;

	@JsonProperty("age")
	private String age;

	@JsonProperty("code")
	private String code;

	@JsonProperty("description")
	private String description;
	
	@JsonProperty("searchType")
	private String searchType;
	
	@JsonProperty("birthYear")
	private Integer birthYear;
	
	@JsonProperty("birthRange")
	private Integer birthRange ;

	@JsonProperty("identifier")
	private String identifier;

	@JsonProperty("identifierType")
	private String identifierType;
	
	@JsonProperty("lvFromDate")
	private Date lvFromDate;
	
	@JsonProperty("lvToDate")
	private Date lvToDate;
	
	@JsonProperty("secondMiddleName")
	private String secondMiddleName;
	
	@JsonProperty("sexDescription")
	private String sexDescription;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("notes")
	private String notes;
	
	@JsonProperty("pinValue")
	private String pinValue;
	
	@JsonProperty("pIdentifierType")
	private String pIdentifierType;
	
	@JsonProperty("pIdentifierValue")
	private String pIdentifierValue;
	
	@JsonProperty("pninValue")
	private String pninValue;
	
	public Date getLvToDate() {
		return lvToDate;
	}

	public void setLvToDate(Date lvToDate) {
		this.lvToDate = lvToDate;
	}

	@JsonProperty("hits")
	private Integer hits;


	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public Integer getBirthRange() {
		return birthRange;
	}

	public void setBirthRange(Integer birthRange) {
		this.birthRange = birthRange;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifierType() {
		return identifierType;
	}

	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	public Date getLvFromDate() {
		return lvFromDate;
	}

	public void setLvFromDate(Date lvFromDate) {
		this.lvFromDate = lvFromDate;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Persons() {
		// Persons
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPersonId() {
		return this.personId;
	}

	public void setPersonId(final Long personId) {
		this.personId = personId;
	}

	public BigDecimal getAliasPersonId() {
		return this.aliasPersonId;
	}

	public void setAliasPersonId(final BigDecimal aliasPersonId) {
		this.aliasPersonId = aliasPersonId;
	}

	public String getAttention() {
		return this.attention;
	}

	public void setAttention(final String attention) {
		this.attention = attention;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(final String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(final Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCareOf() {
		return this.careOf;
	}

	public void setCareOf(final String careOf) {
		this.careOf = careOf;
	}

	public String getCitizenship() {
		return this.citizenship;
	}

	public void setCitizenship(final String citizenship) {
		this.citizenship = citizenship;
	}

	public String getComprehendEnglishFlag() {
		return this.comprehendEnglishFlag;
	}

	public void setComprehendEnglishFlag(final String comprehendEnglishFlag) {
		this.comprehendEnglishFlag = comprehendEnglishFlag;
	}

	public String getCoronerNumber() {
		return this.coronerNumber;
	}

	public void setCoronerNumber(final String coronerNumber) {
		this.coronerNumber = coronerNumber;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCriminalHistoryText() {
		return this.criminalHistoryText;
	}

	public void setCriminalHistoryText(final String criminalHistoryText) {
		this.criminalHistoryText = criminalHistoryText;
	}

	public Date getDeceasedDate() {
		return this.deceasedDate;
	}

	public void setDeceasedDate(final Date deceasedDate) {
		this.deceasedDate = deceasedDate;
	}

	public String getEmployer() {
		return this.employer;
	}

	public void setEmployer(final String employer) {
		this.employer = employer;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getFirstNameKey() {
		return this.firstNameKey;
	}

	public void setFirstNameKey(final String firstNameKey) {
		this.firstNameKey = firstNameKey;
	}

	public String getInterpreterRequired() {
		return this.interpreterRequired;
	}

	public void setInterpreterRequired(final String interpreterRequired) {
		this.interpreterRequired = interpreterRequired;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(final String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getLastNameKey() {
		return this.lastNameKey;
	}

	public void setLastNameKey(final String lastNameKey) {
		this.lastNameKey = lastNameKey;
	}

	public String getLastNameSoundex() {
		return this.lastNameSoundex;
	}

	public void setLastNameSoundex(final String lastNameSoundex) {
		this.lastNameSoundex = lastNameSoundex;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(final String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMemoText() {
		return this.memoText;
	}

	public void setMemoText(final String memoText) {
		this.memoText = memoText;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	public String getMiddleNameKey() {
		return this.middleNameKey;
	}

	public void setMiddleNameKey(final String middleNameKey) {
		this.middleNameKey = middleNameKey;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getNameType() {
		return this.nameType;
	}

	public void setNameType(final String nameType) {
		this.nameType = nameType;
	}

	public String getOccupationCode() {
		return this.occupationCode;
	}

	public void setOccupationCode(final String occupationCode) {
		this.occupationCode = occupationCode;
	}

	public String getPrimaryLanguageCode() {
		return this.primaryLanguageCode;
	}

	public void setPrimaryLanguageCode(final String primaryLanguageCode) {
		this.primaryLanguageCode = primaryLanguageCode;
	}

	public String getProfileCode() {
		return this.profileCode;
	}

	public void setProfileCode(final String profileCode) {
		this.profileCode = profileCode;
	}

	public String getRemitterFlag() {
		return this.remitterFlag;
	}

	public void setRemitterFlag(final String remitterFlag) {
		this.remitterFlag = remitterFlag;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(final String sex) {
		this.sex = sex;
	}

	public String getStaffFlag() {
		return this.staffFlag;
	}

	public void setStaffFlag(final String staffFlag) {
		this.staffFlag = staffFlag;
	}

	public Date getSuspendedDate() {
		return this.suspendedDate;
	}

	public void setSuspendedDate(final Date suspendedDate) {
		this.suspendedDate = suspendedDate;
	}

	public String getSuspendedFlag() {
		return this.suspendedFlag;
	}

	public void setSuspendedFlag(final String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	/**
	 * @return the globalCaseloadId
	 */
	public String getGlobalCaseloadId() {
		return globalCaseloadId;
	}

	/**
	 * @param globalCaseloadId
	 *            the globalCaseloadId to set
	 */
	public void setGlobalCaseloadId(final String globalCaseloadId) {
		this.globalCaseloadId = globalCaseloadId;
	}

	/**
	 * @return the streetInformation
	 */
	public String getStreetInformation() {
		return streetInformation;
	}

	/**
	 * @param streetInformation
	 *            the streetInformation to set
	 */
	public void setStreetInformation(final String streetInformation) {
		this.streetInformation = streetInformation;
	}

	/**
	 * @return the suiteNumber
	 */
	public String getSuiteNumber() {
		return suiteNumber;
	}

	/**
	 * @param suiteNumber
	 *            the suiteNumber to set
	 */
	public void setSuiteNumber(final String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}

	/**
	 * @return the zipPostalCode
	 */
	public String getZipPostalCode() {
		return zipPostalCode;
	}

	/**
	 * @param zipPostalCode
	 *            the zipPostalCode to set
	 */
	public void setZipPostalCode(final String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	/**
	 * @return the cityDesc
	 */
	public String getCityDesc() {
		return cityDesc;
	}

	/**
	 * @param cityDesc
	 *            the cityDesc to set
	 */
	public void setCityDesc(final String cityDesc) {
		this.cityDesc = cityDesc;
	}

	/**
	 * @return the stateDesc
	 */
	public String getStateDesc() {
		return stateDesc;
	}

	/**
	 * @param stateDesc
	 *            the stateDesc to set
	 */
	public void setStateDesc(final String stateDesc) {
		this.stateDesc = stateDesc;
	}

	/**
	 * @return the countryDesc
	 */
	public String getCountryDesc() {
		return countryDesc;
	}

	/**
	 * @param countryDesc
	 *            the countryDesc to set
	 */
	public void setCountryDesc(final String countryDesc) {
		this.countryDesc = countryDesc;
	}

	/**
	 * @return the rootPersonId
	 */
	public BigDecimal getRootPersonId() {
		return rootPersonId;
	}

	/**
	 * @param rootPersonId
	 *            the rootPersonId to set
	 */
	public void setRootPersonId(final BigDecimal rootPersonId) {
		this.rootPersonId = rootPersonId;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getsecondMiddleName() {
		return this.secondMiddleName;
	}

	public void setsecondMiddleName(final String secondMiddleName) {
		this.secondMiddleName = secondMiddleName;
	}
	
	public String getSexDescription() {
		return sexDescription;
	}

	public void setSexDescription(String sexDescription) {
		this.sexDescription = sexDescription;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPinValue() {
		return pinValue;
	}

	public void setPinValue(String pinValue) {
		this.pinValue = pinValue;
	}

	public String getpIdentifierType() {
		return pIdentifierType;
	}

	public String getpIdentifierValue() {
		return pIdentifierValue;
	}

	public void setpIdentifierType(String pIdentifierType) {
		this.pIdentifierType = pIdentifierType;
	}

	public void setpIdentifierValue(String pIdentifierValue) {
		this.pIdentifierValue = pIdentifierValue;
	}

	public String getPninValue() {
		return pninValue;
	}

	public void setPninValue(String pninValue) {
		this.pninValue = pninValue;
	}

}
