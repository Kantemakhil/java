package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderExternalMovements extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("activeFlag")
	@NotNull
	@Size(max = 1)
	private String activeFlag;

	@JsonProperty("applicationDate")
	private Date applicationDate;

	@JsonProperty("applicationTime")
	private Date applicationTime;

	@JsonProperty("arrestAgencyLocId")
	@Size(max = 6)
	private String arrestAgencyLocId;

	@JsonProperty("commentText")
	@Size(max = 240)
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("directionCode")
	@Size(max = 12)
	private String directionCode;

	@JsonProperty("escortCode")
	@Size(max = 12)
	private String escortCode;

	@JsonProperty("escortText")
	@Size(max = 240)
	private String escortText;

	@JsonProperty("eventId")
	private BigDecimal eventId;

	@JsonProperty("fromAddressId")
	private BigDecimal fromAddressId;

	@JsonProperty("fromAgyLocId")
	@Size(max = 6)
	private String fromAgyLocId;

	@JsonProperty("fromCity")
	@Size(max = 12)
	private String fromCity;

	@JsonProperty("internalScheduleReasonCode")
	@Size(max = 12)
	private String internalScheduleReasonCode;

	@JsonProperty("internalScheduleType")
	@Size(max = 12)
	private String internalScheduleType;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("movementDate")
	@NotNull
	private Date movementDate;

	@JsonProperty("movementTime")
	@NotNull
	private Date movementTime;

	@JsonProperty("ojLocationCode")
	@Size(max = 12)
	private String ojLocationCode;

	@JsonProperty("parentEventId")
	private BigDecimal parentEventId;

	@JsonProperty("reportingDate")
	private Date reportingDate;

	@JsonProperty("reportingTime")
	private Date reportingTime;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("toAddressId")
	private BigDecimal toAddressId;

	@JsonProperty("toAgyLocId")
	@Size(max = 6)
	private String toAgyLocId;

	@JsonProperty("toCity")
	@Size(max = 12)
	private String toCity;

	@JsonProperty("toCountryCode")
	@Size(max = 12)
	private String toCountryCode;

	@JsonProperty("toProvStatCode")
	@Size(max = 6)
	private String toProvStatCode;

	@JsonProperty("offenderBookId")
	@NotNull(message = "offender.id.cannot.be.null")
	private Long offenderBookId;

	@JsonProperty("movementSeq")
	@NotNull
	private Long movementSeq;

	@JsonProperty("movementType")
	@Size(max = 12)
	private String movementType;

	@JsonProperty("movementReasonCode")
	@Size(max = 12)
	private String movementReasonCode;

	@JsonProperty("bookingStatus")
	private String bookingStatus;

	@JsonProperty("offenderId")
	@NotNull
	private BigDecimal offenderId;

	@JsonProperty("assignedStaffId")
	private BigDecimal assignedStaffId;

	@JsonProperty("livUnitDesc")
	private String livUnitDesc;
	
	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;
	
	@JsonProperty("dspDescription")
	private String dspDescription;
	
	@JsonProperty("nbtBookNo")
	private String nbtBookNo;
	
	@JsonProperty("timeString")
	private String timeString;
	
	@JsonProperty("offIdDisplay")
	private String offIdDisplay;
	
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("livingUnitId")
	private String livingUnitId;

	@JsonProperty("newBookingFlag")
	private boolean newBookingFlag;

	@JsonProperty("reasonCodeValid")
	private String reasonCodeValid;
	
	@JsonProperty("birthDate")
	private Date birthDate;
	
	@JsonProperty("warningMsg")
	private String warningMsg;
	
	@JsonProperty("warningPrompt")
	private String warningPrompt;
	private String eventSubType;
	private Integer vCount;
	private Boolean vProceedFlg;
	private Long vNotiSeq;
	
	@JsonProperty("eventTime")
	private Date eventTime;
	
	@JsonProperty("proposedMvmntSeq")
	private Integer proposedMvmntSeq;
	
 
	

	@JsonProperty("inOutStatus")
	@NotNull
	@Size(max = 12)
	private String inOutStatus;
	@JsonProperty("notification")
	private String notification;
	
	private String authorization;
	
	@JsonProperty("intCorrelationId")
	private Long intCorrelationId;
	
	private Long OffenderBookIdTemp;

	public String admissionType;

	@JsonProperty("releaseConfNotification")
    public String releaseConfNotification;
	
	
	

	public String getInOutStatus() {
		return inOutStatus;
	}

	public void setInOutStatus(String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	public Long getvNotiSeq() {
		return vNotiSeq;
	}

	public void setvNotiSeq(Long vNotiSeq) {
		this.vNotiSeq = vNotiSeq;
	}

	public Boolean getvProceedFlg() {
		return vProceedFlg;
	}

	public void setvProceedFlg(Boolean vProceedFlg) {
		this.vProceedFlg = vProceedFlg;
	}

	public void setvCount(Integer vCount) {
		this.vCount = vCount;
	}

	public Integer getvCount() {
		return vCount;
	}

	public void setGetvCount(Integer getvCount) {
		this.vCount = getvCount;
	}

	public String getEventSubType() {
		return eventSubType;
	}

	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}

	/**
	 * @return the reasonCodeValid
	 */
	public String getReasonCodeValid() {
		return reasonCodeValid;
	}

	/**
	 * @param reasonCodeValid the reasonCodeValid to set
	 */
	public void setReasonCodeValid(final String reasonCodeValid) {
		this.reasonCodeValid = reasonCodeValid;
	}

	/**
	 * @return the assignedStaffId
	 */
	public final BigDecimal getAssignedStaffId() {
		return assignedStaffId;
	}

	/**
	 * @param assignedStaffId the assignedStaffId to set
	 */
	public final void setAssignedStaffId(final BigDecimal assignedStaffId) {
		this.assignedStaffId = assignedStaffId;
	}

	/**
	 * Creates new OffenderExternalMovements class Object
	 */
	public OffenderExternalMovements() {
		// Super();
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the applicationDate
	 */
	public Date getApplicationDate() {
		return applicationDate;
	}

	/**
	 * @param applicationDate the applicationDate to set
	 */
	public void setApplicationDate(final Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	/**
	 * @return the applicationTime
	 */
	public Date getApplicationTime() {
		return applicationTime;
	}

	/**
	 * @param applicationTime the applicationTime to set
	 */
	public void setApplicationTime(final Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	/**
	 * @return the arrestAgencyLocId
	 */
	public String getArrestAgencyLocId() {
		return arrestAgencyLocId;
	}

	/**
	 * @param arrestAgencyLocId the arrestAgencyLocId to set
	 */
	public void setArrestAgencyLocId(final String arrestAgencyLocId) {
		this.arrestAgencyLocId = arrestAgencyLocId;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
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
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the directionCode
	 */
	public String getDirectionCode() {
		return directionCode;
	}

	/**
	 * @param directionCode the directionCode to set
	 */
	public void setDirectionCode(final String directionCode) {
		this.directionCode = directionCode;
	}

	/**
	 * @return the escortCode
	 */
	public String getEscortCode() {
		return escortCode;
	}

	/**
	 * @param escortCode the escortCode to set
	 */
	public void setEscortCode(final String escortCode) {
		this.escortCode = escortCode;
	}

	/**
	 * @return the escortText
	 */
	public String getEscortText() {
		return escortText;
	}

	/**
	 * @param escortText the escortText to set
	 */
	public void setEscortText(final String escortText) {
		this.escortText = escortText;
	}

	/**
	 * @return the eventId
	 */
	public BigDecimal getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(final BigDecimal eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the fromAddressId
	 */
	public BigDecimal getFromAddressId() {
		return fromAddressId;
	}

	/**
	 * @param fromAddressId the fromAddressId to set
	 */
	public void setFromAddressId(final BigDecimal fromAddressId) {
		this.fromAddressId = fromAddressId;
	}

	/**
	 * @return the fromAgyLocId
	 */
	public String getFromAgyLocId() {
		return fromAgyLocId;
	}

	/**
	 * @param fromAgyLocId the fromAgyLocId to set
	 */
	public void setFromAgyLocId(final String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
	}

	/**
	 * @return the fromCity
	 */
	public String getFromCity() {
		return fromCity;
	}

	/**
	 * @param fromCity the fromCity to set
	 */
	public void setFromCity(final String fromCity) {
		this.fromCity = fromCity;
	}

	/**
	 * @return the internalScheduleReasonCode
	 */
	public String getInternalScheduleReasonCode() {
		return internalScheduleReasonCode;
	}

	/**
	 * @param internalScheduleReasonCode the internalScheduleReasonCode to set
	 */
	public void setInternalScheduleReasonCode(final String internalScheduleReasonCode) {
		this.internalScheduleReasonCode = internalScheduleReasonCode;
	}

	/**
	 * @return the internalScheduleType
	 */
	public String getInternalScheduleType() {
		return internalScheduleType;
	}

	/**
	 * @param internalScheduleType the internalScheduleType to set
	 */
	public void setInternalScheduleType(final String internalScheduleType) {
		this.internalScheduleType = internalScheduleType;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
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
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the movementDate
	 */
	public Date getMovementDate() {
		return movementDate;
	}

	/**
	 * @param movementDate the movementDate to set
	 */
	public void setMovementDate(final Date movementDate) {
		this.movementDate = movementDate;
	}

	/**
	 * @return the movementTime
	 */
	public Date getMovementTime() {
		return movementTime;
	}

	/**
	 * @param movementTime the movementTime to set
	 */
	public void setMovementTime(final Date movementTime) {
		this.movementTime = movementTime;
	}

	/**
	 * @return the ojLocationCode
	 */
	public String getOjLocationCode() {
		return ojLocationCode;
	}

	/**
	 * @param ojLocationCode the ojLocationCode to set
	 */
	public void setOjLocationCode(final String ojLocationCode) {
		this.ojLocationCode = ojLocationCode;
	}

	/**
	 * @return the parentEventId
	 */
	public BigDecimal getParentEventId() {
		return parentEventId;
	}

	/**
	 * @param parentEventId the parentEventId to set
	 */
	public void setParentEventId(final BigDecimal parentEventId) {
		this.parentEventId = parentEventId;
	}

	/**
	 * @return the reportingDate
	 */
	public Date getReportingDate() {
		return reportingDate;
	}

	/**
	 * @param reportingDate the reportingDate to set
	 */
	public void setReportingDate(final Date reportingDate) {
		this.reportingDate = reportingDate;
	}

	/**
	 * @return the reportingTime
	 */
	public Date getReportingTime() {
		return reportingTime;
	}

	/**
	 * @param reportingTime the reportingTime to set
	 */
	public void setReportingTime(final Date reportingTime) {
		this.reportingTime = reportingTime;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the toAddressId
	 */
	public BigDecimal getToAddressId() {
		return toAddressId;
	}

	/**
	 * @param toAddressId the toAddressId to set
	 */
	public void setToAddressId(final BigDecimal toAddressId) {
		this.toAddressId = toAddressId;
	}

	/**
	 * @return the toAgyLocId
	 */
	public String getToAgyLocId() {
		return toAgyLocId;
	}

	/**
	 * @param toAgyLocId the toAgyLocId to set
	 */
	public void setToAgyLocId(final String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	/**
	 * @return the toCity
	 */
	public String getToCity() {
		return toCity;
	}

	/**
	 * @param toCity the toCity to set
	 */
	public void setToCity(final String toCity) {
		this.toCity = toCity;
	}

	/**
	 * @return the toCountryCode
	 */
	public String getToCountryCode() {
		return toCountryCode;
	}

	/**
	 * @param toCountryCode the toCountryCode to set
	 */
	public void setToCountryCode(final String toCountryCode) {
		this.toCountryCode = toCountryCode;
	}

	/**
	 * @return the toProvStatCode
	 */
	public String getToProvStatCode() {
		return toProvStatCode;
	}

	/**
	 * @param toProvStatCode the toProvStatCode to set
	 */
	public void setToProvStatCode(final String toProvStatCode) {
		this.toProvStatCode = toProvStatCode;
	}

	/**
	 * @return the movementSeq
	 */
	public Long getMovementSeq() {
		return movementSeq;
	}

	/**
	 * @param movementSeq the movementSeq to set
	 */
	public void setMovementSeq(final Long movementSeq) {
		this.movementSeq = movementSeq;
	}

	/**
	 * @return the movementType
	 */
	public String getMovementType() {
		return movementType;
	}

	/**
	 * @param movementType the movementType to set
	 */
	public void setMovementType(final String movementType) {
		this.movementType = movementType;
	}

	/**
	 * @return the movementReasonCode
	 */
	public String getMovementReasonCode() {
		return movementReasonCode;
	}

	/**
	 * @param movementReasonCode the movementReasonCode to set
	 */
	public void setMovementReasonCode(final String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the bookingStatus
	 */
	public final String getBookingStatus() {
		return bookingStatus;
	}

	/**
	 * @param bookingStatus the bookingStatus to set
	 */
	public final void setBookingStatus(final String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	/**
	 * @return the offenderId
	 */
	public final BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId the offenderId to set
	 */
	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}
	
	
	

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the livUnitDesc
	 */
	public String getLivUnitDesc() {
		return livUnitDesc;
	}

	/**
	 * @param livUnitDesc the livUnitDesc to set
	 */
	public void setLivUnitDesc(String livUnitDesc) {
		this.livUnitDesc = livUnitDesc;
	}

	/**
	 * @return the rootOffenderId
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId the rootOffenderId to set
	 */
	public void setRootOffenderId(BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getDspDescription() {
		return dspDescription;
	}

	public void setDspDescription(final String dspDescription) {
		this.dspDescription = dspDescription;
	}

	public String getNbtBookNo() {
		return nbtBookNo;
	}

	/**
	 * @return the nbtBookNo
	 */
	public void setNbtBookNo(final String nbtBookNo) {
		this.nbtBookNo = nbtBookNo;
	}

	/**
	 * @return the timeString
	 */
	public String getTimeString() {
		return timeString;
	}

	/**
	 * @param timeString the timeString to set
	 */
	public void setTimeString(final String timeString) {
		this.timeString = timeString;
	}

	public String getOffIdDisplay() {
		return offIdDisplay;
	}

	public void setOffIdDisplay(String offIdDisplay) {
		this.offIdDisplay = offIdDisplay;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getLivingUnitId() {
		return livingUnitId;
	}

	public void setLivingUnitId(String livingUnitId) {
		this.livingUnitId = livingUnitId;
	}
	
	public boolean isNewBookingFlag() {
		return newBookingFlag;
	}

	public void setNewBookingFlag(boolean newBookingFlag) {
		this.newBookingFlag = newBookingFlag;
	}

	@Override
	public String toString() {
		return "OffenderExternalMovements [activeFlag=" + activeFlag + ", applicationDate=" + applicationDate
				+ ", applicationTime=" + applicationTime + ", arrestAgencyLocId=" + arrestAgencyLocId + ", commentText="
				+ commentText + ", createDatetime=" + createDatetime + ", createUserId=" + createUserId
				+ ", directionCode=" + directionCode + ", escortCode=" + escortCode + ", escortText=" + escortText
				+ ", eventId=" + eventId + ", fromAddressId=" + fromAddressId + ", fromAgyLocId=" + fromAgyLocId
				+ ", fromCity=" + fromCity + ", internalScheduleReasonCode=" + internalScheduleReasonCode
				+ ", internalScheduleType=" + internalScheduleType + ", modifyDatetime=" + modifyDatetime
				+ ", modifyUserId=" + modifyUserId + ", movementDate=" + movementDate + ", movementTime=" + movementTime
				+ ", ojLocationCode=" + ojLocationCode + ", parentEventId=" + parentEventId + ", reportingDate="
				+ reportingDate + ", reportingTime=" + reportingTime + ", sealFlag=" + sealFlag + ", toAddressId="
				+ toAddressId + ", toAgyLocId=" + toAgyLocId + ", toCity=" + toCity + ", toCountryCode=" + toCountryCode
				+ ", toProvStatCode=" + toProvStatCode + ", offenderBookId=" + offenderBookId + ", movementSeq="
				+ movementSeq + ", movementType=" + movementType + ", movementReasonCode=" + movementReasonCode
				+ ", bookingStatus=" + bookingStatus + ", offenderId=" + offenderId + ", assignedStaffId="
				+ assignedStaffId + ", livUnitDesc=" + livUnitDesc + ", rootOffenderId=" + rootOffenderId
				+ ", dspDescription=" + dspDescription + ", nbtBookNo=" + nbtBookNo + ", timeString=" + timeString
				+ ", offIdDisplay=" + offIdDisplay + ", caseloadId=" + caseloadId + ", livingUnitId=" + livingUnitId
				+ ", newBookingFlag=" + newBookingFlag + ", reasonCodeValid=" + reasonCodeValid + "]";
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getWarningMsg() {
		return warningMsg;
	}

	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	public String getWarningPrompt() {
		return warningPrompt;
	}

	public void setWarningPrompt(String warningPrompt) {
		this.warningPrompt = warningPrompt;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public Integer getProposedMvmntSeq() {
		return proposedMvmntSeq;
	}

	public void setProposedMvmntSeq(Integer proposedMvmntSeq) {
		this.proposedMvmntSeq = proposedMvmntSeq;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public Long getIntCorrelationId() {
		return intCorrelationId;
	}

	public void setIntCorrelationId(Long intCorrelationId) {
		this.intCorrelationId = intCorrelationId;
	}

	public Long getOffenderBookIdTemp() {
		return OffenderBookIdTemp;
	}

	public void setOffenderBookIdTemp(Long offenderBookIdTemp) {
		OffenderBookIdTemp = offenderBookIdTemp;
	}

	public String getAdmissionType() {
		return admissionType;
	}

	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}

	public String getReleaseConfNotification() {
		return releaseConfNotification;
	}

	public void setReleaseConfNotification(String releaseConfNotification) {
		this.releaseConfNotification = releaseConfNotification;
	}
}
