package net.syscon.s4.cf.deductions.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the OFFENDER_TXN_FEE_DETAILS database table.
 * 
 */
public class OffenderTxnFeeDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date createDatetime;

	private String createUserId;

	private BigDecimal listSeq;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private Long offenderDeductionId;

	private String receiptDeductionType;
	
	@JsonProperty("rowId")
	private Long rowId;

	public OffenderTxnFeeDetails() {
	}

	/**
	 * @return the rowId
	 */
	public Long getRowId() {
		return rowId;
	}

	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(final Long rowId) {
		this.rowId = rowId;
	}

	public Long getOffenderDeductionId() {
		return offenderDeductionId;
	}



	public void setOffenderDeductionId(final Long offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}



	public String getReceiptDeductionType() {
		return receiptDeductionType;
	}

	public void setReceiptDeductionType(final String receiptDeductionType) {
		this.receiptDeductionType = receiptDeductionType;
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

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
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

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
