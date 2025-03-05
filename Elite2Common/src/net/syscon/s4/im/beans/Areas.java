package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Areas extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	@JsonProperty("areaClass")
	private String areaClass;
	@JsonProperty("areaCode")
	private String areaCode;
	@JsonProperty("description")
	private String description;
	@JsonProperty("parentAreaCode")
	private String parentAreaCode;
	@JsonProperty("listSeq")
	private Integer listSeq;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("areaType")
	private String areaType;
	@JsonProperty("createDateTime")
	private Date createDateTime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDateTime")
	private String modifyDateTime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("code")
	private String code;
	@JsonProperty("serverCode")
	private int serverCode;
	@JsonProperty("returnValue")
	private Integer returnValue;
	@JsonProperty("rowId")
	private Long rowId;
	
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;
	
	/**
	 * @return the rowId
	 */
	public Long getRowId() {
		return rowId;
	}
	/**
	 * @param rowId
	 *            the rowId to set
	 */
	public void setRowId(final Long rowId) {
		this.rowId = rowId;
	}
	/**
	 * @return the serverCode
	 */
	public int getServerCode() {
		return serverCode;
	}
	/**
	 * @param serverCode
	 *            the serverCode to set
	 */
	public void setServerCode(final int serverCode) {
		this.serverCode = serverCode;
	}
	/**
	 * @return the returnValue
	 */
	public Integer getReturnValue() {
		return returnValue;
	}
	/**
	 * @param returnValue
	 *            the returnValue to set
	 */
	public void setReturnValue(final Integer returnValue) {
		this.returnValue = returnValue;
	}

	public Areas() {
		// Areas
	}

	/**
	 * @return the areaClass
	 */
	public String getAreaClass() {
		return areaClass;
	}

	/**
	 * @param areaClass
	 *            the areaClass to set
	 */
	public void setAreaClass(final String areaClass) {
		this.areaClass = areaClass;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode
	 *            the areaCode to set
	 */
	public void setAreaCode(final String areaCode) {
		this.areaCode = areaCode;
	}

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
	 * @return the parentAreaCode
	 */
	public String getParentAreaCode() {
		return parentAreaCode;
	}

	/**
	 * @param parentAreaCode
	 *            the parentAreaCode to set
	 */
	public void setParentAreaCode(final String parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
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
	 * @return the areaType
	 */
	public String getAreaType() {
		return areaType;
	}

	/**
	 * @param areaType
	 *            the areaType to set
	 */
	public void setAreaType(final String areaType) {
		this.areaType = areaType;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
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
	 * @return the modifyDateTime
	 */
	public String getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(final String modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
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
	 * @return the canDisplay
	 */
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	/**
	 * @param canDisplay the canDisplay to set
	 */
	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

}
