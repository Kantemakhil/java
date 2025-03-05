package net.syscon.s4.inst.visitsmanagement.beans;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class TagPersonSearchGetPartialSoundexPersons
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagPersonSearchGetPartialSoundexPersons extends BaseModel implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@JsonProperty("hits")
private BigDecimal hits;
 @JsonProperty("pSex")
private String pSex;
 @JsonProperty("pBirthDate")
private Date PBirthDate;
 @JsonProperty("pIdentifierType")
private String pIdentifierType;
 @JsonProperty("pMiddleName")
private String pMiddleName;
 @JsonProperty("lastName")
private String lastName;
 @JsonProperty("pBirthYear")
private Integer pBirthYear;
 @JsonProperty("pSearchType")
private String pSearchType;
 @JsonProperty("pLastName")
private String pLastName;
 @JsonProperty("pBirthRange")
private Integer pBirthRange;
 @JsonProperty("pPersonId")
private Integer pPersonId;
 @JsonProperty("pIdentifierValue")
private String pIdentifierValue;
 @JsonProperty("pFirstName")
private String pFirstName;
 @JsonProperty("inserted")
private boolean inserted;
 @JsonProperty("errorMessage")
private String errorMessage;
 @JsonProperty("secondMiddleName")
 private String secondMiddleName;
/**
 * @return the hits
 */
public BigDecimal getHits() {
	return hits;
}
/**
 * @param hits the hits to set
 */
public void setHits(BigDecimal hits) {
	this.hits = hits;
}
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
public Date getPBirthDate() {
	return PBirthDate;
}
/**
 * @param pBirthDate the pBirthDate to set
 */
public void setPBirthDate(Date pBirthDate) {
	PBirthDate = pBirthDate;
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
 * @return the secondMiddleName
 */
public String getsecondMiddleName() {
	return secondMiddleName;
}
/**
 * @param MiddleName the pMiddleName to set
 */
public void setsecondMiddleName(String secondMiddleName) {
	this.secondMiddleName = secondMiddleName;
}

}