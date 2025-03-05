package net.syscon.s4.inst.property.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class PropertyStorages
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyStorages extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("propertyStorageId")
	private Integer propertyStorageId;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("propertyStorageType")
	private String propertyStorageType;
	
	@JsonProperty("propertyStorageCode")
	private String propertyStorageCode;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("capacity")
	private Integer capacity;
	
	@JsonProperty("listSeq")
	private Integer listSeq;
	
	@JsonProperty("parentStorageId")
	private Integer parentStorageId;
	
	@JsonProperty("userDesc")
	private String userDesc;
	
	@JsonProperty("createDateTime")
	private Timestamp createDateTime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDateTime")
	private Timestamp modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("inserted")
	private boolean inserted;
	
	public PropertyStorages() {
		// PropertyStorages
	}

	/**
	 * @return the propertyStorageId
	 */
	public Integer getPropertyStorageId() {
		return propertyStorageId;
	}

	/**
	 * @param propertyStorageId
	 *            the propertyStorageId to set
	 */
	public void setPropertyStorageId(final Integer propertyStorageId) {
		this.propertyStorageId = propertyStorageId;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the propertyStorageType
	 */
	public String getPropertyStorageType() {
		return propertyStorageType;
	}

	/**
	 * @param propertyStorageType
	 *            the propertyStorageType to set
	 */
	public void setPropertyStorageType(final String propertyStorageType) {
		this.propertyStorageType = propertyStorageType;
	}

	/**
	 * @return the propertyStorageCode
	 */
	public String getPropertyStorageCode() {
		return propertyStorageCode;
	}

	/**
	 * @param propertyStorageCode
	 *            the propertyStorageCode to set
	 */
	public void setPropertyStorageCode(final String propertyStorageCode) {
		this.propertyStorageCode = propertyStorageCode;
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
	 * @return the capacity
	 */
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(final Integer capacity) {
		this.capacity = capacity;
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
	 * @return the parentStorageId
	 */
	public Integer getParentStorageId() {
		return parentStorageId;
	}

	/**
	 * @param parentStorageId
	 *            the parentStorageId to set
	 */
	public void setParentStorageId(final Integer parentStorageId) {
		this.parentStorageId = parentStorageId;
	}

	/**
	 * @return the userDesc
	 */
	public String getUserDesc() {
		return userDesc;
	}

	/**
	 * @param userDesc
	 *            the userDesc to set
	 */
	public void setUserDesc(final String userDesc) {
		this.userDesc = userDesc;
	}

	/**
	 * @return the createDateTime
	 */
	public Timestamp getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(final Timestamp createDateTime) {
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
	public Timestamp getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(final Timestamp modifyDateTime) {
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
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

}