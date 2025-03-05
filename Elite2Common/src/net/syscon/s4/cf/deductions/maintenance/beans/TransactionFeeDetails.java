package net.syscon.s4.cf.deductions.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the TRANSACTION_FEE_DETAILS database table.
 * 
 */
public class TransactionFeeDetails extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("modifyDate")
	private Date modifyDate;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("deductionType")
	private String deductionType;
	@JsonProperty("returnValue")
	private int returnValue;
	@JsonProperty("rowId")
	private Integer rowId;
	
	@JsonProperty("receiptDeductionType")
	private String receiptDeductionType;
	
	public TransactionFeeDetails() {
		//constructor
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
	 * @return the receiptDeductionType
	 */
	public String getReceiptDeductionType() {
		return receiptDeductionType;
	}
	/**
	 * @param receiptDeductionType
	 *            the receiptDeductionType to set
	 */
	public void setReceiptDeductionType(final String receiptDeductionType) {
		this.receiptDeductionType = receiptDeductionType;
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
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate
	 *            the modifyDate to set
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
