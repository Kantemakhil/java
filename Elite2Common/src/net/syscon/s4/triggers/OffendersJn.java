package net.syscon.s4.triggers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffendersJn extends BaseModel implements Serializable{
	
	private String jnOperation;
	private String jnOracleUser;
	private Date jnDatetime;
	private String jnNotes;
	private String jnAppln;
	private BigDecimal jnSession;
	private Long offenderId;
	private Long offenderNameSeq;
	private String idSourceCode;
	private String lastName;
	private String nameType;
	private String firstName;	
	private String middleName;
	private Date birthDate;
	private String sexCode;
	private String suffix;
	private String lastNameSoundex;
	private String birthPlace;
	private String birthCountryCode;
	private Date createDate;
	private String lastNameKey;
	private Long aliasOffenderId;
	private String firstNameKey;
	private String middleNameKey;
	private String offenderIdDisplay;
	private Long rootOffenderId;
	private String caseloadType;
	private String modifyUserId;
	private String aliasNameType;
	private Long parentOffenderId;
	private String uniqueObligationFlag;
	private String suspendedFlag;
	private Date suspendedDate;
	private String raceCode;
	private String remarkCode;
	private String addInfoCode;
	private String birthCounty;
	private String birthState;
	private String middleName2;
	private String title;
	private Long age;
	private String createUserId;
	private String lastNameAlphaKey;
	private String nameSequence;
	private String sealFlag;
	private String genderCode;
	
	
	public String getJnOperation() {
		return jnOperation;
	}
	public void setJnOperation(String jnOperation) {
		this.jnOperation = jnOperation;
	}
	public String getJnOracleUser() {
		return jnOracleUser;
	}
	public void setJnOracleUser(String jnOracleUser) {
		this.jnOracleUser = jnOracleUser;
	}
	public Date getJnDatetime() {
		return jnDatetime;
	}
	public void setJnDatetime(Date jnDatetime) {
		this.jnDatetime = jnDatetime;
	}
	public String getJnNotes() {
		return jnNotes;
	}
	public void setJnNotes(String jnNotes) {
		this.jnNotes = jnNotes;
	}
	public String getJnAppln() {
		return jnAppln;
	}
	public void setJnAppln(String jnAppln) {
		this.jnAppln = jnAppln;
	}
	public BigDecimal getJnSession() {
		return jnSession;
	}
	public void setJnSession(BigDecimal jnSession) {
		this.jnSession = jnSession;
	}
	public Long getOffenderId() {
		return offenderId;
	}
	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}
	public Long getOffenderNameSeq() {
		return offenderNameSeq;
	}
	public void setOffenderNameSeq(Long offenderNameSeq) {
		this.offenderNameSeq = offenderNameSeq;
	}
	public String getIdSourceCode() {
		return idSourceCode;
	}
	public void setIdSourceCode(String idSourceCode) {
		this.idSourceCode = idSourceCode;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getSexCode() {
		return sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getLastNameSoundex() {
		return lastNameSoundex;
	}
	public void setLastNameSoundex(String lastNameSoundex) {
		this.lastNameSoundex = lastNameSoundex;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getBirthCountryCode() {
		return birthCountryCode;
	}
	public void setBirthCountryCode(String birthCountryCode) {
		this.birthCountryCode = birthCountryCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getLastNameKey() {
		return lastNameKey;
	}
	public void setLastNameKey(String lastNameKey) {
		this.lastNameKey = lastNameKey;
	}
	public Long getAliasOffenderId() {
		return aliasOffenderId;
	}
	public void setAliasOffenderId(Long aliasOffenderId) {
		this.aliasOffenderId = aliasOffenderId;
	}
	public String getFirstNameKey() {
		return firstNameKey;
	}
	public void setFirstNameKey(String firstNameKey) {
		this.firstNameKey = firstNameKey;
	}
	public String getMiddleNameKey() {
		return middleNameKey;
	}
	public void setMiddleNameKey(String middleNameKey) {
		this.middleNameKey = middleNameKey;
	}
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}
	public Long getRootOffenderId() {
		return rootOffenderId;
	}
	public void setRootOffenderId(Long rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}
	public String getCaseloadType() {
		return caseloadType;
	}
	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getAliasNameType() {
		return aliasNameType;
	}
	public void setAliasNameType(String aliasNameType) {
		this.aliasNameType = aliasNameType;
	}
	public Long getParentOffenderId() {
		return parentOffenderId;
	}
	public void setParentOffenderId(Long parentOffenderId) {
		this.parentOffenderId = parentOffenderId;
	}
	public String getUniqueObligationFlag() {
		return uniqueObligationFlag;
	}
	public void setUniqueObligationFlag(String uniqueObligationFlag) {
		this.uniqueObligationFlag = uniqueObligationFlag;
	}
	public String getSuspendedFlag() {
		return suspendedFlag;
	}
	public void setSuspendedFlag(String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}
	public Date getSuspendedDate() {
		return suspendedDate;
	}
	public void setSuspendedDate(Date suspendedDate) {
		this.suspendedDate = suspendedDate;
	}
	public String getRaceCode() {
		return raceCode;
	}
	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}
	public String getRemarkCode() {
		return remarkCode;
	}
	public void setRemarkCode(String remarkCode) {
		this.remarkCode = remarkCode;
	}
	public String getAddInfoCode() {
		return addInfoCode;
	}
	public void setAddInfoCode(String addInfoCode) {
		this.addInfoCode = addInfoCode;
	}
	public String getBirthCounty() {
		return birthCounty;
	}
	public void setBirthCounty(String birthCounty) {
		this.birthCounty = birthCounty;
	}
	public String getBirthState() {
		return birthState;
	}
	public void setBirthState(String birthState) {
		this.birthState = birthState;
	}
	public String getMiddleName2() {
		return middleName2;
	}
	public void setMiddleName2(String middleName2) {
		this.middleName2 = middleName2;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getLastNameAlphaKey() {
		return lastNameAlphaKey;
	}
	public void setLastNameAlphaKey(String lastNameAlphaKey) {
		this.lastNameAlphaKey = lastNameAlphaKey;
	}
	public String getNameSequence() {
		return nameSequence;
	}
	public void setNameSequence(String nameSequence) {
		this.nameSequence = nameSequence;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	
	
	
}
