package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VOffBalCals extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long offenderBalCalcId;
	private Long pbScheduleId;
	private Long offenderBookId;
	private BigDecimal offenderId;
	private Date hearingDate;
	private String description;
	private String hearingReasonCode;
	private String hearingReason;
	private Date effectiveDate;
	private Integer cbYears;
	private Integer cbMonths;
	private Integer cbWeeks;
	private Integer cbDays;
	private String message;
	private Long offBalCalcId;
	private Date effectiveDateVal;

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public Long getOffenderBalCalcId() {
		return offenderBalCalcId;
	}

	public void setOffenderBalCalcId(final Long offenderBalCalcId) {
		this.offenderBalCalcId = offenderBalCalcId;
	}

	public Long getPbScheduleId() {
		return pbScheduleId;
	}

	public void setPbScheduleId(final Long pbScheduleId) {
		this.pbScheduleId = pbScheduleId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public Date getHearingDate() {
		return hearingDate;
	}

	public void setHearingDate(final Date hearingDate) {
		this.hearingDate = hearingDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getHearingReasonCode() {
		return hearingReasonCode;
	}

	public void setHearingReasonCode(final String hearingReasonCode) {
		this.hearingReasonCode = hearingReasonCode;
	}

	public String getHearingReason() {
		return hearingReason;
	}

	public void setHearingReason(final String hearingReason) {
		this.hearingReason = hearingReason;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Integer getCbYears() {
		return cbYears;
	}

	public void setCbYears(final Integer cbYears) {
		this.cbYears = cbYears;
	}

	public Integer getCbMonths() {
		return cbMonths;
	}

	public void setCbMonths(final Integer cbMonths) {
		this.cbMonths = cbMonths;
	}

	public Integer getCbWeeks() {
		return cbWeeks;
	}

	public void setCbWeeks(final Integer cbWeeks) {
		this.cbWeeks = cbWeeks;
	}

	public Integer getCbDays() {
		return cbDays;
	}

	public void setCbDays(final Integer cbDays) {
		this.cbDays = cbDays;
	}

	public Long getOffBalCalcId() {
		return offBalCalcId;
	}

	public void setOffBalCalcId(final Long offBalCalcId) {
		this.offBalCalcId = offBalCalcId;
	}

	public Date getEffectiveDateVal() {
		return effectiveDateVal;
	}

	public void setEffectiveDateVal(final Date effectiveDateVal) {
		this.effectiveDateVal = effectiveDateVal;
	}
	

}
