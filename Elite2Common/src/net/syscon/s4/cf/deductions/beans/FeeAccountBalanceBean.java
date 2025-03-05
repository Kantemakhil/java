package net.syscon.s4.cf.deductions.beans;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeeAccountBalanceBean {
	@JsonProperty("feeCode")
	private String feeCode;
	@JsonProperty("caseloadDescription")
	private String caseloadDescription;
	@JsonProperty("beforePaymentBalance")
	private BigDecimal beforePaymentBalance;
	@JsonProperty("paymentAmount")
	private BigDecimal paymentAmount;
	@JsonProperty("afterPaymentBalance")
	private BigDecimal afterPaymentBalance;
	
	@JsonProperty("beginingBalanceAmount")
	private BigDecimal beginingBalanceAmount;

	@JsonProperty("paymentsCreditsAmount")
	private BigDecimal paymentsCreditsAmount;

	@JsonProperty("billingsAmount")
	private BigDecimal billingsAmount;

	@JsonProperty("endingBalanceAmount")
	private BigDecimal endingBalanceAmount;
	
	@JsonProperty("currentRate")
	private String currentRate;

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public String getCaseloadDescription() {
		return caseloadDescription;
	}

	public void setCaseloadDescription(String caseloadDescription) {
		this.caseloadDescription = caseloadDescription;
	}

	public BigDecimal getBeforePaymentBalance() {
		return beforePaymentBalance;
	}

	public void setBeforePaymentBalance(BigDecimal beforePaymentBalance) {
		this.beforePaymentBalance = beforePaymentBalance;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getAfterPaymentBalance() {
		return afterPaymentBalance;
	}

	public void setAfterPaymentBalance(BigDecimal afterPaymentBalance) {
		this.afterPaymentBalance = afterPaymentBalance;
	}

	/**
	 * @return the beginingBalanceAmount
	 */
	public BigDecimal getBeginingBalanceAmount() {
		return beginingBalanceAmount;
	}

	/**
	 * @param beginingBalanceAmount the beginingBalanceAmount to set
	 */
	public void setBeginingBalanceAmount(final BigDecimal beginingBalanceAmount) {
		this.beginingBalanceAmount = beginingBalanceAmount;
	}

	/**
	 * @return the paymentsCreditsAmount
	 */
	public BigDecimal getPaymentsCreditsAmount() {
		return paymentsCreditsAmount;
	}

	/**
	 * @param paymentsCreditsAmount the paymentsCreditsAmount to set
	 */
	public void setPaymentsCreditsAmount(final BigDecimal paymentsCreditsAmount) {
		this.paymentsCreditsAmount = paymentsCreditsAmount;
	}

	/**
	 * @return the billingsAmount
	 */
	public BigDecimal getBillingsAmount() {
		return billingsAmount;
	}

	/**
	 * @param billingsAmount the billingsAmount to set
	 */
	public void setBillingsAmount(final BigDecimal billingsAmount) {
		this.billingsAmount = billingsAmount;
	}

	/**
	 * @return the endingBalanceAmount
	 */
	public BigDecimal getEndingBalanceAmount() {
		return endingBalanceAmount;
	}

	/**
	 * @param endingBalanceAmount the endingBalanceAmount to set
	 */
	public void setEndingBalanceAmount(final BigDecimal endingBalanceAmount) {
		this.endingBalanceAmount = endingBalanceAmount;
	}

	/**
	 * @return the currentRate
	 */
	public String getCurrentRate() {
		return currentRate;
	}

	/**
	 * @param currentRate the currentRate to set
	 */
	public void setCurrentRate(final String currentRate) {
		this.currentRate = currentRate;
	}

}
