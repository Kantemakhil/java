package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class Offenders extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderId")
	@NotNull
	private Long offenderId;

	@JsonProperty("addInfoCode")
	@Size(max = 12)
	private String addInfoCode;

	@JsonProperty("age")
	private BigDecimal age;

	@JsonProperty("aliasNameType")
	@Size(max = 12)
	private String aliasNameType;

	@JsonProperty("birthCountryCode")
	@Size(max = 12)
	private String birthCountryCode;

	@JsonProperty("birthCounty")
	@Size(max = 20)
	private String birthCounty;

	@JsonProperty("birthDate")
	private Date birthDate;

	@JsonProperty("birthPlace")
	@Size(max = 25)
	private String birthPlace;

	@JsonProperty("birthState")
	@Size(max = 20)
	private String birthState;

	@JsonProperty("caseloadType")
	@Size(max = 12)
	private String caseloadType;

	@JsonProperty("createDate")
	@NotNull
	private Date createDate;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("firstName")
	@Size(max = 35)
	private String firstName;

	@JsonProperty("firstNameKey")
	@Size(max = 35)
	private String firstNameKey;

	@JsonProperty("idSourceCode")
	@NotNull
	@Size(max = 12)
	private String idSourceCode;

	@JsonProperty("lastName")
	@NotNull
	private String lastName;

	@JsonProperty("lastNameAlphaKey")
	@Size(max = 1)
	private String lastNameAlphaKey;

	@JsonProperty("lastNameKey")
	@NotNull
	@Size(max = 35)
	private String lastNameKey;

	@JsonProperty("lastNameSoundex")
	@Size(max = 6)
	private String lastNameSoundex;

	@JsonProperty("middleName")
	@Size(max = 35)
	private String middleName;

	@JsonProperty("middleName2")
	@Size(max = 35)
	private String middleName2;

	@JsonProperty("middleNameKey")
	@Size(max = 35)
	private String middleNameKey;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("nameSequence")
	@Size(max = 12)
	private String nameSequence;

	@JsonProperty("nameType")
	@Size(max = 12)
	private String nameType;

	@JsonProperty("offenderIdDisplay")
	@Size(max = 10)
	private String offenderIdDisplay;

	@JsonProperty("offenderNameSeq")
	private BigDecimal offenderNameSeq;

	@JsonProperty("parentOffenderId")
	private BigDecimal parentOffenderId;

	@JsonProperty("raceCode")
	@Size(max = 12)
	private String raceCode;

	@JsonProperty("remarkCode")
	@Size(max = 12)
	private String remarkCode;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("sexCode")
	@NotNull
	@Size(max = 12)
	private String sexCode;

	@JsonProperty("suffix")
	@Size(max = 12)
	private String suffix;

	@JsonProperty("suspendedDate")
	private Date suspendedDate;

	@JsonProperty("suspendedFlag")
	@Size(max = 1)
	private String suspendedFlag;

	@JsonProperty("title")
	@Size(max = 12)
	private String title;

	@JsonProperty("uniqueObligationFlag")
	@Size(max = 1)
	private String uniqueObligationFlag;

	@JsonProperty("aliasOffenderId")
	private Long aliasOffenderId;

	@JsonProperty("domain")
	private String domain;

	@JsonProperty("code")
	private String code;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("aliasColEditCheck")
	private Boolean aliasColEditCheck;

	private Long externalId;
	private String requestStatus;

	@JsonProperty("searchType")
	private String searchType;

	@JsonProperty("birthYear")
	private String birthYear;

	@JsonProperty("birthRange")
	private Integer birthRange;

	@JsonProperty("ageDate")
	private Date ageDate;

	@JsonProperty("ageRange")
	private Integer ageRange;

	@JsonProperty("lvFromDate")
	private Date lvFromDate;

	@JsonProperty("lvToDate")
	private Date lvToDate;

	@JsonProperty("lvFromAgedate")
	private Date lvFromAgedate;

	@JsonProperty("lvToAgedate")
	private Date lvToAgedate;

	@JsonProperty("hits")
	private Integer hits;

	@JsonProperty("switchNames")
	private String switchNames;

	@JsonProperty("nameVariation")
	private String nameVariation;

	@JsonProperty("workingNameFlag")
	private String workingNameFlag;

	@JsonProperty("bookingNo")
	private String bookingNo;

	@JsonProperty("identifier")
	private String identifier;

	private String objectName;
	private String tableName;

	@JsonProperty("genderCode")
	private String genderCode;

	@JsonProperty("offendrBookId")
	private Long offendrBookId;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("communityActiveFlag")
	private String communityActiveFlag;

	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("bookingStatus")
	@Size(max = 12)
	private String bookingStatus;
	
	@JsonProperty("secondMiddleName")
	@Size(max = 35)
	private String secondMiddleName;
	
	@JsonProperty("personId")
	private Long personId;
	
	@JsonProperty("persAddNameId")
	private Long persAddNameId;
	
	@JsonProperty("sexCodeDesc")
	private String sexCodeDesc;
	
	private String pinIdentifier;

	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	@JsonProperty("identifierType")
	private String identifierType;

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

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public String getWorkingNameFlag() {
		return workingNameFlag;
	}

	public void setWorkingNameFlag(String workingNameFlag) {
		this.workingNameFlag = workingNameFlag;
	}

	public String getSwitchNames() {
		return switchNames;
	}

	public void setSwitchNames(String switchNames) {
		this.switchNames = switchNames;
	}

	public String getNameVariation() {
		return nameVariation;
	}

	public void setNameVariation(String nameVariation) {
		this.nameVariation = nameVariation;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Date getLvFromDate() {
		return lvFromDate;
	}

	public void setLvFromDate(Date lvFromDate) {
		this.lvFromDate = lvFromDate;
	}

	public Date getLvToDate() {
		return lvToDate;
	}

	public void setLvToDate(Date lvToDate) {
		this.lvToDate = lvToDate;
	}

	public Date getLvFromAgedate() {
		return lvFromAgedate;
	}

	public void setLvFromAgedate(Date lvFromAgedate) {
		this.lvFromAgedate = lvFromAgedate;
	}

	public Date getLvToAgedate() {
		return lvToAgedate;
	}

	public void setLvToAgedate(Date lvToAgedate) {
		this.lvToAgedate = lvToAgedate;
	}

	public Integer getBirthRange() {
		return birthRange;
	}

	public void setBirthRange(Integer birthRange) {
		this.birthRange = birthRange;
	}

	public Date getAgeDate() {
		return ageDate;
	}

	public void setAgeDate(Date ageDate) {
		this.ageDate = ageDate;
	}

	public Integer getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(Integer ageRange) {
		this.ageRange = ageRange;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * Creates new Offenders class Object
	 */
	public Offenders() {
		// Offenders
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
	 * @return the aliasOffenderId
	 */
	public Long getAliasOffenderId() {
		return aliasOffenderId;
	}

	/**
	 * @param aliasOffenderId the aliasOffenderId to set
	 */
	public void setAliasOffenderId(final Long aliasOffenderId) {
		this.aliasOffenderId = aliasOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 *
	 * @param offenderId
	 */
	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getAddInfoCode() {
		return addInfoCode;
	}

	/**
	 *
	 * @param addInfoCode
	 */
	public void setAddInfoCode(final String addInfoCode) {
		this.addInfoCode = addInfoCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAge() {
		return age;
	}

	/**
	 *
	 * @param age
	 */
	public void setAge(final BigDecimal age) {
		this.age = age;
	}

	/**
	 *
	 * @return
	 */
	public String getAliasNameType() {
		return aliasNameType;
	}

	/**
	 *
	 * @param aliasNameType
	 */
	public void setAliasNameType(final String aliasNameType) {
		this.aliasNameType = aliasNameType;
	}

	/**
	 *
	 * @return
	 */
	public String getBirthCountryCode() {
		return birthCountryCode;
	}

	/**
	 *
	 * @param birthCountryCode
	 */
	public void setBirthCountryCode(final String birthCountryCode) {
		this.birthCountryCode = birthCountryCode;
	}

	/**
	 *
	 * @return
	 */
	public String getBirthCounty() {
		return birthCounty;
	}

	/**
	 *
	 * @param birthCounty
	 */
	public void setBirthCounty(final String birthCounty) {
		this.birthCounty = birthCounty;
	}

	/**
	 *
	 * @return
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 *
	 * @param birthDate
	 */
	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 *
	 * @return
	 */
	public String getBirthPlace() {
		return birthPlace;
	}

	/**
	 *
	 * @param birthPlace
	 */
	public void setBirthPlace(final String birthPlace) {
		this.birthPlace = birthPlace;
	}

	/**
	 *
	 * @return
	 */
	public String getBirthState() {
		return birthState;
	}

	/**
	 *
	 * @param birthState
	 */
	public void setBirthState(final String birthState) {
		this.birthState = birthState;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 *
	 * @param caseloadType
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 *
	 * @param createDate
	 */
	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 *
	 * @param createDateTime
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 *
	 * @param firstName
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 *
	 * @return
	 */
	public String getFirstNameKey() {
		return firstNameKey;
	}

	/**
	 *
	 * @param firstNameKey
	 */
	public void setFirstNameKey(final String firstNameKey) {
		this.firstNameKey = firstNameKey;
	}

	/**
	 *
	 * @return
	 */
	public String getIdSourceCode() {
		return idSourceCode;
	}

	/**
	 *
	 * @param idSourceCode
	 */
	public void setIdSourceCode(final String idSourceCode) {
		this.idSourceCode = idSourceCode;
	}

	/**
	 *
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 *
	 * @param lastName
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 *
	 * @return
	 */
	public String getLastNameAlphaKey() {
		return lastNameAlphaKey;
	}

	/**
	 *
	 * @param lastNameAlphaKey
	 */
	public void setLastNameAlphaKey(final String lastNameAlphaKey) {
		this.lastNameAlphaKey = lastNameAlphaKey;
	}

	/**
	 *
	 * @return
	 */
	public String getLastNameKey() {
		return lastNameKey;
	}

	/**
	 *
	 * @param lastNameKey
	 */
	public void setLastNameKey(final String lastNameKey) {
		this.lastNameKey = lastNameKey;
	}

	/**
	 *
	 * @return
	 */
	public String getLastNameSoundex() {
		return lastNameSoundex;
	}

	/**
	 *
	 * @param lastNameSoundex
	 */
	public void setLastNameSoundex(final String lastNameSoundex) {
		this.lastNameSoundex = lastNameSoundex;
	}

	/**
	 *
	 * @return
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 *
	 * @param middleName
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 *
	 * @return
	 */
	public String getMiddleName2() {
		return middleName2;
	}

	/**
	 *
	 * @param middleName2
	 */
	public void setMiddleName2(final String middleName2) {
		this.middleName2 = middleName2;
	}

	/**
	 *
	 * @return
	 */
	public String getMiddleNameKey() {
		return middleNameKey;
	}

	/**
	 *
	 * @param middleNameKey
	 */
	public void setMiddleNameKey(final String middleNameKey) {
		this.middleNameKey = middleNameKey;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 *
	 * @param modifyDateTime
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getNameSequence() {
		return nameSequence;
	}

	/**
	 *
	 * @param nameSequence
	 */
	public void setNameSequence(final String nameSequence) {
		this.nameSequence = nameSequence;
	}

	/**
	 *
	 * @return
	 */
	public String getNameType() {
		return nameType;
	}

	/**
	 *
	 * @param nameType
	 */
	public void setNameType(final String nameType) {
		this.nameType = nameType;
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
	public BigDecimal getOffenderNameSeq() {
		return offenderNameSeq;
	}

	/**
	 *
	 * @param offenderNameSeq
	 */
	public void setOffenderNameSeq(final BigDecimal offenderNameSeq) {
		this.offenderNameSeq = offenderNameSeq;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getParentOffenderId() {
		return parentOffenderId;
	}

	/**
	 *
	 * @param parentOffenderId
	 */
	public void setParentOffenderId(final BigDecimal parentOffenderId) {
		this.parentOffenderId = parentOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getRaceCode() {
		return raceCode;
	}

	/**
	 *
	 * @param raceCode
	 */
	public void setRaceCode(final String raceCode) {
		this.raceCode = raceCode;
	}

	/**
	 *
	 * @return
	 */
	public String getRemarkCode() {
		return remarkCode;
	}

	/**
	 *
	 * @param remarkCode
	 */
	public void setRemarkCode(final String remarkCode) {
		this.remarkCode = remarkCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 *
	 * @param rootOffenderId
	 */
	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getSexCode() {
		return sexCode;
	}

	/**
	 *
	 * @param sexCode
	 */
	public void setSexCode(final String sexCode) {
		this.sexCode = sexCode;
	}

	/**
	 *
	 * @return
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 *
	 * @param suffix
	 */
	public void setSuffix(final String suffix) {
		this.suffix = suffix;
	}

	/**
	 *
	 * @return
	 */
	public Date getSuspendedDate() {
		return suspendedDate;
	}

	/**
	 *
	 * @param suspendedDate
	 */
	public void setSuspendedDate(final Date suspendedDate) {
		this.suspendedDate = suspendedDate;
	}

	/**
	 *
	 * @return
	 */
	public String getSuspendedFlag() {
		return suspendedFlag;
	}

	/**
	 *
	 * @param suspendedFlag
	 */
	public void setSuspendedFlag(final String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *
	 * @param title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 *
	 * @return
	 */
	public String getUniqueObligationFlag() {
		return uniqueObligationFlag;
	}

	/**
	 *
	 * @param uniqueObligationFlag
	 */
	public void setUniqueObligationFlag(final String uniqueObligationFlag) {
		this.uniqueObligationFlag = uniqueObligationFlag;
	}

	public String getDomain() {
		return domain;
	}

	/**
	 *
	 * @param domain
	 */
	public void setDomain(final String domain) {
		this.domain = domain;
	}

	/**
	 *
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 *
	 * @param code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the aliasColEditCheck
	 */
	public Boolean getAliasColEditCheck() {
		return aliasColEditCheck;
	}

	/**
	 * @param aliasColEditCheck the aliasColEditCheck to set
	 */
	public void setAliasColEditCheck(final Boolean aliasColEditCheck) {
		this.aliasColEditCheck = aliasColEditCheck;
	}

	public Long getExternalId() {
		return externalId;
	}

	public void setExternalId(Long externalId) {
		this.externalId = externalId;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public Long getOffendrBookId() {
		return offendrBookId;
	}

	public void setOffendrBookId(Long offendrBookId) {
		this.offendrBookId = offendrBookId;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getCommunityActiveFlag() {
		return communityActiveFlag;
	}

	public void setCommunityActiveFlag(String communityActiveFlag) {
		this.communityActiveFlag = communityActiveFlag;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	
	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
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
	 * @param secondMiddleName
	 */
	public void setsecondMiddleName(final String secondMiddleName) {
		this.secondMiddleName = secondMiddleName;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getPersAddNameId() {
		return persAddNameId;
	}

	public void setPersAddNameId(Long persAddNameId) {
		this.persAddNameId = persAddNameId;
	}

	public String getSexCodeDesc() {
		return sexCodeDesc;
	}

	public void setSexCodeDesc(String sexCodeDesc) {
		this.sexCodeDesc = sexCodeDesc;
	}

	public String getPinIdentifier() {
		return pinIdentifier;
	}

	public void setPinIdentifier(String pinIdentifier) {
		this.pinIdentifier = pinIdentifier;
	}


}