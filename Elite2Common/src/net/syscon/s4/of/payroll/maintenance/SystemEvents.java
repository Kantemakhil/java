package net.syscon.s4.of.payroll.maintenance;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class SystemEvents
 */
public class SystemEvents extends BaseModel {

	@JsonProperty("eventType")
	private String eventType;
	@JsonProperty("eventSeq")
	private int eventSeq;
	@JsonProperty("description")
	private String description;
	@JsonProperty("eventDate")
	private Date eventDate;
	@JsonProperty("modifyDate")
	private Date modifyDate;
	@JsonProperty("eventEndDate")
	private Date eventEndDate;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("systemEventId")
	private int systemEventId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("sealFlag")
	private String sealFlag;
	private boolean inserted;

	/**
	 * @param eventType
	 *            eventType to set
	 */
	public void setEventType(final String eventType) {
		this.eventType = eventType;
	}

	/**
	 * return theeventType
	 */
	public String getEventType() {
		return this.eventType;
	}

	/**
	 * @param eventSeq
	 *            eventSeq to set
	 */
	public void setEventSeq(final int eventSeq) {
		this.eventSeq = eventSeq;
	}

	/**
	 * return theeventSeq
	 */
	public int getEventSeq() {
		return this.eventSeq;
	}

	/**
	 * @param description
	 *            description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * return thedescription
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param eventDate
	 *            eventDate to set
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
	 * @param modifyDate
	 *            modifyDate to set
	 */
	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * return themodifyDate
	 */
	public Date getModifyDate() {
		return this.modifyDate;
	}

	/**
	 * @param eventEndDate
	 *            eventEndDate to set
	 */
	public void setEventEndDate(final Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	/**
	 * return theeventEndDate
	 */
	public Date getEventEndDate() {
		return this.eventEndDate;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
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
	 * @param systemEventId
	 *            systemEventId to set
	 */
	public void setSystemEventId(final int systemEventId) {
		this.systemEventId = systemEventId;
	}

	/**
	 * return thesystemEventId
	 */
	public int getSystemEventId() {
		return this.systemEventId;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
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
	 * @param createUserId
	 *            createUserId to set
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
	 * @param modifyDatetime
	 *            modifyDatetime to set
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
	 * @param sealFlag
	 *            sealFlag to set
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
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

}