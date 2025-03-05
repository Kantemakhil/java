package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderAuthorisedVisitor extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("approvedVisitorFlag")
	private String approvedVisitorFlag;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("contactType")
	private String contactType;

	@JsonProperty("createDate")
	private Date createDate;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("personId")
	private java.math.BigDecimal personId;

	@JsonProperty("relationshipType")
	private String relationshipType;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("visitorType")
	private String visitorType;

	@JsonProperty("offenderBooking1")
	private OffenderBookings offenderBooking1;

	@JsonProperty("offenderBooking2")
	private OffenderBookings offenderBooking2;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("visitorSeq")
	private long visitorSeq;

	/**
	*
	*@return 
	*/
	public String getApprovedVisitorFlag(){
		return approvedVisitorFlag;
	}

	/**
	*
	*@param approvedVisitorFlag 
	*/
	public void setApprovedVisitorFlag(String approvedVisitorFlag){
		this.approvedVisitorFlag = approvedVisitorFlag;
	}

	/**
	*
	*@return 
	*/
	public String getCommentText(){
		return commentText;
	}

	/**
	*
	*@param commentText 
	*/
	public void setCommentText(String commentText){
		this.commentText = commentText;
	}

	/**
	*
	*@return 
	*/
	public String getContactType(){
		return contactType;
	}

	/**
	*
	*@param contactType 
	*/
	public void setContactType(String contactType){
		this.contactType = contactType;
	}

	/**
	*
	*@return 
	*/
	public Date getCreateDate(){
		return createDate;
	}

	/**
	*
	*@param createDate 
	*/
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	/**
	*
	*@return 
	*/
	public Date getCreateDatetime(){
		return createDatetime;
	}

	/**
	*
	*@param createDatetime 
	*/
	public void setCreateDatetime(Date createDatetime){
		this.createDatetime = createDatetime;
	}

	/**
	*
	*@return 
	*/
	public String getCreateUserId(){
		return createUserId;
	}

	/**
	*
	*@param createUserId 
	*/
	public void setCreateUserId(String createUserId){
		this.createUserId = createUserId;
	}

	/**
	*
	*@return 
	*/
	public Date getModifyDatetime(){
		return modifyDatetime;
	}

	/**
	*
	*@param modifyDatetime 
	*/
	public void setModifyDatetime(Date modifyDatetime){
		this.modifyDatetime = modifyDatetime;
	}

	/**
	*
	*@return 
	*/
	public String getModifyUserId(){
		return modifyUserId;
	}

	/**
	*
	*@param modifyUserId 
	*/
	public void setModifyUserId(String modifyUserId){
		this.modifyUserId = modifyUserId;
	}

	/**
	*
	*@return 
	*/
	public java.math.BigDecimal getPersonId(){
		return personId;
	}

	/**
	*
	*@param personId 
	*/
	public void setPersonId(java.math.BigDecimal personId){
		this.personId = personId;
	}

	/**
	*
	*@return 
	*/
	public String getRelationshipType(){
		return relationshipType;
	}

	/**
	*
	*@param relationshipType 
	*/
	public void setRelationshipType(String relationshipType){
		this.relationshipType = relationshipType;
	}

	/**
	*
	*@return 
	*/
	public String getSealFlag(){
		return sealFlag;
	}

	/**
	*
	*@param sealFlag 
	*/
	public void setSealFlag(String sealFlag){
		this.sealFlag = sealFlag;
	}

	/**
	*
	*@return 
	*/
	public String getVisitorType(){
		return visitorType;
	}

	/**
	*
	*@param visitorType 
	*/
	public void setVisitorType(String visitorType){
		this.visitorType = visitorType;
	}

	/**
	*
	*@return 
	*/
	public OffenderBookings getOffenderBooking1(){
		return offenderBooking1;
	}

	/**
	*
	*@param offenderBooking1 
	*/
	public void setOffenderBooking1(OffenderBookings offenderBooking1){
		this.offenderBooking1 = offenderBooking1;
	}

	/**
	*
	*@return 
	*/
	public OffenderBookings getOffenderBooking2(){
		return offenderBooking2;
	}

	/**
	*
	*@param offenderBooking2 
	*/
	public void setOffenderBooking2(OffenderBookings offenderBooking2){
		this.offenderBooking2 = offenderBooking2;
	}

	/**
	*
	*@return 
	*/
	public long getOffenderBookId(){
		return offenderBookId;
	}

	/**
	*
	*@param offenderBookId 
	*/
	public void setOffenderBookId(long offenderBookId){
		this.offenderBookId = offenderBookId;
	}

	/**
	*
	*@return 
	*/
	public long getVisitorSeq(){
		return visitorSeq;
	}

	/**
	*
	*@param visitorSeq 
	*/
	public void setVisitorSeq(long visitorSeq){
		this.visitorSeq = visitorSeq;
	}

}