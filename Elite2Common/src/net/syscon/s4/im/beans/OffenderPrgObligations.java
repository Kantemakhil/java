package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderPrgObligations extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderPrgObligationId")
	@NotNull
	private Long offenderPrgObligationId;

	@JsonProperty("availabilityCode")
	private String availabilityCode;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("decisionDate")
	private Date decisionDate;

	@JsonProperty("description")
	private String description;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("eventSubType")
	private String eventSubType;

	@JsonProperty("eventType")
	@NotNull
	@Size(max = 12)
	private String eventType;

	@JsonProperty("length")
	private Long length;

	@JsonProperty("lengthUnit")
	private String lengthUnit;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("obligationSource")
	@NotNull
	@Size(max = 12)
	private String obligationSource;

	@JsonProperty("offenderBookId")
	@NotNull
	private Long offenderBookId;

	@JsonProperty("offenderSentConditionId")
	private Long offenderSentConditionId;

	@JsonProperty("offenderSentCondActId")
	private Long offenderSentCondActId;

	@JsonProperty("programId")
	private Long programId;

	@JsonProperty("referralDate")
	@NotNull
	private Date referralDate;

	@JsonProperty("referralPriority")
	private String referralPriority;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("sentenceSeq")
	private Long sentenceSeq;

	@JsonProperty("specialNeedFlag")
	private String specialNeedFlag;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("status")
	@NotNull
	@Size(max = 12)
	private String status;

	@JsonProperty("statusChangeDate")
	private Date statusChangeDate;

	@JsonProperty("statusChangeReason")
	private String statusChangeReason;

	@JsonProperty("statusDescription")
	private String statusDescription;
	
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;

	@JsonProperty("sentenceCategory")
	private String sentenceCategory;

	@JsonProperty("sentenceConditionDesc")
	private String sentenceConditionDesc;

	@JsonProperty("sentenceDesc")
	private String sentenceDesc;

	@JsonProperty("sentenceEndDate")
	private Date sentenceEndDate;

	@JsonProperty("sentenceStartDate")
	private Date sentenceStartDate;

	@JsonProperty("sentenceStatus")
	private String sentenceStatus;
	
	private String oblCur;
	
	@JsonProperty("orderType")
	private String orderType;
	
	@JsonProperty("programDesc")
	private String programDesc;

	/**
	 * Creates new OffenderPrgObligation class Object
	 */
	public OffenderPrgObligations() {
		// OffenderPrgObligations
	}

	/**
	 * @return the statusDescription
	 */
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * @param statusDescription
	 *            the statusDescription to set
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	/**
	 * @return the offenderSentCondActId
	 */
	public Long getOffenderSentCondActId() {
		return offenderSentCondActId;
	}

	/**
	 * @param offenderSentCondActId
	 *            the offenderSentCondActId to set
	 */
	public void setOffenderSentCondActId(Long offenderSentCondActId) {
		this.offenderSentCondActId = offenderSentCondActId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the offenderPrgObligationId
	 */
	public Long getOffenderPrgObligationId() {
		return offenderPrgObligationId;
	}

	/**
	 * @param offenderPrgObligationId
	 *            the offenderPrgObligationId to set
	 */
	public void setOffenderPrgObligationId(Long offenderPrgObligationId) {
		this.offenderPrgObligationId = offenderPrgObligationId;
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
	public void setAvailabilityCode(String availabilityCode) {
		this.availabilityCode = availabilityCode;
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
	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
	public void setCreateDatetime(Date createDatetime) {
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
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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
	public void setDecisionDate(Date decisionDate) {
		this.decisionDate = decisionDate;
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
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
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
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the length
	 */
	public Long getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(Long length) {
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
	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
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
	public void setModifyDatetime(Date modifyDatetime) {
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
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
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
	public void setObligationSource(String obligationSource) {
		this.obligationSource = obligationSource;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the offenderSentConditionId
	 */
	public Long getOffenderSentConditionId() {
		return offenderSentConditionId;
	}

	/**
	 * @param offenderSentConditionId
	 *            the offenderSentConditionId to set
	 */
	public void setOffenderSentConditionId(Long offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}

	/**
	 * @return the programId
	 */
	public Long getProgramId() {
		return programId;
	}

	/**
	 * @param programId
	 *            the programId to set
	 */
	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	/**
	 * @return the referralDate
	 */
	public Date getReferralDate() {
		return referralDate;
	}

	/**
	 * @param referralDate
	 *            the referralDate to set
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	/**
	 * @return the referralPriority
	 */
	public String getReferralPriority() {
		return referralPriority;
	}

	/**
	 * @param referralPriority
	 *            the referralPriority to set
	 */
	public void setReferralPriority(String referralPriority) {
		this.referralPriority = referralPriority;
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
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the sentenceSeq
	 */
	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	/**
	 * @param sentenceSeq
	 *            the sentenceSeq to set
	 */
	public void setSentenceSeq(Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
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
	public void setSpecialNeedFlag(String specialNeedFlag) {
		this.specialNeedFlag = specialNeedFlag;
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
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
	public void setStatus(String status) {
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
	public void setStatusChangeDate(Date statusChangeDate) {
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
	public void setStatusChangeReason(String statusChangeReason) {
		this.statusChangeReason = statusChangeReason;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOblCur() {
		return oblCur;
	}

	public void setOblCur(String oblCur) {
		this.oblCur = oblCur;
	}

	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	public String getSentenceConditionDesc() {
		return sentenceConditionDesc;
	}

	public void setSentenceConditionDesc(String sentenceConditionDesc) {
		this.sentenceConditionDesc = sentenceConditionDesc;
	}

	public String getSentenceDesc() {
		return sentenceDesc;
	}

	public void setSentenceDesc(String sentenceDesc) {
		this.sentenceDesc = sentenceDesc;
	}

	public Date getSentenceEndDate() {
		return sentenceEndDate;
	}

	public void setSentenceEndDate(Date sentenceEndDate) {
		this.sentenceEndDate = sentenceEndDate;
	}

	public Date getSentenceStartDate() {
		return sentenceStartDate;
	}

	public void setSentenceStartDate(Date sentenceStartDate) {
		this.sentenceStartDate = sentenceStartDate;
	}

	public String getSentenceStatus() {
		return sentenceStatus;
	}

	public void setSentenceStatus(String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}

	public String getOrderType() {
		return orderType;
	}


	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getProgramDesc() {
		return programDesc;
	}

	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}

	
	
}
