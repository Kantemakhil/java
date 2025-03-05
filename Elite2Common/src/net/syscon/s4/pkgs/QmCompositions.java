package net.syscon.s4.pkgs;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class QmCompositions extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long compositionId;
	private String managingAgyLocId;
	private String eventTypeAgyLocId;
	private Long processId;
	private String eventType;
	private String activeFlag;
	private Date expiryDate;
	private String sealFlag;
	private String createUserId;
	private Date createDatetime;
	private String modifyUserId;
	private Date modifyDatetime;

	public Long getCompositionId() {
		return compositionId;
	}

	public void setCompositionId(Long compositionId) {
		this.compositionId = compositionId;
	}

	public String getManagingAgyLocId() {
		return managingAgyLocId;
	}

	public void setManagingAgyLocId(String managingAgyLocId) {
		this.managingAgyLocId = managingAgyLocId;
	}

	public String getEventTypeAgyLocId() {
		return eventTypeAgyLocId;
	}

	public void setEventTypeAgyLocId(String eventTypeAgyLocId) {
		this.eventTypeAgyLocId = eventTypeAgyLocId;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

}
