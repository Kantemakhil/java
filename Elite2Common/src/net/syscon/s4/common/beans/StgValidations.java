package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the STG_VALIDATIONS database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StgValidations extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("action")
	private String action;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("designation")
	private String designation;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("reason")
	private String reason;
	@JsonProperty("reviewDate")
	private Date reviewDate;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("validationDate")
	private Date validationDate;
	@JsonProperty("stgId")
	private BigDecimal stgId;
	@JsonProperty("validationSeq")
	private BigDecimal validationSeq;

	public StgValidations() {
		// StgValidations
	}

	public BigDecimal getStgId() {
		return stgId;
	}

	public void setStgId(final BigDecimal stgId) {
		this.stgId = stgId;
	}

	public BigDecimal getValidationSeq() {
		return validationSeq;
	}

	public void setValidationSeq(final BigDecimal validationSeq) {
		this.validationSeq = validationSeq;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(final String action) {
		this.action = action;
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

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(final String designation) {
		this.designation = designation;
	}

	public Date getModifyDatetime() {
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

	public String getReason() {
		return this.reason;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	public Date getReviewDate() {
		return this.reviewDate;
	}

	public void setReviewDate(final Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getValidationDate() {
		return this.validationDate;
	}

	public void setValidationDate(final Date validationDate) {
		this.validationDate = validationDate;
	}

}
