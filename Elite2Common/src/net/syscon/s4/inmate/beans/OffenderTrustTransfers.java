package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 * class OffenderTrustTransfers
 *
 */
public class OffenderTrustTransfers extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("txnId")
	private  BigDecimal  txnId;
	@JsonProperty("fromCaseload")
    private  String  fromCaseload;
	@JsonProperty("createUserId")
    private  String  createUserId;
	@JsonProperty("modifyUserId")
    private  String  modifyUserId;
	@JsonProperty("modifyDate")
    private  Date  modifyDate;
	@JsonProperty("modifyDateTime")
    private  Date  modifyDateTime;
	@JsonProperty("createDateTime")
    private  Date  createDateTime;
	@JsonProperty("sealFlag")
    private  String  sealFlag;
	@JsonProperty("amount")
    private  BigDecimal  amount;
	@JsonProperty("transferDate")
    private  Date  transferDate;
	@JsonProperty("toCaseload")
    private  String  toCaseload;
	@JsonProperty("postedFlag")
    private  String  postedFlag;
	
	@JsonProperty("dspDescription")
	private String dspDescription;
	
	@JsonProperty("checkNo")
	private Integer checkNo;
	
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("caseloadType")
	private String caseloadType;
	
	@JsonProperty("subAcntType")
	private String subAcntType;
	
	@JsonProperty("offenderId")
	private Long offenderId;
	
	@JsonProperty("acntClosedFlag")
	private String acntClosedFlag;
	
	@JsonProperty("sta")
	private String sta;
	
	@JsonProperty("txnEntrySeq")
	private Integer txnEntrySeq;
	
	@JsonProperty("txnUsage")
	private String txnUsage;	
	
	@JsonProperty("recdoFlag")
	private String recdoFlag;
	
	@JsonProperty("receiptNumber")
	private String receiptNumber;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("txnType")
	private String txnType;
	
	@JsonProperty("txndesc")
	private String txndesc;
	
	@JsonProperty("txnEntryAmnt")
	private Double txnEntryAmnt;
	
	@JsonProperty("txnEntryDate")
	private Date txnEntryDate;
	
		
	
	
	
	
	
	
	public OffenderTrustTransfers(){
	//	OffenderTrustTransfers
	}
	/**
	 * @return the txnId
	 */
	public BigDecimal getTxnId() {
		return txnId;
	}
	/**
	 * @param txnId the txnId to set
	 */
	public void setTxnId(final BigDecimal txnId) {
		this.txnId = txnId;
	}
	/**
	 * @return the fromCaseload
	 */
	public String getFromCaseload() {
		return fromCaseload;
	}
	/**
	 * @param fromCaseload the fromCaseload to set
	 */
	public void setFromCaseload(final String fromCaseload) {
		this.fromCaseload = fromCaseload;
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
	 * @return the transferDate
	 */
	public Date getTransferDate() {
		return transferDate;
	}
	/**
	 * @param transferDate the transferDate to set
	 */
	public void setTransferDate(final Date transferDate) {
		this.transferDate = transferDate;
	}
	/**
	 * @return the toCaseload
	 */
	public String getToCaseload() {
		return toCaseload;
	}
	/**
	 * @param toCaseload the toCaseload to set
	 */
	public void setToCaseload(final String toCaseload) {
		this.toCaseload = toCaseload;
	}
	/**
	 * @return the postedFlag
	 */
	public String getPostedFlag() {
		return postedFlag;
	}
	/**
	 * @param postedFlag the postedFlag to set
	 */
	public void setPostedFlag(final String postedFlag) {
		this.postedFlag = postedFlag;
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
	public void setDspDescription(String dspDescription) {
		this.dspDescription = dspDescription;
	}
	/**
	 * @return the checkNo
	 */
	public Integer getCheckNo() {
		return checkNo;
	}
	/**
	 * @param checkNo the checkNo to set
	 */
	public void setCheckNo(final Integer checkNo) {
		this.checkNo = checkNo;
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
	 * @return the subAcntType
	 */
	public String getSubAcntType() {
		return subAcntType;
	}
	/**
	 * @param subAcntType the subAcntType to set
	 */
	public void setSubAcntType(final String subAcntType) {
		this.subAcntType = subAcntType;
	}
	/**
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return offenderId;
	}
	/**
	 * @param offenderId the offenderId to set
	 */
	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}
	/**
	 * @return the acntClosedFlag
	 */
	public String getAcntClosedFlag() {
		return acntClosedFlag;
	}
	/**
	 * @param acntClosedFlag the acntClosedFlag to set
	 */
	public void setAcntClosedFlag(final String acntClosedFlag) {
		this.acntClosedFlag = acntClosedFlag;
	}
	/**
	 * @return the sta
	 */
	public String getSta() {
		return sta;
	}
	/**
	 * @param sta the sta to set
	 */
	public void setSta(final String sta) {
		this.sta = sta;
	}
	/**
	 * @return the txnEntrySeq
	 */
	public Integer getTxnEntrySeq() {
		return txnEntrySeq;
	}
	/**
	 * @param txnEntrySeq the txnEntrySeq to set
	 */
	public void setTxnEntrySeq(final Integer txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}
	/**
	 * @return the txnUsage
	 */
	public String getTxnUsage() {
		return txnUsage;
	}
	/**
	 * @param txnUsage the txnUsage to set
	 */
	public void setTxnUsage(String txnUsage) {
		this.txnUsage = txnUsage;
	}
	/**
	 * @return the recdoFlag
	 */
	public String getRecdoFlag() {
		return recdoFlag;
	}
	/**
	 * @param recdoFlag the recdoFlag to set
	 */
	public void setRecdoFlag(String recdoFlag) {
		this.recdoFlag = recdoFlag;
	}
	/**
	 * @return the receiptNumber
	 */
	public String getReceiptNumber() {
		return receiptNumber;
	}
	/**
	 * @param receiptNumber the receiptNumber to set
	 */
	public void setReceiptNumber(final String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}
	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}
	/**
	 * @param txnType the txnType to set
	 */
	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}
	/**
	 * @return the txndesc
	 */
	public String getTxndesc() {
		return txndesc;
	}
	/**
	 * @param txndesc the txndesc to set
	 */
	public void setTxndesc(final String txndesc) {
		this.txndesc = txndesc;
	}
	/**
	 * @return the txnEntryAmnt
	 */
	public Double getTxnEntryAmnt() {
		return txnEntryAmnt;
	}
	/**
	 * @param txnEntryAmnt the txnEntryAmnt to set
	 */
	public void setTxnEntryAmnt(Double txnEntryAmnt) {
		this.txnEntryAmnt = txnEntryAmnt;
	}
	/**
	 * @return the txnEntryDate
	 */
	public Date getTxnEntryDate() {
		return txnEntryDate;
	}
	/**
	 * @param txnEntryDate the txnEntryDate to set
	 */
	public void setTxnEntryDate(Date txnEntryDate) {
		this.txnEntryDate = txnEntryDate;
	}
    
    
    


}
