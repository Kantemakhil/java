package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the DEDUCTION_TYPES database table.
 * 
 */
public class DeductionTypes extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("deductionType")
	private String deductionType;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("caseloadCode")
	private String caseloadCode;

	@JsonProperty("caseloadRestrictedFlag")
	private String caseloadRestrictedFlag;

	@JsonProperty("clpFlag")
	private String clpFlag;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("deductionCategory")
	private String deductionCategory;

	@JsonProperty("deductionDesc")
	private String deductionDesc;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("fromBalanceType")
	private String fromBalanceType;

	@JsonProperty("incrementPayablesFlag")
	private String incrementPayablesFlag;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDate")
	private Date modifyDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("percentageOfParent")
	private BigDecimal percentageOfParent;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;

	@JsonProperty("description")
	private String description;

	@JsonProperty("code")
	private String code;

	@JsonProperty("parentDeductionType")
	private String parentDeductionType;

	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;
	
	
	@JsonProperty("calculateON")
	private String calculateON;
	
	

	public String getCalculateON() {
		return calculateON;
	}

	public void setCalculateON(String calculateON) {
		this.calculateON = calculateON;
	}

	public DeductionTypes() {
		// DeductionType
	}

	public String getDeductionType() {
		return this.deductionType;
	}

	public void setDeductionType(final String deductionType) {
		this.deductionType = deductionType;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getCaseloadCode() {
		return this.caseloadCode;
	}

	public void setCaseloadCode(final String caseloadCode) {
		this.caseloadCode = caseloadCode;
	}

	public String getCaseloadRestrictedFlag() {
		return this.caseloadRestrictedFlag;
	}

	public void setCaseloadRestrictedFlag(final String caseloadRestrictedFlag) {
		this.caseloadRestrictedFlag = caseloadRestrictedFlag;
	}

	public String getClpFlag() {
		return this.clpFlag;
	}

	public void setClpFlag(final String clpFlag) {
		this.clpFlag = clpFlag;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getDeductionCategory() {
		return this.deductionCategory;
	}

	public void setDeductionCategory(final String deductionCategory) {
		this.deductionCategory = deductionCategory;
	}

	public String getDeductionDesc() {
		return this.deductionDesc;
	}

	public void setDeductionDesc(final String deductionDesc) {
		this.deductionDesc = deductionDesc;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFromBalanceType() {
		return this.fromBalanceType;
	}

	public void setFromBalanceType(final String fromBalanceType) {
		this.fromBalanceType = fromBalanceType;
	}

	public String getIncrementPayablesFlag() {
		return this.incrementPayablesFlag;
	}

	public void setIncrementPayablesFlag(final String incrementPayablesFlag) {
		this.incrementPayablesFlag = incrementPayablesFlag;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getPercentageOfParent() {
		return this.percentageOfParent;
	}

	public void setPercentageOfParent(final BigDecimal percentageOfParent) {
		this.percentageOfParent = percentageOfParent;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getUpdateAllowedFlag() {
		return this.updateAllowedFlag;
	}

	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
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
	 * @return the parentDeductionType
	 */
	public String getParentDeductionType() {
		return parentDeductionType;
	}

	/**
	 * @param parentDeductionType
	 *            the parentDeductionType to set
	 */
	public void setParentDeductionType(final String parentDeductionType) {
		this.parentDeductionType = parentDeductionType;
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

}
