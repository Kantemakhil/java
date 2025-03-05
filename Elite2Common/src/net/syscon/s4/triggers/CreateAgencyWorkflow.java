package net.syscon.s4.triggers;

import java.sql.SQLXML;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class CreateAgencyWorkflow extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String triggerName;
	private String agyLocId;
	private String key;
	private String context;
	private SQLXML params;
	private Long offenderBookId;
	private Date eventDate;
	private Date overrideDueDate;
	private Double dueDatePeriod;
	private Date createDatetime;
	private String createUserId;

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public SQLXML getParams() {
		return params;
	}

	public void setParams(SQLXML params) {
		this.params = params;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getOverrideDueDate() {
		return overrideDueDate;
	}

	public void setOverrideDueDate(Date overrideDueDate) {
		this.overrideDueDate = overrideDueDate;
	}

	public Double getDueDatePeriod() {
		return dueDatePeriod;
	}

	public void setDueDatePeriod(Double dueDatePeriod) {
		this.dueDatePeriod = dueDatePeriod;
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

}
