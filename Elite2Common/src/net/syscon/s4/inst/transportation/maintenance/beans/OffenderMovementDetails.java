package net.syscon.s4.inst.transportation.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderMovementDetails extends BaseModel implements Serializable {
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("movementSeq")
	private Long movementSeq;
	
	@JsonProperty("detailSeq")
	private Long detailSeq;
	
	@JsonProperty("statusCode")
	private String statusCode;
	
	@JsonProperty("recordedBy")
	private String recordedBy;
	
	@JsonProperty("recordedDate")
	private Date recordedDate;
	
	@JsonProperty("appRsn")
	private String appRsn;
	
	@JsonProperty("txnStatus")
	private String txnStatus;
	
	@JsonProperty("txnRsn")
	private String txnRsn;
	
	@JsonProperty("nonCompletionFlag")
	private String nonCompletionFlag;
	
	@JsonProperty("nonCompSchdTripId")
	private Long nonCompSchdTripId;
	
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
	
	@JsonProperty("schedYn")
	private String schedYn;
	
	@JsonProperty("scheduledTripId")
	private BigDecimal scheduledTripId;
	
	@JsonProperty("offenderId")
	private Integer offenderId;
	
	@JsonProperty("vCoice")
	private String vCoice;
	
	@JsonProperty("choice")
	 public String choice;
	
	@JsonProperty("nonAssoFlag")
	 public String nonAssoFlag;
	
	@JsonProperty("actionType")
	public String actionType;
	
	@JsonProperty("nonAdmInmateId")
	private Long nonAdmInmateId;
	
	@JsonProperty("selected")
	private Boolean selected;
	
	@JsonProperty("ScheduleDate")
	private Integer ScheduleDate;
	
	@JsonProperty("checkFlag")
	private Boolean checkFlag;
	
	public Boolean getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Integer getScheduleDate() {
		return ScheduleDate;
	}

	public void setScheduleDate(Integer scheduleDate) {
		ScheduleDate = scheduleDate;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Long getNonAdmInmateId() {
		return nonAdmInmateId;
	}

	public void setNonAdmInmateId(Long nonAdmInmateId) {
		this.nonAdmInmateId = nonAdmInmateId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getvCoice() {
		return vCoice;
	}

	public void setvCoice(String vCoice) {
		this.vCoice = vCoice;
	}
	
	public String getSchedYn() {
		return schedYn;
	}

	public void setSchedYn(String schedYn) {
		this.schedYn = schedYn;
	}

	public BigDecimal getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(BigDecimal scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public Integer getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Integer offenderId) {
		this.offenderId = offenderId;
	}

	public OffenderMovementDetails() {
		
	}




	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getMovementSeq() {
		return movementSeq;
	}

	public void setMovementSeq(Long movementSeq) {
		this.movementSeq = movementSeq;
	}

	public Long getDetailSeq() {
		return detailSeq;
	}

	public void setDetailSeq(Long detailSeq) {
		this.detailSeq = detailSeq;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getRecordedBy() {
		return recordedBy;
	}

	public void setRecordedBy(String recordedBy) {
		this.recordedBy = recordedBy;
	}

	public Date getRecordedDate() {
		return recordedDate;
	}

	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}

	public String getAppRsn() {
		return appRsn;
	}

	public void setAppRsn(String appRsn) {
		this.appRsn = appRsn;
	}

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public String getTxnRsn() {
		return txnRsn;
	}

	public void setTxnRsn(String txnRsn) {
		this.txnRsn = txnRsn;
	}

	public String getNonCompletionFlag() {
		return nonCompletionFlag;
	}

	public void setNonCompletionFlag(String nonCompletionFlag) {
		this.nonCompletionFlag = nonCompletionFlag;
	}

	public Long getNonCompSchdTripId() {
		return nonCompSchdTripId;
	}

	public void setNonCompSchdTripId(Long nonCompSchdTripId) {
		this.nonCompSchdTripId = nonCompSchdTripId;
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
	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
	public String getNonAssoFlag() {
		return nonAssoFlag;
	}

	public void setNonAssoFlag(String nonAssoFlag) {
		this.nonAssoFlag = nonAssoFlag;
	}

	@Override
	public String toString() {
		return "OffenderMovementDetails [offenderBookId=" + offenderBookId + ", movementSeq=" + movementSeq
				+ ", detailSeq=" + detailSeq + ", statusCode=" + statusCode + ", recordedBy=" + recordedBy
				+ ", recordedDate=" + recordedDate + ", appRsn=" + appRsn + ", txnStatus=" + txnStatus + ", txnRsn="
				+ txnRsn + ", nonCompletionFlag=" + nonCompletionFlag + ", nonCompSchdTripId=" + nonCompSchdTripId
				+ ", createDatetime=" + createDatetime + ", createUserId=" + createUserId + ", modifyDatetime="
				+ modifyDatetime + ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag + "]";
	}
	
	
	

}
