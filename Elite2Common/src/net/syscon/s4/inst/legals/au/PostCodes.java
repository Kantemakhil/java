package net.syscon.s4.inst.legals.au;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class PostCodes extends BaseModel implements Serializable{
	
	
	public static final long serialVersionUID =  1L;
	
	
	@JsonProperty("postCode")
	private String postCode;
	
	@JsonProperty("locality")
	private String locality;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("deliveryOffice")
	private String deliveryOffice;
	
	@JsonProperty("presortIndicator")
	private String presortIndicator;
	
	@JsonProperty("parcelZone")
	private String parcelZone;
	
	@JsonProperty("bspNo")
	private String bspNo;
	
	@JsonProperty("bspName")
	private String bspName;
	
	@JsonProperty("category")
	private String category;
	
	@JsonProperty("cityCode")
	private String cityCode;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("omsPostCode")
	private String omsPostCode;
	
	@JsonProperty("code")
	private String code;
	
	

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @return the deliveryOffice
	 */
	public String getDeliveryOffice() {
		return deliveryOffice;
	}

	/**
	 * @return the presortIndicator
	 */
	public String getPresortIndicator() {
		return presortIndicator;
	}

	/**
	 * @return the parcelZone
	 */
	public String getParcelZone() {
		return parcelZone;
	}

	/**
	 * @return the bspNo
	 */
	public String getBspNo() {
		return bspNo;
	}

	/**
	 * @return the bspName
	 */
	public String getBspName() {
		return bspName;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the omsPostCode
	 */
	public String getOmsPostCode() {
		return omsPostCode;
	}

	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(final String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @param locality the locality to set
	 */
	public void setLocality(final String locality) {
		this.locality = locality;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @param deliveryOffice the deliveryOffice to set
	 */
	public void setDeliveryOffice(final String deliveryOffice) {
		this.deliveryOffice = deliveryOffice;
	}

	/**
	 * @param presortIndicator the presortIndicator to set
	 */
	public void setPresortIndicator(final String presortIndicator) {
		this.presortIndicator = presortIndicator;
	}

	/**
	 * @param parcelZone the parcelZone to set
	 */
	public void setParcelZone(final String parcelZone) {
		this.parcelZone = parcelZone;
	}

	/**
	 * @param bspNo the bspNo to set
	 */
	public void setBspNo(final String bspNo) {
		this.bspNo = bspNo;
	}

	/**
	 * @param bspName the bspName to set
	 */
	public void setBspName(final String bspName) {
		this.bspName = bspName;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(final String category) {
		this.category = category;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(final String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param omsPostCode the omsPostCode to set
	 */
	public void setOmsPostCode(final String omsPostCode) {
		this.omsPostCode = omsPostCode;
	}
	
	
	

}
