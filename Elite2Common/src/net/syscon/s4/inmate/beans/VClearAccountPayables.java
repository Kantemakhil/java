package net.syscon.s4.inmate.beans;

import java.io.Serializable;

import net.syscon.s4.common.beans.BaseModel;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the V_CLEAR_ACCOUNT_PAYABLES database table.
 * 
 */
public class VClearAccountPayables extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountCode")
	private BigDecimal accountCode;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("corporateId")
	private BigDecimal corporateId;

	@JsonProperty("corporateName")
	private String corporateName;

	@JsonProperty("personId")
	private BigDecimal personId;

	@JsonProperty("totalAmount")
	private BigDecimal totalAmount;
	
	@JsonProperty("minBalAmount")
	private BigDecimal minBalAmount;
	
	@JsonProperty("caseloadIdTemp")
	private String caseloadIdTemp;
	
	@JsonProperty("cgnbtCaseloadId")
	private String cgnbtCaseloadId;

	public VClearAccountPayables() {
		// VClearAccountPayable
	}

	public BigDecimal getAccountCode() {
		return this.accountCode;
	}

	public void setAccountCode(final BigDecimal accountCode) {
		this.accountCode = accountCode;
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public BigDecimal getCorporateId() {
		return this.corporateId;
	}

	public void setCorporateId(final BigDecimal corporateId) {
		this.corporateId = corporateId;
	}

	public String getCorporateName() {
		return this.corporateName;
	}

	public void setCorporateName(final String corporateName) {
		this.corporateName = corporateName;
	}

	public BigDecimal getPersonId() {
		return this.personId;
	}

	public void setPersonId(final BigDecimal personId) {
		this.personId = personId;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(final BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the minBalAmount
	 */
	public BigDecimal getMinBalAmount() {
		return minBalAmount;
	}

	/**
	 * @param minBalAmount the minBalAmount to set
	 */
	public void setMinBalAmount(BigDecimal minBalAmount) {
		this.minBalAmount = minBalAmount;
	}

	/**
	 * @return the caseloadIdTemp
	 */
	public String getCaseloadIdTemp() {
		return caseloadIdTemp;
	}

	/**
	 * @param caseloadIdTemp the caseloadIdTemp to set
	 */
	public void setCaseloadIdTemp(String caseloadIdTemp) {
		this.caseloadIdTemp = caseloadIdTemp;
	}

	/**
	 * @return the cgnbtCaseloadId
	 */
	public String getCgnbtCaseloadId() {
		return cgnbtCaseloadId;
	}

	/**
	 * @param cgnbtCaseloadId the cgnbtCaseloadId to set
	 */
	public void setCgnbtCaseloadId(final String cgnbtCaseloadId) {
		this.cgnbtCaseloadId = cgnbtCaseloadId;
	}

}
