package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class AdjustmentDetails extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("offenderSentenceAdjustId")
	private Long offenderSentenceAdjustId;
	
	@JsonProperty("sentenceAdjustCode")
	private String sentenceAdjustCode;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("toDate")
	private Date toDate;
	
	@JsonProperty("days")
	private Integer days;
	
	@JsonProperty("comment")
	private String comment;
	
	@JsonProperty("postedDate")
	private Date postedDate;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("sentenceSeq")
	private Integer sentenceSeq;
	
	@JsonProperty("offenceDate")
	private Date offenceDate;

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getOffenderSentenceAdjustId() {
		return offenderSentenceAdjustId;
	}

	public void setOffenderSentenceAdjustId(Long offenderSentenceAdjustId) {
		this.offenderSentenceAdjustId = offenderSentenceAdjustId;
	}

	public String getSentenceAdjustCode() {
		return sentenceAdjustCode;
	}

	public void setSentenceAdjustCode(String sentenceAdjustCode) {
		this.sentenceAdjustCode = sentenceAdjustCode;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Integer getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public Date getOffenceDate() {
		return offenceDate;
	}

	public void setOffenceDate(Date offenceDate) {
		this.offenceDate = offenceDate;
	}
	
	
	

}
