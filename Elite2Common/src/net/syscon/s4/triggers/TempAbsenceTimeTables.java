package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class TempAbsenceTimeTables extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long taId;
	private Long taTtSeq;
	private Date outDate;
	private Date outTime;
	private Date inDate;
	private Date inTime;
	private String agyLocId;
	private String cityCode;
	private String cancelReasonCode;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private Long tempAbsSchId;
	private String sealFlag;

	public Long getTaId() {
		return taId;
	}

	public void setTaId(final Long taId) {
		this.taId = taId;
	}

	public Long getTaTtSeq() {
		return taTtSeq;
	}

	public void setTaTtSeq(final Long taTtSeq) {
		this.taTtSeq = taTtSeq;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(final Date outDate) {
		this.outDate = outDate;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(final Date outTime) {
		this.outTime = outTime;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(final Date inDate) {
		this.inDate = inDate;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(final Date inTime) {
		this.inTime = inTime;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(final String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCancelReasonCode() {
		return cancelReasonCode;
	}

	public void setCancelReasonCode(final String cancelReasonCode) {
		this.cancelReasonCode = cancelReasonCode;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Long getTempAbsSchId() {
		return tempAbsSchId;
	}

	public void setTempAbsSchId(final Long tempAbsSchId) {
		this.tempAbsSchId = tempAbsSchId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
