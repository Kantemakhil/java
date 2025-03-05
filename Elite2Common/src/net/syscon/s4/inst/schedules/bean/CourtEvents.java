package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.converters.BigDecimalConverter;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.Offenders;

/**
 * 
 * class CourtEvents
 *
 */
public class CourtEvents extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("eventId")
	private int eventId;
	@JsonProperty("caseId")
	private Integer caseId;
	@JsonProperty("offenderBookId")
	private int offenderBookId;
	@JsonProperty("eventDate")
	private Date eventDate;
	@JsonProperty("startTime")
	private Date startTime;
	@JsonProperty("endTime")
	private Date endTime;
	@JsonProperty("courtEventType")
	private String courtEventType;
	@JsonProperty("judgeName")
	private String judgeName;
	@JsonProperty("eventStatus")
	private String eventStatus;
	@JsonProperty("parentEventId")
	private Integer parentEventId;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("outcomeReasonCode")
	private String outcomeReasonCode;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("eventOutcome")
	private String eventOutcome;
	@JsonProperty("nextEventRequestFlag")
	private String nextEventRequestFlag;
	@JsonProperty("orderRequestedFlag")
	private String orderRequestedFlag;
	@JsonProperty("resultCode")
	private String resultCode;
	@JsonProperty("nextEventDate")
	private Date nextEventDate;
	@JsonProperty("nextEventStartTime")
	private Date nextEventStartTime;
	@JsonProperty("outcomeDate")
	private Date outcomeDate;
	@JsonProperty("offenderProceedingId")
	private Integer offenderProceedingId;
	@JsonProperty("directionCode")
	private String directionCode;
	@JsonProperty("holdFlag")
	private String holdFlag;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("scheduledTripId")
	private Integer scheduledTripId;
	private boolean inserted;
	@JsonProperty("nbtLastName")
	private String nbtLastName;
	@JsonProperty("nbtFirstName")
	private String nbtFirstName;
	@JsonProperty("nbtOffenderIdDisplay")
	private String nbtOffenderIdDisplay;
	@JsonProperty("nbtInst")
	private String nbtInst;
	@JsonProperty("courtAgyLocId")
	private String courtAgyLocId;
	@JsonProperty("movementReasonCode")
	private String movementReasonCode;
	@JsonProperty("conflictFlag")
	private boolean conflictFlag;
	@JsonProperty("courtEventSubType")
	private String courtEventSubType;
	@JsonProperty("bailStatus")
	private String bailStatus;
	@JsonProperty("requestFlag")
	private String requestFlag;
	@JsonProperty("appealFlag")
	private String appealFlag;
	@JsonProperty("remarksFlag")
	private String remarksFlag;
	@JsonProperty("policeStnAgyLocId")
	private String policeStnAgyLocId;
	@JsonProperty("stateCode")
	private String stateCode;
	@JsonProperty("courtEventDesc")
	private String courtEventDesc;
	@JsonProperty("bailApplicationFlag")
	private String bailApplicationFlag;
	@JsonProperty("appealId")
	private Long appealId;
	private String agencyDescription;
	@JsonProperty("caseIdTemp")
	private String caseIdTemp;
	@JsonProperty("courtEventTypeTemp")
	private String courtEventTypeTemp;
	private String eventOutComeDescription;
	private String matter;
	private String appearanceLocation;
	private String appearanceType;
	private String hearingReason;
	private String court;
	private Integer countOutcomeReason;
	
	@JsonProperty("linkData")
	private Integer linkData;
	
	@JsonProperty("emailFlag")
	private String emailFlag;
	@JsonProperty("smsFlag")
	private String smsFlag;
	@JsonProperty("emailScheduleHoursBefore")
	private Integer emailScheduleHoursBefore;
	@JsonProperty("smsScheduleHoursBefore")
	private Integer smsScheduleHoursBefore;
	
	@JsonProperty("emailCheckFlag")
	private Boolean emailCheckFlag;
	@JsonProperty("phoneNumberCheckFlag")
	private Boolean phoneNumberCheckFlag;
	

	public Boolean getEmailCheckFlag() {
		return emailCheckFlag;
	}

	public void setEmailCheckFlag(Boolean emailCheckFlag) {
		this.emailCheckFlag = emailCheckFlag;
	}

	public Boolean getPhoneNumberCheckFlag() {
		return phoneNumberCheckFlag;
	}

	public void setPhoneNumberCheckFlag(Boolean phoneNumberCheckFlag) {
		this.phoneNumberCheckFlag = phoneNumberCheckFlag;
	}

	public String getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public Integer getEmailScheduleHoursBefore() {
		return emailScheduleHoursBefore;
	}

	public void setEmailScheduleHoursBefore(Integer emailScheduleHoursBefore) {
		this.emailScheduleHoursBefore = emailScheduleHoursBefore;
	}

	public Integer getSmsScheduleHoursBefore() {
		return smsScheduleHoursBefore;
	}

	public void setSmsScheduleHoursBefore(Integer smsScheduleHoursBefore) {
		this.smsScheduleHoursBefore = smsScheduleHoursBefore;
	}

	public Integer getCountOutcomeReason() {
		return countOutcomeReason;
	}

	public void setCountOutcomeReason(Integer countOutcomeReason) {
		this.countOutcomeReason = countOutcomeReason;
	}


	private Boolean cancelFlag;
	
	
	public Boolean getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}


	@JsonProperty("caseLoad")
	private String caseLoad;

	public String getCaseLoad() {
		return caseLoad;
	}

	public void setCaseLoad(String caseLoad) {
		this.caseLoad = caseLoad;
	}


	@JsonProperty("externalNonAssDetailsInd")
	private String externalNonAssDetailsInd;

	@JsonProperty("externalNonAssDetailsGang")
	private String externalNonAssDetailsGang;

	@JsonProperty("offenderNonAssociationsByInd")
	private List<Integer> offenderNonAssociationsByInd;

	

	@JsonProperty("offenderNonAssociationsByGang")
	private List<Integer> offenderNonAssociationsByGang;

	
	public List<Integer> getOffenderNonAssociationsByInd() {
		return offenderNonAssociationsByInd;
	}

	public void setOffenderNonAssociationsByInd(List<Integer> offenderNonAssociationsByInd) {
		this.offenderNonAssociationsByInd = offenderNonAssociationsByInd;
	}

	public List<Integer> getOffenderNonAssociationsByGang() {
		return offenderNonAssociationsByGang;
	}

	public void setOffenderNonAssociationsByGang(List<Integer> offenderNonAssociationsByGang) {
		this.offenderNonAssociationsByGang = offenderNonAssociationsByGang;
	}

	public String getExternalNonAssDetailsInd() {
		return externalNonAssDetailsInd;
	}

	public void setExternalNonAssDetailsInd(String externalNonAssDetailsInd) {
		this.externalNonAssDetailsInd = externalNonAssDetailsInd;
	}

	public String getExternalNonAssDetailsGang() {
		return externalNonAssDetailsGang;
	}

	public void setExternalNonAssDetailsGang(String externalNonAssDetailsGang) {
		this.externalNonAssDetailsGang = externalNonAssDetailsGang;
	}

	@JsonProperty("toAgyLocId")
	private String toAgyLocId;

	@JsonProperty("sentenseSeq")
	private BigDecimal sentenseSeq;

	@JsonProperty("recommendedSanctionCount")
	private BigDecimal recommendedSanctionCount;

	@JsonProperty("recommendedRewardCount")
	private BigDecimal recommendedRewardCount;

	@JsonProperty("orderType")
	private String orderType;

	@JsonProperty("additionalCountsComment")
	private String additionalCountsComment;

	public Long getAppealId() {
		return appealId;
	}

	public void setAppealId(Long appealId) {
		this.appealId = appealId;
	}

	public String getBailApplicationFlag() {
		return bailApplicationFlag;
	}

	public void setBailApplicationFlag(String bailApplicationFlag) {
		this.bailApplicationFlag = bailApplicationFlag;
	}

	public String getCourtEventDesc() {
		return courtEventDesc;
	}

	public void setCourtEventDesc(final String courtEventDesc) {
		this.courtEventDesc = courtEventDesc;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(final String officerName) {
		this.officerName = officerName;
	}

	public String getEscortCode() {
		return escortCode;
	}

	public void setEscortCode(final String escortCode) {
		this.escortCode = escortCode;
	}

	@JsonProperty("officerName")
	private String officerName;
	@JsonProperty("escortCode")
	private String escortCode;

	public String getAppealFlag() {
		return appealFlag;
	}

	public void setAppealFlag(final String appealFlag) {
		this.appealFlag = appealFlag;
	}

	public String getRemarksFlag() {
		return remarksFlag;
	}

	public void setRemarksFlag(final String remarksFlag) {
		this.remarksFlag = remarksFlag;
	}

	public String getPoliceStnAgyLocId() {
		return policeStnAgyLocId;
	}

	public void setPoliceStnAgyLocId(final String policeStnAgyLocId) {
		this.policeStnAgyLocId = policeStnAgyLocId;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(final String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @param eventId eventId to set
	 */
	public void setEventId(final int eventId) {
		this.eventId = eventId;
	}

	/**
	 * return theeventId
	 */
	public int getEventId() {
		return this.eventId;
	}

	/**
	 * @param caseId caseId to set
	 */
	public void setCaseId(final Integer caseId) {
		this.caseId = caseId;
	}

	/**
	 * return thecaseId
	 */
	public Integer getCaseId() {
		return this.caseId;
	}

	/**
	 * @param offenderBookId offenderBookId to set
	 */
	public void setOffenderBookId(final int offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * return theoffenderBookId
	 */
	public int getOffenderBookId() {
		return this.offenderBookId;
	}

	/**
	 * @param eventDate eventDate to set
	 */
	public void setEventDate(final Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * return theeventDate
	 */
	public Date getEventDate() {
		return this.eventDate;
	}

	/**
	 * @param startTime startTime to set
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * return thestartTime
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * @param endTime endTime to set
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * return theendTime
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * @param courtEventType courtEventType to set
	 */
	public void setCourtEventType(final String courtEventType) {
		this.courtEventType = courtEventType;
	}

	/**
	 * return thecourtEventType
	 */
	public String getCourtEventType() {
		return this.courtEventType;
	}

	/**
	 * @param judgeName judgeName to set
	 */
	public void setJudgeName(final String judgeName) {
		this.judgeName = judgeName;
	}

	/**
	 * return thejudgeName
	 */
	public String getJudgeName() {
		return this.judgeName;
	}

	/**
	 * @param eventStatus eventStatus to set
	 */
	public void setEventStatus(final String eventStatus) {
		this.eventStatus = eventStatus;
	}

	/**
	 * return theeventStatus
	 */
	public String getEventStatus() {
		return this.eventStatus;
	}

	/**
	 * @param parentEventId parentEventId to set
	 */
	public void setParentEventId(final Integer parentEventId) {
		this.parentEventId = parentEventId;
	}

	/**
	 * return theparentEventId
	 */
	public Integer getParentEventId() {
		return this.parentEventId;
	}

	/**
	 * @param agyLocId agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * return theagyLocId
	 */
	public String getAgyLocId() {
		return this.agyLocId;
	}

	/**
	 * @param outcomeReasonCode outcomeReasonCode to set
	 */
	public void setOutcomeReasonCode(final String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	/**
	 * return theoutcomeReasonCode
	 */
	public String getOutcomeReasonCode() {
		return this.outcomeReasonCode;
	}

	/**
	 * @param commentText commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * return thecommentText
	 */
	public String getCommentText() {
		return this.commentText;
	}

	/**
	 * @param createDatetime createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param createUserId createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param modifyDatetime modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param modifyUserId modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param eventOutcome eventOutcome to set
	 */
	public void setEventOutcome(final String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	/**
	 * return theeventOutcome
	 */
	public String getEventOutcome() {
		return this.eventOutcome;
	}

	/**
	 * @param nextEventRequestFlag nextEventRequestFlag to set
	 */
	public void setNextEventRequestFlag(final String nextEventRequestFlag) {
		this.nextEventRequestFlag = nextEventRequestFlag;
	}

	/**
	 * return thenextEventRequestFlag
	 */
	public String getNextEventRequestFlag() {
		return this.nextEventRequestFlag;
	}

	/**
	 * @param orderRequestedFlag orderRequestedFlag to set
	 */
	public void setOrderRequestedFlag(final String orderRequestedFlag) {
		this.orderRequestedFlag = orderRequestedFlag;
	}

	/**
	 * return theorderRequestedFlag
	 */
	public String getOrderRequestedFlag() {
		return this.orderRequestedFlag;
	}

	/**
	 * @param resultCode resultCode to set
	 */
	public void setResultCode(final String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * return theresultCode
	 */
	public String getResultCode() {
		return this.resultCode;
	}

	/**
	 * @param nextEventDate nextEventDate to set
	 */
	public void setNextEventDate(final Date nextEventDate) {
		this.nextEventDate = nextEventDate;
	}

	/**
	 * return thenextEventDate
	 */
	public Date getNextEventDate() {
		return this.nextEventDate;
	}

	/**
	 * @param nextEventStartTime nextEventStartTime to set
	 */
	public void setNextEventStartTime(final Date nextEventStartTime) {
		this.nextEventStartTime = nextEventStartTime;
	}

	/**
	 * return thenextEventStartTime
	 */
	public Date getNextEventStartTime() {
		return this.nextEventStartTime;
	}

	/**
	 * @param outcomeDate outcomeDate to set
	 */
	public void setOutcomeDate(final Date outcomeDate) {
		this.outcomeDate = outcomeDate;
	}

	/**
	 * return theoutcomeDate
	 */
	public Date getOutcomeDate() {
		return this.outcomeDate;
	}

	/**
	 * @param offenderProceedingId offenderProceedingId to set
	 */
	public void setOffenderProceedingId(final Integer offenderProceedingId) {
		this.offenderProceedingId = offenderProceedingId;
	}

	/**
	 * return theoffenderProceedingId
	 */
	public Integer getOffenderProceedingId() {
		return this.offenderProceedingId;
	}

	/**
	 * @param directionCode directionCode to set
	 */
	public void setDirectionCode(final String directionCode) {
		this.directionCode = directionCode;
	}

	/**
	 * return thedirectionCode
	 */
	public String getDirectionCode() {
		return this.directionCode;
	}

	/**
	 * @param holdFlag holdFlag to set
	 */
	public void setHoldFlag(final String holdFlag) {
		this.holdFlag = holdFlag;
	}

	public String getAgencyDescription() {
		return agencyDescription;
	}

	public void setAgencyDescription(String agencyDescription) {
		this.agencyDescription = agencyDescription;
	}

	/**
	 * return theholdFlag
	 */
	public String getHoldFlag() {
		return this.holdFlag;
	}

	/**
	 * @param sealFlag sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @param scheduledTripId scheduledTripId to set
	 */
	public void setScheduledTripId(final Integer scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	/**
	 * return thescheduledTripId
	 */
	public Integer getScheduledTripId() {
		return this.scheduledTripId;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the nbtLastName
	 */
	public String getNbtLastName() {
		return nbtLastName;
	}

	/**
	 * @param nbtLastName the nbtLastName to set
	 */
	public void setNbtLastName(final String nbtLastName) {
		this.nbtLastName = nbtLastName;
	}

	/**
	 * @return the nbtFirstName
	 */
	public String getNbtFirstName() {
		return nbtFirstName;
	}

	/**
	 * @param nbtFirstName the nbtFirstName to set
	 */
	public void setNbtFirstName(final String nbtFirstName) {
		this.nbtFirstName = nbtFirstName;
	}

	/**
	 * @return the nbtOffenderIdDisplay
	 */
	public String getNbtOffenderIdDisplay() {
		return nbtOffenderIdDisplay;
	}

	/**
	 * @param nbtOffenderIdDisplay the nbtOffenderIdDisplay to set
	 */
	public void setNbtOffenderIdDisplay(final String nbtOffenderIdDisplay) {
		this.nbtOffenderIdDisplay = nbtOffenderIdDisplay;
	}

	/**
	 * @return the nbtInst
	 */
	public String getNbtInst() {
		return nbtInst;
	}

	/**
	 * @param nbtInst the nbtInst to set
	 */
	public void setNbtInst(final String nbtInst) {
		this.nbtInst = nbtInst;
	}

	/**
	 * @param courtAgyLocId courtAgyLocId to set
	 */
	public void setCourtAgyLocId(final String courtAgyLocId) {
		this.courtAgyLocId = courtAgyLocId;
	}

	/**
	 * return the courtAgyLocId
	 */
	public String getCourtAgyLocId() {
		return this.courtAgyLocId;
	}

	/**
	 * @param movementReasonCode movementReasonCode to set
	 */
	public void setMovementReasonCode(final String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	/**
	 * return the movementReasonCode
	 */
	public String getMovementReasonCode() {
		return this.movementReasonCode;
	}

	/**
	 * @return the conflictFlag
	 */
	public boolean getConflictFlag() {
		return conflictFlag;
	}

	/**
	 * @param conflictFlag the conflictFlag to set
	 */
	public void setConflictFlag(final boolean conflictFlag) {
		this.conflictFlag = conflictFlag;
	}

	public String getCourtEventSubType() {
		return courtEventSubType;
	}

	public void setCourtEventSubType(final String courtEventSubType) {
		this.courtEventSubType = courtEventSubType;
	}

	public String getBailStatus() {
		return bailStatus;
	}

	public void setBailStatus(final String bailStatus) {
		this.bailStatus = bailStatus;
	}

	public String getRequestFlag() {
		return requestFlag;
	}

	public void setRequestFlag(final String requestFlag) {
		this.requestFlag = requestFlag;
	}

	public String getCaseIdTemp() {
		return caseIdTemp;
	}

	public void setCaseIdTemp(String caseIdTemp) {
		this.caseIdTemp = caseIdTemp;
	}

	public String getCourtEventTypeTemp() {
		return courtEventTypeTemp;
	}

	public void setCourtEventTypeTemp(String courtEventTypeTemp) {
		this.courtEventTypeTemp = courtEventTypeTemp;
	}

	public String getEventOutComeDescription() {
		return eventOutComeDescription;
	}

	public void setEventOutComeDescription(String eventOutComeDescription) {
		this.eventOutComeDescription = eventOutComeDescription;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public String getAppearanceLocation() {
		return appearanceLocation;
	}

	public void setAppearanceLocation(String appearanceLocation) {
		this.appearanceLocation = appearanceLocation;
	}

	public String getAppearanceType() {
		return appearanceType;
	}

	public void setAppearanceType(String appearanceType) {
		this.appearanceType = appearanceType;
	}

	public String getHearingReason() {
		return hearingReason;
	}

	public void setHearingReason(String hearingReason) {
		this.hearingReason = hearingReason;
	}

	public String getCourt() {
		return court;
	}

	public String getMatter() {
		return matter;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public BigDecimal getRecommendedSanctionCount() {
		return recommendedSanctionCount;
	}

	public void setRecommendedSanctionCount(BigDecimal recommendedSanctionCount) {
		this.recommendedSanctionCount = recommendedSanctionCount;
	}

	public BigDecimal getRecommendedRewardCount() {
		return recommendedRewardCount;
	}

	public void setRecommendedRewardCount(BigDecimal recommendedRewardCount) {
		this.recommendedRewardCount = recommendedRewardCount;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public BigDecimal getSentenseSeq() {
		return sentenseSeq;
	}

	public void setSentenseSeq(BigDecimal sentenseSeq) {
		this.sentenseSeq = sentenseSeq;
	}

	public String getAdditionalCountsComment() {
		return additionalCountsComment;
	}

	public void setAdditionalCountsComment(String additionalCountsComment) {
		this.additionalCountsComment = additionalCountsComment;
	}

	
	public Integer getLinkData() {
		return linkData;
	}

	public void setLinkData(Integer linkData) {
		this.linkData = linkData;
	}

	@Override
	public String toString() {
		return "CourtEvents [eventId=" + eventId + ", caseId=" + caseId + ", offenderBookId=" + offenderBookId
				+ ", eventDate=" + eventDate + ", startTime=" + startTime + ", endTime=" + endTime + ", courtEventType="
				+ courtEventType + ", judgeName=" + judgeName + ", eventStatus=" + eventStatus + ", parentEventId="
				+ parentEventId + ", agyLocId=" + agyLocId + ", outcomeReasonCode=" + outcomeReasonCode
				+ ", commentText=" + commentText + ", createDatetime=" + createDatetime + ", createUserId="
				+ createUserId + ", modifyDatetime=" + modifyDatetime + ", modifyUserId=" + modifyUserId
				+ ", eventOutcome=" + eventOutcome + ", nextEventRequestFlag=" + nextEventRequestFlag
				+ ", orderRequestedFlag=" + orderRequestedFlag + ", resultCode=" + resultCode + ", nextEventDate="
				+ nextEventDate + ", nextEventStartTime=" + nextEventStartTime + ", outcomeDate=" + outcomeDate
				+ ", offenderProceedingId=" + offenderProceedingId + ", directionCode=" + directionCode + ", holdFlag="
				+ holdFlag + ", sealFlag=" + sealFlag + ", scheduledTripId=" + scheduledTripId + ", inserted="
				+ inserted + ", nbtLastName=" + nbtLastName + ", nbtFirstName=" + nbtFirstName
				+ ", nbtOffenderIdDisplay=" + nbtOffenderIdDisplay + ", nbtInst=" + nbtInst + ", courtAgyLocId="
				+ courtAgyLocId + ", movementReasonCode=" + movementReasonCode + ", conflictFlag=" + conflictFlag
				+ ", courtEventSubType=" + courtEventSubType + ", bailStatus=" + bailStatus + ", requestFlag="
				+ requestFlag + ", appealFlag=" + appealFlag + ", remarksFlag=" + remarksFlag + ", policeStnAgyLocId="
				+ policeStnAgyLocId + ", stateCode=" + stateCode + ", courtEventDesc=" + courtEventDesc
				+ ", bailApplicationFlag=" + bailApplicationFlag + ", appealId=" + appealId + ", agencyDescription="
				+ agencyDescription + ", caseIdTemp=" + caseIdTemp + ", courtEventTypeTemp=" + courtEventTypeTemp
				+ ", eventOutComeDescription=" + eventOutComeDescription + ", matter=" + matter
				+ ", appearanceLocation=" + appearanceLocation + ", appearanceType=" + appearanceType
				+ ", hearingReason=" + hearingReason + ", court=" + court + ", cancelFlag=" + cancelFlag + ", caseLoad="
				+ caseLoad + ", externalNonAssDetailsInd=" + externalNonAssDetailsInd + ", externalNonAssDetailsGang="
				+ externalNonAssDetailsGang + ", offenderNonAssociationsByInd=" + offenderNonAssociationsByInd
				+ ", offenderNonAssociationsByGang=" + offenderNonAssociationsByGang + ", toAgyLocId=" + toAgyLocId
				+ ", sentenseSeq=" + sentenseSeq + ", recommendedSanctionCount=" + recommendedSanctionCount
				+ ", recommendedRewardCount=" + recommendedRewardCount + ", orderType=" + orderType
				+ ", additionalCountsComment=" + additionalCountsComment + ", officerName=" + officerName
				+ ", escortCode=" + escortCode + "]";
	}



}
