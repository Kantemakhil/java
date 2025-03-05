package net.syscon.s4.inst.movementexternal.beans;

import java.io.Serializable;

public class OffenderNotCompletions implements Serializable {
	private static final long serialVersionUID = 1L;

//	private OffenderNotCompletionPK id;

	private String commentText;

	private Object completionDate;

	private Object createDatetime;

	private String createUserId;

	private Object modifyDatetime;

	private String modifyUserId;

	private String notiAgencyPartyCode;

	private java.math.BigDecimal notiCorpId;

	private java.math.BigDecimal notiPersonId;

	private String notifyMethod;

	private String sealFlag;

	private String status;

	//bi-directional many-to-one association to OffenderPendNotifications
		
	private OffenderPendNotifications OffenderPendNotifications;
	
	private long offenderBookId;

	private long notiSeq;

	private long notiMoveSeq;

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

	public long getNotiMoveSeq() {
		return notiMoveSeq;
	}

	public void setNotiMoveSeq(long notiMoveSeq) {
		this.notiMoveSeq = notiMoveSeq;
	}

	public OffenderNotCompletions() {
	}

	/*public OffenderNotCompletionPK getId() {
		return this.id;
	}

	public void setId(OffenderNotCompletionPK id) {
		this.id = id;
	}*/

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Object getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(Object completionDate) {
		this.completionDate = completionDate;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getNotiAgencyPartyCode() {
		return this.notiAgencyPartyCode;
	}

	public void setNotiAgencyPartyCode(String notiAgencyPartyCode) {
		this.notiAgencyPartyCode = notiAgencyPartyCode;
	}

	public java.math.BigDecimal getNotiCorpId() {
		return this.notiCorpId;
	}

	public void setNotiCorpId(java.math.BigDecimal notiCorpId) {
		this.notiCorpId = notiCorpId;
	}

	public java.math.BigDecimal getNotiPersonId() {
		return this.notiPersonId;
	}

	public void setNotiPersonId(java.math.BigDecimal notiPersonId) {
		this.notiPersonId = notiPersonId;
	}

	public String getNotifyMethod() {
		return this.notifyMethod;
	}

	public void setNotifyMethod(String notifyMethod) {
		this.notifyMethod = notifyMethod;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OffenderPendNotifications getOffenderPendNotifications() {
		return this.OffenderPendNotifications;
	}

	public void setOffenderPendNotifications(OffenderPendNotifications OffenderPendNotifications) {
		this.OffenderPendNotifications = OffenderPendNotifications;
	}

}
