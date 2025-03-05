package net.syscon.s4.common;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class InsertGlTransNew  extends BaseModel  implements Serializable{
	
	@JsonProperty("pPostType")
	private String pPostType;
	@JsonProperty("pAccountCode")
	private Long pAccountCode;
	@JsonProperty("pAcntPosting")
	private String pAcntPosting;
	@JsonProperty("pCsldId")
	private String pCsldId;
	@JsonProperty("pTransType")
	private String pTransType;
	@JsonProperty("pTransAmount")
	private Double pTransAmount;
	@JsonProperty("pTransNumber")
	private Long pTransNumber;
	@JsonProperty("pTransDate")
	private Date pTransDate;
	@JsonProperty("pTransDesc")
	private String pTransDesc;
	@JsonProperty("pTransSeq")
	private Integer pTransSeq;
	@JsonProperty("pGlSqnc")
	private Integer pGlSqnc;
	@JsonProperty("pOffId")
	private Long pOffId;
	@JsonProperty("pOffBookId")
	private Long pOffBookId;
	@JsonProperty("pInfoNumber")
	private String pInfoNumber;
	@JsonProperty("pPayeePersonId")
	private Long pPayeePersonId;
	@JsonProperty("pPayeeCorporateId")
	private Long pPayeeCorporateId;
	@JsonProperty("pPayeeNameText")
	private String pPayeeNameText;
	@JsonProperty("pRevrTxnId")
	private Integer pRevrTxnId;
	@JsonProperty("pRevrTxnEntrySeq")
	private Integer pRevrTxnEntrySeq;
	@JsonProperty("pRevrGlEntrySeq")
	private Integer pRevrGlEntrySeq;
	@JsonProperty("pTxnRefNumber")
	private String pTxnRefNumber;
	@JsonProperty("pOffDedId")
	private Long pOffDedId;

	public InsertGlTransNew() {
//		InsertGlTransNew
	}

	/**
	 * @return the pPostType
	 */
	public String getpPostType() {
		return pPostType;
	}

	/**
	 * @param pPostType the pPostType to set
	 */
	public void setpPostType(final String pPostType) {
		this.pPostType = pPostType;
	}

	/**
	 * @return the pAccountCode
	 */
	public Long getpAccountCode() {
		return pAccountCode;
	}

	/**
	 * @param pAccountCode the pAccountCode to set
	 */
	public void setpAccountCode(final Long pAccountCode) {
		this.pAccountCode = pAccountCode;
	}

	/**
	 * @return the pAcntPosting
	 */
	public String getpAcntPosting() {
		return pAcntPosting;
	}

	/**
	 * @param pAcntPosting the pAcntPosting to set
	 */
	public void setpAcntPosting(final String pAcntPosting) {
		this.pAcntPosting = pAcntPosting;
	}

	/**
	 * @return the pCsldId
	 */
	public String getpCsldId() {
		return pCsldId;
	}

	/**
	 * @param pCsldId the pCsldId to set
	 */
	public void setpCsldId(final String pCsldId) {
		this.pCsldId = pCsldId;
	}

	/**
	 * @return the pTransType
	 */
	public String getpTransType() {
		return pTransType;
	}

	/**
	 * @param pTransType the pTransType to set
	 */
	public void setpTransType(final String pTransType) {
		this.pTransType = pTransType;
	}

	/**
	 * @return the pTransAmount
	 */
	public Double getpTransAmount() {
		return pTransAmount;
	}

	/**
	 * @param pTransAmount the pTransAmount to set
	 */
	public void setpTransAmount(final Double pTransAmount) {
		this.pTransAmount = pTransAmount;
	}

	/**
	 * @return the pTransNumber
	 */
	public Long getpTransNumber() {
		return pTransNumber;
	}

	/**
	 * @param pTransNumber the pTransNumber to set
	 */
	public void setpTransNumber(final Long pTransNumber) {
		this.pTransNumber = pTransNumber;
	}

	/**
	 * @return the pTransDate
	 */
	public Date getpTransDate() {
		return pTransDate;
	}

	/**
	 * @param pTransDate the pTransDate to set
	 */
	public void setpTransDate(final Date pTransDate) {
		this.pTransDate = pTransDate;
	}

	/**
	 * @return the pTransDesc
	 */
	public String getpTransDesc() {
		return pTransDesc;
	}

	/**
	 * @param pTransDesc the pTransDesc to set
	 */
	public void setpTransDesc(final String pTransDesc) {
		this.pTransDesc = pTransDesc;
	}

	/**
	 * @return the pTransSeq
	 */
	public Integer getpTransSeq() {
		return pTransSeq;
	}

	/**
	 * @param pTransSeq the pTransSeq to set
	 */
	public void setpTransSeq(final Integer pTransSeq) {
		this.pTransSeq = pTransSeq;
	}

	/**
	 * @return the pGlSqnc
	 */
	public Integer getpGlSqnc() {
		return pGlSqnc;
	}

	/**
	 * @param pGlSqnc the pGlSqnc to set
	 */
	public void setpGlSqnc(final Integer pGlSqnc) {
		this.pGlSqnc = pGlSqnc;
	}

	/**
	 * @return the pOffId
	 */
	public Long getpOffId() {
		return pOffId;
	}

	/**
	 * @param pOffId the pOffId to set
	 */
	public void setpOffId(final Long pOffId) {
		this.pOffId = pOffId;
	}

	/**
	 * @return the pOffBookId
	 */
	public Long getpOffBookId() {
		return pOffBookId;
	}

	/**
	 * @param pOffBookId the pOffBookId to set
	 */
	public void setpOffBookId(final Long pOffBookId) {
		this.pOffBookId = pOffBookId;
	}

	/**
	 * @return the pInfoNumber
	 */
	public String getpInfoNumber() {
		return pInfoNumber;
	}

	/**
	 * @param pInfoNumber the pInfoNumber to set
	 */
	public void setpInfoNumber(final String pInfoNumber) {
		this.pInfoNumber = pInfoNumber;
	}

	/**
	 * @return the pPayeePersonId
	 */
	public Long getpPayeePersonId() {
		return pPayeePersonId;
	}

	/**
	 * @param pPayeePersonId the pPayeePersonId to set
	 */
	public void setpPayeePersonId(final Long pPayeePersonId) {
		this.pPayeePersonId = pPayeePersonId;
	}

	/**
	 * @return the pPayeeCorporateId
	 */
	public Long getpPayeeCorporateId() {
		return pPayeeCorporateId;
	}

	/**
	 * @param pPayeeCorporateId the pPayeeCorporateId to set
	 */
	public void setpPayeeCorporateId(final Long pPayeeCorporateId) {
		this.pPayeeCorporateId = pPayeeCorporateId;
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
	public void setpPayeeNameText(final String pPayeeNameText) {
		this.pPayeeNameText = pPayeeNameText;
	}

	/**
	 * @return the pRevrTxnId
	 */
	public Integer getpRevrTxnId() {
		return pRevrTxnId;
	}

	/**
	 * @param pRevrTxnId the pRevrTxnId to set
	 */
	public void setpRevrTxnId(final Integer pRevrTxnId) {
		this.pRevrTxnId = pRevrTxnId;
	}

	/**
	 * @return the pRevrTxnEntrySeq
	 */
	public Integer getpRevrTxnEntrySeq() {
		return pRevrTxnEntrySeq;
	}

	/**
	 * @param pRevrTxnEntrySeq the pRevrTxnEntrySeq to set
	 */
	public void setpRevrTxnEntrySeq(final Integer pRevrTxnEntrySeq) {
		this.pRevrTxnEntrySeq = pRevrTxnEntrySeq;
	}

	/**
	 * @return the pRevrGlEntrySeq
	 */
	public Integer getpRevrGlEntrySeq() {
		return pRevrGlEntrySeq;
	}

	/**
	 * @param pRevrGlEntrySeq the pRevrGlEntrySeq to set
	 */
	public void setpRevrGlEntrySeq(final Integer pRevrGlEntrySeq) {
		this.pRevrGlEntrySeq = pRevrGlEntrySeq;
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
	public void setpTxnRefNumber(final String pTxnRefNumber) {
		this.pTxnRefNumber = pTxnRefNumber;
	}

	/**
	 * @return the pOffDedId
	 */
	public Long getpOffDedId() {
		return pOffDedId;
	}

	/**
	 * @param pOffDedId the pOffDedId to set
	 */
	public void setpOffDedId(final Long pOffDedId) {
		this.pOffDedId = pOffDedId;
	}

}
