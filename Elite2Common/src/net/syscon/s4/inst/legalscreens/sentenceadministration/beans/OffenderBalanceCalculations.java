package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderBalanceCalculations extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBalCalcId")
	private Long offenderBalCalcId;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("pbScheduleId")
	private Long pbScheduleId;
	@JsonProperty("effectiveDate")
	private Date effectiveDate;
	@JsonProperty("cbYears")
	private Long cbYears;
	@JsonProperty("cbMonths")
	private Long cbMonths;
	@JsonProperty("cbWeeks")
	private Long cbWeeks;
	@JsonProperty("cbDays")
	private Long cbDays;
	@JsonProperty("verificationFlag")
	private String verificationFlag;

	/**
	 * Creates new OffenderBalanceCalculations class Object
	 */
	public OffenderBalanceCalculations() {
		// OffenderBalanceCalculations
	}

	/**
	 * @return the offenderBalCalcId
	 */
	public Long getOffenderBalCalcId() {
		return offenderBalCalcId;
	}

	/**
	 * @param offenderBalCalcId the offenderBalCalcId to set
	 */
	public void setOffenderBalCalcId(final Long offenderBalCalcId) {
		this.offenderBalCalcId = offenderBalCalcId;
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
	 * @return the pbScheduleId
	 */
	public Long getPbScheduleId() {
		return pbScheduleId;
	}

	/**
	 * @param pbScheduleId the pbScheduleId to set
	 */
	public void setPbScheduleId(final Long pbScheduleId) {
		this.pbScheduleId = pbScheduleId;
	}

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the cbYears
	 */
	public Long getCbYears() {
		return cbYears;
	}

	/**
	 * @param cbYears the cbYears to set
	 */
	public void setCbYears(final Long cbYears) {
		this.cbYears = cbYears;
	}

	/**
	 * @return the cbMonths
	 */
	public Long getCbMonths() {
		return cbMonths;
	}

	/**
	 * @param cbMonths the cbMonths to set
	 */
	public void setCbMonths(final Long cbMonths) {
		this.cbMonths = cbMonths;
	}

	/**
	 * @return the cbWeeks
	 */
	public Long getCbWeeks() {
		return cbWeeks;
	}

	/**
	 * @param cbWeeks the cbWeeks to set
	 */
	public void setCbWeeks(final Long cbWeeks) {
		this.cbWeeks = cbWeeks;
	}

	/**
	 * @return the cbDays
	 */
	public Long getCbDays() {
		return cbDays;
	}

	/**
	 * @param cbDays the cbDays to set
	 */
	public void setCbDays(final Long cbDays) {
		this.cbDays = cbDays;
	}

	/**
	 * @return the verificationFlag
	 */
	public String getVerificationFlag() {
		return verificationFlag;
	}

	/**
	 * @param verificationFlag the verificationFlag to set
	 */
	public void setVerificationFlag(final String verificationFlag) {
		this.verificationFlag = verificationFlag;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
