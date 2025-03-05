package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


@JsonIgnoreProperties(ignoreUnknown = true)
public class VOicHearings extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("hearingDate")
	private Date hearingDate;

	@JsonProperty("hearingStaffName")
	private String hearingStaffName;

	@JsonProperty("hearingTime")
	private Date hearingTime;

	@JsonProperty("intLocDescription")
	private String intLocDescription;

	@JsonProperty("oicHearingId")
	private BigDecimal oicHearingId;

	@JsonProperty("oicHearingType")
	private String oicHearingType;

	@JsonProperty("oicHearingTypeDesc")
	private String oicHearingTypeDesc;

	@JsonProperty("oicIncidentId")
	private BigDecimal oicIncidentId;

	@JsonProperty("representativeText")
	private String representativeText;
	
	/**
	 * Creates new VOicHearings class Object
	 */
	public VOicHearings() {

		// VOicHearings
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
	public void setCommentText(final String commentText){
		this.commentText = commentText;
	}

	/**
	*
	*@return 
	*/
	public Date getHearingDate(){
		return hearingDate;
	}

	/**
	*
	*@param hearingDate 
	*/
	public void setHearingDate(final Date hearingDate){
		this.hearingDate = hearingDate;
	}

	/**
	*
	*@return 
	*/
	public String getHearingStaffName(){
		return hearingStaffName;
	}

	/**
	*
	*@param hearingStaffName 
	*/
	public void setHearingStaffName(final String hearingStaffName){
		this.hearingStaffName = hearingStaffName;
	}

	/**
	*
	*@return 
	*/
	public Date getHearingTime(){
		return hearingTime;
	}

	/**
	*
	*@param hearingTime 
	*/
	public void setHearingTime(final Date hearingTime){
		this.hearingTime = hearingTime;
	}

	/**
	*
	*@return 
	*/
	public String getIntLocDescription(){
		return intLocDescription;
	}

	/**
	*
	*@param intLocDescription 
	*/
	public void setIntLocDescription(final String intLocDescription){
		this.intLocDescription = intLocDescription;
	}

	/**
	*
	*@return 
	*/
	public BigDecimal getOicHearingId(){
		return oicHearingId;
	}

	/**
	*
	*@param oicHearingId 
	*/
	public void setOicHearingId(final BigDecimal oicHearingId){
		this.oicHearingId = oicHearingId;
	}

	/**
	*
	*@return 
	*/
	public String getOicHearingType(){
		return oicHearingType;
	}

	/**
	*
	*@param oicHearingType 
	*/
	public void setOicHearingType(final String oicHearingType){
		this.oicHearingType = oicHearingType;
	}

	/**
	*
	*@return 
	*/
	public String getOicHearingTypeDesc(){
		return oicHearingTypeDesc;
	}

	/**
	*
	*@param oicHearingTypeDesc 
	*/
	public void setOicHearingTypeDesc(final String oicHearingTypeDesc){
		this.oicHearingTypeDesc = oicHearingTypeDesc;
	}

	/**
	*
	*@return 
	*/
	public BigDecimal getOicIncidentId(){
		return oicIncidentId;
	}

	/**
	*
	*@param oicIncidentId 
	*/
	public void setOicIncidentId(final BigDecimal oicIncidentId){
		this.oicIncidentId = oicIncidentId;
	}

	/**
	*
	*@return 
	*/
	public String getRepresentativeText(){
		return representativeText;
	}

	/**
	*
	*@param representativeText 
	*/
	public void setRepresentativeText(final String representativeText){
		this.representativeText = representativeText;
	}

}