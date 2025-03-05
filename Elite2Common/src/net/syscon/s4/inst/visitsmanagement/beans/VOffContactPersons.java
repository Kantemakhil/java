package net.syscon.s4.inst.visitsmanagement.beans;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Class VOffContactPersons
 */
public class VOffContactPersons implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@JsonProperty("offenderIdDisplay") 
private String offenderIdDisplay;
@JsonProperty("lastName") 
private String lastName;
@JsonProperty("firstName") 
private String firstName;
@JsonProperty("middleName") 
private String middleName;
@JsonProperty("contactType") 
private String contactType;
@JsonProperty("contactTypeDescription") 
private String contactTypeDescription;
@JsonProperty("relationshipType") 
private String relationshipType;
@JsonProperty("relationshipTypeDescription") 
private String relationshipTypeDescription;
@JsonProperty("personId") 
private Integer personId;
@JsonProperty("offenderBookId") 
private Integer offenderBookId;
@JsonProperty("offenderId") 
private Integer offenderId;
@JsonProperty("approvedVisitorFlag") 
private String approvedVisitorFlag;
@JsonProperty("contactRootOffenderId") 
private Integer contactRootOffenderId;
@JsonProperty("commentText") 
private String commentText;
@JsonProperty("offenderContactPersonId") 
private Integer offenderContactPersonId;
@JsonProperty("personLastName") 
private String personLastName;
@JsonProperty("personFirstName") 
private String personFirstName;
@JsonProperty("caseloadType") 
private String caseloadType;
@JsonProperty("modifyDatetime") 
private Date modifyDatetime;
@JsonProperty("modifyUserId") 
private String modifyUserId;
@JsonProperty("checkSum") 
private Integer checkSum;
@JsonProperty("activeFlag") 
private String activeFlag;
private boolean inserted;
@JsonProperty("visitDate") 
private Date visitDate;
@JsonProperty("restriction") 
private String restriction ;
@JsonProperty("globalRestriction") 
private String globalRestriction ;
@JsonProperty("age") 
private BigDecimal age;



public VOffContactPersons(){
	// VOffContactPersons
}
 /**
 * @return the restriction
 */
public String getRestriction() {
	return restriction;
}
/**
 * @param restriction the restriction to set
 */
public void setRestriction(final String restriction) {
	this.restriction = restriction;
}
/**
 * @return the globalRestriction
 */
public String getGlobalRestriction() {
	return globalRestriction;
}
/**
 * @param globalRestriction the globalRestriction to set
 */
public void setGlobalRestriction(final String globalRestriction) {
	this.globalRestriction = globalRestriction;
}
/**
 * @return the age
 */
public BigDecimal getAge() {
	return age;
}
/**
 * @param age the age to set
 */
public void setAge(final BigDecimal age) {
	this.age = age;
}
/**
 * @return the visitDate
 */
public Date getVisitDate() {
	return visitDate;
}
/**
 * @param visitDate the visitDate to set
 */
public void setVisitDate(final Date visitDate) {
	this.visitDate = visitDate;
}
/**
 * @param	offenderIdDisplay
*offenderIdDisplay	 to set
*/
public void setOffenderIdDisplay(final String offenderIdDisplay){
	 this.offenderIdDisplay = offenderIdDisplay;
}

/**
*return theoffenderIdDisplay
*/
public String getOffenderIdDisplay(){
	 return this.offenderIdDisplay;
}

 /**
 * @param	lastName
*lastName	 to set
*/
public void setLastName(final String lastName){
	 this.lastName = lastName;
}

/**
*return thelastName
*/
public String getLastName(){
	 return this.lastName;
}

 /**
 * @param	firstName
*firstName	 to set
*/
public void setFirstName(final String firstName){
	 this.firstName = firstName;
}

/**
*return thefirstName
*/
public String getFirstName(){
	 return this.firstName;
}

 /**
 * @param	middleName
*middleName	 to set
*/
public void setMiddleName(final String middleName){
	 this.middleName = middleName;
}

/**
*return themiddleName
*/
public String getMiddleName(){
	 return this.middleName;
}

 /**
 * @param	contactType
*contactType	 to set
*/
public void setContactType(final String contactType){
	 this.contactType = contactType;
}

/**
*return thecontactType
*/
public String getContactType(){
	 return this.contactType;
}

 /**
 * @param	contactTypeDescription
*contactTypeDescription	 to set
*/
public void setContactTypeDescription(final String contactTypeDescription){
	 this.contactTypeDescription = contactTypeDescription;
}

/**
*return thecontactTypeDescription
*/
public String getContactTypeDescription(){
	 return this.contactTypeDescription;
}

 /**
 * @param	relationshipType
*relationshipType	 to set
*/
public void setRelationshipType(final String relationshipType){
	 this.relationshipType = relationshipType;
}

