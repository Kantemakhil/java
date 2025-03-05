package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProgramAssessments implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long programId;

	private String activeFlag;

	private Date expiryDate;

	private BigDecimal assessmentId;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String assessmentCode;

	private String sealFlag;

	public ProgramAssessments() {
		// ProgramAssessments
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(final Long programId) {
		this.programId = programId;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(final BigDecimal assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the assessmentCode
	 */
	public String getAssessmentCode() {
		return assessmentCode;
	}

	/**
	 * @param assessmentCode
	 *            the assessmentCode to set
	 */
	public void setAssessmentCode(final String assessmentCode) {
		this.assessmentCode = assessmentCode;
	}

}
