package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderBailDetails extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("seqNo")
	private Integer seqNo;
	
	@JsonProperty("bookId")
	private Integer bookId;
	
	@JsonProperty("chargeId")
	private Integer chargeId;
	
	@JsonProperty("bailBookId")
	private Integer bailBookId;
	
	@JsonProperty("bailChargeId")
	private Integer bailChargeId;
	
	@JsonProperty("bailStatus")
	private String bailStatus;
	
	@JsonProperty("cashFlag")
	private String cashFlag;
	
	@JsonProperty("cash")
	private Double cash;
	
	@JsonProperty("surety")
	private Double surety;
	
	@JsonProperty("property")
	private Double property;
	
	@JsonProperty("preferedDateTime")
	private Date preferedDateTime;
	
	@JsonProperty("time")
	private Date time;
	
	@JsonProperty("preferedBy")
	private String preferedBy;
	
	@JsonProperty("method")
	private String method;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("BailDate")
	private Date BailDate;
	
	@JsonProperty("judge")
	private String judge;
	
	@JsonProperty("receiptText")
	private String receiptText;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("action")
	private String action;
	
	@JsonProperty("offencesDec")
	private String offencesDec;
	
	@JsonProperty("commitFlag")
	private String commitFlag;
	
	@JsonProperty("eventId")
	private Long eventId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("eventDate")
	private Date eventDate;
	
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(final Date eventDate) {
		this.eventDate = eventDate;
	}
	public Long getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getChargeId() {
		return chargeId;
	}
	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}
	
	public Integer getBailBookId() {
		return bailBookId;
	}
	public void setBailBookId(Integer bailBookId) {
		this.bailBookId = bailBookId;
	}
	public Integer getBailChargeId() {
		return bailChargeId;
	}
	public void setBailChargeId(Integer bailChargeId) {
		this.bailChargeId = bailChargeId;
	}
	public String getBailStatus() {
		return bailStatus;
	}
	public void setBailStatus(String bailStatus) {
		this.bailStatus = bailStatus;
	}
	public String getCashFlag() {
		return cashFlag;
	}
	public void setCashFlag(String cashFlag) {
		this.cashFlag = cashFlag;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Double getSurety() {
		return surety;
	}
	public void setSurety(Double surety) {
		this.surety = surety;
	}
	public Double getProperty() {
		return property;
	}
	public void setProperty(Double property) {
		this.property = property;
	}
	public Date getPreferedDateTime() {
		return preferedDateTime;
	}
	public void setPreferedDateTime(Date preferedDateTime) {
		this.preferedDateTime = preferedDateTime;
	}
	public String getPreferedBy() {
		return preferedBy;
	}
	public void setPreferedBy(String preferedBy) {
		this.preferedBy = preferedBy;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public Date getBailDate() {
		return BailDate;
	}
	public void setBailDate(Date bailDate) {
		BailDate = bailDate;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	public String getReceiptText() {
		return receiptText;
	}
	public void setReceiptText(String receiptText) {
		this.receiptText = receiptText;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	public String getOffencesDec() {
		return offencesDec;
	}
	public void setOffencesDec(String offencesDec) {
		this.offencesDec = offencesDec;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getCommitFlag() {
		return commitFlag;
	}
	public void setCommitFlag(String commitFlag) {
		this.commitFlag = commitFlag;
	}

	public Long getEventId() {
		return eventId;
	}
	public void setEventId(final Long eventId) {
		this.eventId = eventId;
	}
}