/**
*return therelationshipType
*/
public String getRelationshipType(){
	 return this.relationshipType;
}

 /**
 * @param	relationshipTypeDescription
*relationshipTypeDescription	 to set
*/
public void setRelationshipTypeDescription(final String relationshipTypeDescription){
	 this.relationshipTypeDescription = relationshipTypeDescription;
}

/**
*return therelationshipTypeDescription
*/
public String getRelationshipTypeDescription(){
	 return this.relationshipTypeDescription;
}

 /**
 * @param	personId
*personId	 to set
*/
public void setPersonId(final Integer personId){
	 this.personId = personId;
}

/**
*return thepersonId
*/
public Integer getPersonId(){
	 return this.personId;
}

 /**
 * @param	offenderBookId
*offenderBookId	 to set
*/
public void setOffenderBookId(final Integer offenderBookId){
	 this.offenderBookId = offenderBookId;
}

/**
*return theoffenderBookId
*/
public Integer getOffenderBookId(){
	 return this.offenderBookId;
}

 /**
 * @param	offenderId
*offenderId	 to set
*/
public void setOffenderId(final Integer offenderId){
	 this.offenderId = offenderId;
}

/**
*return theoffenderId
*/
public Integer getOffenderId(){
	 return this.offenderId;
}

 /**
 * @param	approvedVisitorFlag
*approvedVisitorFlag	 to set
*/
public void setApprovedVisitorFlag(final String approvedVisitorFlag){
	 this.approvedVisitorFlag = approvedVisitorFlag;
}

/**
*return theapprovedVisitorFlag
*/
public String getApprovedVisitorFlag(){
	 return this.approvedVisitorFlag;
}

 /**
 * @param	contactRootOffenderId
*contactRootOffenderId	 to set
*/
public void setContactRootOffenderId(final Integer contactRootOffenderId){
	 this.contactRootOffenderId = contactRootOffenderId;
}

/**
*return thecontactRootOffenderId
*/
public Integer getContactRootOffenderId(){
	 return this.contactRootOffenderId;
}

 /**
 * @param	commentText
*commentText	 to set
*/
public void setCommentText(final String commentText){
	 this.commentText = commentText;
}

/**
*return thecommentText
*/
public String getCommentText(){
	 return this.commentText;
}

 /**
 * @param	offenderContactPersonId
*offenderContactPersonId	 to set
*/
public void setOffenderContactPersonId(final Integer offenderContactPersonId){
	 this.offenderContactPersonId = offenderContactPersonId;
}

/**
*return theoffenderContactPersonId
*/
public Integer getOffenderContactPersonId(){
	 return this.offenderContactPersonId;
}

 /**
 * @param	personLastName
*personLastName	 to set
*/
public void setPersonLastName(final String personLastName){
	 this.personLastName = personLastName;
}

/**
*return thepersonLastName
*/
public String getPersonLastName(){
	 return this.personLastName;
}

 /**
 * @param	personFirstName
*personFirstName	 to set
*/
public void setPersonFirstName(final String personFirstName){
	 this.personFirstName = personFirstName;
}

/**
*return thepersonFirstName
*/
public String getPersonFirstName(){
	 return this.personFirstName;
}

 /**
 * @param	caseloadType
*caseloadType	 to set
*/
public void setCaseloadType(final String caseloadType){
	 this.caseloadType = caseloadType;
}

/**
*return thecaseloadType
*/
public String getCaseloadType(){
	 return this.caseloadType;
}

 /**
 * @param	modifyDatetime
*modifyDatetime	 to set
*/
public void setModifyDatetime(final Date modifyDatetime){
	 this.modifyDatetime = modifyDatetime;
}

/**
*return themodifyDatetime
*/
public Date getModifyDatetime(){
	 return this.modifyDatetime;
}

 /**
 * @param	modifyUserId
*modifyUserId	 to set
*/
public void setModifyUserId(final String modifyUserId){
	 this.modifyUserId = modifyUserId;
}

/**
*return themodifyUserId
*/
public String getModifyUserId(){
	 return this.modifyUserId;
}

 /**
 * @param	checkSum
*checkSum	 to set
*/
public void setCheckSum(final Integer checkSum){
	 this.checkSum = checkSum;
}

/**
*return thecheckSum
*/
public Integer getCheckSum(){
	 return this.checkSum;
}

 /**
 * @param	activeFlag
*activeFlag	 to set
*/
public void setActiveFlag(final String activeFlag){
	 this.activeFlag = activeFlag;
}

/**
*return theactiveFlag
*/
public String getActiveFlag(){
	 return this.activeFlag;
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
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

}