package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderActivityEvent extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("activityCode")
	private String activityCode;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("eventDate")
	private Date eventDate;

	@JsonProperty("eventTime")
	private Date eventTime;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("scheduleId")
	private BigDecimal scheduleId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBooking")
	private OffenderBookings offenderBooking;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("eventsSeq")
	private long eventsSeq;

	/**
	 *
	 * @return
	 */
	public String getActivityCode() {
		return activityCode;
	}

	/**
	 *
	 * @param activityCode
	 */
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
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
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 *
	 * @param eventDate
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getEventTime() {
		return eventTime;
	}

	/**
	 *
	 * @param eventTime
	 */
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
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
	public BigDecimal getScheduleId() {
		return scheduleId;
	}

	/**
	 *
	 * @param scheduleId
	 */
	public void setScheduleId(BigDecimal scheduleId) {
		this.scheduleId = scheduleId;
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
	public OffenderBookings getOffenderBooking() {
		return offenderBooking;
	}

	/**
	 *
	 * @param offenderBooking
	 */
	public void setOffenderBooking(OffenderBookings offenderBooking) {
		this.offenderBooking = offenderBooking;
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
	public long getEventsSeq() {
		return eventsSeq;
	}

	/**
	 *
	 * @param eventsSeq
	 */
	public void setEventsSeq(long eventsSeq) {
		this.eventsSeq = eventsSeq;
	}

}