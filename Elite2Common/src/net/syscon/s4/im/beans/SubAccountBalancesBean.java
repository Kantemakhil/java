package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

/**
 * The persistent class for the CORPORATE_TYPES database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class SubAccountBalancesBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("fAccountName")
	private String fAccountName;
	@JsonProperty("fOpeningBalance")
	private BigDecimal fOpeningBalance;
	@JsonProperty("fClosigBalance")
	private BigDecimal fClosigBalance;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("fType")
	private String fType;
	@JsonProperty("fPayable")
	private String fPayable;
	@JsonProperty("fInfoNumber")
	private String fInfoNumber;
	@JsonProperty("fAmount")
	private BigDecimal fAmount;
	@JsonProperty("fAmtPaid")
	private BigDecimal fAmtPaid;
	@JsonProperty("fWriteOff")
	private BigDecimal fWriteOff;
	@JsonProperty("fSubAcctNameOne")
	private String fSubAcctNameOne;
	@JsonProperty("fBookLabel")
	private String fBookLabel;
	@JsonProperty("fTxnEnt")
	private Date fTxnEnt;
	@JsonProperty("fBookNumberOne")
	private String fBookNumberOne;
	@JsonProperty("fTxnType")
	private String fTxnType;
	@JsonProperty("fTxnEntryDesc")
	private String fTxnEntryDesc;
	@JsonProperty("fTxnEntryAmount")
	private BigDecimal fTxnEntryAmount;
	@JsonProperty("fBalance")
	private BigDecimal fBalance;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("cfCur")
	private String cfCur;
	@JsonProperty("subAccountType")
	private String subAccountType;
	@JsonProperty("maxMonthlyAmount")
	private BigDecimal maxMonthlyAmount;
	@JsonProperty("maxTotalAmount")
	private BigDecimal maxTotalAmount;
	@JsonProperty("fAmountOne")
	private String fAmountOne;
	@JsonProperty("adjustmentReasonCode")
	private String adjustmentReasonCode;
	@JsonProperty("offenderDeductionId")
	private Long offenderDeductionId;
	@JsonProperty("fTxnId")
	private BigDecimal fTxnId;
	@JsonProperty("fTxnEntrySeq")
	private BigDecimal fTxnEntrySeq;

	/**
	 * @return the fTxnEntrySeq
	 */
	public BigDecimal getfTxnEntrySeq() {
		return fTxnEntrySeq;
	}

	/**
	 * @param fTxnEntrySeq the fTxnEntrySeq to set
	 */
	public void setfTxnEntrySeq(BigDecimal fTxnEntrySeq) {
		this.fTxnEntrySeq = fTxnEntrySeq;
	}

	/**
	 * @return the fTxnId
	 */
	public BigDecimal getfTxnId() {
		return fTxnId;
	}

	/**
	 * @param fTxnId the fTxnId to set
	 */
	public void setfTxnId(BigDecimal fTxnId) {
		this.fTxnId = fTxnId;
	}

	/**
	 * @return the subAccountType
	 */
	public String getSubAccountType() {
		return subAccountType;
	}

	/**
	 * @return the adjustmentReasonCode
	 */
	public String getAdjustmentReasonCode() {
		return adjustmentReasonCode;
	}

	/**
	 * @param adjustmentReasonCode
	 *            the adjustmentReasonCode to set
	 */
	public void setAdjustmentReasonCode(final String adjustmentReasonCode) {
		this.adjustmentReasonCode = adjustmentReasonCode;
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
	 * @return the fAmountOne
	 */
	public String getfAmountOne() {
		return fAmountOne;
	}

	/**
	 * @param fAmountOne
	 *            the fAmountOne to set
	 */
	public void setfAmountOne(final String fAmountOne) {
		this.fAmountOne = fAmountOne;
	}

	/**
	 * @param subAccountType
	 *            the subAccountType to set
	 */
	public void setSubAccountType(final String subAccountType) {
		this.subAccountType = subAccountType;
	}

	public SubAccountBalancesBean() {
		// SubAccountBalancesBean
	}

	/**
	 * @return the fAccountName
	 */
	public String getfAccountName() {
		return fAccountName;
	}

	/**
	 * @param fAccountName
	 *            the fAccountName to set
	 */
	public void setfAccountName(final String fAccountName) {
		this.fAccountName = fAccountName;
	}

	/**
	 * @return the fOpeningBalance
	 */
	public BigDecimal getfOpeningBalance() {
		return fOpeningBalance;
	}

	/**
	 * @param fOpeningBalance
	 *            the fOpeningBalance to set
	 */
	public void setfOpeningBalance(final BigDecimal fOpeningBalance) {
		this.fOpeningBalance = fOpeningBalance;
	}

	/**
	 * @return the fClosigBalance
	 */
	public BigDecimal getfClosigBalance() {
		return fClosigBalance;
	}

	/**
	 * @param fClosigBalance
	 *            the fClosigBalance to set
	 */
	public void setfClosigBalance(final BigDecimal fClosigBalance) {
		this.fClosigBalance = fClosigBalance;
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
	 * @return the fType
	 */
	public String getfType() {
		return fType;
	}

	/**
	 * @param fType
	 *            the fType to set
	 */
	public void setfType(final String fType) {
		this.fType = fType;
	}

	/**
	 * @return the fPayable
	 */
	public String getfPayable() {
		return fPayable;
	}

	/**
	 * @param fPayable
	 *            the fPayable to set
	 */
	public void setfPayable(final String fPayable) {
		this.fPayable = fPayable;
	}

	/**
	 * @return the fInfoNumber
	 */
	public String getfInfoNumber() {
		return fInfoNumber;
	}

	/**
	 * @param fInfoNumber
	 *            the fInfoNumber to set
	 */
	public void setfInfoNumber(final String fInfoNumber) {
		this.fInfoNumber = fInfoNumber;
	}

	/**
	 * @return the fAmount
	 */
	public BigDecimal getfAmount() {
		return fAmount;
	}

	/**
	 * @param fAmount
	 *            the fAmount to set
	 */
	public void setfAmount(final BigDecimal fAmount) {
		this.fAmount = fAmount;
	}

	/**
	 * @return the fAmtPaid
	 */
	public BigDecimal getfAmtPaid() {
		return fAmtPaid;
	}

	/**
	 * @param fAmtPaid
	 *            the fAmtPaid to set
	 */
	public void setfAmtPaid(final BigDecimal fAmtPaid) {
		this.fAmtPaid = fAmtPaid;
	}

	/**
	 * @return the fWriteOff
	 */
	public BigDecimal getfWriteOff() {
		return fWriteOff;
	}

	/**
	 * @param fWriteOff
	 *            the fWriteOff to set
	 */
	public void setfWriteOff(final BigDecimal fWriteOff) {
		this.fWriteOff = fWriteOff;
	}

	/**
	 * @return the fSubAcctNameOne
	 */
	public String getfSubAcctNameOne() {
		return fSubAcctNameOne;
	}

	/**
	 * @param fSubAcctNameOne
	 *            the fSubAcctNameOne to set
	 */
	public void setfSubAcctNameOne(final String fSubAcctNameOne) {
		this.fSubAcctNameOne = fSubAcctNameOne;
	}

	/**
	 * @return the fBookLabel
	 */
	public String getfBookLabel() {
		return fBookLabel;
	}

	/**
	 * @param fBookLabel
	 *            the fBookLabel to set
	 */
	public void setfBookLabel(final String fBookLabel) {
		this.fBookLabel = fBookLabel;
	}

	/**
	 * @return the fTxnEnt
	 */
	public Date getfTxnEnt() {
		return fTxnEnt;
	}

	/**
	 * @param fTxnEnt
	 *            the fTxnEnt to set
	 */
	public void setfTxnEnt(final Date fTxnEnt) {
		this.fTxnEnt = fTxnEnt;
	}

	/**
	 * @return the fBookNumberOne
	 */
	public String getfBookNumberOne() {
		return fBookNumberOne;
	}

	/**
	 * @param fBookNumberOne
	 *            the fBookNumberOne to set
	 */
	public void setfBookNumberOne(final String fBookNumberOne) {
		this.fBookNumberOne = fBookNumberOne;
	}

	/**
	 * @return the fTxnType
	 */
	public String getfTxnType() {
		return fTxnType;
	}

	/**
	 * @param fTxnType
	 *            the fTxnType to set
	 */
	public void setfTxnType(final String fTxnType) {
		this.fTxnType = fTxnType;
	}

	/**
	 * @return the fTxnEntryDesc
	 */
	public String getfTxnEntryDesc() {
		return fTxnEntryDesc;
	}

	/**
	 * @param fTxnEntryDesc
	 *            the fTxnEntryDesc to set
	 */
	public void setfTxnEntryDesc(final String fTxnEntryDesc) {
		this.fTxnEntryDesc = fTxnEntryDesc;
	}

	/**
	 * @return the fTxnEntryAmount
	 */
	public BigDecimal getfTxnEntryAmount() {
		return fTxnEntryAmount;
	}

	/**
	 * @param fTxnEntryAmount
	 *            the fTxnEntryAmount to set
	 */
	public void setfTxnEntryAmount(final BigDecimal fTxnEntryAmount) {
		this.fTxnEntryAmount = fTxnEntryAmount;
	}

	/**
	 * @return the fBalance
	 */
	public BigDecimal getfBalance() {
		return fBalance;
	}

	/**
	 * @param fBalance
	 *            the fBalance to set
	 */
	public void setfBalance(final BigDecimal fBalance) {
		this.fBalance = fBalance;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the cfCur
	 */
	public String getCfCur() {
		return cfCur;
	}

	/**
	 * @param cfCur
	 *            the cfCur to set
	 */
	public void setCfCur(final String cfCur) {
		this.cfCur = cfCur;
	}

	/**
	 * @return the maxMonthlyAmount
	 */
	public BigDecimal getMaxMonthlyAmount() {
		return maxMonthlyAmount;
	}

	/**
	 * @param maxMonthlyAmount
	 *            the maxMonthlyAmount to set
	 */
	public void setMaxMonthlyAmount(final BigDecimal maxMonthlyAmount) {
		this.maxMonthlyAmount = maxMonthlyAmount;
	}

	/**
	 * @return the maxTotalAmount
	 */
	public BigDecimal getMaxTotalAmount() {
		return maxTotalAmount;
	}

	/**
	 * @param maxTotalAmount
	 *            the maxTotalAmount to set
	 */
	public void setMaxTotalAmount(final BigDecimal maxTotalAmount) {
		this.maxTotalAmount = maxTotalAmount;
	}

}