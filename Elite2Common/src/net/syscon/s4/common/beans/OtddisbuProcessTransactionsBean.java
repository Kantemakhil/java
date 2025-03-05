package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OtddisbuProcessTransactionsBean extends BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("pCaseloadId")
	private String pCaseloadId;
	@JsonProperty("pTxnType")
	private String pTxnType;
	@JsonProperty("pTxnDesc")
	private String pTxnDesc;
	@JsonProperty("pTxnUsage")
	private String pTxnUsage;
	@JsonProperty("pTxnId")
	private Integer pTxnId;
	@JsonProperty("pOffenderId")
	private Long pOffenderId;
	@JsonProperty("pTxnEntryAmount")
	private BigDecimal pTxnEntryAmount;
	@JsonProperty("pPayeePersonId")
	private Integer pPayeePersonId;
	@JsonProperty("pPayeeCorpId")
	private Integer pPayeeCorpId;
	@JsonProperty("pPayeeNameText")
	private String pPayeeNameText;
	@JsonProperty("pReceiptFlag")
	private String pReceiptFlag;
	@JsonProperty("pTxnRefNumber")
	private String pTxnRefNumber;
	@JsonProperty("pChequeProdFlag")
	private String pChequeProdFlag;
	@JsonProperty("pTxnEntrySeq")
	private Integer pTxnEntrySeq;
	@JsonProperty("pErrorMessage")
	private String pErrorMessage;
	@JsonProperty("pTxnFee")
	private BigDecimal pTxnFee;
	@JsonProperty("pReceiptNum")
	private String pReceiptNum;
	
	@JsonProperty("caseLoadType")
	private String caseLoadType;    

	@JsonProperty("pModuleName")
	private String pModuleName;
	
	@JsonProperty("txnPostType")
	private String txnPostType;    

	@JsonProperty("subActType")
	private String subActType;    
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId; 	


	public String getCaseLoadType() {
		return caseLoadType;
	}

	public void setCaseLoadType(String caseLoadType) {
		this.caseLoadType = caseLoadType;
	}

	public String getpModuleName() {
		return pModuleName;
	}

	public void setpModuleName(String pModuleName) {
		this.pModuleName = pModuleName;
	}

	public String getTxnPostType() {
		return txnPostType;
	}

	public void setTxnPostType(String txnPostType) {
		this.txnPostType = txnPostType;
	}

	public String getSubActType() {
		return subActType;
	}

	public void setSubActType(String subActType) {
		this.subActType = subActType;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public OtddisbuProcessTransactionsBean() {
//		OtddisbuProcessTransactionsBean
	}

	/**
	 * @return the pCaseloadId
	 */
	public String getpCaseloadId() {
		return pCaseloadId;
	}

	/**
	 * @param pCaseloadId the pCaseloadId to set
	 */
	public void setpCaseloadId(String pCaseloadId) {
		this.pCaseloadId = pCaseloadId;
	}

	/**
	 * @return the pTxnType
	 */
	public String getpTxnType() {
		return pTxnType;
	}

	/**
	 * @param pTxnType the pTxnType to set
	 */
	public void setpTxnType(String pTxnType) {
		this.pTxnType = pTxnType;
	}

	/**
	 * @return the pTxnDesc
	 */
	public String getpTxnDesc() {
		return pTxnDesc;
	}

	/**
	 * @param pTxnDesc the pTxnDesc to set
	 */
	public void setpTxnDesc(String pTxnDesc) {
		this.pTxnDesc = pTxnDesc;
	}

	/**
	 * @return the pTxnUsage
	 */
	public String getpTxnUsage() {
		return pTxnUsage;
	}

	/**
	 * @param pTxnUsage the pTxnUsage to set
	 */
	public void setpTxnUsage(String pTxnUsage) {
		this.pTxnUsage = pTxnUsage;
	}

	/**
	 * @return the pTxnId
	 */
	public Integer getpTxnId() {
		return pTxnId;
	}

	/**
	 * @param pTxnId the pTxnId to set
	 */
	public void setpTxnId(Integer pTxnId) {
		this.pTxnId = pTxnId;
	}

	/**
	 * @return the pOffenderId
	 */
	public Long getpOffenderId() {
		return pOffenderId;
	}

	/**
	 * @param pOffenderId the pOffenderId to set
	 */
	public void setpOffenderId(Long pOffenderId) {
		this.pOffenderId = pOffenderId;
	}

	/**
	 * @return the pTxnEntryAmount
	 */
	public BigDecimal getpTxnEntryAmount() {
		return pTxnEntryAmount;
	}

	/**
	 * @param pTxnEntryAmount the pTxnEntryAmount to set
	 */
	public void setpTxnEntryAmount(BigDecimal pTxnEntryAmount) {
		this.pTxnEntryAmount = pTxnEntryAmount;
	}

	/**
	 * @return the pPayeePersonId
	 */
	public Integer getpPayeePersonId() {
		return pPayeePersonId;
	}

	/**
	 * @param pPayeePersonId the pPayeePersonId to set
	 */
	public void setpPayeePersonId(Integer pPayeePersonId) {
		this.pPayeePersonId = pPayeePersonId;
	}

	/**
	 * @return the pPayeeCorpId
	 */
	public Integer getpPayeeCorpId() {
		return pPayeeCorpId;
	}

	/**
	 * @param pPayeeCorpId the pPayeeCorpId to set
	 */
	public void setpPayeeCorpId(Integer pPayeeCorpId) {
		this.pPayeeCorpId = pPayeeCorpId;
	}

	/**
	 * @return the pPayeeNameText
	 */
	public String getpPayeeNameText() {
		return pPayeeNameText;
	}

	/**
	 * @param pPayeeNameText the pPayeeNameText to set
	 */
	public void setpPayeeNameText(String pPayeeNameText) {
		this.pPayeeNameText = pPayeeNameText;
	}

	/**
	 * @return the pReceiptFlag
	 */
	public String getpReceiptFlag() {
		return pReceiptFlag;
	}

	/**
	 * @param pReceiptFlag the pReceiptFlag to set
	 */
	public void setpReceiptFlag(String pReceiptFlag) {
		this.pReceiptFlag = pReceiptFlag;
	}

	/**
	 * @return the pTxnRefNumber
	 */
	public String getpTxnRefNumber() {
		return pTxnRefNumber;
	}

	/**
	 * @param pTxnRefNumber the pTxnRefNumber to set
	 */
	public void setpTxnRefNumber(String pTxnRefNumber) {
		this.pTxnRefNumber = pTxnRefNumber;
	}

	/**
	 * @return the pChequeProdFlag
	 */
	public String getpChequeProdFlag() {
		return pChequeProdFlag;
	}

	/**
	 * @param pChequeProdFlag the pChequeProdFlag to set
	 */
	public void setpChequeProdFlag(String pChequeProdFlag) {
		this.pChequeProdFlag = pChequeProdFlag;
	}

	/**
	 * @return the pTxnEntrySeq
	 */
	public Integer getpTxnEntrySeq() {
		return pTxnEntrySeq;
	}

	/**
	 * @param pTxnEntrySeq the pTxnEntrySeq to set
	 */
	public void setpTxnEntrySeq(Integer pTxnEntrySeq) {
		this.pTxnEntrySeq = pTxnEntrySeq;
	}

	/**
	 * @return the pErrorMessage
	 */
	public String getpErrorMessage() {
		return pErrorMessage;
	}

	/**
	 * @param pErrorMessage the pErrorMessage to set
	 */
	public void setpErrorMessage(String pErrorMessage) {
		this.pErrorMessage = pErrorMessage;
	}

	/**
	 * @return the pTxnFee
	 */
	public BigDecimal getpTxnFee() {
		return pTxnFee;
	}

	/**
	 * @param pTxnFee the pTxnFee to set
	 */
	public void setpTxnFee(BigDecimal pTxnFee) {
		this.pTxnFee = pTxnFee;
	}

	/**
	 * @return the pReceiptNum
	 */
	public String getpReceiptNum() {
		return pReceiptNum;
	}

	/**
	 * @param pReceiptNum the pReceiptNum to set
	 */
	public void setpReceiptNum(String pReceiptNum) {
		this.pReceiptNum = pReceiptNum;
	}

}
