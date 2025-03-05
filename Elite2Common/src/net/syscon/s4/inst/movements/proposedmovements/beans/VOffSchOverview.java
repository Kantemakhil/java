package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VOffSchOverview extends BaseModel implements Serializable {

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	
	@JsonProperty("moveSeq")
	private Integer moveSeq;
	  
	@JsonProperty("intExt")
	private String intExt;
	
	@JsonProperty("statusCode")
	private String statusCode;
	
	@JsonProperty("tstatus")
	private String tstatus;
	
	@JsonProperty("txnStatus")
	private String txnStatus;
	
	
	@JsonProperty("eventDate")
	private Date eventDate;
	
	@JsonProperty("eventTime")
	private String eventTime;
	
	@JsonProperty("movementType")
	private String movementType;
	
	@JsonProperty("typeDescp")
	private String typeDescp;
	
	@JsonProperty("movementReason")
	private String movementReason;
	
	@JsonProperty("rsnDescp")
	private String rsnDescp;
	
	@JsonProperty("moduleName")
	private String moduleName;


	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getMoveSeq() {
		return moveSeq;
	}

	public void setMoveSeq(Integer moveSeq) {
		this.moveSeq = moveSeq;
	}

	public String getIntExt() {
		return intExt;
	}

	public void setIntExt(String intExt) {
		this.intExt = intExt;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getTstatus() {
		return tstatus;
	}

	public void setTstatus(String tstatus) {
		this.tstatus = tstatus;
	}

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public String getTypeDescp() {
		return typeDescp;
	}

	public void setTypeDescp(String typeDescp) {
		this.typeDescp = typeDescp;
	}

	public String getMovementReason() {
		return movementReason;
	}

	public void setMovementReason(String movementReason) {
		this.movementReason = movementReason;
	}

	public String getRsnDescp() {
		return rsnDescp;
	}

	public void setRsnDescp(String rsnDescp) {
		this.rsnDescp = rsnDescp;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	
}
