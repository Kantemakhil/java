package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffenderCriminogenicNeeds implements Serializable {
	private static final long serialVersionUID = 1L;

	private long offCrimNeedId;

	private String assessedNeedCode;

	private java.math.BigDecimal casePlanId;

	private Date createDatetime;

	private String createUserId;

	private Date endDate;

	private Date modifyDatetime;

	private String modifyUserId;

	private String objective;

	private java.math.BigDecimal offenderBookId;

	private String sealFlag;

	private String statusCode;

	private Date targetDate;

	private Date latestDate;

	@JsonProperty("rowId")
	private Long rowId;

	public OffenderCriminogenicNeeds() {
		// OffenderCriminogenicNeeds
	}

	public long getOffCrimNeedId() {
		return this.offCrimNeedId;
	}

	/**
	 * @return the assessedNeedCode
	 */
	public String getAssessedNeedCode() {
		return assessedNeedCode;
	}

	/**
	 * @param assessedNeedCode the assessedNeedCode to set
	 */
	public void setAssessedNeedCode(String assessedNeedCode) {
		this.assessedNeedCode = assessedNeedCode;
	}

	/**
	 * @return the casePlanId
	 */
	public java.math.BigDecimal getCasePlanId() {
		return casePlanId;
	}

	/**
	 * @param casePlanId the casePlanId to set
	 */
	public void setCasePlanId(java.math.BigDecimal casePlanId) {
		this.casePlanId = casePlanId;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the objective
	 */
	public String getObjective() {
		return objective;
	}

	/**
	 * @param objective the objective to set
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}

	/**
	 * @return the offenderBookId
	 */
	public java.math.BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(java.math.BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the targetDate
	 */
	public Date getTargetDate() {
		return targetDate;
	}

	/**
	 * @param targetDate the targetDate to set
	 */
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	/**
	 * @param offCrimNeedId the offCrimNeedId to set
	 */
	public void setOffCrimNeedId(long offCrimNeedId) {
		this.offCrimNeedId = offCrimNeedId;
	}

	/**
	 * @return the latestDate
	 */
	public Date getLatestDate() {
		return latestDate;
	}

	/**
	 * @param latestDate the latestDate to set
	 */
	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}

	/**
	 * @return the rowId
	 */
	public Long getRowId() {
		return rowId;
	}

	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(final Long rowId) {
		this.rowId = rowId;
	}

}
