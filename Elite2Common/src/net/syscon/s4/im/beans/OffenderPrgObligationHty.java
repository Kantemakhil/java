package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the OFFENDER_PRG_OBLIGATION_HTY database table.
 * 
 */
public class OffenderPrgObligationHty implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderPrgObligationHtyId")
	private Integer offenderPrgObligationHtyId;
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
	@JsonProperty("status")
	private String status;
	@JsonProperty("statusChangeDate")
	private Date statusChangeDate;
	@JsonProperty("statusChangeReason")
	private String statusChangeReason;
	@JsonProperty("offenderPrgObligationId")
	private Integer offenderPrgObligationId;
	@JsonProperty("programId")
	private Integer programId;
	@JsonProperty("offenderSentcondActId")
	private Integer offenderSentcondActId;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("eventType")
	private String eventType;
	@JsonProperty("eventSubType")
	private String eventSubType;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("sentenceSeq")
	private Integer sentenceSeq;
	@JsonProperty("length")
	private Integer length;
	@JsonProperty("lengthUnit")
	private String lengthUnit;
	@JsonProperty("offenderSentconditionId")
	private Integer offenderSentconditionId;
	@JsonProperty("refferalDate")
	private Date refferalDate;
	@JsonProperty("specialNeedFlag")
	private String specialNeedFlag;
	@JsonProperty("availabilityCode")
	private String availabilityCode;
	@JsonProperty("obligationSource")
	private String obligationSource;
	@JsonProperty("refferealPriority")
	private String refferealPriority;
	@JsonProperty("decisionDate")
	private Date decisionDate;
	@JsonProperty("statusDesc")
	private String statusDesc;
	@JsonProperty("statusChangeReasonDesc")
	private String statusChangeReasonDesc;
	
	public OffenderPrgObligationHty() {
		// OffenderPrgObligationHty
	}

	/**
	 * @return the statusChangeReasonDesc
	 */
	public String getStatusChangeReasonDesc() {
		return statusChangeReasonDesc;
	}

	/**
	 * @param statusChangeReasonDesc the statusChangeReasonDesc to set
	 */
	public void setStatusChangeReasonDesc(String statusChangeReasonDesc) {
		this.statusChangeReasonDesc = statusChangeReasonDesc;
	}

	/**
	 * @return the statusDesc
	 */
	public String getStatusDesc() {
		return statusDesc;
	}

	/**
	 * @param statusDesc the statusDesc to set
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	/**
	 * @return the offenderPrgObligationHtyId
	 */
	public Integer getOffenderPrgObligationHtyId() {
		return offenderPrgObligationHtyId;
	}

	/**
	 * @param offenderPrgObligationHtyId
	 *            the offenderPrgObligationHtyId to set
	 */
	public void setOffenderPrgObligationHtyId(final Integer offenderPrgObligationHtyId) {
		this.offenderPrgObligationHtyId = offenderPrgObligationHtyId;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @return the statusChangeDate
	 */
	public Date getStatusChangeDate() {
		return statusChangeDate;
	}

	/**
	 * @param statusChangeDate
	 *            the statusChangeDate to set
	 */
	public void setStatusChangeDate(final Date statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}

	/**
	 * @return the statusChangeReason
	 */
	public String getStatusChangeReason() {
		return statusChangeReason;
	}

	/**
	 * @param statusChangeReason
	 *            the statusChangeReason to set
	 */
	public void setStatusChangeReason(final String statusChangeReason) {
		this.statusChangeReason = statusChangeReason;
	}

	/**
	 * @return the offenderPrgObligationId
	 */
	public Integer getOffenderPrgObligationId() {
		return offenderPrgObligationId;
	}

	/**
	 * @param offenderPrgObligationId
	 *            the offenderPrgObligationId to set
	 */
	public void setOffenderPrgObligationId(final Integer offenderPrgObligationId) {
		this.offenderPrgObligationId = offenderPrgObligationId;
	}

	/**
	 * @return the programId
	 */
	public Integer getProgramId() {
		return programId;
	}

	/**
	 * @param programId
	 *            the programId to set
	 */
	public void setProgramId(final Integer programId) {
		this.programId = programId;
	}

	/**
	 * @return the offenderSentcondActId
	 */
	public Integer getOffenderSentcondActId() {
		return offenderSentcondActId;
	}

	/**
	 * @param offenderSentcondActId
	 *            the offenderSentcondActId to set
	 */
	public void setOffenderSentcondActId(final Integer offenderSentcondActId) {
		this.offenderSentcondActId = offenderSentcondActId;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public void setEventType(final String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the eventSubType
	 */
	public String getEventSubType() {
		return eventSubType;
	}

	/**
	 * @param eventSubType
	 *            the eventSubType to set
	 */
	public void setEventSubType(final String eventSubType) {
		this.eventSubType = eventSubType;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the sentenceSeq
	 */
	public Integer getSentenceSeq() {
		return sentenceSeq;
	}

	/**
	 * @param sentenceSeq
	 *            the sentenceSeq to set
	 */
	public void setSentenceSeq(final Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(final Integer length) {
		this.length = length;
	}

	/**
	 * @return the lengthUnit
	 */
	public String getLengthUnit() {
		return lengthUnit;
	}

	/**
	 * @param lengthUnit
	 *            the lengthUnit to set
	 */
	public void setLengthUnit(final String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}

	/**
	 * @return the offenderSentconditionId
	 */
	public Integer getOffenderSentconditionId() {
		return offenderSentconditionId;
	}

	/**
	 * @param offenderSentconditionId
	 *            the offenderSentconditionId to set
	 */
	public void setOffenderSentconditionId(final Integer offenderSentconditionId) {
		this.offenderSentconditionId = offenderSentconditionId;
	}

	/**
	 * @return the refferalDate
	 */
	public Date getRefferalDate() {
		return refferalDate;
	}

	/**
	 * @param refferalDate
	 *            the refferalDate to set
	 */
	public void setRefferalDate(final Date refferalDate) {
		this.refferalDate = refferalDate;
	}

	/**
	 * @return the specialNeedFlag
	 */
	public String getSpecialNeedFlag() {
		return specialNeedFlag;
	}

	/**
	 * @param specialNeedFlag
	 *            the specialNeedFlag to set
	 */
	public void setSpecialNeedFlag(final String specialNeedFlag) {
		this.specialNeedFlag = specialNeedFlag;
	}

	/**
	 * @return the availabilityCode
	 */
	public String getAvailabilityCode() {
		return availabilityCode;
	}

	/**
	 * @param availabilityCode
	 *            the availabilityCode to set
	 */
	public void setAvailabilityCode(final String availabilityCode) {
		this.availabilityCode = availabilityCode;
	}

	/**
	 * @return the obligationSource
	 */
	public String getObligationSource() {
		return obligationSource;
	}

	/**
	 * @param obligationSource
	 *            the obligationSource to set
	 */
	public void setObligationSource(final String obligationSource) {
		this.obligationSource = obligationSource;
	}

	/**
	 * @return the refferealPriority
	 */
	public String getRefferealPriority() {
		return refferealPriority;
	}

	/**
	 * @param refferealPriority
	 *            the refferealPriority to set
	 */
	public void setRefferealPriority(final String refferealPriority) {
		this.refferealPriority = refferealPriority;
	}

	/**
	 * @return the decisionDate
	 */
	public Date getDecisionDate() {
		return decisionDate;
	}

	/**
	 * @param decisionDate
	 *            the decisionDate to set
	 */
	public void setDecisionDate(final Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
