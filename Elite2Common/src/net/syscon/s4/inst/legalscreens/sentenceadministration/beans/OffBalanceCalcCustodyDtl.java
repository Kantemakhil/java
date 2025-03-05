package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffBalanceCalcCustodyDtl extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBalCalcId")
	private Long offenderBalCalcId;
	@JsonProperty("admissionDate")
	private Date admissionDate;
	@JsonProperty("releaseDate")
	private Date releaseDate;
	@JsonProperty("daysAfterEffDate")
	private Long daysAfterEffDate;
	@JsonProperty("addittionalDays")
	private Long addittionalDays;
	@JsonProperty("addittionalDaysReason")
	private String addittionalDaysReason;

	/**
	 * Creates new OffBalanceCalcCustodyDtl class Object
	 */
	public OffBalanceCalcCustodyDtl() {
		// OffBalanceCalcCustodyDtl
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
	 * @return the admissionDate
	 */
	public Date getAdmissionDate() {
		return admissionDate;
	}

	/**
	 * @param admissionDate the admissionDate to set
	 */
	public void setAdmissionDate(final Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	/**
	 * @return the releaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(final Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the daysAfterEffDate
	 */
	public Long getDaysAfterEffDate() {
		return daysAfterEffDate;
	}

	/**
	 * @param daysAfterEffDate the daysAfterEffDate to set
	 */
	public void setDaysAfterEffDate(final Long daysAfterEffDate) {
		this.daysAfterEffDate = daysAfterEffDate;
	}

	/**
	 * @return the addittionalDays
	 */
	public Long getAddittionalDays() {
		return addittionalDays;
	}

	/**
	 * @param addittionalDays the addittionalDays to set
	 */
	public void setAddittionalDays(final Long addittionalDays) {
		this.addittionalDays = addittionalDays;
	}

	/**
	 * @return the addittionalDaysReason
	 */
	public String getAddittionalDaysReason() {
		return addittionalDaysReason;
	}

	/**
	 * @param addittionalDaysReason the addittionalDaysReason to set
	 */
	public void setAddittionalDaysReason(final String addittionalDaysReason) {
		this.addittionalDaysReason = addittionalDaysReason;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
