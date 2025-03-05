package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderDeductionReceipts extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDateTime")
	private Date createDateTime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("flatRate")
	private BigDecimal flatRate;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("receiptPercentage")
	private BigDecimal receiptPercentage;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderDeductionId")
	private Long offenderDeductionId;

	@JsonProperty("receiptTxnType")
	private String receiptTxnType;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("deductionType")
	private String deductionType;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	
	@JsonProperty("dspDescription")
	private String dspDescription;
	
	@JsonProperty("caseloadType")
	private String caseloadType;
	
	@JsonProperty("percentage")
	private BigDecimal percentage;
	
	@JsonProperty("globalSuccessFlag")
	private String globalSuccessFlag;
	
	@JsonProperty("bell")
	private String bell;
	
	@JsonProperty("fifoFlag")
	private String fifoFlag;
	
	@JsonProperty("maintenanceFlag")
	private String maintenanceFlag;
	
	
	
	
	
	
	

	/**
	 * @return the maintenanceFlag
	 */
	public String getMaintenanceFlag() {
		return maintenanceFlag;
	}

	/**
	 * @param maintenanceFlag the maintenanceFlag to set
	 */
	public void setMaintenanceFlag(final String maintenanceFlag) {
		this.maintenanceFlag = maintenanceFlag;
	}

	/**
	 * Creates new OffenderDeductionReceipts class Object
	 */
	public OffenderDeductionReceipts() {
		// OffenderDeductionReceipts
	}

	/**
	 * @return the offenderId
	 */
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
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
	 * @return the flatRate
	 */
	public BigDecimal getFlatRate() {
		return flatRate;
	}

	/**
	 * @param flatRate
	 *            the flatRate to set
	 */
	public void setFlatRate(final BigDecimal flatRate) {
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
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
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
	 * @return the receiptPercentage
	 */
	public BigDecimal getReceiptPercentage() {
		return receiptPercentage;
	}

	/**
	 * @param receiptPercentage
	 *            the receiptPercentage to set
	 */
	public void setReceiptPercentage(final BigDecimal receiptPercentage) {
		this.receiptPercentage = receiptPercentage;
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
	 * @return the receiptTxnType
	 */
	public String getReceiptTxnType() {
		return receiptTxnType;
	}

	/**
	 * @param receiptTxnType
	 *            the receiptTxnType to set
	 */
	public void setReceiptTxnType(final String receiptTxnType) {
		this.receiptTxnType = receiptTxnType;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the dspDescription
	 */
	public String getDspDescription() {
		return dspDescription;
	}

	/**
	 * @param dspDescription the dspDescription to set
	 */
	public void setDspDescription(final String dspDescription) {
		this.dspDescription = dspDescription;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType the caseloadType to set
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 * @return the percentage
	 */
	public BigDecimal getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(final BigDecimal percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return the globalSuccessFlag
	 */
	public String getGlobalSuccessFlag() {
		return globalSuccessFlag;
	}

	/**
	 * @param globalSuccessFlag the globalSuccessFlag to set
	 */
	public void setGlobalSuccessFlag(final String globalSuccessFlag) {
		this.globalSuccessFlag = globalSuccessFlag;
	}

	/**
	 * @return the bell
	 */
	public String getBell() {
		return bell;
	}

	/**
	 * @param bell the bell to set
	 */
	public void setBell(final String bell) {
		this.bell = bell;
	}

	/**
	 * @return the fifoFlag
	 */
	public String getFifoFlag() {
		return fifoFlag;
	}

	/**
	 * @param fifoFlag the fifoFlag to set
	 */
	public void setFifoFlag(final String fifoFlag) {
		this.fifoFlag = fifoFlag;
	}

}
