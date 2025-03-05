package net.syscon.s4.inst.property.bean;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_PPTY_ITEM_EVENTS database table.
 * 
 */
public class OffenderPptyItemEvents extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date createDate;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private Integer offenderBookId;

	private Integer eventSeq;

	public OffenderPptyItemEvents() {
		// OffenderPptyItemEvent
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getEventSeq() {
		return eventSeq;
	}

	public void setEventSeq(final Integer eventSeq) {
		this.eventSeq = eventSeq;
	}

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
