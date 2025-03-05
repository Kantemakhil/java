package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


@JsonIgnoreProperties(ignoreUnknown = true)
public class VOicHearingResults extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("findingDescription")
	private String findingDescription;

	@JsonProperty("oicChargeId")
	private String oicChargeId;

	@JsonProperty("oicHearingId")
	private BigDecimal oicHearingId;

	@JsonProperty("oicOffenceCategory")
	private String oicOffenceCategory;

	@JsonProperty("oicOffenceCode")
	private String oicOffenceCode;

	@JsonProperty("oicOffenceDescription")
	private String oicOffenceDescription;

	@JsonProperty("oicOffenceType")
	private String oicOffenceType;

	@JsonProperty("oicOfnTypeDesc")
	private String oicOfnTypeDesc;

	@JsonProperty("pleaDescription")
	private String pleaDescription;

	@JsonProperty("resultOffenceType")
	private String resultOffenceType;

	@JsonProperty("resultOicOffenceCategory")
	private String resultOicOffenceCategory;

	@JsonProperty("resultOicOffenceCode")
	private String resultOicOffenceCode;

	@JsonProperty("resultOicOffenceDescription")
	private String resultOicOffenceDescription;

	@JsonProperty("resultOicOfnTypeDesc")
	private String resultOicOfnTypeDesc;

	@JsonProperty("resultSeq")
	private BigDecimal resultSeq;
	
	/**
	 * Creates new VOicHearingResults class Object
	 */
	public VOicHearingResults() {
		// VOicHearingResults
	}

	/**
	*
	*@return 
	*/
	public String getFindingDescription(){
		return findingDescription;
	}

	/**
	*
	*@param findingDescription 
	*/
	public void setFindingDescription(final String findingDescription){
		this.findingDescription = findingDescription;
	}

	/**
	*
	*@return 
	*/
	public String getOicChargeId(){
		return oicChargeId;
	}

	/**
	*
	*@param oicChargeId 
	*/
	public void setOicChargeId(final String oicChargeId){
		this.oicChargeId = oicChargeId;
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
	public String getOicOffenceCategory(){
		return oicOffenceCategory;
	}

	/**
	*
	*@param oicOffenceCategory 
	*/
	public void setOicOffenceCategory(final String oicOffenceCategory){
		this.oicOffenceCategory = oicOffenceCategory;
	}

	/**
	*
	*@return 
	*/
	public String getOicOffenceCode(){
		return oicOffenceCode;
	}

	/**
	*
	*@param oicOffenceCode 
	*/
	public void setOicOffenceCode(final String oicOffenceCode){
		this.oicOffenceCode = oicOffenceCode;
	}

	/**
	*
	*@return 
	*/
	public String getOicOffenceDescription(){
		return oicOffenceDescription;
	}

	/**
	*
	*@param oicOffenceDescription 
	*/
	public void setOicOffenceDescription(final String oicOffenceDescription){
		this.oicOffenceDescription = oicOffenceDescription;
	}

	/**
	*
	*@return 
	*/
	public String getOicOffenceType(){
		return oicOffenceType;
	}

	/**
	*
	*@param oicOffenceType 
	*/
	public void setOicOffenceType(final String oicOffenceType){
		this.oicOffenceType = oicOffenceType;
	}

	/**
	*
	*@return 
	*/
	public String getOicOfnTypeDesc(){
		return oicOfnTypeDesc;
	}

	/**
	*
	*@param oicOfnTypeDesc 
	*/
	public void setOicOfnTypeDesc(final String oicOfnTypeDesc){
		this.oicOfnTypeDesc = oicOfnTypeDesc;
	}

	/**
	*
	*@return 
	*/
	public String getPleaDescription(){
		return pleaDescription;
	}

	/**
	*
	*@param pleaDescription 
	*/
	public void setPleaDescription(final String pleaDescription){
		this.pleaDescription = pleaDescription;
	}

	/**
	*
	*@return 
	*/
	public String getResultOffenceType(){
		return resultOffenceType;
	}

	/**
	*
	*@param resultOffenceType 
	*/
	public void setResultOffenceType(final String resultOffenceType){
		this.resultOffenceType = resultOffenceType;
	}

	/**
	*
	*@return 
	*/
	public String getResultOicOffenceCategory(){
		return resultOicOffenceCategory;
	}

	/**
	*
	*@param resultOicOffenceCategory 
	*/
	public void setResultOicOffenceCategory(final String resultOicOffenceCategory){
		this.resultOicOffenceCategory = resultOicOffenceCategory;
	}

	/**
	*
	*@return 
	*/
	public String getResultOicOffenceCode(){
		return resultOicOffenceCode;
	}

	/**
	*
	*@param resultOicOffenceCode 
	*/
	public void setResultOicOffenceCode(final String resultOicOffenceCode){
		this.resultOicOffenceCode = resultOicOffenceCode;
	}

	/**
	*
	*@return 
	*/
	public String getResultOicOffenceDescription(){
		return resultOicOffenceDescription;
	}

	/**
	*
	*@param resultOicOffenceDescription 
	*/
	public void setResultOicOffenceDescription(final String resultOicOffenceDescription){
		this.resultOicOffenceDescription = resultOicOffenceDescription;
	}

	/**
	*
	*@return 
	*/
	public String getResultOicOfnTypeDesc(){
		return resultOicOfnTypeDesc;
	}

	/**
	*
	*@param resultOicOfnTypeDesc 
	*/
	public void setResultOicOfnTypeDesc(final String resultOicOfnTypeDesc){
		this.resultOicOfnTypeDesc = resultOicOfnTypeDesc;
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

}