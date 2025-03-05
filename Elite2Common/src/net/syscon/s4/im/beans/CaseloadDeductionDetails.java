package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CaseloadDeductionDetails extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("flatRate")
	private BigDecimal flatRate;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("minimumTrustBalanceFlag")
	private String minimumTrustBalanceFlag;

	@JsonProperty("modifyDate")
	@NotNull
	private Date modifyDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("percentage")
	private BigDecimal percentage;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("caseloadId")
	@NotNull
	@Size(max = 6)
	private String caseloadId;

	@JsonProperty("deductionType")
	@NotNull
	@Size(max = 6)
	private String deductionType;

	@JsonProperty("receiptTxnType")
	@NotNull
	@Size(max = 6)
	private String receiptTxnType;

	@JsonProperty("offenderDeductionId")
	private Long offenderDeductionId;

	@JsonProperty("nbtModifyUserId")
	private String nbtModifyUserId;

	@JsonProperty("foFlag")
	private String foFlag;

	@JsonProperty("dialogFlg")
	private String dialogFlg;
	
	@JsonProperty("rowId")
	private String rowId;
	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("offenderFeeId")
	private BigDecimal offenderFeeId;


	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("receiptPercent")
	private BigDecimal receiptPercent;
	@JsonProperty("offFeeDedReceiptId")
	private Long offFeeDedReceiptId;
	

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	/**
	 * Creates new CaseloadDeductionDetails class Object
	 */
	public CaseloadDeductionDetails() {
		// CaseloadDeductionDetails
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
	public void setCreateDatetime(Date createDatetime) {
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
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the flatRate
	 */
	public BigDecimal getFlatRate() {
		return flatRate;
	}

	/**
	 * @param flatRate
	 *            the flatRate to set
	 */
	public void setFlatRate(BigDecimal flatRate) {
		this.flatRate = flatRate;
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
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the minimumTrustBalanceFlag
	 */
	public String getMinimumTrustBalanceFlag() {
		return minimumTrustBalanceFlag;
	}

	/**
	 * @param minimumTrustBalanceFlag
	 *            the minimumTrustBalanceFlag to set
	 */
	public void setMinimumTrustBalanceFlag(String minimumTrustBalanceFlag) {
		this.minimumTrustBalanceFlag = minimumTrustBalanceFlag;
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
	public void setModifyDate(Date modifyDate) {
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
	public void setModifyDatetime(Date modifyDatetime) {
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
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the percentage
	 */
	public BigDecimal getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage
	 *            the percentage to set
	 */
	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
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
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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
	public void setCaseloadId(String caseloadId) {
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
	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}

	/**
	 * @return the receiptTxnType
	 */
	public String getReceiptTxnType() {
		return receiptTxnType;
	}

	/**
	 * @param receiptTxnType
	 *            the receiptTxnType to set
	 */
	public void setReceiptTxnType(String receiptTxnType) {
		this.receiptTxnType = receiptTxnType;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the offenderDeductionId
	 */
	public Long getOffenderDeductionId() {
		return offenderDeductionId;
	}

	/**
	 * @param offenderDeductionId
	 *            the offenderDeductionId to set
	 */
	public void setOffenderDeductionId(final Long offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	/**
	 * @return the nbtModifyUserId
	 */
	public String getNbtModifyUserId() {
		return nbtModifyUserId;
	}

	/**
	 * @param nbtModifyUserId
	 *            the nbtModifyUserId to set
	 */
	public void setNbtModifyUserId(final String nbtModifyUserId) {
		this.nbtModifyUserId = nbtModifyUserId;
	}

	/**
	 * @return the foFlag
	 */
	public String getFoFlag() {
		return foFlag;
	}

	/**
	 * @param foFlag
	 *            the foFlag to set
	 */
	public void setFoFlag(final String foFlag) {
		this.foFlag = foFlag;
	}

	/**
	 * @return the dialogFlg
	 */
	public String getDialogFlg() {
		return dialogFlg;
	}

	/**
	 * @param dialogFlg
	 *            the dialogFlg to set
	 */
	public void setDialogFlg(final String dialogFlg) {
		this.dialogFlg = dialogFlg;
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

	public BigDecimal getReceiptPercent() {
		return receiptPercent;
	}

	public void setReceiptPercent(BigDecimal receiptPercent) {
		this.receiptPercent = receiptPercent;
	}

	public Long getOffFeeDedReceiptId() {
		return offFeeDedReceiptId;
	}

	public void setOffFeeDedReceiptId(Long offFeeDedReceiptId) {
		this.offFeeDedReceiptId = offFeeDedReceiptId;
	}
}
