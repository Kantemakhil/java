package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VQmPc extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long processId;
	private String name;
	private String nameDesc;
	private String description;
	private String executionType;
	private String locationType;
	private String activeFlag;
	private Date expiryDate;
	private String eventType;
	private Long compositionId;
	private String managingAgyLocId;
	private String eventTypeAgyLocId;
	private String qmcActiveFlag;
	private Date qmcExpiryDate;
	private String qmcEventType;

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameDesc() {
		return nameDesc;
	}

	public void setNameDesc(String nameDesc) {
		this.nameDesc = nameDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExecutionType() {
		return executionType;
	}

	public void setExecutionType(String executionType) {
		this.executionType = executionType;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
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

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

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

	public String getQmcActiveFlag() {
		return qmcActiveFlag;
	}

	public void setQmcActiveFlag(String qmcActiveFlag) {
		this.qmcActiveFlag = qmcActiveFlag;
	}

	public Date getQmcExpiryDate() {
		return qmcExpiryDate;
	}

	public void setQmcExpiryDate(Date qmcExpiryDate) {
		this.qmcExpiryDate = qmcExpiryDate;
	}

	public String getQmcEventType() {
		return qmcEventType;
	}

	public void setQmcEventType(String qmcEventType) {
		this.qmcEventType = qmcEventType;
	}

}
