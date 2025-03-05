package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VOffenderRestrictions
 * 
 */
public class VOffenderRestrictions extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal authorisedStaffId;

	private String authroisedStaffName;

	private String commentText;

	private Date effectiveDate;

	private BigDecimal enteredStaffId;

	private String enteredStaffName;

	private Date expiryDate;

	private BigDecimal offenderBookId;

	private BigDecimal offenderRestrictionId;

	private String restrictionDesc;

	private String restrictionType;

	public VOffenderRestrictions() {
		// VOffenderRestrictions
	}

	public BigDecimal getAuthorisedStaffId() {
		return this.authorisedStaffId;
	}

	public void setAuthorisedStaffId(BigDecimal authorisedStaffId) {
		this.authorisedStaffId = authorisedStaffId;
	}

	public String getAuthroisedStaffName() {
		return this.authroisedStaffName;
	}

	public void setAuthroisedStaffName(String authroisedStaffName) {
		this.authroisedStaffName = authroisedStaffName;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public BigDecimal getEnteredStaffId() {
		return this.enteredStaffId;
	}

	public void setEnteredStaffId(BigDecimal enteredStaffId) {
		this.enteredStaffId = enteredStaffId;
	}

	public String getEnteredStaffName() {
		return this.enteredStaffName;
	}

	public void setEnteredStaffName(String enteredStaffName) {
		this.enteredStaffName = enteredStaffName;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderRestrictionId() {
		return this.offenderRestrictionId;
	}

	public void setOffenderRestrictionId(BigDecimal offenderRestrictionId) {
		this.offenderRestrictionId = offenderRestrictionId;
	}

	public String getRestrictionDesc() {
		return this.restrictionDesc;
	}

	public void setRestrictionDesc(String restrictionDesc) {
		this.restrictionDesc = restrictionDesc;
	}

	public String getRestrictionType() {
		return this.restrictionType;
	}

	public void setRestrictionType(String restrictionType) {
		this.restrictionType = restrictionType;
	}

}
