package net.syscon.s4.inst.legals.beans;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Charges implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("holdWarrentDetainerId")
	private Long holdWarrentId;
	@JsonProperty("holdWarrentDetainerChargeId")
	private Long holdWarrentDetainerChargeId;
	@JsonProperty("chargeCode")
	private String chargeCode;
	@JsonProperty("chargeComment")
	private String chargeComment;
	@JsonProperty("triedUntried")
	private String triedUntried;
	@JsonProperty("chargeStatus")
	private String chargeStatus;
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
	public Long getHoldWarrentDetainerChargeId() {
		return holdWarrentDetainerChargeId;
	}
	public void setHoldWarrentDetainerChargeId(Long holdWarrentDetainerChargeId) {
		this.holdWarrentDetainerChargeId = holdWarrentDetainerChargeId;
	}
	public String getChargeCode() {
		return chargeCode;
	}
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
	public String getChargeComment() {
		return chargeComment;
	}
	public void setChargeComment(String chargeComment) {
		this.chargeComment = chargeComment;
	}
	public String getTriedUntried() {
		return triedUntried;
	}
	public void setTriedUntried(String triedUntried) {
		this.triedUntried = triedUntried;
	}
	public String getChargeStatus() {
		return chargeStatus;
	}
	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
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
