package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;

import net.syscon.s4.common.beans.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class AssessmentSectionScoresV1
 * 
 */
public class AssessmentSectionScoresV1 extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal assessmentId;

	private BigDecimal assessmentSeq;

	private String description;

	private Date effectiveDate;

	private BigDecimal offenderBookId;

	private BigDecimal parentAssessmentId;

	private BigDecimal score;

	private String section;
	
	private String securityLevelDesc;
	
	private String domain;

	public AssessmentSectionScoresV1() {
		// AssessmentSectionScoresV1
	}

	public BigDecimal getAssessmentId() {
		return this.assessmentId;
	}

	public void setAssessmentId(BigDecimal assessmentId) {
		this.assessmentId = assessmentId;
	}

	public BigDecimal getAssessmentSeq() {
		return this.assessmentSeq;
	}

	public void setAssessmentSeq(BigDecimal assessmentSeq) {
		this.assessmentSeq = assessmentSeq;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getParentAssessmentId() {
		return this.parentAssessmentId;
	}

	public void setParentAssessmentId(BigDecimal parentAssessmentId) {
		this.parentAssessmentId = parentAssessmentId;
	}

	public BigDecimal getScore() {
		return this.score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSecurityLevelDesc() {
		return securityLevelDesc;
	}

	public void setSecurityLevelDesc(String securityLevelDesc) {
		this.securityLevelDesc = securityLevelDesc;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
