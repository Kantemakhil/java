package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the CASELOAD_DED_BENEFICIARIES database table.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseloadDedBeneficiaries extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("caseloadDedBeneficiaryId")
	private BigDecimal caseloadDedBeneficiaryId;
	
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("deductionType")
	private String deductionType;
	
	@JsonProperty("amount")
	private BigDecimal amount;
	
	@JsonProperty("corporateId")
	private BigDecimal corporateId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDate")
	private Date modifyDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("percent")
	private BigDecimal percent;

	@JsonProperty("personId")
	private BigDecimal personId;

	@JsonProperty("priority")
	private BigDecimal priority;

	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("dspFirstName")
	private String dspFirstName;
	
	@JsonProperty("dspLastName")
	private String dspLastName;
	
	@JsonProperty("dspCorporateName")
	private String dspCorporateName;
	
	@JsonProperty("coCreditWhenIndigentFlag")
	private String coCreditWhenIndigentFlag;
	
	

	@JsonProperty("maxTotalAmount")
	private BigDecimal maxTotalAmount;

	@JsonProperty("caseloadType")
	private String caseloadType;


	@JsonProperty("offenderFeeId")
	private BigDecimal offenderFeeId;


	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("offFeeDedBeneficiaryId")
	private Long offFeeDedBeneficiaryId;

	public CaseloadDedBeneficiaries() {
//		CaseloadDedBeneficiaries
	}




	/**
	 * @return the caseloadDedBeneficiaryId
	 */
	public BigDecimal getCaseloadDedBeneficiaryId() {
		return caseloadDedBeneficiaryId;
	}




	/**
	 * @param caseloadDedBeneficiaryId the caseloadDedBeneficiaryId to set
	 */
	public void setCaseloadDedBeneficiaryId(final BigDecimal caseloadDedBeneficiaryId) {
		this.caseloadDedBeneficiaryId = caseloadDedBeneficiaryId;
	}




	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}




	/**
	 * @param amount the amount to set
	 */
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}




	/**
	 * @return the corporateId
	 */
	public BigDecimal getCorporateId() {
		return corporateId;
	}




	/**
	 * @param corporateId the corporateId to set
	 */
	public void setCorporateId(final BigDecimal corporateId) {
		this.corporateId = corporateId;
	}




	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}




	/**
	 * @param createDatetime the createDatetime to set
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
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}




	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}




	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
	}




	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}




	/**
	 * @param modifyDatetime the modifyDatetime to set
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
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}




	/**
	 * @return the percent
	 */
	public BigDecimal getPercent() {
		return percent;
	}




	/**
	 * @param percent the percent to set
	 */
	public void setPercent(final BigDecimal percent) {
		this.percent = percent;
	}




	/**
	 * @return the personId
	 */
	public BigDecimal getPersonId() {
		return personId;
	}




	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(final BigDecimal personId) {
		this.personId = personId;
	}




	/**
	 * @return the priority
	 */
	public BigDecimal getPriority() {
		return priority;
	}




	/**
	 * @param priority the priority to set
	 */
	public void setPriority(final BigDecimal priority) {
		this.priority = priority;
	}




	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}




	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}




	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}




	/**
	 * @param caseloadId the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}




	/**
	 * @return the deductionType
	 */
	public String getDeductionType() {
		return deductionType;
	}




	/**
	 * @param deductionType the deductionType to set
	 */
	public void setDeductionType(final String deductionType) {
		this.deductionType = deductionType;
	}




	/**
	 * @return the dspFirstName
	 */
	public String getDspFirstName() {
		return dspFirstName;
	}




	/**
	 * @param dspFirstName the dspFirstName to set
	 */
	public void setDspFirstName(final String dspFirstName) {
		this.dspFirstName = dspFirstName;
	}




	/**
	 * @return the dspLastName
	 */
	public String getDspLastName() {
		return dspLastName;
	}




	/**
	 * @param dspLastName the dspLastName to set
	 */
	public void setDspLastName(final String dspLastName) {
		this.dspLastName = dspLastName;
	}




	/**
	 * @return the dspCorporateName
	 */
	public String getDspCorporateName() {
		return dspCorporateName;
	}




	/**
	 * @param dspCorporateName the dspCorporateName to set
	 */
	public void setDspCorporateName(final String dspCorporateName) {
		this.dspCorporateName = dspCorporateName;
	}




	/**
	 * @return the coCreditWhenIndigentFlag
	 */
	public String getCoCreditWhenIndigentFlag() {
		return coCreditWhenIndigentFlag;
	}




	/**
	 * @param coCreditWhenIndigentFlag the coCreditWhenIndigentFlag to set
	 */
	public void setCoCreditWhenIndigentFlag(final String coCreditWhenIndigentFlag) {
		this.coCreditWhenIndigentFlag = coCreditWhenIndigentFlag;
	}

	public BigDecimal getMaxTotalAmount() {
		return maxTotalAmount;
	}

	public void setMaxTotalAmount(BigDecimal maxTotalAmount) {
		this.maxTotalAmount = maxTotalAmount;
	}

	public String getCaseloadType() {
		return caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}


	public Long getOffenderBookId() {
		return offenderBookId;
	}




	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}




	public BigDecimal getOffenderFeeId() {
		return offenderFeeId;
	}




	public void setOffenderFeeId(BigDecimal offenderFeeId) {
		this.offenderFeeId = offenderFeeId;
	}




	public Long getOffFeeDedBeneficiaryId() {
		return offFeeDedBeneficiaryId;
	}




	public void setOffFeeDedBeneficiaryId(Long offFeeDedBeneficiaryId) {
		this.offFeeDedBeneficiaryId = offFeeDedBeneficiaryId;
	}




}
