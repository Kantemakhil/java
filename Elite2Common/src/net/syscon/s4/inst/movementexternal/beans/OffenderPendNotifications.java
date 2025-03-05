package net.syscon.s4.inst.movementexternal.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class OffenderPendNotifications implements Serializable {
	private static final long serialVersionUID = 1L;

	private Object createDatetime;

	private String createUserId;

	private Object modifyDatetime;

	private String modifyUserId;

	private String moveScheduleFlag;

	private Object movementDate;

	private String movementReasonCode;

	private String movementType;

	private BigDecimal scheduleId;

	private String sealFlag;

	// bi-directional many-to-one association to OffenderNotCompletions
	private List<OffenderNotCompletions> OffenderNotCompletions;

	private long offenderBookId;

	private long notiSeq;

	private long notiMoveSeq;

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getNotiSeq() {
		return notiSeq;
	}

	public void setNotiSeq(long notiSeq) {
		this.notiSeq = notiSeq;
	}

	public long getNotiMoveSeq() {
		return notiMoveSeq;
	}

	public void setNotiMoveSeq(long notiMoveSeq) {
		this.notiMoveSeq = notiMoveSeq;
	}

	public OffenderPendNotifications() {
	}

	/*public OffenderPendNotificationPK getId() {
		return this.id;
	}

	public void setId(OffenderPendNotificationPK id) {
		this.id = id;
	}*/

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getMoveScheduleFlag() {
		return this.moveScheduleFlag;
	}

	public void setMoveScheduleFlag(String moveScheduleFlag) {
		this.moveScheduleFlag = moveScheduleFlag;
	}

	public Object getMovementDate() {
		return this.movementDate;
	}

	public void setMovementDate(Object movementDate) {
		this.movementDate = movementDate;
	}

	public String getMovementReasonCode() {
		return this.movementReasonCode;
	}

	public void setMovementReasonCode(String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	public String getMovementType() {
		return this.movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public BigDecimal getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(BigDecimal scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the offenderNotCompletions
	 */
	public List<OffenderNotCompletions> getOffenderNotCompletions() {
		return OffenderNotCompletions;
	}

	/**
	 * @param offenderNotCompletions the offenderNotCompletions to set
	 */
	public void setOffenderNotCompletions(List<OffenderNotCompletions> offenderNotCompletions) {
		OffenderNotCompletions = offenderNotCompletions;
	}

}
