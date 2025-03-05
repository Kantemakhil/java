	package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import net.syscon.s4.common.beans.BaseModel;



/**
 * The persistent class for the OFFENDER_SENTENCE_STATUSES database table.
 * 
 */
public class OffenderSentenceStatus extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private long offenderSentenceStatusId;

	private Object createDatetime;

	private String createUserId;

	private Object modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private String statusUpdateComment;

	private Object statusUpdateDate;

	private String statusUpdateReason;

	private BigDecimal statusUpdateStaffId;
	private String previousStatus;
	private String previousStatusDesc;

	//bi-directional many-to-one association to OffenderSentence
	private OffenderSentences offenderSentences;

 private String staffName;
	public OffenderSentenceStatus() {
	}

	public long getOffenderSentenceStatusId() {
		return this.offenderSentenceStatusId;
	}

	public void setOffenderSentenceStatusId(long offenderSentenceStatusId) {
		this.offenderSentenceStatusId = offenderSentenceStatusId;
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

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getStatusUpdateComment() {
		return this.statusUpdateComment;
	}

	public void setStatusUpdateComment(String statusUpdateComment) {
		this.statusUpdateComment = statusUpdateComment;
	}

	public Object getStatusUpdateDate() {
		return this.statusUpdateDate;
	}

	public void setStatusUpdateDate(Object statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public String getStatusUpdateReason() {
		return this.statusUpdateReason;
	}

	public void setStatusUpdateReason(String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	public BigDecimal getStatusUpdateStaffId() {
		return this.statusUpdateStaffId;
	}

	public void setStatusUpdateStaffId(BigDecimal statusUpdateStaffId) {
		this.statusUpdateStaffId = statusUpdateStaffId;
	}

	public OffenderSentences getOffenderSentences() {
		return this.offenderSentences;
	}

	public void setOffenderSentences(OffenderSentences offenderSentence) {
		this.offenderSentences = offenderSentences;
	}
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(String previousStatus) {
		this.previousStatus = previousStatus;
	}

	public String getPreviousStatusDesc() {
		return previousStatusDesc;
	}

	public void setPreviousStatusDesc(String previousStatusDesc) {
		this.previousStatusDesc = previousStatusDesc;
	}

}
