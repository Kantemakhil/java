package net.syscon.s4.triggers;

import java.io.Serializable;
import java.util.Date;

public class OffenderAssocPEventNotifs implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 1L;
	private Long trgEventId;
	private Date eventDate;
	private Long offenderBookId;
	private String notificationCode;
	private Date createDatetime;
	private String createUserId;
	private String sealFlag;
	private Date modifyDatetime;
	private String modifyUserId;
	private String lCheckExistFlag;

	public Long getTrgEventId() {
		return trgEventId;
	}

	public void setTrgEventId(Long trgEventId) {
		this.trgEventId = trgEventId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getNotificationCode() {
		return notificationCode;
	}

	public void setNotificationCode(String notificationCode) {
		this.notificationCode = notificationCode;
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getlCheckExistFlag() {
		return lCheckExistFlag;
	}

	public void setlCheckExistFlag(String lCheckExistFlag) {
		this.lCheckExistFlag = lCheckExistFlag;
	}

}
