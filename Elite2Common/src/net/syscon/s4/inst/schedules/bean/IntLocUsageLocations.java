package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * 
 * class IntLocUsageLocations
 *
 */
public class IntLocUsageLocations extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer internalLocationId;
	private Integer internalLocationUsageId;
	private int capacity;
	private String usageLocationType;
	private Date createDateTime;
	private Date modifyDateTime;
	private String createUserId;
	private String modifyUserId;
	private int listSeq;
	private Integer usageLocationId;
	@JsonProperty("code")
	private String code;
	@JsonProperty("description")
	private String description;
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;
	@JsonProperty("seq")
	private int seq;
	@JsonProperty("parentUsageLocationId")
	private Integer parentUsageLocationId;
	@JsonProperty("locCode")
	private String locCode;
	@JsonProperty("locDescription")
	private String locDescription;
	@JsonProperty("userDescription")
	private String userDescription;
	@JsonProperty("tabIndex")
	private Integer tabIndex;
	@JsonProperty("activeFlag")
	private String activeFlag;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the internalLocationId
	 */
	public Integer getInternalLocationId() {
		return internalLocationId;
	}

	/**
	 * @param internalLocationId
	 *            the internalLocationId to set
	 */
	public void setInternalLocationId(final Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	/**
	 * @return the internalLocationUsageId
	 */
	public Integer getInternalLocationUsageId() {
		return internalLocationUsageId;
	}

	/**
	 * @param internalLocationUsageId
	 *            the internalLocationUsageId to set
	 */
	public void setInternalLocationUsageId(final Integer internalLocationUsageId) {
		this.internalLocationUsageId = internalLocationUsageId;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(final int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the usageLocationType
	 */
	public String getUsageLocationType() {
		return usageLocationType;
	}

	/**
	 * @param usageLocationType
	 *            the usageLocationType to set
	 */
	public void setUsageLocationType(final String usageLocationType) {
		this.usageLocationType = usageLocationType;
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
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
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
	 * @return the listSeq
	 */
	public int getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final int listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the usageLocationId
	 */
	public Integer getUsageLocationId() {
		return usageLocationId;
	}

	/**
	 * @param usageLocationId
	 *            the usageLocationId to set
	 */
	public void setUsageLocationId(final Integer usageLocationId) {
		this.usageLocationId = usageLocationId;
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
	
	/**
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * @param seq
	 *            the seq to set
	 */
	public void setSeq(final int seq) {
		this.seq = seq;
	}
	public Integer getParentUsageLocationId() {
		return parentUsageLocationId;
	}

	public void setParentUsageLocationId(Integer parentUsageLocationId) {
		this.parentUsageLocationId = parentUsageLocationId;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getLocDescription() {
		return locDescription;
	}

	public void setLocDescription(String locDescription) {
		this.locDescription = locDescription;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public Integer getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(Integer tabIndex) {
		this.tabIndex = tabIndex;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

}