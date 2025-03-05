package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacementDurations extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("placementType")
	private String placementType;

	@JsonProperty("durationType")
	private String durationType;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("listSeq")
	private Integer listSeq;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("maximumDuration")
	private Integer maximumDuration;

	@JsonProperty("minimumDuration")
	private Integer minimumDuration;

	@JsonProperty("code")
	private String code;

	@JsonProperty("description")
	private String description;

	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;
	
	@JsonProperty("rowId")
	private String rowId;
	
	@JsonProperty("rowId1")
	private Integer rowId1;
	
	
	
	
	
	

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the placementType
	 */
	public String getPlacementType() {
		return placementType;
	}

	/**
	 * @param placementType
	 *            the placementType to set
	 */
	public void setPlacementType(final String placementType) {
		this.placementType = placementType;
	}

	/**
	 * @return the durationType
	 */
	public String getDurationType() {
		return durationType;
	}

	/**
	 * @param durationType
	 *            the durationType to set
	 */
	public void setDurationType(final String durationType) {
		this.durationType = durationType;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the listSeq
	 */
	public Integer getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final Integer listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the maximumDuration
	 */
	public Integer getMaximumDuration() {
		return maximumDuration;
	}

	/**
	 * @param maximumDuration
	 *            the maximumDuration to set
	 */
	public void setMaximumDuration(final Integer maximumDuration) {
		this.maximumDuration = maximumDuration;
	}

	/**
	 * @return the minimumDuration
	 */
	public Integer getMinimumDuration() {
		return minimumDuration;
	}

	/**
	 * @param minimumDuration
	 *            the minimumDuration to set
	 */
	public void setMinimumDuration(final Integer minimumDuration) {
		this.minimumDuration = minimumDuration;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the canDisplay
	 */
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	/**
	 * @param canDisplay
	 *            the canDisplay to set
	 */
	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(final String rowId) {
		this.rowId = rowId;
	}

	public Integer getRowId1() {
		return rowId1;
	}

	public void setRowId1(final Integer rowId1) {
		this.rowId1 = rowId1;
	}
	
	

}
