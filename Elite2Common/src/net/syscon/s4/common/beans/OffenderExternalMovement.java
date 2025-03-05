package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderExternalMovement extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("applicationDate")
	private Date applicationDate;

	@JsonProperty("applicationTime")
	private Date applicationTime;

	@JsonProperty("arrestAgencyLocId")
	private String arrestAgencyLocId;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("directionCode")
	private String directionCode;

	@JsonProperty("escortCode")
	private String escortCode;

	@JsonProperty("escortText")
	private String escortText;

	@JsonProperty("eventId")
	private BigDecimal eventId;

	@JsonProperty("fromAddressId")
	private BigDecimal fromAddressId;

	@JsonProperty("fromAgyLocId")
	private String fromAgyLocId;

	@JsonProperty("fromCity")
	private String fromCity;

	@JsonProperty("internalScheduleReasonCode")
	private String internalScheduleReasonCode;

	@JsonProperty("internalScheduleType")
	private String internalScheduleType;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("movementDate")
	private Date movementDate;

	@JsonProperty("movementTime")
	private Date movementTime;

	@JsonProperty("ojLocationCode")
	private String ojLocationCode;

	@JsonProperty("parentEventId")
	private BigDecimal parentEventId;

	@JsonProperty("reportingDate")
	private Date reportingDate;

	@JsonProperty("reportingTime")
	private Date reportingTime;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("toAddressId")
	private BigDecimal toAddressId;

	@JsonProperty("toAgyLocId")
	private String toAgyLocId;

	@JsonProperty("toCity")
	private String toCity;

	@JsonProperty("toCountryCode")
	private String toCountryCode;

	@JsonProperty("toProvStatCode")
	private String toProvStatCode;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("movementSeq")
	private long movementSeq;

	@JsonProperty("movementReason")
	private MovementReason movementReason;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("movementType")
	private String movementType;

	@JsonProperty("notification")
	private String notification;
	/**
	 * @return the movementType
	 */
	public String getMovementType() {
		return movementType;
	}

	/**
	 * @param movementType
	 *            the movementType to set
	 */
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	/**
	 *
	 * @return
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 *
	 * @param activeFlag
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getApplicationDate() {
		return applicationDate;
	}

	/**
	 *
	 * @param applicationDate
	 */
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getApplicationTime() {
		return applicationTime;
	}

	/**
	 *
	 * @param applicationTime
	 */
	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	/**
	 *
	 * @return
	 */
	public String getArrestAgencyLocId() {
		return arrestAgencyLocId;
	}

	/**
	 *
	 * @param arrestAgencyLocId
	 */
	public void setArrestAgencyLocId(String arrestAgencyLocId) {
		this.arrestAgencyLocId = arrestAgencyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getDirectionCode() {
		return directionCode;
	}

	/**
	 *
	 * @param directionCode
	 */
	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	/**
	 *
	 * @return
	 */
	public String getEscortCode() {
		return escortCode;
	}

	/**
	 *
	 * @param escortCode
	 */
	public void setEscortCode(String escortCode) {
		this.escortCode = escortCode;
	}

	/**
	 *
	 * @return
	 */
	public String getEscortText() {
		return escortText;
	}

	/**
	 *
	 * @param escortText
	 */
	public void setEscortText(String escortText) {
		this.escortText = escortText;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getEventId() {
		return eventId;
	}

	/**
	 *
	 * @param eventId
	 */
	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getFromAddressId() {
		return fromAddressId;
	}

	/**
	 *
	 * @param fromAddressId
	 */
	public void setFromAddressId(BigDecimal fromAddressId) {
		this.fromAddressId = fromAddressId;
	}

	/**
	 *
	 * @return
	 */
	public String getFromAgyLocId() {
		return fromAgyLocId;
	}

	/**
	 *
	 * @param fromAgyLocId
	 */
	public void setFromAgyLocId(String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getFromCity() {
		return fromCity;
	}

	/**
	 *
	 * @param fromCity
	 */
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	/**
	 *
	 * @return
	 */
	public String getInternalScheduleReasonCode() {
		return internalScheduleReasonCode;
	}

	/**
	 *
	 * @param internalScheduleReasonCode
	 */
	public void setInternalScheduleReasonCode(String internalScheduleReasonCode) {
		this.internalScheduleReasonCode = internalScheduleReasonCode;
	}

	/**
	 *
	 * @return
	 */
	public String getInternalScheduleType() {
		return internalScheduleType;
	}

	/**
	 *
	 * @param internalScheduleType
	 */
	public void setInternalScheduleType(String internalScheduleType) {
		this.internalScheduleType = internalScheduleType;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getMovementDate() {
		return movementDate;
	}

	/**
	 *
	 * @param movementDate
	 */
	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getMovementTime() {
		return movementTime;
	}

	/**
	 *
	 * @param movementTime
	 */
	public void setMovementTime(Date movementTime) {
		this.movementTime = movementTime;
	}

	/**
	 *
	 * @return
	 */
	public String getOjLocationCode() {
		return ojLocationCode;
	}

	/**
	 *
	 * @param ojLocationCode
	 */
	public void setOjLocationCode(String ojLocationCode) {
		this.ojLocationCode = ojLocationCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getParentEventId() {
		return parentEventId;
	}

	/**
	 *
	 * @param parentEventId
	 */
	public void setParentEventId(BigDecimal parentEventId) {
		this.parentEventId = parentEventId;
	}

	/**
	 *
	 * @return
	 */
	public Date getReportingDate() {
		return reportingDate;
	}

	/**
	 *
	 * @param reportingDate
	 */
	public void setReportingDate(Date reportingDate) {
		this.reportingDate = reportingDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getReportingTime() {
		return reportingTime;
	}

	/**
	 *
	 * @param reportingTime
	 */
	public void setReportingTime(Date reportingTime) {
		this.reportingTime = reportingTime;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getToAddressId() {
		return toAddressId;
	}

	/**
	 *
	 * @param toAddressId
	 */
	public void setToAddressId(BigDecimal toAddressId) {
		this.toAddressId = toAddressId;
	}

	/**
	 *
	 * @return
	 */
	public String getToAgyLocId() {
		return toAgyLocId;
	}

	/**
	 *
	 * @param toAgyLocId
	 */
	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getToCity() {
		return toCity;
	}

	/**
	 *
	 * @param toCity
	 */
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	/**
	 *
	 * @return
	 */
	public String getToCountryCode() {
		return toCountryCode;
	}

	/**
	 *
	 * @param toCountryCode
	 */
	public void setToCountryCode(String toCountryCode) {
		this.toCountryCode = toCountryCode;
	}

	/**
	 *
	 * @return
	 */
	public String getToProvStatCode() {
		return toProvStatCode;
	}

	/**
	 *
	 * @param toProvStatCode
	 */
	public void setToProvStatCode(String toProvStatCode) {
		this.toProvStatCode = toProvStatCode;
	}

	/**
	 *
	 * @return
	 */
	public long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public long getMovementSeq() {
		return movementSeq;
	}

	/**
	 *
	 * @param movementSeq
	 */
	public void setMovementSeq(long movementSeq) {
		this.movementSeq = movementSeq;
	}

	/**
	 *
	 * @return
	 */
	public MovementReason getMovementReason() {
		return movementReason;
	}

	/**
	 *
	 * @param movementReason
	 */
	public void setMovementReason(MovementReason movementReason) {
		this.movementReason = movementReason;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

}