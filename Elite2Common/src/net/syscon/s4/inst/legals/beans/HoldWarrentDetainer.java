package net.syscon.s4.inst.legals.beans;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
public class HoldWarrentDetainer implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("holdWarrentDetainerId")
	private Long holdWarrentId;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("receivedDate")
	private Date receivedDate;
	@JsonProperty("issuingAgyLocId")
	private String issuingAgyLocId;
	@JsonProperty("holdWarrentDetainerType")
	private String holdWarrentDetainerType;
	@JsonProperty("warrentNumber")
	private String warrentNumber;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("bailAmount")
	private Float bailAmount;
	@JsonProperty("probRevocFlag")
	private String probRevocFlag;
	@JsonProperty("holdWarrentDetainerTypeInfoIdStatus")
	private String holdWarrentDetainerTypeInfoIdStatus;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("createDateTime")
	private Date createDateTime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	public Long getHoldWarrentId() {
		return holdWarrentId;
	}
	public void setHoldWarrentId(Long holdWarrentId) {
		this.holdWarrentId = holdWarrentId;
	}
	public Long getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getIssuingAgyLocId() {
		return issuingAgyLocId;
	}
	public void setIssuingAgyLocId(String issuingAgyLocId) {
		this.issuingAgyLocId = issuingAgyLocId;
	}
	public String getHoldWarrentDetainerType() {
		return holdWarrentDetainerType;
	}
	public void setHoldWarrentDetainerType(String holdWarrentDetainerType) {
		this.holdWarrentDetainerType = holdWarrentDetainerType;
	}
	public String getWarrentNumber() {
		return warrentNumber;
	}
	public void setWarrentNumber(String warrentNumber) {
		this.warrentNumber = warrentNumber;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Float getBailAmount() {
		return bailAmount;
	}
	public void setBailAmount(Float bailAmount) {
		this.bailAmount = bailAmount;
	}
	public String getProbRevocFlag() {
		return probRevocFlag;
	}
	public void setProbRevocFlag(String probRevocFlag) {
		this.probRevocFlag = probRevocFlag;
	}
	public String getHoldWarrentDetainerTypeInfoIdStatus() {
		return holdWarrentDetainerTypeInfoIdStatus;
	}
	public void setHoldWarrentDetainerTypeInfoIdStatus(String holdWarrentDetainerTypeInfoIdStatus) {
		this.holdWarrentDetainerTypeInfoIdStatus = holdWarrentDetainerTypeInfoIdStatus;
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
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getModifyDateTime() {
		return modifyDateTime;
	}
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
	
		
}
