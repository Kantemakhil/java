package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


@JsonIgnoreProperties(ignoreUnknown = true)
public class VOffenderOicSanctions extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("compensationAmount")
	private BigDecimal compensationAmount;

	@JsonProperty("consecutiveSanctionSeq")
	private BigDecimal consecutiveSanctionSeq;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("oicHearingId")
	private BigDecimal oicHearingId;

	@JsonProperty("oicIncidentId")
	private BigDecimal oicIncidentId;

	@JsonProperty("oicSanctionCode")
	private String oicSanctionCode;

	@JsonProperty("oicSanctionDesc")
	private String oicSanctionDesc;

	@JsonProperty("resultSeq")
	private BigDecimal resultSeq;

	@JsonProperty("sanctionDays")
	private BigDecimal sanctionDays;

	@JsonProperty("sanctionMonths")
	private BigDecimal sanctionMonths;

	@JsonProperty("sanctionSeq")
	private BigDecimal sanctionSeq;

	@JsonProperty("statusDate")
	private Date statusDate;

	@JsonProperty("statusDescription")
	private String statusDescription;
	
	/**
	 * Creates new VOffenderOicSanctions class Object
	 */
	public VOffenderOicSanctions() {
		
		// VOffenderOicSanctions
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
	public BigDecimal getCompensationAmount(){
		return compensationAmount;
	}

	/**
	*
	*@param compensationAmount 
	*/
	public void setCompensationAmount(final BigDecimal compensationAmount){
		this.compensationAmount = compensationAmount;
	}

	/**
	*
	*@return 
	*/
	public BigDecimal getConsecutiveSanctionSeq(){
		return consecutiveSanctionSeq;
	}

	/**
	*
	*@param consecutiveSanctionSeq 
	*/
	public void setConsecutiveSanctionSeq(final BigDecimal consecutiveSanctionSeq){
		this.consecutiveSanctionSeq = consecutiveSanctionSeq;
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
	public void setEffectiveDate(final Date effectiveDate){
		this.effectiveDate = effectiveDate;
	}

	/**
	*
	*@return 
	*/
	public BigDecimal getOffenderBookId(){
		return offenderBookId;
	}

	/**
	*
	*@param offenderBookId 
	*/
	public void setOffenderBookId(final BigDecimal offenderBookId){
		this.offenderBookId = offenderBookId;
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
	public String getOicSanctionCode(){
		return oicSanctionCode;
	}

	/**
	*
	*@param oicSanctionCode 
	*/
	public void setOicSanctionCode(final String oicSanctionCode){
		this.oicSanctionCode = oicSanctionCode;
	}

	/**
	*
	*@return 
	*/
	public String getOicSanctionDesc(){
		return oicSanctionDesc;
	}

	/**
	*
	*@param oicSanctionDesc 
	*/
	public void setOicSanctionDesc(final String oicSanctionDesc){
		this.oicSanctionDesc = oicSanctionDesc;
	}

	/**
	*
	*@return 
	*/
	public BigDecimal getResultSeq(){
		return resultSeq;
	}

	/**
	*
	*@param resultSeq 
	*/
	public void setResultSeq(final BigDecimal resultSeq){
		this.resultSeq = resultSeq;
	}

	/**
	*
	*@return 
	*/
	public BigDecimal getSanctionDays(){
		return sanctionDays;
	}

	/**
	*
	*@param sanctionDays 
	*/
	public void setSanctionDays(final BigDecimal sanctionDays){
		this.sanctionDays = sanctionDays;
	}

	/**
	*
	*@return 
	*/
	public BigDecimal getSanctionMonths(){
		return sanctionMonths;
	}

	/**
	*
	*@param sanctionMonths 
	*/
	public void setSanctionMonths(final BigDecimal sanctionMonths){
		this.sanctionMonths = sanctionMonths;
	}

	/**
	*
	*@return 
	*/
	public BigDecimal getSanctionSeq(){
		return sanctionSeq;
	}

	/**
	*
	*@param sanctionSeq 
	*/
	public void setSanctionSeq(final BigDecimal sanctionSeq){
		this.sanctionSeq = sanctionSeq;
	}

	/**
	*
	*@return 
	*/
	public Date getStatusDate(){
		return statusDate;
	}

	/**
	*
	*@param statusDate 
	*/
	public void setStatusDate(final Date statusDate){
		this.statusDate = statusDate;
	}

	/**
	*
	*@return 
	*/
	public String getStatusDescription(){
		return statusDescription;
	}

	/**
	*
	*@param statusDescription 
	*/
	public void setStatusDescription(final String statusDescription){
		this.statusDescription = statusDescription;
	}

}