package net.syscon.s4.cf.deductions.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the TIERED_TRANSACTION_FEE_AMOUNTS database table.
 * 
 */
public class TieredTransactionFeeAmounts extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("feeAmount")
	private BigDecimal feeAmount;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("percentage")
	private BigDecimal percentage;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("deductionType")
	private String deductionType;
	@JsonProperty("fromAmount")
	private BigDecimal fromAmount;
	@JsonProperty("toAmount")
	private BigDecimal toAmount;
	private int returnValue;
	@JsonProperty("rowId")
	private Integer rowId;
	@JsonProperty("pFlag")
	private String pFlag;
	@JsonProperty("deductionId")
	private Long deductionId;
	public Long getDeductionId() {
		return deductionId;
	}
	public void setDeductionId(Long deductionId) {
		this.deductionId = deductionId;
	}
	@JsonProperty("overlapCount")
   private int overlapCount;
	public TieredTransactionFeeAmounts() {
		//constructor
	}
	/**
	 * @return the overlapCount
	 */
	public int getOverlapCount() {
	return overlapCount;
}
	/**
	 * @param overlapCount
	 *            the overlapCount to set
	 */
public void setOverlapCount(final int overlapCount) {
	this.overlapCount = overlapCount;
}
/**
 * @return the pFlag
 */
	public String getpFlag() {
		return pFlag;
	}
	/**
	 * @param pFlag
	 *            the pFlag to set
	 */
	public void setpFlag(final String pFlag) {
		this.pFlag = pFlag;
	}
	/**
	 * @return the rowId
	 */
	public Integer getRowId() {
		return rowId;
	}
	/**
	 * @param rowId
	 *            the rowId to set
	 */
	public void setRowId(final Integer rowId) {
		this.rowId = rowId;
	}
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

	
	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}
	/**
	 * @param caseloadId
	 *            the caseloadId to set
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
	 * @param deductionType
	 *            the deductionType to set
	 */
	public void setDeductionType(final String deductionType) {
		this.deductionType = deductionType;
	}

	
	/**
	 * @return the fromAmount
	 */

	public BigDecimal getFromAmount() {
		return fromAmount;
	}
	/**
	 * @param fromAmount
	 *            the fromAmount to set
	 */
	public void setFromAmount(final BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}
	/**
	 * @return the toAmount
	 */
	public BigDecimal getToAmount() {
		return toAmount;
	}
	/**
	 * @param toAmount
	 *            the toAmount to set
	 */
	public void setToAmount(final BigDecimal toAmount) {
		this.toAmount = toAmount;
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
	 * @return the feeAmount
	 */
	public BigDecimal getFeeAmount() {
		return this.feeAmount;
	}
	/**
	 * @param feeAmount
	 *            the feeAmount to set
	 */
	public void setFeeAmount(final BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
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
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	/**
	 * @return the percentage
	 */
	public BigDecimal getPercentage() {
		return this.percentage;
	}
	/**
	 * @param percentage
	 *            the percentage to set
	 */
	public void setPercentage(final BigDecimal percentage) {
		this.percentage = percentage;
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

}
