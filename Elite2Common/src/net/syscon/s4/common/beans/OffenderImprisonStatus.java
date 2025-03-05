package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderImprisonStatus extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDate")
	private Date createDate;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("effectiveTime")
	private Date effectiveTime;

	@JsonProperty("errorFlag")
	private String errorFlag;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("imprisonmentStatus")
	private String imprisonmentStatus;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("state")
	private String state;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("imprisonStatusSeq")
	private long imprisonStatusSeq;

	/**
	*
	*@return 
	*/
	public String getAgyLocId(){
		return agyLocId;
	}

	/**
	*
	*@param agyLocId 
	*/
	public void setAgyLocId(String agyLocId){
		this.agyLocId = agyLocId;
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
	public Date getEffectiveDate(){
		return effectiveDate;
	}

	/**
	*
	*@param effectiveDate 
	*/
	public void setEffectiveDate(Date effectiveDate){
		this.effectiveDate = effectiveDate;
	}

	/**
	*
	*@return 
	*/
	public Date getEffectiveTime(){
		return effectiveTime;
	}

	/**
	*
	*@param effectiveTime 
	*/
	public void setEffectiveTime(Date effectiveTime){
		this.effectiveTime = effectiveTime;
	}

	/**
	*
	*@return 
	*/
	public String getErrorFlag(){
		return errorFlag;
	}

	/**
	*
	*@param errorFlag 
	*/
	public void setErrorFlag(String errorFlag){
		this.errorFlag = errorFlag;
	}

	/**
	*
	*@return 
	*/
	public Date getExpiryDate(){
		return expiryDate;
	}

	/**
	*
	*@param expiryDate 
	*/
	public void setExpiryDate(Date expiryDate){
		this.expiryDate = expiryDate;
	}

	/**
	*
	*@return 
	*/
	public String getImprisonmentStatus(){
		return imprisonmentStatus;
	}

	/**
	*
	*@param imprisonmentStatus 
	*/
	public void setImprisonmentStatus(String imprisonmentStatus){
		this.imprisonmentStatus = imprisonmentStatus;
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
	public String getState(){
		return state;
	}

	/**
	*
	*@param state 
	*/
	public void setState(String state){
		this.state = state;
	}

	/**
	*
	*@return 
	*/
	public OffenderBookings getOffenderBookings(){
		return offenderBookings;
	}

	/**
	*
	*@param offenderBookings 
	*/
	public void setOffenderBookings(OffenderBookings offenderBookings){
		this.offenderBookings = offenderBookings;
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
	public long getImprisonStatusSeq(){
		return imprisonStatusSeq;
	}

	/**
	*
	*@param imprisonStatusSeq 
	*/
	public void setImprisonStatusSeq(long imprisonStatusSeq){
		this.imprisonStatusSeq = imprisonStatusSeq;
	}

}