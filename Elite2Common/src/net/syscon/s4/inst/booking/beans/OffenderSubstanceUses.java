package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_SUBSTANCE_USES database table.
 * 
 */
public class OffenderSubstanceUses extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal ageUsed;

	private String caseloadType;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal rootOffenderId;

	private String sealFlag;

	private String nbtCaseloadType;

	// bi-directional many-to-one association to OffenderSubstanceDetail
	private List<OffenderSubstanceDetails> offenderSubstanceDetails;

	// bi-directional many-to-one association to OffenderSubstanceTreatment
	private List<OffenderSubstanceTreatments> offenderSubstanceTreatments;

	private long offenderBookId;

	private String substanceType;

	@JsonProperty("rowId")
	private String rowId;
	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getSubstanceType() {
		return substanceType;
	}

	public void setSubstanceType(final String substanceType) {
		this.substanceType = substanceType;
	}

	public OffenderSubstanceUses() {
		// OffenderSubstanceUs
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

	public BigDecimal getAgeUsed() {
		return this.ageUsed;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public void setAgeUsed(final BigDecimal ageUsed) {
		this.ageUsed = ageUsed;
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
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

	public List<OffenderSubstanceDetails> getOffenderSubstanceDetails() {
		return this.offenderSubstanceDetails;
	}

	public void setOffenderSubstanceDetails(final List<OffenderSubstanceDetails> offenderSubstanceDetails) {
		this.offenderSubstanceDetails = offenderSubstanceDetails;
	}

	public String getNbtCaseloadType() {
		return nbtCaseloadType;
	}

	public void setNbtCaseloadType(final String nbtCaseloadType) {
		this.nbtCaseloadType = nbtCaseloadType;
	}

	public OffenderSubstanceDetails addOffenderSubstanceDetail(final OffenderSubstanceDetails offenderSubstanceDetail) {
		getOffenderSubstanceDetails().add(offenderSubstanceDetail);
		offenderSubstanceDetail.setOffenderSubstanceUs(this);

		return offenderSubstanceDetail;
	}

	public OffenderSubstanceDetails removeOffenderSubstanceDetail(
			final OffenderSubstanceDetails offenderSubstanceDetail) {
		getOffenderSubstanceDetails().remove(offenderSubstanceDetail);
		offenderSubstanceDetail.setOffenderSubstanceUs(null);

		return offenderSubstanceDetail;
	}

	public List<OffenderSubstanceTreatments> getOffenderSubstanceTreatments() {
		return this.offenderSubstanceTreatments;
	}

	public void setOffenderSubstanceTreatments(final List<OffenderSubstanceTreatments> offenderSubstanceTreatments) {
		this.offenderSubstanceTreatments = offenderSubstanceTreatments;
	}

	public OffenderSubstanceTreatments addOffenderSubstanceTreatment(
			final OffenderSubstanceTreatments offenderSubstanceTreatment) {
		getOffenderSubstanceTreatments().add(offenderSubstanceTreatment);
		offenderSubstanceTreatment.setOffenderSubstanceUs(this);

		return offenderSubstanceTreatment;
	}

	public OffenderSubstanceTreatments removeOffenderSubstanceTreatment(
			final OffenderSubstanceTreatments offenderSubstanceTreatment) {
		getOffenderSubstanceTreatments().remove(offenderSubstanceTreatment);
		offenderSubstanceTreatment.setOffenderSubstanceUs(null);

		return offenderSubstanceTreatment;
	}

}
