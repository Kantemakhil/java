package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.cf.deductions.beans.FeeAccountBalanceBean;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

/**
 * The persistent class for the CORPORATE_TYPES database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class TransStatementBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("fDateOne")
	private Date fDateOne;
	@JsonProperty("fReportHeaderLabelName")
	private String fReportHeaderLabelName;
	@JsonProperty("fPageOne")
	private Long fPageOne;
	@JsonProperty("fTotOnw")
	private Long fTotOnw;
	@JsonProperty("fUserOne")
	private String fUserOne;
	@JsonProperty("fCaseloadNameOne")
	private String fCaseloadNameOne;
	@JsonProperty("fDoc")
	private String fDoc;
	@JsonProperty("fOffenderId")
	private String fOffenderId;
	@JsonProperty("fName")
	private String fName;
	@JsonProperty("fDob")
	private String fDob;
	@JsonProperty("fBirthDate")
	private Date fBirthDate;
	@JsonProperty("fLoc")
	private String fLoc;
	@JsonProperty("fLivingUnitDescription")
	private String fLivingUnitDescription;
	@JsonProperty("fTotal")
	private BigDecimal fTotal;
	@JsonProperty("fCurrentBalance")
	private BigDecimal fCurrentBalance;
	@JsonProperty("fHoldBalance")
	private BigDecimal fHoldBalance;
	@JsonProperty("cfCur")
	private String cfCur;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("caseloadType")
	private String caseloadType;
	@JsonProperty("fStartEndDate")
	private String fStartEndDate;
	@JsonProperty("beginDate")
	private Date beginDate;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("offenderIdOne")
	private BigDecimal offenderIdOne;
	@JsonProperty("livingUnit")
	private BigDecimal livingUnit;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("maxDate")
	private Date maxDate;
	@JsonProperty("batchId")
	private String batchId;
	@JsonProperty("fOffenderIdOne")
	private String fOffenderIdOne;
	@JsonProperty("subAccountType")
	private String subAccountType;
	@JsonProperty("disclosureFlag")
	private String disclosureFlag;

	private List<SubAccountBalancesBean> subAccountBalancesBean;
	private List<SubAccountBalancesBean> debsObligationsBean;
	private List<SubAccountBalancesBean> subAccountTransactionsBean;
	private List<SubAccountBalancesBean> regAccountTransactionsBean;
	private List<SubAccountBalancesBean> savAccountTransactionsBean;
	
	@JsonProperty("fReceiptNumber")
	private String fReceiptNumber;
	@JsonProperty("caselodHeaderLabelName")
	private String caselodHeaderLabelName;
	@JsonProperty("fAddressOne")
	private String fAddressOne;
	@JsonProperty("fAddressTwo")
	private String fAddressTwo;
	@JsonProperty("fPhoneNumber")
	private String fPhoneNumber;
	@JsonProperty("fClientName")
	private String fClientName;
	@JsonProperty("fPoName")
	private String fPoName;
	@JsonProperty("fClientAddress")
	private String fClientAddress;
	@JsonProperty("fRemitter")
	private String fRemitter;
	@JsonProperty("fSidNumber")
	private Long fSidNumber;
	@JsonProperty("fPaymentType")
	private String fPaymentType;
	@JsonProperty("fDateOfBirth")
	private String fDateOfBirth;
	@JsonProperty("fEndingDate")
	private Date fEndingDate;
	@JsonProperty("fPaymentNumber")
	private String fPaymentNumber;
	@JsonProperty("feeAccountBalanceBean")
	private List<FeeAccountBalanceBean> feeAccountBalanceBean;
	
	@JsonProperty("paymantCreditAccount")
	private List<FeeAccountBalanceBean> paymantCreditAccount;
	
	@JsonProperty("billingAccount")
	private List<FeeAccountBalanceBean> billingAccount;
	
	@JsonProperty("fBlncBeforePaymentTot")
	private BigDecimal fBlncBeforePaymentTot;
	
	@JsonProperty("fPaymentAmountTot")
	private BigDecimal fPaymentAmountTot;
	
	@JsonProperty("fPaymentAfterAmountTot")
	private BigDecimal fPaymentAfterAmountTot;
	
	@JsonProperty("paymentSumAmount")
	private BigDecimal paymentSumAmount;
	
	@JsonProperty("billingSumAmount")
	private BigDecimal billingSumAmount;

	public TransStatementBean() {
		// TransStatementBean
	}

	/**
	 * @return the regAccountTransactionsBean
	 */
	public List<SubAccountBalancesBean> getRegAccountTransactionsBean() {
		return regAccountTransactionsBean;
	}

	/**
	 * @param regAccountTransactionsBean the regAccountTransactionsBean to set
	 */
	public void setRegAccountTransactionsBean(final List<SubAccountBalancesBean> regAccountTransactionsBean) {
		this.regAccountTransactionsBean = regAccountTransactionsBean;
	}

	/**
	 * @return the savAccountTransactionsBean
	 */
	public List<SubAccountBalancesBean> getSavAccountTransactionsBean() {
		return savAccountTransactionsBean;
	}

	/**
	 * @param savAccountTransactionsBean the savAccountTransactionsBean to set
	 */
	public void setSavAccountTransactionsBean(final List<SubAccountBalancesBean> savAccountTransactionsBean) {
		this.savAccountTransactionsBean = savAccountTransactionsBean;
	}

	/**
	 * @return the fDateOne
	 */
	public Date getfDateOne() {
		return fDateOne;
	}

	/**
	 * @param fDateOne
	 *            the fDateOne to set
	 */
	public void setfDateOne(final Date fDateOne) {
		this.fDateOne = fDateOne;
	}

	/**
	 * @return the fReportHeaderLabelName
	 */
	public String getfReportHeaderLabelName() {
		return fReportHeaderLabelName;
	}

	/**
	 * @param fReportHeaderLabelName
	 *            the fReportHeaderLabelName to set
	 */
	public void setfReportHeaderLabelName(final String fReportHeaderLabelName) {
		this.fReportHeaderLabelName = fReportHeaderLabelName;
	}

	/**
	 * @return the fPageOne
	 */
	public Long getfPageOne() {
		return fPageOne;
	}

	/**
	 * @param fPageOne
	 *            the fPageOne to set
	 */
	public void setfPageOne(final Long fPageOne) {
		this.fPageOne = fPageOne;
	}

	/**
	 * @return the fTotOnw
	 */
	public Long getfTotOnw() {
		return fTotOnw;
	}

	/**
	 * @param fTotOnw
	 *            the fTotOnw to set
	 */
	public void setfTotOnw(final Long fTotOnw) {
		this.fTotOnw = fTotOnw;
	}


	/**
	 * @return the fCaseloadNameOne
	 */
	public String getfCaseloadNameOne() {
		return fCaseloadNameOne;
	}

	/**
	 * @param fCaseloadNameOne
	 *            the fCaseloadNameOne to set
	 */
	public void setfCaseloadNameOne(final String fCaseloadNameOne) {
		this.fCaseloadNameOne = fCaseloadNameOne;
	}

	/**
	 * @return the fDoc
	 */
	public String getfDoc() {
		return fDoc;
	}

	/**
	 * @param fDoc
	 *            the fDoc to set
	 */
	public void setfDoc(final String fDoc) {
		this.fDoc = fDoc;
	}

	/**
	 * @return the fOffenderId
	 */
	public String getfOffenderId() {
		return fOffenderId;
	}

	/**
	 * @param fOffenderId
	 *            the fOffenderId to set
	 */
	public void setfOffenderId(final String fOffenderId) {
		this.fOffenderId = fOffenderId;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName
	 *            the fName to set
	 */
	public void setfName(final String fName) {
		this.fName = fName;
	}

	/**
	 * @return the fDob
	 */
	public String getfDob() {
		return fDob;
	}

	/**
	 * @param fDob
	 *            the fDob to set
	 */
	public void setfDob(final String fDob) {
		this.fDob = fDob;
	}

	/**
	 * @return the fBirthDate
	 */
	public Date getfBirthDate() {
		return fBirthDate;
	}

	/**
	 * @param fBirthDate
	 *            the fBirthDate to set
	 */
	public void setfBirthDate(final Date fBirthDate) {
		this.fBirthDate = fBirthDate;
	}

	/**
	 * @return the fLoc
	 */
	public String getfLoc() {
		return fLoc;
	}

	/**
	 * @param fLoc
	 *            the fLoc to set
	 */
	public void setfLoc(final String fLoc) {
		this.fLoc = fLoc;
	}

	/**
	 * @return the fLivingUnitDescription
	 */
	public String getfLivingUnitDescription() {
		return fLivingUnitDescription;
	}

	/**
	 * @param fLivingUnitDescription
	 *            the fLivingUnitDescription to set
	 */
	public void setfLivingUnitDescription(final String fLivingUnitDescription) {
		this.fLivingUnitDescription = fLivingUnitDescription;
	}

	/**
	 * @return the fTotal
	 */
	public BigDecimal getfTotal() {
		return fTotal;
	}

	/**
	 * @param fTotal
	 *            the fTotal to set
	 */
	public void setfTotal(final BigDecimal fTotal) {
		this.fTotal = fTotal;
	}

	/**
	 * @return the fCurrentBalance
	 */
	public BigDecimal getfCurrentBalance() {
		return fCurrentBalance;
	}

	/**
	 * @param fCurrentBalance
	 *            the fCurrentBalance to set
	 */
	public void setfCurrentBalance(final BigDecimal fCurrentBalance) {
		this.fCurrentBalance = fCurrentBalance;
	}

	/**
	 * @return the fHoldBalance
	 */
	public BigDecimal getfHoldBalance() {
		return fHoldBalance;
	}

	/**
	 * @param fHoldBalance
	 *            the fHoldBalance to set
	 */
	public void setfHoldBalance(final BigDecimal fHoldBalance) {
		this.fHoldBalance = fHoldBalance;
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
	 * @return the subAccountBalancesBean
	 */
	public List<SubAccountBalancesBean> getSubAccountBalancesBean() {
		return subAccountBalancesBean;
	}

	/**
	 * @param subAccountBalancesBean
	 *            the subAccountBalancesBean to set
	 */
	public void setSubAccountBalancesBean(final List<SubAccountBalancesBean> subAccountBalancesBean) {
		this.subAccountBalancesBean = subAccountBalancesBean;
	}

	/**
	 * @return the debsObligationsBean
	 */
	public List<SubAccountBalancesBean> getDebsObligationsBean() {
		return debsObligationsBean;
	}

	/**
	 * @param debsObligationsBean
	 *            the debsObligationsBean to set
	 */
	public void setDebsObligationsBean(final List<SubAccountBalancesBean> debsObligationsBean) {
		this.debsObligationsBean = debsObligationsBean;
	}

	/**
	 * @return the subAccountTransactionsBean
	 */
	public List<SubAccountBalancesBean> getSubAccountTransactionsBean() {
		return subAccountTransactionsBean;
	}

	/**
	 * @param subAccountTransactionsBean
	 *            the subAccountTransactionsBean to set
	 */
	public void setSubAccountTransactionsBean(final List<SubAccountBalancesBean> subAccountTransactionsBean) {
		this.subAccountTransactionsBean = subAccountTransactionsBean;
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
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType
	 *            the caseloadType to set
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 * @return the fStartEndDate
	 */
	public String getfStartEndDate() {
		return fStartEndDate;
	}

	/**
	 * @param fStartEndDate
	 *            the fStartEndDate to set
	 */
	public void setfStartEndDate(final String fStartEndDate) {
		this.fStartEndDate = fStartEndDate;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate
	 *            the beginDate to set
	 */
	public void setBeginDate(final Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the offenderIdOne
	 */
	public BigDecimal getOffenderIdOne() {
		return offenderIdOne;
	}

	/**
	 * @param offenderIdOne
	 *            the offenderIdOne to set
	 */
	public void setOffenderIdOne(final BigDecimal offenderIdOne) {
		this.offenderIdOne = offenderIdOne;
	}

	/**
	 * @return the livingUnit
	 */
	public BigDecimal getLivingUnit() {
		return livingUnit;
	}

	/**
	 * @param livingUnit
	 *            the livingUnit to set
	 */
	public void setLivingUnit(final BigDecimal livingUnit) {
		this.livingUnit = livingUnit;
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
	 * @return the maxDate
	 */
	public Date getMaxDate() {
		return maxDate;
	}

	/**
	 * @param maxDate
	 *            the maxDate to set
	 */
	public void setMaxDate(final Date maxDate) {
		this.maxDate = maxDate;
	}

	/**
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * @param batchId
	 *            the batchId to set
	 */
	public void setBatchId(final String batchId) {
		this.batchId = batchId;
	}

	/**
	 * @return the fOffenderIdOne
	 */
	public String getfOffenderIdOne() {
		return fOffenderIdOne;
	}

	/**
	 * @param fOffenderIdOne
	 *            the fOffenderIdOne to set
	 */
	public void setfOffenderIdOne(final String fOffenderIdOne) {
		this.fOffenderIdOne = fOffenderIdOne;
	}

	/**
	 * @return the subAccountType
	 */
	public String getSubAccountType() {
		return subAccountType;
	}

	/**
	 * @param subAccountType
	 *            the subAccountType to set
	 */
	public void setSubAccountType(final String subAccountType) {
		this.subAccountType = subAccountType;
	}

	/**
	 * @return the disclosureFlag
	 */
	public String getDisclosureFlag() {
		return disclosureFlag;
	}

	/**
	 * @param disclosureFlag
	 *            the disclosureFlag to set
	 */
	public void setDisclosureFlag(final String disclosureFlag) {
		this.disclosureFlag = disclosureFlag;
	}
	
	public String getfUserOne() {
		return fUserOne;
	}

	public void setfUserOne(String fUserOne) {
		this.fUserOne = fUserOne;
	}

	public String getfReceiptNumber() {
		return fReceiptNumber;
	}

	public void setfReceiptNumber(String fReceiptNumber) {
		this.fReceiptNumber = fReceiptNumber;
	}

	public String getCaselodHeaderLabelName() {
		return caselodHeaderLabelName;
	}

	public void setCaselodHeaderLabelName(String caselodHeaderLabelName) {
		this.caselodHeaderLabelName = caselodHeaderLabelName;
	}

	public String getfAddressOne() {
		return fAddressOne;
	}

	public void setfAddressOne(String fAddressOne) {
		this.fAddressOne = fAddressOne;
	}

	public String getfAddressTwo() {
		return fAddressTwo;
	}

	public void setfAddressTwo(String fAddressTwo) {
		this.fAddressTwo = fAddressTwo;
	}

	public String getfPhoneNumber() {
		return fPhoneNumber;
	}

	public void setfPhoneNumber(String fPhoneNumber) {
		this.fPhoneNumber = fPhoneNumber;
	}

	public String getfClientName() {
		return fClientName;
	}

	public void setfClientName(String fClientName) {
		this.fClientName = fClientName;
	}

	public String getfPoName() {
		return fPoName;
	}

	public void setfPoName(String fPoName) {
		this.fPoName = fPoName;
	}

	public String getfClientAddress() {
		return fClientAddress;
	}

	public void setfClientAddress(String fClientAddress) {
		this.fClientAddress = fClientAddress;
	}

	public String getfRemitter() {
		return fRemitter;
	}

	public void setfRemitter(String fRemitter) {
		this.fRemitter = fRemitter;
	}

	public Long getfSidNumber() {
		return fSidNumber;
	}

	public void setfSidNumber(Long fSidNumber) {
		this.fSidNumber = fSidNumber;
	}

	public String getfPaymentType() {
		return fPaymentType;
	}

	public void setfPaymentType(String fPaymentType) {
		this.fPaymentType = fPaymentType;
	}

	public String getfDateOfBirth() {
		return fDateOfBirth;
	}

	public void setfDateOfBirth(String fDateOfBirth) {
		this.fDateOfBirth = fDateOfBirth;
	}

	public Date getfEndingDate() {
		return fEndingDate;
	}

	public void setfEndingDate(Date fEndingDate) {
		this.fEndingDate = fEndingDate;
	}

	public BigDecimal getBillingSumAmount() {
		return billingSumAmount;
	}

	public void setBillingSumAmount(BigDecimal billingSumAmount) {
		this.billingSumAmount = billingSumAmount;
	}

	public String getfPaymentNumber() {
		return fPaymentNumber;
	}

	public void setfPaymentNumber(String fPaymentNumber) {
		this.fPaymentNumber = fPaymentNumber;
	}

	public List<FeeAccountBalanceBean> getFeeAccountBalanceBean() {
		return feeAccountBalanceBean;
	}

	public void setFeeAccountBalanceBean(List<FeeAccountBalanceBean> feeAccountBalanceBean) {
		this.feeAccountBalanceBean = feeAccountBalanceBean;
	}

	public BigDecimal getfBlncBeforePaymentTot() {
		return fBlncBeforePaymentTot;
	}

	public void setfBlncBeforePaymentTot(BigDecimal fBlncBeforePaymentTot) {
		this.fBlncBeforePaymentTot = fBlncBeforePaymentTot;
	}

	public BigDecimal getfPaymentAmountTot() {
		return fPaymentAmountTot;
	}

	public void setfPaymentAmountTot(BigDecimal fPaymentAmountTot) {
		this.fPaymentAmountTot = fPaymentAmountTot;
	}

	public BigDecimal getfPaymentAfterAmountTot() {
		return fPaymentAfterAmountTot;
	}

	public void setfPaymentAfterAmountTot(BigDecimal fPaymentAfterAmountTot) {
		this.fPaymentAfterAmountTot = fPaymentAfterAmountTot;
	}
	
	public List<FeeAccountBalanceBean> getPaymantCreditAccount() {
		return paymantCreditAccount;
	}

	public void setPaymantCreditAccount(List<FeeAccountBalanceBean> paymantCreditAccount) {
		this.paymantCreditAccount = paymantCreditAccount;
	}

	/**
	 * @return the paymentSumAmount
	 */
	public BigDecimal getPaymentSumAmount() {
		return paymentSumAmount;
	}

	/**
	 * @param paymentSumAmount the paymentSumAmount to set
	 */
	public void setPaymentSumAmount(final BigDecimal paymentSumAmount) {
		this.paymentSumAmount = paymentSumAmount;
	}

	/**
	 * @return the billingAccount
	 */
	public List<FeeAccountBalanceBean> getBillingAccount() {
		return billingAccount;
	}

	/**
	 * @param billingAccount the billingAccount to set
	 */
	public void setBillingAccount(final List<FeeAccountBalanceBean> billingAccount) {
		this.billingAccount = billingAccount;
	}

}