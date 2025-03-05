package net.syscon.s4.cf.deductions.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.im.beans.SubAccountBalancesBean;

public class OcrorrecReportsBean {
	@JsonProperty("fUserOne")
	private String fUserOne;
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
	@JsonProperty("fDateOne")
	private String fDateOne;
	@JsonProperty("fHoldBalance")
	private BigDecimal fHoldBalance;
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
	
	private List<SubAccountBalancesBean> subAccountBalancesBean;
	private List<SubAccountBalancesBean> debsObligationsBean;
	private List<SubAccountBalancesBean> subAccountTransactionsBean;
	private List<SubAccountBalancesBean> regAccountTransactionsBean;
	private List<SubAccountBalancesBean> savAccountTransactionsBean;
	@JsonProperty("cfCur")
	private String cfCur;
	
	@JsonProperty("globalMsg")
	private String globalMsg;
	
	@JsonProperty("countySpecificMsg")
	private String countySpecificMsg;
	
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

	/**
	 * @return the fDateOne
	 */
	public String getfDateOne() {
		return fDateOne;
	}

	/**
	 * @param fDateOne the fDateOne to set
	 */
	public void setfDateOne(final String fDateOne) {
		this.fDateOne = fDateOne;
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
	 * @return the globalMsg
	 */
	public String getGlobalMsg() {
		return globalMsg;
	}

	/**
	 * @param globalMsg the globalMsg to set
	 */
	public void setGlobalMsg(String globalMsg) {
		this.globalMsg = globalMsg;
	}

	/**
	 * @return the countySpecificMsg
	 */
	public String getCountySpecificMsg() {
		return countySpecificMsg;
	}

	/**
	 * @param countySpecificMsg the countySpecificMsg to set
	 */
	public void setCountySpecificMsg(String countySpecificMsg) {
		this.countySpecificMsg = countySpecificMsg;
	}
	
	

}
