package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentenceCharges extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	
	@JsonProperty("sentenceSeq")
	private long sentenceSeq;
	
	@JsonProperty("offenderChargeId")
	private long offenderChargeId;
	
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

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public long getOffenderChargeId() {
		return offenderChargeId;
	}

	public void setOffenderChargeId(long offenderChargeId) {
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
