package net.syscon.s4.inst.property.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class Persons
 * 
 */
@XmlRootElement
public class Persons extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("personId")
	private Integer personId;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("middleName")
	private String middleName;
	
	@JsonProperty("birthdate")
	private Date birthdate;
	
	@JsonProperty("occupationCode")
	private String occupationCode;
	
	@JsonProperty("criminalHistoryText")
	private String criminalHistoryText;
	
	@JsonProperty("nameType")
	private String nameType;
	
	@JsonProperty("aliasPersonId")
	private Integer aliasPersonId;
	
	@JsonProperty("rootPersonId")
	private Integer rootPersonId;
	
	@JsonProperty("languageCode")
	private String languageCode;
	
	@JsonProperty("comprehendEnglishFlag")
	private String comprehendEnglishFlag;
	
	@JsonProperty("sex")
	private String sex;
	
	@JsonProperty("birthPlace")
	private String birthPlace;
	
	@JsonProperty("employer")
	private String employer;
	
	@JsonProperty("profileCode")
	private String profileCode;
	
	@JsonProperty("interpreterRequired")
	private String interpreterRequired;
	
	@JsonProperty("primaryLanguageCode")
	private String primaryLanguageCode;
	
	@JsonProperty("memoText")
	private String memoText;
	
	@JsonProperty("suspendedFlag")
	private String suspendedFlag;
	
	@JsonProperty("maritalStatus")
	private String maritalStatus;
	
	@JsonProperty("citizenship")
	private String citizenship;
	
	@JsonProperty("deceasedDate")
	private Date deceasedDate;
	
	@JsonProperty("coronerNumber")
	private String coronerNumber;
	
	@JsonProperty("attention")
	private String attention;
	
	@JsonProperty("careOf")
	private String careOf;
	
	@JsonProperty("suspendedDate")
	private Date suspendedDate;
	
	@JsonProperty("createDatetime")
	private Timestamp createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Timestamp modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("staffFlag")
	private String staffFlag;
	
	@JsonProperty("remitterFlag")
	private String remitterFlag;
	
	@JsonProperty("lastNameSoundex")
	private String lastNameSoundex;
	
	@JsonProperty("firstNameKey")
	private String firstNameKey;
	
	@JsonProperty("middleNameKey")
	private String middleNameKey;
	
	@JsonProperty("lastNameKey")
	private String lastNameKey;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("inserted")
	private boolean inserted;
	
	@JsonProperty("dspName")
	private String dspName;
	
	public Persons() {
		// Persons
	}
	
	
	/**
	 * @param personId
	 *            personId to set
	 */
	public void setPersonId(final Integer personId) {
		this.personId = personId;
	}

	/**
	 * return the personId
	 */
	public Integer getPersonId() {
		return this.personId;
	}

	/**
	 * @param lastName
	 *            lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @param firstName
	 *            firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param middleName
	 *            middleName to set
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 * return the middleName
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * @param birthdate
	 *            birthdate to set
	 */
	public void setBirthdate(final Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * return the birthdate
	 */
	public Date getBirthdate() {
		return this.birthdate;
	}

	/**
	 * @param occupationCode
	 *            occupationCode to set
	 */
	public void setOccupationCode(final String occupationCode) {
		this.occupationCode = occupationCode;
	}

	/**
	 * return the occupationCode
	 */
	public String getOccupationCode() {
		return this.occupationCode;
	}

	/**
	 * @param criminalHistoryText
	 *            criminalHistoryText to set
	 */
	public void setCriminalHistoryText(final String criminalHistoryText) {
		this.criminalHistoryText = criminalHistoryText;
	}

	/**
	 * return the criminalHistoryText
	 */
	public String getCriminalHistoryText() {
		return this.criminalHistoryText;
	}

	/**
	 * @param nameType
	 *            nameType to set
	 */
	public void setNameType(final String nameType) {
		this.nameType = nameType;
	}

	/**
	 * return the nameType
	 */
	public String getNameType() {
		return this.nameType;
	}

	/**
	 * @param aliasPersonId
	 *            aliasPersonId to set
	 */
	public void setAliasPersonId(final Integer aliasPersonId) {
		this.aliasPersonId = aliasPersonId;
	}

	/**
	 * return the aliasPersonId
	 */
	public Integer getAliasPersonId() {
		return this.aliasPersonId;
	}

	/**
	 * @param rootPersonId
	 *            rootPersonId to set
	 */
	public void setRootPersonId(final Integer rootPersonId) {
		this.rootPersonId = rootPersonId;
	}

	/**
	 * return the rootPersonId
	 */
	public Integer getRootPersonId() {
		return this.rootPersonId;
	}

	/**
	 * @param languageCode
	 *            languageCode to set
	 */
	public void setLanguageCode(final String languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * return thelanguageCode
	 */
	public String getLanguageCode() {
		return this.languageCode;
	}

	/**
	 * @param comprehendEnglishFlag
	 *            comprehendEnglishFlag to set
	 */
	public void setComprehendEnglishFlag(final String comprehendEnglishFlag) {
		this.comprehendEnglishFlag = comprehendEnglishFlag;
	}

	/**
	 * return thecomprehendEnglishFlag
	 */
	public String getComprehendEnglishFlag() {
		return this.comprehendEnglishFlag;
	}

	/**
	 * @param sex
	 *            sex to set
	 */
	public void setSex(final String sex) {
		this.sex = sex;
	}

	/**
	 * return thesex
	 */
	public String getSex() {
		return this.sex;
	}

	/**
	 * @param birthPlace
	 *            birthPlace to set
	 */
	public void setBirthPlace(final String birthPlace) {
		this.birthPlace = birthPlace;
	}

	/**
	 * return thebirthPlace
	 */
	public String getBirthPlace() {
		return this.birthPlace;
	}

	/**
	 * @param employer
	 *            employer to set
	 */
	public void setEmployer(final String employer) {
		this.employer = employer;
	}

	/**
	 * return theemployer
	 */
	public String getEmployer() {
		return this.employer;
	}

	/**
	 * @param profileCode
	 *            profileCode to set
	 */
	public void setProfileCode(final String profileCode) {
		this.profileCode = profileCode;
	}

	/**
	 * return theprofileCode
	 */
	public String getProfileCode() {
		return this.profileCode;
	}

	/**
	 * @param interpreterRequired
	 *            interpreterRequired to set
	 */
	public void setInterpreterRequired(final String interpreterRequired) {
		this.interpreterRequired = interpreterRequired;
	}

	/**
	 * return theinterpreterRequired
	 */
	public String getInterpreterRequired() {
		return this.interpreterRequired;
	}

	/**
	 * @param primaryLanguageCode
	 *            primaryLanguageCode to set
	 */
	public void setPrimaryLanguageCode(final String primaryLanguageCode) {
		this.primaryLanguageCode = primaryLanguageCode;
	}

	/**
	 * return theprimaryLanguageCode
	 */
	public String getPrimaryLanguageCode() {
		return this.primaryLanguageCode;
	}

	/**
	 * @param memoText
	 *            memoText to set
	 */
	public void setMemoText(final String memoText) {
		this.memoText = memoText;
	}

	/**
	 * return thememoText
	 */
	public String getMemoText() {
		return this.memoText;
	}

	/**
	 * @param suspendedFlag
	 *            suspendedFlag to set
	 */
	public void setSuspendedFlag(final String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	/**
	 * return thesuspendedFlag
	 */
	public String getSuspendedFlag() {
		return this.suspendedFlag;
	}

	/**
	 * @param maritalStatus
	 *            maritalStatus to set
	 */
	public void setMaritalStatus(final String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * return themaritalStatus
	 */
	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	/**
	 * @param citizenship
	 *            citizenship to set
	 */
	public void setCitizenship(final String citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * return thecitizenship
	 */
	public String getCitizenship() {
		return this.citizenship;
	}

	/**
	 * @param deceasedDate
	 *            deceasedDate to set
	 */
	public void setDeceasedDate(final Date deceasedDate) {
		this.deceasedDate = deceasedDate;
	}

	/**
	 * return thedeceasedDate
	 */
	public Date getDeceasedDate() {
		return this.deceasedDate;
	}

	/**
	 * @param coronerNumber
	 *            coronerNumber to set
	 */
	public void setCoronerNumber(final String coronerNumber) {
		this.coronerNumber = coronerNumber;
	}

	/**
	 * return thecoronerNumber
	 */
	public String getCoronerNumber() {
		return this.coronerNumber;
	}

	/**
	 * @param attention
	 *            attention to set
	 */
	public void setAttention(final String attention) {
		this.attention = attention;
	}

	/**
	 * return the attention
	 */
	public String getAttention() {
		return this.attention;
	}

	/**
	 * @param careOf
	 *            careOf to set
	 */
	public void setCareOf(final String careOf) {
		this.careOf = careOf;
	}

	/**
	 * return thecareOf
	 */
	public String getCareOf() {
		return this.careOf;
	}

	/**
	 * @param suspendedDate
	 *            suspendedDate to set
	 */
	public void setSuspendedDate(final Date suspendedDate) {
		this.suspendedDate = suspendedDate;
	}

	/**
	 * return thesuspendedDate
	 */
	public Date getSuspendedDate() {
		return this.suspendedDate;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDatetime(final Timestamp createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Timestamp getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(final Timestamp modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Timestamp getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param staffFlag
	 *            staffFlag to set
	 */
	public void setStaffFlag(final String staffFlag) {
		this.staffFlag = staffFlag;
	}

	/**
	 * return thestaffFlag
	 */
	public String getStaffFlag() {
		return this.staffFlag;
	}

	/**
	 * @param remitterFlag
	 *            remitterFlag to set
	 */
	public void setRemitterFlag(final String remitterFlag) {
		this.remitterFlag = remitterFlag;
	}

	/**
	 * return theremitterFlag
	 */
	public String getRemitterFlag() {
		return this.remitterFlag;
	}

	/**
	 * @param lastNameSoundex
	 *            lastNameSoundex to set
	 */
	public void setLastNameSoundex(final String lastNameSoundex) {
		this.lastNameSoundex = lastNameSoundex;
	}

	/**
	 * return thelastNameSoundex
	 */
	public String getLastNameSoundex() {
		return this.lastNameSoundex;
	}

	/**
	 * @param firstNameKey
	 *            firstNameKey to set
	 */
	public void setFirstNameKey(final String firstNameKey) {
		this.firstNameKey = firstNameKey;
	}

	/**
	 * return thefirstNameKey
	 */
	public String getFirstNameKey() {
		return this.firstNameKey;
	}

	/**
	 * @param middleNameKey
	 *            middleNameKey to set
	 */
	public void setMiddleNameKey(final String middleNameKey) {
		this.middleNameKey = middleNameKey;
	}

	/**
	 * return themiddleNameKey
	 */
	public String getMiddleNameKey() {
		return this.middleNameKey;
	}

	/**
	 * @param lastNameKey
	 *            lastNameKey to set
	 */
	public void setLastNameKey(final String lastNameKey) {
		this.lastNameKey = lastNameKey;
	}

	/**
	 * return thelastNameKey
	 */
	public String getLastNameKey() {
		return this.lastNameKey;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the dspName
	 */
	public String getDspName() {
		return dspName;
	}

	/**
	 * @param dspName the dspName to set
	 */
	public void setDspName(final String dspName) {
		this.dspName = dspName;
	}

}