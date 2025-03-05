package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderProposedMvmnts extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	@JsonProperty("movementSeq")
	private Integer movementSeq;

	@JsonProperty("fromAgyLocId")
	private String fromAgyLocId;

	@JsonProperty("toAgyLocId")
	private String toAgyLocId;

	@JsonProperty("movementType")
	private String movementType;

	@JsonProperty("movementReason")
	private String movementReason;

	@JsonProperty("eventDate")
	private Date eventDate;

	@JsonProperty("moveByDate")
	private Date moveByDate;

	@JsonProperty("moveAllowDate")
	private Date moveAllowDate;

	@JsonProperty("priorityCode")
	private String priorityCode;

	@JsonProperty("priorityAssignedBy")
	private String priorityAssignedBy;

	@JsonProperty("priorityAssignedDate")
	private Date priorityAssignedDate;

	@JsonProperty("reasonText")
	private String reasonText;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("scheduledTripId")
	private Integer scheduledTripId;

	@JsonProperty("judge")
	private String judge;

	@JsonProperty("alternateAgyLocId")
	private String alternateAgyLocId;

	@JsonProperty("algoComment")
	private String algoComment;

	@JsonProperty("userDefineFlag1")
	private String userDefineFlag1;

	@JsonProperty("userDefineFlag2")
	private String userDefineFlag2;

	@JsonProperty("tmpGroupId")
	private Integer tmpGroupId;

	@JsonProperty("fromAgySeq")
	private Integer fromAgySeq;

	@JsonProperty("toAgySeq")
	private Integer toAgySeq;

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

	@JsonProperty("ApprDate")
	private Date ApprDate;

	@JsonProperty("AppRsn")
	private String AppRsn;

	@JsonProperty("AppStat")
	private String AppStat;

	@JsonProperty("AppUser")
	private String AppUser;

	@JsonProperty("TxnRsn")
	private String TxnRsn;

	@JsonProperty("TxnUser")
	private String TxnUser;

	@JsonProperty("TxnStat")
	private String TxnStat;

	@JsonProperty("nbtApprDate")
	private Date nbtApprDate;

	@JsonProperty("nbtAppRsn")
	private String nbtAppRsn;

	@JsonProperty("nbtAppStat")
	private String nbtAppStat;

	@JsonProperty("nbtAppUser")
	private String nbtAppUser;

	@JsonProperty("nbtTxnRsn")
	private String nbtTxnRsn;

	@JsonProperty("nbtTxnUser")
	private String nbtTxnUser;

	@JsonProperty("nbtTxnStat")
	private String nbtTxnStat;
	
	@JsonProperty("nbtEventDate")
	private Date nbtEventDate;
	
	
	@JsonProperty("initiatedBy")
	private String initiatedBy;
	
	@JsonProperty("approvedBy")
	private String approvedBy;
	
	
	@JsonProperty("reason")
	private String reason;
	
	@JsonProperty("requestCancellation")
	private Boolean requestCancellation;
	
	@JsonProperty("requestedBy")
	private String requestedBy;
	
	@JsonProperty("eventTime")
	private Date eventTime;
	
	
	@JsonProperty("cantmoveuntil")
	private String cantmoveuntil;
	
	@JsonProperty("mustmoveBy")
	private String mustmoveBy;

	@JsonProperty("movementcomment")
	private String movementcomment;

	@JsonProperty("moveType")
	private String moveType;

	@JsonProperty("vCount")
	private Long vCount;

	@JsonProperty("lvReturnCheckSecurity")
	private String lvReturnCheckSecurity;

	@JsonProperty("lvReturnCheckNonAsso")
	private String lvReturnCheckNonAsso;

	public Long getvCount() {
		return vCount;
	}

	public void setvCount(Long vCount) {
		this.vCount = vCount;
	}

	public String getLvReturnCheckSecurity() {
		return lvReturnCheckSecurity;
	}

	public void setLvReturnCheckSecurity(String lvReturnCheckSecurity) {
		this.lvReturnCheckSecurity = lvReturnCheckSecurity;
	}

	public String getLvReturnCheckNonAsso() {
		return lvReturnCheckNonAsso;
	}

	public void setLvReturnCheckNonAsso(String lvReturnCheckNonAsso) {
		this.lvReturnCheckNonAsso = lvReturnCheckNonAsso;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public String getCantmoveuntil() {
		return cantmoveuntil;
	}

	public void setCantmoveuntil(String cantmoveuntil) {
		this.cantmoveuntil = cantmoveuntil;
	}

	public String getMustmoveBy() {
		return mustmoveBy;
	}

	public void setMustmoveBy(String mustmoveBy) {
		this.mustmoveBy = mustmoveBy;
	}

	public String getMovementcomment() {
		return movementcomment;
	}

	public void setMovementcomment(String movementcomment) {
		this.movementcomment = movementcomment;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public String getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getRequestCancellation() {
		return requestCancellation;
	}

	public void setRequestCancellation(Boolean requestCancellation) {
		this.requestCancellation = requestCancellation;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public Date getNbtApprDate() {
		return nbtApprDate;
	}

	public void setNbtApprDate(Date nbtApprDate) {
		this.nbtApprDate = nbtApprDate;
	}

	public String getNbtAppRsn() {
		return nbtAppRsn;
	}

	public void setNbtAppRsn(String nbtAppRsn) {
		this.nbtAppRsn = nbtAppRsn;
	}

	public String getNbtAppStat() {
		return nbtAppStat;
	}

	public void setNbtAppStat(String nbtAppStat) {
		this.nbtAppStat = nbtAppStat;
	}

	public String getNbtAppUser() {
		return nbtAppUser;
	}

	public void setNbtAppUser(String nbtAppUser) {
		this.nbtAppUser = nbtAppUser;
	}

	public String getNbtTxnRsn() {
		return nbtTxnRsn;
	}

	public void setNbtTxnRsn(String nbtTxnRsn) {
		this.nbtTxnRsn = nbtTxnRsn;
	}

	public String getNbtTxnUser() {
		return nbtTxnUser;
	}

	public void setNbtTxnUser(String nbtTxnUser) {
		this.nbtTxnUser = nbtTxnUser;
	}

	public String getNbtTxnStat() {
		return nbtTxnStat;
	}

	public void setNbtTxnStat(String nbtTxnStat) {
		this.nbtTxnStat = nbtTxnStat;
	}

	public Date getNbtEventDate() {
		return nbtEventDate;
	}

	public void setNbtEventDate(Date nbtEventDate) {
		this.nbtEventDate = nbtEventDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getApprDate() {
		return ApprDate;
	}

	public void setApprDate(Date apprDate) {
		ApprDate = apprDate;
	}

	public String getAppRsn() {
		return AppRsn;
	}

	public void setAppRsn(String appRsn) {
		AppRsn = appRsn;
	}

	public String getAppStat() {
		return AppStat;
	}

	public void setAppStat(String appStat) {
		AppStat = appStat;
	}

	public String getAppUser() {
		return AppUser;
	}

	public void setAppUser(String appUser) {
		AppUser = appUser;
	}

	public String getTxnRsn() {
		return TxnRsn;
	}

	public void setTxnRsn(String txnRsn) {
		TxnRsn = txnRsn;
	}

	public String getTxnUser() {
		return TxnUser;
	}

	public void setTxnUser(String txnUser) {
		TxnUser = txnUser;
	}

	public String getTxnStat() {
		return TxnStat;
	}

	public void setTxnStat(String txnStat) {
		TxnStat = txnStat;
	}

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getMovementSeq() {
		return movementSeq;
	}

	public void setMovementSeq(Integer movementSeq) {
		this.movementSeq = movementSeq;
	}

	public String getFromAgyLocId() {
		return fromAgyLocId;
	}

	public void setFromAgyLocId(String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public String getMovementReason() {
		return movementReason;
	}

	public void setMovementReason(String movementReason) {
		this.movementReason = movementReason;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getMoveByDate() {
		return moveByDate;
	}

	public void setMoveByDate(Date moveByDate) {
		this.moveByDate = moveByDate;
	}

	public Date getMoveAllowDate() {
		return moveAllowDate;
	}

	public void setMoveAllowDate(Date moveAllowDate) {
		this.moveAllowDate = moveAllowDate;
	}

	public String getPriorityCode() {
		return priorityCode;
	}

	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}

	public String getPriorityAssignedBy() {
		return priorityAssignedBy;
	}

	public void setPriorityAssignedBy(String priorityAssignedBy) {
		this.priorityAssignedBy = priorityAssignedBy;
	}

	public Date getPriorityAssignedDate() {
		return priorityAssignedDate;
	}

	public void setPriorityAssignedDate(Date priorityAssignedDate) {
		this.priorityAssignedDate = priorityAssignedDate;
	}

	public String getReasonText() {
		return reasonText;
	}

	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Integer getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Integer scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	public String getAlternateAgyLocId() {
		return alternateAgyLocId;
	}

	public void setAlternateAgyLocId(String alternateAgyLocId) {
		this.alternateAgyLocId = alternateAgyLocId;
	}

	public String getAlgoComment() {
		return algoComment;
	}

	public void setAlgoComment(String algoComment) {
		this.algoComment = algoComment;
	}

	public String getUserDefineFlag1() {
		return userDefineFlag1;
	}

	public void setUserDefineFlag1(String userDefineFlag1) {
		this.userDefineFlag1 = userDefineFlag1;
	}

	public String getUserDefineFlag2() {
		return userDefineFlag2;
	}

	public void setUserDefineFlag2(String userDefineFlag2) {
		this.userDefineFlag2 = userDefineFlag2;
	}

	public Integer getTmpGroupId() {
		return tmpGroupId;
	}

	public void setTmpGroupId(Integer tmpGroupId) {
		this.tmpGroupId = tmpGroupId;
	}

	public Integer getFromAgySeq() {
		return fromAgySeq;
	}

	public void setFromAgySeq(Integer fromAgySeq) {
		this.fromAgySeq = fromAgySeq;
	}

	public Integer getToAgySeq() {
		return toAgySeq;
	}

	public void setToAgySeq(Integer toAgySeq) {
		this.toAgySeq = toAgySeq;
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