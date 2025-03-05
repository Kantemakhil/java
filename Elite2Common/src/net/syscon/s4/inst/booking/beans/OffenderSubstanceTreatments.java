package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_SUBSTANCE_TREATMENTS database table.
 * 
 */
public class OffenderSubstanceTreatments extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String caseloadType;

	private String commentText;

	private Date createDatetime;

	private String createUserId;

	private String fromDateFlag;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal rootOffenderId;

	private String sealFlag;

	private String toDateFlag;

	private String treatmentCode;

	private Date treatmentFromDate;

	private String treatmentPlace;

	private Date treatmentToDate;

	private OffenderSubstanceUses offenderSubstanceUs;

	private long offenderBookId;

	private long treatmentSeq;

	private String substanceType;

	private String nbtCaseloadType;

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getNbtCaseloadType() {
		return nbtCaseloadType;
	}

	public void setNbtCaseloadType(final String nbtCaseloadType) {
		this.nbtCaseloadType = nbtCaseloadType;
	}

	public long getTreatmentSeq() {
		return treatmentSeq;
	}

	public void setTreatmentSeq(final long treatmentSeq) {
		this.treatmentSeq = treatmentSeq;
	}

	public String getSubstanceType() {
		return substanceType;
	}

	public void setSubstanceType(final String substanceType) {
		this.substanceType = substanceType;
	}

	public OffenderSubstanceTreatments() {
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getFromDateFlag() {
		return this.fromDateFlag;
	}

	public void setFromDateFlag(final String fromDateFlag) {
		this.fromDateFlag = fromDateFlag;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getRootOffenderId() {
		return this.rootOffenderId;
	}

	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getToDateFlag() {
		return this.toDateFlag;
	}

	public void setToDateFlag(final String toDateFlag) {
		this.toDateFlag = toDateFlag;
	}

	public String getTreatmentCode() {
		return this.treatmentCode;
	}

	public void setTreatmentCode(final String treatmentCode) {
		this.treatmentCode = treatmentCode;
	}

	public String getTreatmentPlace() {
		return this.treatmentPlace;
	}

	public void setTreatmentPlace(final String treatmentPlace) {
		this.treatmentPlace = treatmentPlace;
	}

	public OffenderSubstanceUses getOffenderSubstanceUs() {
		return this.offenderSubstanceUs;
	}

	public Date getTreatmentFromDate() {
		return treatmentFromDate;
	}

	public void setTreatmentFromDate(final Date treatmentFromDate) {
		this.treatmentFromDate = treatmentFromDate;
	}

	public Date getTreatmentToDate() {
		return treatmentToDate;
	}

	public void setTreatmentToDate(final Date treatmentToDate) {
		this.treatmentToDate = treatmentToDate;
	}

	public void setOffenderSubstanceUs(final OffenderSubstanceUses offenderSubstanceUs) {
		this.offenderSubstanceUs = offenderSubstanceUs;
	}

}
