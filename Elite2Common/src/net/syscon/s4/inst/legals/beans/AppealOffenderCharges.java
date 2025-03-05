package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class AppealOffenderCharges extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("appealId")
	private Long appealId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("chargeSeq")
	private Long chargeSeq;
	
	@JsonProperty("allSentenceFlag")
	private String allSentenceFlag;
	
	@JsonProperty("orderId")
	private Long orderId;
	
	@JsonProperty("offenderChargeId")
	private Long offenderChargeId;

	public Long getAppealId() {
		return appealId;
	}

	public void setAppealId(Long appealId) {
		this.appealId = appealId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getChargeSeq() {
		return chargeSeq;
	}

	public void setChargeSeq(Long chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public String getAllSentenceFlag() {
		return allSentenceFlag;
	}

	public void setAllSentenceFlag(String allSentenceFlag) {
		this.allSentenceFlag = allSentenceFlag;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOffenderChargeId() {
		return offenderChargeId;
	}

	public void setOffenderChargeId(Long offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
	}
	
	
	

}
