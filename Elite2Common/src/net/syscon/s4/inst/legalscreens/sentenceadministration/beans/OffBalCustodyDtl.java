package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffBalCustodyDtl extends BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal offenderBalCalcId;
	private Date admissionDate;
	private Date releaseDate;
	private int daysAfterEffDate;
	private int addittionalDays;
	private String addittionalDaysReason;
	public BigDecimal getOffenderBalCalcId() {
		return offenderBalCalcId;
	}
	public void setOffenderBalCalcId(BigDecimal offenderBalCalcId) {
		this.offenderBalCalcId = offenderBalCalcId;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getDaysAfterEffDate() {
		return daysAfterEffDate;
	}
	public void setDaysAfterEffDate(int daysAfterEffDate) {
		this.daysAfterEffDate = daysAfterEffDate;
	}
	public int getAddittionalDays() {
		return addittionalDays;
	}
	public void setAddittionalDays(int addittionalDays) {
		this.addittionalDays = addittionalDays;
	}
	public String getAddittionalDaysReason() {
		return addittionalDaysReason;
	}
	public void setAddittionalDaysReason(String addittionalDaysReason) {
		this.addittionalDaysReason = addittionalDaysReason;
	}
	
	

}

