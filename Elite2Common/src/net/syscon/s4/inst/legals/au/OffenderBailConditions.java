package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderBailConditions extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("bailConditionCode")
	private String bailConditionCode;
	
	@JsonProperty("bailConditionId")
	private Long bailConditionId;
	
	@JsonProperty("cashDeposit")
	private Long cashDeposit;
	
	@JsonProperty("cashDepositDist")
	private String cashDepositDist;
	
	@JsonProperty("cashDepositReceipt")
	private String cashDepositReceipt;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("createDate")
	private Date createDate;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("eventId")
	private Long eventId;
	
	@JsonProperty("forfeitedFunds")
	private Long forfeitedFunds;
	
	@JsonProperty("forfeitedFundsDist")
	private String forfeitedFundsDist;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("securityDeposit")
	private Long securityDeposit;
	
	@JsonProperty("securityDepositDistCode")
	private String securityDepositDistCode;
	
	@JsonProperty("suretyName1")
	private String suretyName1;
	
	@JsonProperty("suretyName2")
	private String suretyName2;

	public String getBailConditionCode() {
		return bailConditionCode;
	}

	public void setBailConditionCode(final String bailConditionCode) {
		this.bailConditionCode = bailConditionCode;
	}

	public Long getBailConditionId() {
		return bailConditionId;
	}

	public void setBailConditionId(final Long bailConditionId) {
		this.bailConditionId = bailConditionId;
	}

	public Long getCashDeposit() {
		return cashDeposit;
	}

	public void setCashDeposit(final Long cashDeposit) {
		this.cashDeposit = cashDeposit;
	}

	public String getCashDepositDist() {
		return cashDepositDist;
	}

	public void setCashDepositDist(final String cashDepositDist) {
		this.cashDepositDist = cashDepositDist;
	}

	public String getCashDepositReceipt() {
		return cashDepositReceipt;
	}

	public void setCashDepositReceipt(final String cashDepositReceipt) {
		this.cashDepositReceipt = cashDepositReceipt;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(final Long eventId) {
		this.eventId = eventId;
	}

	public Long getForfeitedFunds() {
		return forfeitedFunds;
	}

	public void setForfeitedFunds(final Long forfeitedFunds) {
		this.forfeitedFunds = forfeitedFunds;
	}

	public String getForfeitedFundsDist() {
		return forfeitedFundsDist;
	}

	public void setForfeitedFundsDist(final String forfeitedFundsDist) {
		this.forfeitedFundsDist = forfeitedFundsDist;
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

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(final Long securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public String getSecurityDepositDistCode() {
		return securityDepositDistCode;
	}

	public void setSecurityDepositDistCode(final String securityDepositDistCode) {
		this.securityDepositDistCode = securityDepositDistCode;
	}

	public String getSuretyName1() {
		return suretyName1;
	}

	public void setSuretyName1(final String suretyName1) {
		this.suretyName1 = suretyName1;
	}

	public String getSuretyName2() {
		return suretyName2;
	}

	public void setSuretyName2(final String suretyName2) {
		this.suretyName2 = suretyName2;
	}
	
	
}
