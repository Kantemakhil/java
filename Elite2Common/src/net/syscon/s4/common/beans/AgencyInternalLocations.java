package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AgencyInternalLocations extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("internalLocationId")
	@NotNull
	private Integer internalLocationId;
	@JsonProperty("internalLocationCode")
	@NotNull
	@Size(max = 40)
	private String internalLocationCode;
	@JsonProperty("agyLocId")
	@NotNull
	@Size(max = 6)
	private String agyLocId;
	@JsonProperty("internalLocationType")
	@NotNull
	@Size(max = 12)
	private String internalLocationType;
	@JsonProperty("description")
	@NotNull
	@Size(max = 40)
	private String description;
	@JsonProperty("securityLevelCode")
	@Size(max = 12)
	private String securityLevelCode;
	@JsonProperty("capacity")
	private Integer capacity;
	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;
	@JsonProperty("parentInternalLocationId")
	private Integer parentInternalLocationId;
	@JsonProperty("activeFlag")
	@NotNull
	@Size(max = 1)
	private String activeFlag;
	@JsonProperty("listSeq")
	private Integer listSeq;
	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;
	@JsonProperty("cnaNo")
	private Integer cnaNo;
	@JsonProperty("certifiedFlag")
	@NotNull
	@Size(max = 1)
	private String certifiedFlag;
	@JsonProperty("deactivateDate")
	private Date deactivateDate;
	@JsonProperty("reactivateDate")
	private Date reactivateDate;
	@JsonProperty("deactiveReasonCode")
	@Size(max = 12)
	private String deactiveReasonCode;
	@JsonProperty("commentText")
	@Size(max = 240)
	private String commentText;
	@JsonProperty("userDesc")
	@Size(max = 40)
	private String userDesc;
	@JsonProperty("acaCapRating")
	private Integer acaCapRating;
	@JsonProperty("unitType")
	@Size(max = 12)
	private String unitType;
	@JsonProperty("operationCapacity")
	private Integer operationCapacity;
	@JsonProperty("noOfOccupant")
	private Integer noOfOccupant;
	@JsonProperty("trackingFlag")
	@NotNull
	@Size(max = 1)
	private String trackingFlag;
	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;
	@JsonProperty("inserted")
	private Boolean inserted;
	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("code")
	private String code;

	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;

	@JsonProperty("levelCode")
	private String levelCode;

	@JsonProperty("activeFlagData")
	private String activeFlagData;

	@JsonProperty("trackingFlagData")
	private String trackingFlagData;
	
	private BigDecimal offenderId;
	
	private Long offenderBookId;
	
	private String level1Code;
	
	private String level2Code;
	
	private String level3Code;
	
	private Integer emptySearchCount;
	
	private String level4Code;
	
	private String locationLevel;
	
	private Integer pLevel;
	
	private Integer linkedOffenderCount;
	
	private Integer linkedOffenderCount1;

	
	

	@JsonProperty("usageLoc")
	private Integer usageLoc;

	public Integer getpLevel() {
		return pLevel;
	}

	public void setpLevel(Integer pLevel) {
		this.pLevel = pLevel;
	}

	/**
	 * Creates new AgencyInternalLocations class Object
	 */
	public AgencyInternalLocations() {
		// AgencyInternalLocations
	}

	/**
	 * @return the activeFlagData
	 */
	public String getActiveFlagData() {
		return activeFlagData;
	}

	/**
	 * @param activeFlagData
	 *            the activeFlagData to set
	 */
	public void setActiveFlagData(final String activeFlagData) {
		this.activeFlagData = activeFlagData;
	}

	/**
	 * @return the trackingFlagData
	 */
	public String getTrackingFlagData() {
		return trackingFlagData;
	}

	/**
	 * @param trackingFlagData
	 *            the trackingFlagData to set
	 */
	public void setTrackingFlagData(final String trackingFlagData) {
		this.trackingFlagData = trackingFlagData;
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
	 * @return the internalLocationCode
	 */
	public String getInternalLocationCode() {
		return internalLocationCode;
	}

	/**
	 * @param internalLocationCode
	 *            the internalLocationCode to set
	 */
	public void setInternalLocationCode(final String internalLocationCode) {
		this.internalLocationCode = internalLocationCode;
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
	 * @return the internalLocationType
	 */
	public String getInternalLocationType() {
		return internalLocationType;
	}

	/**
	 * @param internalLocationType
	 *            the internalLocationType to set
	 */
	public void setInternalLocationType(final String internalLocationType) {
		this.internalLocationType = internalLocationType;
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
	 * @return the securityLevelCode
	 */
	public String getSecurityLevelCode() {
		return securityLevelCode;
	}

	/**
	 * @param securityLevelCode
	 *            the securityLevelCode to set
	 */
	public void setSecurityLevelCode(final String securityLevelCode) {
		this.securityLevelCode = securityLevelCode;
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
	 * @return the parentInternalLocationId
	 */
	public Integer getParentInternalLocationId() {
		return parentInternalLocationId;
	}

	/**
	 * @param parentInternalLocationId
	 *            the parentInternalLocationId to set
	 */
	public void setParentInternalLocationId(final Integer parentInternalLocationId) {
		this.parentInternalLocationId = parentInternalLocationId;
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
	 * @return the cnaNo
	 */
	public Integer getCnaNo() {
		return cnaNo;
	}

	/**
	 * @param cnaNo
	 *            the cnaNo to set
	 */
	public void setCnaNo(final Integer cnaNo) {
		this.cnaNo = cnaNo;
	}

	/**
	 * @return the certifiedFlag
	 */
	public String getCertifiedFlag() {
		return certifiedFlag;
	}

	/**
	 * @param certifiedFlag
	 *            the certifiedFlag to set
	 */
	public void setCertifiedFlag(final String certifiedFlag) {
		this.certifiedFlag = certifiedFlag;
	}

	/**
	 * @return the deactiveDate
	 */
	public Date getDeactivateDate() {
		return deactivateDate;
	}

	/**
	 * @param deactiveDate
	 *            the deactiveDate to set
	 */
	public void setDeactivateDate(final Date deactivateDate) {
		this.deactivateDate = deactivateDate;
	}

	/**
	 * @return the reactiveDate
	 */
	public Date getReactivateDate() {
		return reactivateDate;
	}

	/**
	 * @param reactiveDate
	 *            the reactiveDate to set
	 */
	public void setReactivateDate(final Date reactivateDate) {
		this.reactivateDate = reactivateDate;
	}

	/**
	 * @return the deactiveReasonCode
	 */
	public String getDeactiveReasonCode() {
		return deactiveReasonCode;
	}

	/**
	 * @param deactiveReasonCode
	 *            the deactiveReasonCode to set
	 */
	public void setDeactiveReasonCode(final String deactiveReasonCode) {
		this.deactiveReasonCode = deactiveReasonCode;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
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
	 * @return the acaCapRating
	 */
	public Integer getAcaCapRating() {
		return acaCapRating;
	}

	/**
	 * @param acaCapRating
	 *            the acaCapRating to set
	 */
	public void setAcaCapRating(final Integer acaCapRating) {
		this.acaCapRating = acaCapRating;
	}

	/**
	 * @return the unitType
	 */
	public String getUnitType() {
		return unitType;
	}

	/**
	 * @param unitType
	 *            the unitType to set
	 */
	public void setUnitType(final String unitType) {
		this.unitType = unitType;
	}

	/**
	 * @return the operationCapacity
	 */
	public Integer getOperationCapacity() {
		return operationCapacity;
	}

	/**
	 * @param operationCapacity
	 *            the operationCapacity to set
	 */
	public void setOperationCapacity(final Integer operationCapacity) {
		this.operationCapacity = operationCapacity;
	}

	/**
	 * @return the noOfOccupant
	 */
	public Integer getNoOfOccupant() {
		return noOfOccupant;
	}

	/**
	 * @param noOfOccupant
	 *            the noOfOccupant to set
	 */
	public void setNoOfOccupant(final Integer noOfOccupant) {
		this.noOfOccupant = noOfOccupant;
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
	public void setTrackingFlag(final String trackingFlag) {
		this.trackingFlag = trackingFlag;
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
	public Boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final Boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCode() {
		return code;
	}

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

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(final String levelCode) {
		this.levelCode = levelCode;
	}

	@Override
	public String toString() {
		return "AgencyInternalLocations [internalLocationId=" + internalLocationId + ", internalLocationCode="
				+ internalLocationCode + ", agyLocId=" + agyLocId + ", internalLocationType=" + internalLocationType
				+ ", description=" + description + ", securityLevelCode=" + securityLevelCode + ", capacity=" + capacity
				+ ", createUserId=" + createUserId + ", parentInternalLocationId=" + parentInternalLocationId
				+ ", activeFlag=" + activeFlag + ", listSeq=" + listSeq + ", createDateTime=" + createDateTime
				+ ", modifyDateTime=" + modifyDateTime + ", modifyUserId=" + modifyUserId + ", cnaNo=" + cnaNo
				+ ", certifiedFlag=" + certifiedFlag + ", deactivateDate=" + deactivateDate + ", reactivateDate="
				+ reactivateDate + ", deactiveReasonCode=" + deactiveReasonCode + ", commentText=" + commentText
				+ ", userDesc=" + userDesc + ", acaCapRating=" + acaCapRating + ", unitType=" + unitType
				+ ", operationCapacity=" + operationCapacity + ", noOfOccupant=" + noOfOccupant + ", trackingFlag="
				+ trackingFlag + ", sealFlag=" + sealFlag + ", inserted=" + inserted + ", errorMessage=" + errorMessage
				+ ", code=" + code + ", canDisplay=" + canDisplay + ", levelCode=" + levelCode + ", activeFlagData="
				+ activeFlagData + ", trackingFlagData=" + trackingFlagData + "]";
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getLevel1Code() {
		return level1Code;
	}

	public void setLevel1Code(String level1Code) {
		this.level1Code = level1Code;
	}

	public String getLevel2Code() {
		return level2Code;
	}

	public void setLevel2Code(String level2Code) {
		this.level2Code = level2Code;
	}

	public String getLevel3Code() {
		return level3Code;
	}

	public void setLevel3Code(String level3Code) {
		this.level3Code = level3Code;
	}

	public String getLevel4Code() {
		return level4Code;
	}

	public void setLevel4Code(String level4Code) {
		this.level4Code = level4Code;
	}

	public Integer getEmptySearchCount() {
		return emptySearchCount;
	}

	public String getLocationLevel() {
		return locationLevel;
	}

	public void setLocationLevel(String locationLevel) {
		this.locationLevel = locationLevel;
	}

	public void setEmptySearchCount(Integer emptySearchCount) {
		this.emptySearchCount = emptySearchCount;
	}

	public Integer getUsageLoc() {
		return usageLoc;
	}

	public void setUsageLoc(Integer usageLoc) {
		this.usageLoc = usageLoc;
	}
	
	public Integer getLinkedOffenderCount() {
		return linkedOffenderCount;
	}

	public void setLinkedOffenderCount(Integer linkedOffenderCount) {
		this.linkedOffenderCount = linkedOffenderCount;
	}

	public Integer getLinkedOffenderCount1() {
		return linkedOffenderCount1;
	}

	public void setLinkedOffenderCount1(Integer linkedOffenderCount1) {
		this.linkedOffenderCount1 = linkedOffenderCount1;
	}

	
}