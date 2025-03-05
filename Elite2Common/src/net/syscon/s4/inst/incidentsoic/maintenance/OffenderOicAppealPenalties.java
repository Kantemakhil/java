package net.syscon.s4.inst.incidentsoic.maintenance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderOicAppealPenalties extends BaseModel implements Serializable {
	@JsonProperty("oicApprealId")
	private Integer oicApprealId;
	
	@JsonProperty("oicHearingId")
	private Integer oicHearingId;
	
	@JsonProperty("resultSeq")
	private Integer resultSeq;
	
	@JsonProperty("seq")
	private Integer seq;
	
	@JsonProperty("oicPenaltyType")
	private String oicPenaltyType;
	
	@JsonProperty("effectDate")
	private Date effectDate;
	
	@JsonProperty("month")
	private Integer month;
	
	@JsonProperty("days")
	private Integer days;
	
	@JsonProperty("compensation")
	private BigDecimal compensation;
	
	@JsonProperty("oicComment")
	private String oicComment;
	
	@JsonProperty("oicAppealIdLog")
	private Integer oicAppealIdLog;
	
	@JsonProperty("oicSeqLog")
	private Integer oicSeqLog;
	
	@JsonProperty("oicHearingIdLog")
	private Integer oicHearingIdLog;
	
	@JsonProperty("resultSeqLog")
	private Integer resultSeqLog;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("offenderAdjustId")
	private Integer offenderAdjustId;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date  modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("offenderBookingId")
	private BigDecimal offenderBookingId;
	
	@JsonProperty("hearingDate")
	private String hearingDate;
	
	@JsonProperty("hearingTime")
	private String hearingTime;
	
	@JsonProperty("oicResultSeqLog")
	private Integer oicResultSeqLog;
	
	
	
	public Integer getOicResultSeqLog() {
		return oicResultSeqLog;
	}

	public void setOicResultSeqLog(Integer oicResultSeqLog) {
		this.oicResultSeqLog = oicResultSeqLog;
	}

	public String getHearingDate() {
		return hearingDate;
	}

	public void setHearingDate(String hearingDate) {
		this.hearingDate = hearingDate;
	}

	public String getHearingTime() {
		return hearingTime;
	}

	public void setHearingTime(String hearingTime) {
		this.hearingTime = hearingTime;
	}

	public BigDecimal getOffenderBookingId() {
		return offenderBookingId;
	}

	public void setOffenderBookingId(BigDecimal offenderBookingId) {
		this.offenderBookingId = offenderBookingId;
	}

	public Integer getOicApprealId() {
		return oicApprealId;
	}

	public void setOicApprealId(Integer oicApprealId) {
		this.oicApprealId = oicApprealId;
	}

	public Integer getOicHearingId() {
		return oicHearingId;
	}

	public void setOicHearingId(Integer oicHearingId) {
		this.oicHearingId = oicHearingId;
	}

	public Integer getResultSeq() {
		return resultSeq;
	}

	public void setResultSeq(Integer resultSeq) {
		this.resultSeq = resultSeq;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getOicPenaltyType() {
		return oicPenaltyType;
	}

	public void setOicPenaltyType(String oicPenaltyType) {
		this.oicPenaltyType = oicPenaltyType;
	}

	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public BigDecimal getCompensation() {
		return compensation;
	}

	public void setCompensation(BigDecimal compensation) {
		this.compensation = compensation;
	}

	public String getOicComment() {
		return oicComment;
	}

	public void setOicComment(String oicComment) {
		this.oicComment = oicComment;
	}

	public Integer getOicAppealIdLog() {
		return oicAppealIdLog;
	}

	public void setOicAppealIdLog(Integer oicAppealIdLog) {
		this.oicAppealIdLog = oicAppealIdLog;
	}

	public Integer getOicSeqLog() {
		return oicSeqLog;
	}

	public void setOicSeqLog(Integer oicSeqLog) {
		this.oicSeqLog = oicSeqLog;
	}

	public Integer getOicHearingIdLog() {
		return oicHearingIdLog;
	}

	public void setOicHearingIdLog(Integer oicHearingIdLog) {
		this.oicHearingIdLog = oicHearingIdLog;
	}

	public Integer getResultSeqLog() {
		return resultSeqLog;
	}

	public void setResultSeqLog(Integer resultSeqLog) {
		this.resultSeqLog = resultSeqLog;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOffenderAdjustId() {
		return offenderAdjustId;
	}

	public void setOffenderAdjustId(Integer offenderAdjustId) {
		this.offenderAdjustId = offenderAdjustId;
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


}
