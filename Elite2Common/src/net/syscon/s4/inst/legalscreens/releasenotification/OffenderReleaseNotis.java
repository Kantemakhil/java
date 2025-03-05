package net.syscon.s4.inst.legalscreens.releasenotification;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderReleaseNotis extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private long offenderBookId;
	
	@JsonProperty("notiSeq") 
	private long notiSeq;	
	
	@JsonProperty("notiAgencyPartyCode") 
	private String notiAgencyPartyCode;	
	
	@JsonProperty("recordStatus")
	private String recordStatus;
	
	@JsonProperty("notiCorpId")  
	private long notiCorpId;
	
	@JsonProperty("notiPersonId") 
	private long notiPersonId;	
	
	@JsonProperty("notifyMethod") 
	private String notifyMethod;
	
	@JsonProperty("commentText") 
	private String commentText;	
	
	@JsonProperty("notifyDate") 
	private Date notifyDate;
	
	@JsonProperty("expirationDate") 
	private Date expirationDate;
	
	@JsonProperty("expirationReason") 
	private String expirationReason;
	
	@JsonProperty("completionDate") 
	private Date completionDate;
	
	@JsonProperty("completeCommentText") 
	private String completeCommentText;
	
	@JsonProperty("completedByStaffId") 
	private Long completedByStaffId;
	
	@JsonProperty("registeredByStaffId") 
	private Long registeredByStaffId;
	
	@JsonProperty("completedBy") 
	private String completedBy;
	
	@JsonProperty("registeredBy") 
	private String registeredBy;
	
	@JsonProperty("modifyUserId") 
	private String modifyUserId;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId") 
	private String createUserId;	
	
	@JsonProperty("modifyDatetime")  
	private Date modifyDatetime;	
	
	@JsonProperty("sealFlag")  
	private String sealFlag;
	
	@JsonProperty("nbtStatus")  
	private String nbtStatus;
	
	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getNotiSeq() {
		return notiSeq;
	}

	public void setNotiSeq(long notiSeq) {
		this.notiSeq = notiSeq;
	}

	public String getNotiAgencyPartyCode() {
		return notiAgencyPartyCode;
	}

	public void setNotiAgencyPartyCode(String notiAgencyPartyCode) {
		this.notiAgencyPartyCode = notiAgencyPartyCode;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public long getNotiCorpId() {
		return notiCorpId;
	}

	public void setNotiCorpId(long notiCorpId) {
		this.notiCorpId = notiCorpId;
	}

	public long getNotiPersonId() {
		return notiPersonId;
	}

	public void setNotiPersonId(long notiPersonId) {
		this.notiPersonId = notiPersonId;
	}

	public String getNotifyMethod() {
		return notifyMethod;
	}

	public void setNotifyMethod(String notifyMethod) {
		this.notifyMethod = notifyMethod;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getExpirationReason() {
		return expirationReason;
	}

	public void setExpirationReason(String expirationReason) {
		this.expirationReason = expirationReason;
	}
	
	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public String getCompleteCommentText() {
		return completeCommentText;
	}

	public void setCompleteCommentText(String completeCommentText) {
		this.completeCommentText = completeCommentText;
	}

	public Long getCompletedByStaffId() {
		return completedByStaffId;
	}

	public void setCompletedByStaffId(Long completedByStaffId) {
		this.completedByStaffId = completedByStaffId;
	}

	public Long getRegisteredByStaffId() {
		return registeredByStaffId;
	}

	public void setRegisteredByStaffId(Long registeredByStaffId) {
		this.registeredByStaffId = registeredByStaffId;
	}
	
	public String getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	public String getRegisteredBy() {
		return registeredBy;
	}

	public void setRegisteredBy(String registeredBy) {
		this.registeredBy = registeredBy;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
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

	public String getNbtStatus() {
		return nbtStatus;
	}

	public void setNbtStatus(String nbtStatus) {
		this.nbtStatus = nbtStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	
}
