package net.syscon.s4.inst.movements.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VIntLocSummaries extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("capacity")
	private BigDecimal capacity;

	@JsonProperty("deactivateDate")
	private Object deactivateDate;

	@JsonProperty("inLocations")
	private BigDecimal inLocations;

	@JsonProperty("internalLocationCode")
	private String internalLocationCode;

	@JsonProperty("internalLocationDesc")
	private String internalLocationDesc;

	@JsonProperty("internalLocationId")
	private BigDecimal internalLocationId;

	@JsonProperty("internalLocationType")
	private String internalLocationType;

	@JsonProperty("internalLocationTypeDesc")
	private String internalLocationTypeDesc;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("parentInternalLocationId")
	private BigDecimal parentInternalLocationId;

	@JsonProperty("trackingFlag")
	private String trackingFlag;

	@JsonProperty("userDesc")
	private String userDesc;

	@JsonProperty("description")
	private String description;

	@JsonProperty("code")
	private String code;

	@JsonProperty("nextButton")
	private String nextButton;
	
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;

	/**
	 * Creates new VIntLocSummaries class Object
	 */
	public VIntLocSummaries() {
		// VIntLocSummaries
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
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
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
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the capacity
	 */
	public BigDecimal getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the deactivateDate
	 */
	public Object getDeactivateDate() {
		return deactivateDate;
	}

	/**
	 * @param deactivateDate
	 *            the deactivateDate to set
	 */
	public void setDeactivateDate(Object deactivateDate) {
		this.deactivateDate = deactivateDate;
	}

	/**
	 * @return the inLocations
	 */
	public BigDecimal getInLocations() {
		return inLocations;
	}

	/**
	 * @param inLocations
	 *            the inLocations to set
	 */
	public void setInLocations(BigDecimal inLocations) {
		this.inLocations = inLocations;
	}

	/**
	 * @return the internalLocationCode
	 */
	public String getInternalLocationCode() {
		return internalLocationCode;
	}

	/**
	 * @param internalLocationCode
	 *            the internalLocationCode to set
	 */
	public void setInternalLocationCode(String internalLocationCode) {
		this.internalLocationCode = internalLocationCode;
	}

	/**
	 * @return the internalLocationDesc
	 */
	public String getInternalLocationDesc() {
		return internalLocationDesc;
	}

	/**
	 * @param internalLocationDesc
	 *            the internalLocationDesc to set
	 */
	public void setInternalLocationDesc(String internalLocationDesc) {
		this.internalLocationDesc = internalLocationDesc;
	}

	/**
	 * @return the internalLocationId
	 */
	public BigDecimal getInternalLocationId() {
		return internalLocationId;
	}

	/**
	 * @param internalLocationId
	 *            the internalLocationId to set
	 */
	public void setInternalLocationId(BigDecimal internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	/**
	 * @return the internalLocationType
	 */
	public String getInternalLocationType() {
		return internalLocationType;
	}

	/**
	 * @param internalLocationType
	 *            the internalLocationType to set
	 */
	public void setInternalLocationType(String internalLocationType) {
		this.internalLocationType = internalLocationType;
	}

	/**
	 * @return the internalLocationTypeDesc
	 */
	public String getInternalLocationTypeDesc() {
		return internalLocationTypeDesc;
	}

	/**
	 * @param internalLocationTypeDesc
	 *            the internalLocationTypeDesc to set
	 */
	public void setInternalLocationTypeDesc(String internalLocationTypeDesc) {
		this.internalLocationTypeDesc = internalLocationTypeDesc;
	}

	/**
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the parentInternalLocationId
	 */
	public BigDecimal getParentInternalLocationId() {
		return parentInternalLocationId;
	}

	/**
	 * @param parentInternalLocationId
	 *            the parentInternalLocationId to set
	 */
	public void setParentInternalLocationId(BigDecimal parentInternalLocationId) {
		this.parentInternalLocationId = parentInternalLocationId;
	}

	/**
	 * @return the trackingFlag
	 */
	public String getTrackingFlag() {
		return trackingFlag;
	}

	/**
	 * @param trackingFlag
	 *            the trackingFlag to set
	 */
	public void setTrackingFlag(String trackingFlag) {
		this.trackingFlag = trackingFlag;
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
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
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
	public void setDescription(String description) {
		this.description = description;
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
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the nextButton
	 */
	public String getNextButton() {
		return nextButton;
	}

	/**
	 * @param nextButton
	 *            the nextButton to set
	 */
	public void setNextButton(String nextButton) {
		this.nextButton = nextButton;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

}
