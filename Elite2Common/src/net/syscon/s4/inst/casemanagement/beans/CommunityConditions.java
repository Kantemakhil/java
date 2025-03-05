package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CommunityConditions extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("amountRequiredFlag")
	private String amountRequiredFlag;
	@JsonProperty("casePlanFlag")
	private String casePlanFlag;
	@JsonProperty("conditionText")
	private String conditionText;
	@JsonProperty("conditionTextTemp")
	private String conditionTextTemp;
	@JsonProperty("conditionUnitType")
	private String conditionUnitType;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("creationDate")
	private Date creationDate;
	@JsonProperty("creationUser")
	private String creationUser;
	@JsonProperty("csoFlag")
	private String csoFlag;
	@JsonProperty("allocationFlag")
	private String allocationFlag;
	@JsonProperty("description")
	private String description;
	@JsonProperty("dueDateRequiredFlag")
	private String dueDateRequiredFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("financialFlag")
	private String financialFlag;
	@JsonProperty("functionType")
	private String functionType;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("maximumAmount")
	private BigDecimal maximumAmount;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("programFlag")
	private String programFlag;
	@JsonProperty("programMethod")
	private String programMethod;
	@JsonProperty("programUnits")
	private BigDecimal programUnits;
	@JsonProperty("provisoFlag")
	private String provisoFlag;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("substanceFlag")
	private String substanceFlag;
	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;
	@JsonProperty("code")
	private String code;
	@JsonProperty("descriptionType")
	private String descriptionType;

	public String getConditionTextTemp() {
		return conditionTextTemp;
	}
	public void setConditionTextTemp(String conditionTextTemp) {
		this.conditionTextTemp = conditionTextTemp;
	}
	public CommunityConditions() {
		// CommunityConditions
	}
	@JsonProperty("commConditionType")
	private String commConditionType;
	@JsonProperty("commConditionCode")
	private String commConditionCode;
	@JsonProperty("categoryType")
	private String categoryType;
	/**
	 * @return the commConditionType
	 */
	public String getCommConditionType() {
		return this.commConditionType;
	}
	/**
	 * @param commConditionType
	 *            the commConditionType to set
	 */
	public void setCommConditionType(final String commConditionType) {
		this.commConditionType = commConditionType;
	}
	/**
	 * @return the commConditionCode
	 */
	public String getCommConditionCode() {
		return this.commConditionCode;
	}
	/**
	 * @param commConditionCode
	 *            the commConditionCode to set
	 */
	public void setCommConditionCode(final String commConditionCode) {
		this.commConditionCode = commConditionCode;
	}
	/**
	 * @return the categoryType
	 */
	public String getCategoryType() {
		return this.categoryType;
	}
	/**
	 * @param categoryType
	 *            the categoryType to set
	 */
	public void setCategoryType(final String categoryType) {
		this.categoryType = categoryType;
	}
	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return this.activeFlag;
	}
	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}
	/**
	 * @return the amountRequiredFlag
	 */
	public String getAmountRequiredFlag() {
		return this.amountRequiredFlag;
	}
	/**
	 * @param amountRequiredFlag
	 *            the amountRequiredFlag to set
	 */
	public void setAmountRequiredFlag(final String amountRequiredFlag) {
		this.amountRequiredFlag = amountRequiredFlag;
	}
	/**
	 * @return the casePlanFlag
	 */
	public String getCasePlanFlag() {
		return this.casePlanFlag;
	}
	/**
	 * @param casePlanFlag
	 *            the casePlanFlag to set
	 */
	public void setCasePlanFlag(final String casePlanFlag) {
		this.casePlanFlag = casePlanFlag;
	}
	/**
	 * @return the conditionText
	 */
	public String getConditionText() {
		return this.conditionText;
	}
	/**
	 * @param conditionText
	 *            the conditionText to set
	 */
	public void setConditionText(final String conditionText) {
		this.conditionText = conditionText;
	}
	/**
	 * @return the conditionUnitType
	 */
	public String getConditionUnitType() {
		return this.conditionUnitType;
	}
	/**
	 * @param conditionUnitType
	 *            the conditionUnitType to set
	 */
	public void setConditionUnitType(final String conditionUnitType) {
		this.conditionUnitType = conditionUnitType;
	}
	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
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
		return this.createUserId;
	}
	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return this.creationDate;
	}
	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the creationUser
	 */
	public String getCreationUser() {
		return this.creationUser;
	}
	/**
	 * @param creationUser
	 *            the creationUser to set
	 */
	public void setCreationUser(final String creationUser) {
		this.creationUser = creationUser;
	}
	/**
	 * @return the csoFlag
	 */
	public String getCsoFlag() {
		return this.csoFlag;
	}
	/**
	 * @param csoFlag
	 *            the csoFlag to set
	 */
	public void setCsoFlag(final String csoFlag) {
		this.csoFlag = csoFlag;
	}
	/**
	 * @return the allocationFlag
	 */
	public String getAllocationFlag() {
		return this.allocationFlag;
	}
	/**
	 * @param allocationFlag
	 *            the allocationFlag to set
	 */
	public void setAllocationFlag(final String allocationFlag) {
		this.allocationFlag = allocationFlag;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	/**
	 * @return the dueDateRequiredFlag
	 */
	public String getDueDateRequiredFlag() {
		return this.dueDateRequiredFlag;
	}
	/**
	 * @param dueDateRequiredFlag
	 *            the dueDateRequiredFlag to set
	 */
	public void setDueDateRequiredFlag(final String dueDateRequiredFlag) {
		this.dueDateRequiredFlag = dueDateRequiredFlag;
	}
	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return this.expiryDate;
	}
	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return the financialFlag
	 */
	public String getFinancialFlag() {
		return this.financialFlag;
	}
	/**
	 * @param financialFlag
	 *            the financialFlag to set
	 */
	public void setFinancialFlag(final String financialFlag) {
		this.financialFlag = financialFlag;
	}
	/**
	 * @return the functionType
	 */
	public String getFunctionType() {
		return this.functionType;
	}
	/**
	 * @param functionType
	 *            the functionType to set
	 */
	public void setFunctionType(final String functionType) {
		this.functionType = functionType;
	}
	/**
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return this.listSeq;
	}
	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}
	/**
	 * @return the maximumAmount
	 */
	public BigDecimal getMaximumAmount() {
		return this.maximumAmount;
	}
	/**
	 * @param maximumAmount
	 *            the maximumAmount to set
	 */
	public void setMaximumAmount(final BigDecimal maximumAmount) {
		this.maximumAmount = maximumAmount;
	}
	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
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
		return this.modifyUserId;
	}
	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * @return the programFlag
	 */
	public String getProgramFlag() {
		return this.programFlag;
	}
	/**
	 * @param programFlag
	 *            the programFlag to set
	 */
	public void setProgramFlag(final String programFlag) {
		this.programFlag = programFlag;
	}
	/**
	 * @return the programMethod
	 */
	public String getProgramMethod() {
		return this.programMethod;
	}
	/**
	 * @param programMethod
	 *            the programMethod to set
	 */
	public void setProgramMethod(final String programMethod) {
		this.programMethod = programMethod;
	}
	/**
	 * @return the programUnits
	 */
	public BigDecimal getProgramUnits() {
		return this.programUnits;
	}
	/**
	 * @param programUnits
	 *            the programUnits to set
	 */
	public void setProgramUnits(final BigDecimal programUnits) {
		this.programUnits = programUnits;
	}

	public String getProvisoFlag() {
		return this.provisoFlag;
	}

	public void setProvisoFlag(final String provisoFlag) {
		this.provisoFlag = provisoFlag;
	}
	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}
	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	/**
	 * @return the substanceFlag
	 */
	public String getSubstanceFlag() {
		return this.substanceFlag;
	}
	/**
	 * @param substanceFlag
	 *            the substanceFlag to set
	 */
	public void setSubstanceFlag(final String substanceFlag) {
		this.substanceFlag = substanceFlag;
	}
	/**
	 * @return the updateAllowedFlag
	 */
	public String getUpdateAllowedFlag() {
		return this.updateAllowedFlag;
	}
	/**
	 * @param updateAllowedFlag
	 *            the updateAllowedFlag to set
	 */
	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
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
	 * @return the descriptionType
	 */
	public String getDescriptionType() {
		return descriptionType;
	}
	/**
	 * @param descriptionType the descriptionType to set
	 */
	public void setDescriptionType(final String descriptionType) {
		this.descriptionType = descriptionType;
	}
private int returnValue;
/**
 * @return the returnValue
 */
public int getReturnValue() {
	return returnValue;
}
/**
 * @param returnValue
 *            the returnValue to set
 */
public void setReturnValue(final int returnValue) {
	this.returnValue = returnValue;
}
private int deleteRecordCountData;
/**
 * @return the deleteRecordCountData
 */
public int getDeleteRecordCountData() {
	return deleteRecordCountData;
}
/**
 * @param deleteRecordCountData
 *            the deleteRecordCountData to set
 */
public void setDeleteRecordCountData(final int deleteRecordCountData) {
	this.deleteRecordCountData = deleteRecordCountData;
}
@JsonProperty("serverCode")
private int serverCode;
/**
 * @return the serverCode
 */
public int getServerCode() {
	return serverCode;
}

public void setServerCode(int serverCode) {
	this.serverCode = serverCode;
}


}
