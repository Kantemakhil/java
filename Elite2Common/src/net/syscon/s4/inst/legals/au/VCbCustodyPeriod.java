package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VCbCustodyPeriod extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long offenderBookId;
	private BigDecimal offenderId;
	private Integer custodyPeriodSeq;
	private Date admissionDate;
	private Date releaseDate;
	private Integer daysAfterEffDate;
	private Integer addittionalDays;
	private Long offBalCalcId;
	private Date effectiveDate;
	private String description;
	private String code;

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

	public Integer getCustodyPeriodSeq() {
		return custodyPeriodSeq;
	}

	public void setCustodyPeriodSeq(final Integer custodyPeriodSeq) {
		this.custodyPeriodSeq = custodyPeriodSeq;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(final Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(final Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getDaysAfterEffDate() {
		return daysAfterEffDate;
	}

	public void setDaysAfterEffDate(final Integer daysAfterEffDate) {
		this.daysAfterEffDate = daysAfterEffDate;
	}

	public Integer getAddittionalDays() {
		return addittionalDays;
	}

	public void setAddittionalDays(final Integer addittionalDays) {
		this.addittionalDays = addittionalDays;
	}

	public Long getOffBalCalcId() {
		return offBalCalcId;
	}

	public void setOffBalCalcId(final Long offBalCalcId) {
		this.offBalCalcId = offBalCalcId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

}
