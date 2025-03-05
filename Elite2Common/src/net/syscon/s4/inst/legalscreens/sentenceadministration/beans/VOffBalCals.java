package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VOffBalCals extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	private int offenderBalCalcId;
	private int pbScheduleId;
	private int offenderBookId;
	private int offenderId;
	private Date  hearingDate;
	private String description;
	private String hearingReasonCode;
	private String hearingReason;
	private Date  effectiveDate;
	private int cbYears;
	private int cbMonths;
	private int cbWeeks;
	private int cbDays;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getOffenderBalCalcId() {
		return offenderBalCalcId;
	}
	public void setOffenderBalCalcId(int offenderBalCalcId) {
		this.offenderBalCalcId = offenderBalCalcId;
	}
	public int getPbScheduleId() {
		return pbScheduleId;
	}
	public void setPbScheduleId(int pbScheduleId) {
		this.pbScheduleId = pbScheduleId;
	}
	public int getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(int offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public int getOffenderId() {
		return offenderId;
	}
	public void setOffenderId(int offenderId) {
		this.offenderId = offenderId;
	}
	public Date getHearingDate() {
		return hearingDate;
	}
	public void setHearingDate(Date hearingDate) {
		this.hearingDate = hearingDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHearingReasonCode() {
		return hearingReasonCode;
	}
	public void setHearingReasonCode(String hearingReasonCode) {
		this.hearingReasonCode = hearingReasonCode;
	}
	public String getHearingReason() {
		return hearingReason;
	}
	public void setHearingReason(String hearingReason) {
		this.hearingReason = hearingReason;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public int getCbYears() {
		return cbYears;
	}
	public void setCbYears(int cbYears) {
		this.cbYears = cbYears;
	}
	public int getCbMonths() {
		return cbMonths;
	}
	public void setCbMonths(int cbMonths) {
		this.cbMonths = cbMonths;
	}
	public int getCbWeeks() {
		return cbWeeks;
	}
	public void setCbWeeks(int cbWeeks) {
		this.cbWeeks = cbWeeks;
	}
	public int getCbDays() {
		return cbDays;
	}
	public void setCbDays(int cbDays) {
		this.cbDays = cbDays;
	}
	
	
	
}
