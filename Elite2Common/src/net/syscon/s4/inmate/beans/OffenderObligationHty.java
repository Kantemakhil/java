package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderObligationHty extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderDeductionId")
    private BigDecimal offenderDeductionId;
	@JsonProperty("informationNumber")
    private String informationNumber;
	@JsonProperty("createUserId")
    private String createUserId;
	@JsonProperty("modifyUserId")
    private String modifyUserId;
	@JsonProperty("modifiedDateTime")
    private Date modifiedDateTime;
	@JsonProperty("modifiedUserId")
    private String modifiedUserId;
	@JsonProperty("modifyDateTime")
    private Date modifyDateTime;
	@JsonProperty("createDateTime")
    private Date createDateTime;
	@JsonProperty("sealFlag")
    private String sealFlag;
	@JsonProperty("deductionSeq")
    private Integer deductionSeq;
	@JsonProperty("adjustedAmount")
    private BigDecimal adjustedAmount;
	@JsonProperty("maxTotalAmount")
    private BigDecimal maxTotalAmount;
	@JsonProperty("deductionType")
    private String deductionType;
	
	public OffenderObligationHty(){
		// OffenderObligationHty
	}

	/**
	 * @return the offenderDeductionId
	 */
	public BigDecimal getOffenderDeductionId() {
		return offenderDeductionId;
	}

	/**
	 * @param offenderDeductionId the offenderDeductionId to set
	 */
	public void setOffenderDeductionId(final BigDecimal offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	/**
	 * @return the informationNumber
	 */
	public String getInformationNumber() {
		return informationNumber;
	}

	/**
	 * @param informationNumber the informationNumber to set
	 */
	public void setInformationNumber(final String informationNumber) {
		this.informationNumber = informationNumber;
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
	 * @return the modifiedDateTime
	 */
	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}

	/**
	 * @param modifiedDateTime the modifiedDateTime to set
	 */
	public void setModifiedDateTime(final Date modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	/**
	 * @return the modifiedUserId
	 */
	public String getModifiedUserId() {
		return modifiedUserId;
	}

	/**
	 * @param modifiedUserId the modifiedUserId to set
	 */
	public void setModifiedUserId(final String modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}

	/**
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime the modifyDateTime to set
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
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
	 * @return the deductionSeq
	 */
	public Integer getDeductionSeq() {
		return deductionSeq;
	}

	/**
	 * @param deductionSeq the deductionSeq to set
	 */
	public void setDeductionSeq(final Integer deductionSeq) {
		this.deductionSeq = deductionSeq;
	}

	/**
	 * @return the adjustedAmount
	 */
	public BigDecimal getAdjustedAmount() {
		return adjustedAmount;
	}

	/**
	 * @param adjustedAmount the adjustedAmount to set
	 */
	public void setAdjustedAmount(final BigDecimal adjustedAmount) {
		this.adjustedAmount = adjustedAmount;
	}

	/**
	 * @return the maxTotalAmount
	 */
	public BigDecimal getMaxTotalAmount() {
		return maxTotalAmount;
	}

	/**
	 * @param maxTotalAmount the maxTotalAmount to set
	 */
	public void setMaxTotalAmount(final BigDecimal maxTotalAmount) {
		this.maxTotalAmount = maxTotalAmount;
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
	
	

}
