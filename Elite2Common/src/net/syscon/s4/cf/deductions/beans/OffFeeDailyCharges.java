package net.syscon.s4.cf.deductions.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffFeeDailyCharges {

	@JsonProperty("offenderFeeId")
	private String offenderFeeId;

	@JsonProperty("chargeDay")
	private String chargeDay;

	@JsonProperty("chargeAmount")
	private BigDecimal chargeAmount;

	@JsonProperty("chargeCreateDatetime")
	private Date chargeCreateDatetime;

	@JsonProperty("billGeneratedFlag")
	private String billGeneratedFlag;

	@JsonProperty("billId")
	private Integer billId;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	public OffFeeDailyCharges() {
		// constructor
	}

	public String getOffenderFeeId() {
		return offenderFeeId;
	}

	public void setOffenderFeeId(String offenderFeeId) {
		this.offenderFeeId = offenderFeeId;
	}

	public String getChargeDay() {
		return chargeDay;
	}

	public void setChargeDay(String chargeDay) {
		this.chargeDay = chargeDay;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public Date getChargeCreateDatetime() {
		return chargeCreateDatetime;
	}

	public void setChargeCreateDatetime(Date chargeCreateDatetime) {
		this.chargeCreateDatetime = chargeCreateDatetime;
	}

	public String getBillGeneratedFlag() {
		return billGeneratedFlag;
	}

	public void setBillGeneratedFlag(String billGeneratedFlag) {
		this.billGeneratedFlag = billGeneratedFlag;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
