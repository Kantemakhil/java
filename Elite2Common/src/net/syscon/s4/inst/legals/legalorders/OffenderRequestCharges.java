package net.syscon.s4.inst.legals.legalorders;

import java.math.BigDecimal;
import java.util.Date;

public class OffenderRequestCharges {
	private long offenderBookId;

	private Integer eventId;

	private long requestSeq;
	
	private BigDecimal offenderChargeId;

	private Date createDatetime;

	private String createUserId;
	
	private Date modifyDatetime;

	private String modifyUserId;
	
	private Boolean applyFlag;
	
	public OffenderRequestCharges() {
		// OffenderRequestCharges
	}
	

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public long getRequestSeq() {
		return requestSeq;
	}

	public void setRequestSeq(long requestSeq) {
		this.requestSeq = requestSeq;
	}

	public BigDecimal getOffenderChargeId() {
		return offenderChargeId;
	}

	public void setOffenderChargeId(BigDecimal offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
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

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}


	public Boolean getApplyFlag() {
		return applyFlag;
	}


	public void setApplyFlag(Boolean applyFlag) {
		this.applyFlag = applyFlag;
	}
}
