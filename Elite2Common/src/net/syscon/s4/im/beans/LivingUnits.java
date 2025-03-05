package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;


@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class LivingUnits extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("acaCapRating")
	private BigDecimal acaCapRating;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("capacity")
	private BigDecimal capacity;

	@JsonProperty("certifiedFlag")
	private String certifiedFlag;

	@JsonProperty("cnaNo")
	private BigDecimal cnaNo;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("controlActiveFlag")
	private String controlActiveFlag;

	@JsonProperty("deactivateDate")
	private Date deactivateDate;

	@JsonProperty("deactivateReasonCode")
	private String deactivateReasonCode;

	@JsonProperty("description")
	private String description;

	@JsonProperty("housingUnitType")
	private String housingUnitType;

	@JsonProperty("level1Code")
	private String level1Code;

	@JsonProperty("level2Code")
	private String level2Code;

	@JsonProperty("level3Code")
	private String level3Code;

	@JsonProperty("level4Code")
	private String level4Code;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("livingUnitCode")
	private String livingUnitCode;

	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;

	@JsonProperty("livingUnitType")
	private String livingUnitType;

	@JsonProperty("lowestLevelFlag")
	private String lowestLevelFlag;

	@JsonProperty("noOfOccupant")
	private BigDecimal noOfOccupant;

	@JsonProperty("operationCapacity")
	private BigDecimal operationCapacity;

	@JsonProperty("parentLivingUnitId")
	private BigDecimal parentLivingUnitId;

	@JsonProperty("reachOperCapacityFlag")
	private String reachOperCapacityFlag;

	@JsonProperty("reactivateDate")
	private Date reactivateDate;

	@JsonProperty("securityLevelCode")
	private String securityLevelCode;

	@JsonProperty("userDesc")
	private String userDesc;

	@JsonProperty("code")
	private String code;
	@JsonProperty("locationCode")
	private String locationCode;
	@JsonProperty("agencyImlId")
	private Integer agencyImlId;

	@JsonProperty("numbers")
	private Integer numbers;

	@JsonProperty("stgId")
	private Long stgId;

	@JsonProperty("luType")
	private String luType;

	@JsonProperty("level1Id")
	private Integer level1Id;
	@JsonProperty("level2Id")
	private Integer level2Id;
	@JsonProperty("level3Id")
	private Integer level3Id;
	@JsonProperty("level4Id")
	private Integer level4Id;
	@JsonProperty("canDisplay")
	 private Boolean canDisplay = true;
	@JsonProperty("deactivateReasonCodeTemp")
	private String deactivateReasonCodeTemp;
	
	@JsonProperty("prisonerConflict")
	private String prisonerConflict;

	@JsonProperty("securityConflict")
	private String securityConflict;
	
	@JsonProperty("cellSharingConflict")
	private String cellSharingConflict;
	
	@JsonProperty("noOfAvailable")
	private BigDecimal noOfAvailable;
	
	@JsonProperty("unitAtCapacity")
	private String unitAtCapacity;
	
	@JsonProperty("level")
	private Integer level;
	
	private String createUserId;
	
	private String modifyUserId;
	
	
	public BigDecimal getNoOfAvailable() {
		return noOfAvailable;
	}

	public void setNoOfAvailable(BigDecimal noOfAvailable) {
		this.noOfAvailable = noOfAvailable;
	}

	public String getUnitAtCapacity() {
		return unitAtCapacity;
	}

	public void setUnitAtCapacity(String unitAtCapacity) {
		this.unitAtCapacity = unitAtCapacity;
	}

	public String getSecurityConflict() {
		return securityConflict;
	}

	public void setSecurityConflict(String securityConflict) {
		this.securityConflict = securityConflict;
	}

	public String getCellSharingConflict() {
		return cellSharingConflict;
	}

	public void setCellSharingConflict(String cellSharingConflict) {
		this.cellSharingConflict = cellSharingConflict;
	}

	public String getPrisonerConflict() {
		return prisonerConflict;
	}

	public void setPrisonerConflict(String prisonerConflict) {
		this.prisonerConflict = prisonerConflict;
	}

	public Long getStgId() {
		return stgId;
	}

	public void setStgId(final Long stgId) {
		this.stgId = stgId;
	}

	public Integer getNumbers() {
		return numbers;
	}

	public void setNumbers(final Integer numbers) {
		this.numbers = numbers;
	}

	/**
	 * Creates new OffenderAlerts class Date
	 */
	public LivingUnits() {
		// LivingUnits
	}

	/**
	 * @return the agencyImlId
	 */
	public Integer getAgencyImlId() {
		return agencyImlId;
	}

	/**
	 * @param agencyImlId
	 *            the agencyImlId to set
	 */
	public void setAgencyImlId(final Integer agencyImlId) {
		this.agencyImlId = agencyImlId;
	}

	/**
	 * @return the locationCode
	 */
	public String getLocationCode() {
		return locationCode;
	}

	/**
	 * @param locationCode
	 *            the locationCode to set
	 */
	public void setLocationCode(final String locationCode) {
		this.locationCode = locationCode;
	}

	/**
	 * @return the acaCapRating
	 */
	public BigDecimal getAcaCapRating() {
		return acaCapRating;
	}

	/**
	 * @param acaCapRating
	 *            the acaCapRating to set
	 */
	public void setAcaCapRating(final BigDecimal acaCapRating) {
		this.acaCapRating = acaCapRating;
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
	 * @return the capacity
	 */
	public BigDecimal getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(final BigDecimal capacity) {
		this.capacity = capacity;
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
	 * @return the cnaNo
	 */
	public BigDecimal getCnaNo() {
		return cnaNo;
	}

	/**
	 * @param cnaNo
	 *            the cnaNo to set
	 */
	public void setCnaNo(final BigDecimal cnaNo) {
		this.cnaNo = cnaNo;
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
	 * @return the controlActiveFlag
	 */
	public String getControlActiveFlag() {
		return controlActiveFlag;
	}

	/**
	 * @param controlActiveFlag
	 *            the controlActiveFlag to set
	 */
	public void setControlActiveFlag(final String controlActiveFlag) {
		this.controlActiveFlag = controlActiveFlag;
	}

	/**
	 * @return the deactivateDate
	 */
	public Date getDeactivateDate() {
		return deactivateDate;
	}

	/**
	 * @param deactivateDate
	 *            the deactivateDate to set
	 */
	public void setDeactivateDate(final Date deactivateDate) {
		this.deactivateDate = deactivateDate;
	}

	/**
	 * @return the deactivateReasonCode
	 */
	public String getDeactivateReasonCode() {
		return deactivateReasonCode;
	}

	/**
	 * @param deactivateReasonCode
	 *            the deactivateReasonCode to set
	 */
	public void setDeactivateReasonCode(final String deactivateReasonCode) {
		this.deactivateReasonCode = deactivateReasonCode;
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
	 * @return the housingUnitType
	 */
	public String getHousingUnitType() {
		return housingUnitType;
	}

	/**
	 * @param housingUnitType
	 *            the housingUnitType to set
	 */
	public void setHousingUnitType(final String housingUnitType) {
		this.housingUnitType = housingUnitType;
	}

	/**
	 * @return the level1Code
	 */
	public String getLevel1Code() {
		return level1Code;
	}

	/**
	 * @param level1Code
	 *            the level1Code to set
	 */
	public void setLevel1Code(final String level1Code) {
		this.level1Code = level1Code;
	}

	/**
	 * @return the level2Code
	 */
	public String getLevel2Code() {
		return level2Code;
	}

	/**
	 * @param level2Code
	 *            the level2Code to set
	 */
	public void setLevel2Code(final String level2Code) {
		this.level2Code = level2Code;
	}

	/**
	 * @return the level3Code
	 */
	public String getLevel3Code() {
		return level3Code;
	}

	/**
	 * @param level3Code
	 *            the level3Code to set
	 */
	public void setLevel3Code(final String level3Code) {
		this.level3Code = level3Code;
	}

	/**
	 * @return the level4Code
	 */
	public String getLevel4Code() {
		return level4Code;
	}

	/**
	 * @param level4Code
	 *            the level4Code to set
	 */
	public void setLevel4Code(final String level4Code) {
		this.level4Code = level4Code;
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
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the livingUnitCode
	 */
	public String getLivingUnitCode() {
		return livingUnitCode;
	}

	/**
	 * @param livingUnitCode
	 *            the livingUnitCode to set
	 */
	public void setLivingUnitCode(final String livingUnitCode) {
		this.livingUnitCode = livingUnitCode;
	}

	/**
	 * @return the livingUnitId
	 */
	public BigDecimal getLivingUnitId() {
		return livingUnitId;
	}

	/**
	 * @param livingUnitId
	 *            the livingUnitId to set
	 */
	public void setLivingUnitId(final BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	/**
	 * @return the livingUnitType
	 */
	public String getLivingUnitType() {
		return livingUnitType;
	}

	/**
	 * @param livingUnitType
	 *            the livingUnitType to set
	 */
	public void setLivingUnitType(final String livingUnitType) {
		this.livingUnitType = livingUnitType;
	}

	/**
	 * @return the lowestLevelFlag
	 */
	public String getLowestLevelFlag() {
		return lowestLevelFlag;
	}

	/**
	 * @param lowestLevelFlag
	 *            the lowestLevelFlag to set
	 */
	public void setLowestLevelFlag(final String lowestLevelFlag) {
		this.lowestLevelFlag = lowestLevelFlag;
	}

	/**
	 * @return the noOfOccupant
	 */
	public BigDecimal getNoOfOccupant() {
		return noOfOccupant;
	}

	/**
	 * @param noOfOccupant
	 *            the noOfOccupant to set
	 */
	public void setNoOfOccupant(final BigDecimal noOfOccupant) {
		this.noOfOccupant = noOfOccupant;
	}

	/**
	 * @return the operationCapacity
	 */
	public BigDecimal getOperationCapacity() {
		return operationCapacity;
	}

	/**
	 * @param operationCapacity
	 *            the operationCapacity to set
	 */
	public void setOperationCapacity(final BigDecimal operationCapacity) {
		this.operationCapacity = operationCapacity;
	}

	/**
	 * @return the parentLivingUnitId
	 */
	public BigDecimal getParentLivingUnitId() {
		return parentLivingUnitId;
	}

	/**
	 * @param parentLivingUnitId
	 *            the parentLivingUnitId to set
	 */
	public void setParentLivingUnitId(final BigDecimal parentLivingUnitId) {
		this.parentLivingUnitId = parentLivingUnitId;
	}

	/**
	 * @return the reachOperCapacityFlag
	 */
	public String getReachOperCapacityFlag() {
		return reachOperCapacityFlag;
	}

	/**
	 * @param reachOperCapacityFlag
	 *            the reachOperCapacityFlag to set
	 */
	public void setReachOperCapacityFlag(final String reachOperCapacityFlag) {
		this.reachOperCapacityFlag = reachOperCapacityFlag;
	}

	/**
	 * @return the reactivateDate
	 */
	public Date getReactivateDate() {
		return reactivateDate;
	}

	/**
	 * @param reactivateDate
	 *            the reactivateDate to set
	 */
	public void setReactivateDate(final Date reactivateDate) {
		this.reactivateDate = reactivateDate;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the luType
	 */
	public String getLuType() {
		return luType;
	}

	/**
	 * @param luType
	 *            the luType to set
	 */
	public void setLuType(final String luType) {
		this.luType = luType;
	}

	/**
	 * @return the level1Id
	 */
	public Integer getLevel1Id() {
		return level1Id;
	}

	/**
	 * @param level1Id
	 *            the level1Id to set
	 */
	public void setLevel1Id(Integer level1Id) {
		this.level1Id = level1Id;
	}

	/**
	 * @return the level2Id
	 */
	public Integer getLevel2Id() {
		return level2Id;
	}

	/**
	 * @param level2Id
	 *            the level2Id to set
	 */
	public void setLevel2Id(Integer level2Id) {
		this.level2Id = level2Id;
	}

	/**
	 * @return the level3Id
	 */
	public Integer getLevel3Id() {
		return level3Id;
	}

	/**
	 * @param level3Id
	 *            the level3Id to set
	 */
	public void setLevel3Id(Integer level3Id) {
		this.level3Id = level3Id;
	}

	/**
	 * @return the level4Id
	 */
	public Integer getLevel4Id() {
		return level4Id;
	}

	/**
	 * @param level4Id
	 *            the level4Id to set
	 */
	public void setLevel4Id(Integer level4Id) {
		this.level4Id = level4Id;
	}
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public String getDeactivateReasonCodeTemp() {
		return deactivateReasonCodeTemp;
	}

	public void setDeactivateReasonCodeTemp(String deactivateReasonCodeTemp) {
		this.deactivateReasonCodeTemp = deactivateReasonCodeTemp;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

}
