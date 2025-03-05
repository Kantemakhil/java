package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the OFFENDER_FREEZE_DISBURSEMENTS database table.
 * 
 */
public class OffenderFreezeDisbursements extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderFreezeId")
	private Long offenderFreezeId;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("freezeReasonCode")
	private String freezeReasonCode;

	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenderId")
	private Long offenderId;

	@JsonProperty("removedFlag")
	private String removedFlag;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("toDate")
	private Date toDate;

	public OffenderFreezeDisbursements() {
		// OffenderFreezeDisbursements
	}

	public Long getOffenderFreezeId() {
		return this.offenderFreezeId;
	}

	public void setOffenderFreezeId(final Long offenderFreezeId) {
		this.offenderFreezeId = offenderFreezeId;
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getFreezeReasonCode() {
		return this.freezeReasonCode;
	}

	public void setFreezeReasonCode(final String freezeReasonCode) {
		this.freezeReasonCode = freezeReasonCode;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Long getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	public String getRemovedFlag() {
		return this.removedFlag;
	}

	public void setRemovedFlag(final String removedFlag) {
		this.removedFlag = removedFlag;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}

}
