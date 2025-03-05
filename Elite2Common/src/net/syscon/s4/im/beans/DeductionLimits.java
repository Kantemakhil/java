package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeductionLimits extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("maxDeduction")
	private BigDecimal maxDeduction;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("compensationCode")
	private String compensationCode;
	@JsonProperty("payRangeCode")
	private String payRangeCode;
	@JsonProperty("receiptType")
	private String receiptType;

	/**
	 * Creates new DeductionLimits class Object
	 */
	public DeductionLimits() {
		// DeductionLimits
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
	 * @return the maxDeduction
	 */
	public BigDecimal getMaxDeduction() {
		return maxDeduction;
	}

	/**
	 * @param maxDeduction
	 *            the maxDeduction to set
	 */
	public void setMaxDeduction(final BigDecimal maxDeduction) {
		this.maxDeduction = maxDeduction;
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
	 * @return the compensationCode
	 */
	public String getCompensationCode() {
		return compensationCode;
	}

	/**
	 * @param compensationCode
	 *            the compensationCode to set
	 */
	public void setCompensationCode(final String compensationCode) {
		this.compensationCode = compensationCode;
	}

	/**
	 * @return the payRangeCode
	 */
	public String getPayRangeCode() {
		return payRangeCode;
	}

	/**
	 * @param payRangeCode
	 *            the payRangeCode to set
	 */
	public void setPayRangeCode(final String payRangeCode) {
		this.payRangeCode = payRangeCode;
	}

	/**
	 * @return the receiptType
	 */
	public String getReceiptType() {
		return receiptType;
	}

	/**
	 * @param receiptType
	 *            the receiptType to set
	 */
	public void setReceiptType(final String receiptType) {
		this.receiptType = receiptType;
	}

}
