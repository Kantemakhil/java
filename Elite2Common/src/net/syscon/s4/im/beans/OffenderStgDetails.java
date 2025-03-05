package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_STG_DETAILS database table.
 * 
 */
public class OffenderStgDetails extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("actionCode")
	private String actionCode;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("reasonCode")
	private String reasonCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("valDate")
	private Date valDate;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("stgSeq")
	private BigDecimal stgSeq;

	@JsonProperty("detailSeq")
	private BigDecimal detailSeq;
	
	@JsonProperty("deValDate")
	private Date deValDate;

	public OffenderStgDetails() {
		// OffenderStgDetails
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getStgSeq() {
		return stgSeq;
	}

	public void setStgSeq(final BigDecimal stgSeq) {
		this.stgSeq = stgSeq;
	}

	public BigDecimal getDetailSeq() {
		return detailSeq;
	}

	public void setDetailSeq(final BigDecimal detailSeq) {
		this.detailSeq = detailSeq;
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(final String actionCode) {
		this.actionCode = actionCode;
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

	public String getReasonCode() {
		return this.reasonCode;
	}

	public void setReasonCode(final String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getValDate() {
		return this.valDate;
	}

	public void setValDate(final Date valDate) {
		this.valDate = valDate;
	}

	public Date getDeValDate() {
		return deValDate;
	}

	public void setDeValDate(final Date deValDate) {
		this.deValDate = deValDate;
	}

}
