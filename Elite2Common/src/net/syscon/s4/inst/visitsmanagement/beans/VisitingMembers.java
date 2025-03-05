package net.syscon.s4.inst.visitsmanagement.beans;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Class VisitingMembers
 */
public class VisitingMembers {

@JsonProperty("groupId") 
private Integer groupId;
@JsonProperty("memberSeq") 
private Integer memberSeq;
@JsonProperty("lastName") 
private String lastName;
@JsonProperty("firstName") 
private String firstName;
@JsonProperty("dob") 
private Date dob;
@JsonProperty("idType") 
private String idType;
@JsonProperty("identifier") 
private String identifier;
@JsonProperty("commentText") 
private String commentText;
@JsonProperty("verifiedFlag") 
private String verifiedFlag;
@JsonProperty("modifyUserId") 
private String modifyUserId;
@JsonProperty("modifyDatetime") 
private Date modifyDatetime;
@JsonProperty("createDatetime") 
private Date createDatetime;
@JsonProperty("createUserId") 
private String createUserId;
@JsonProperty("sealFlag") 
private String sealFlag;
private boolean inserted;


public VisitingMembers() {
// 
}
 /**
 * @param	groupId
*groupId	 to set
*/
public void setGroupId(final Integer groupId){
	 this.groupId = groupId;
}

/**
*return thegroupId
*/
public Integer getGroupId(){
	 return this.groupId;
}

 /**
 * @param	memberSeq
*memberSeq	 to set
*/
public void setMemberSeq(final Integer memberSeq){
	 this.memberSeq = memberSeq;
}

/**
*return thememberSeq
*/
public Integer getMemberSeq(){
	 return this.memberSeq;
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
 * @param	dob
*dob	 to set
*/
public void setDob(final Date dob){
	 this.dob = dob;
}

/**
*return thedob
*/
public Date getDob(){
	 return this.dob;
}

 /**
 * @param	idType
*idType	 to set
*/
public void setIdType(final String idType){
	 this.idType = idType;
}

/**
*return theidType
*/
public String getIdType(){
	 return this.idType;
}

 /**
 * @param	identifier
*identifier	 to set
*/
public void setIdentifier(final String identifier){
	 this.identifier = identifier;
}

/**
*return theidentifier
*/
public String getIdentifier(){
	 return this.identifier;
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
 * @param	verifiedFlag
*verifiedFlag	 to set
*/
public void setVerifiedFlag(final String verifiedFlag){
	 this.verifiedFlag = verifiedFlag;
}

/**
*return theverifiedFlag
*/
public String getVerifiedFlag(){
	 return this.verifiedFlag;
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
 * @param	createDatetime
*createDatetime	 to set
*/
public void setCreateDatetime(final Date createDatetime){
	 this.createDatetime = createDatetime;
}

/**
*return thecreateDatetime
*/
public Date getCreateDatetime(){
	 return this.createDatetime;
}

 /**
 * @param	createUserId
*createUserId	 to set
*/
public void setCreateUserId(final String createUserId){
	 this.createUserId = createUserId;
}

/**
*return thecreateUserId
*/
public String getCreateUserId(){
	 return this.createUserId;
}

 /**
 * @param	sealFlag
*sealFlag	 to set
*/
public void setSealFlag(final String sealFlag){
	 this.sealFlag = sealFlag;
}

/**
*return thesealFlag
*/
public String getSealFlag(){
	 return this.sealFlag;
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