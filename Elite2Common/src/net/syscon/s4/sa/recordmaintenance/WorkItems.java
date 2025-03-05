package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class WorkItems extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("workId")
	private long workId;
	
	@JsonProperty("businessFunction")
	private String businessFunction;
	
	@JsonProperty("module")
	private String module;
	
	@JsonProperty("triggerId")
	private String triggerId;
	
	@JsonProperty("process")
	private Long process;
	
	@JsonProperty("addTrigger")
	private String addTrigger;
	
	@JsonProperty("updateTrigger")
	private String updateTrigger;
	
	@JsonProperty("deleteTrigger")
	private String deleteTrigger;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;

	public long getWorkId() {
		return workId;
	}

	public void setWorkId(long workId) {
		this.workId = workId;
	}

	public String getBusinessFunction() {
		return businessFunction;
	}

	public void setBusinessFunction(String businessFunction) {
		this.businessFunction = businessFunction;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Long getProcess() {
		return process;
	}

	public void setProcess(Long process) {
		this.process = process;
	}

	public String getAddTrigger() {
		return addTrigger;
	}

	public void setAddTrigger(String addTrigger) {
		this.addTrigger = addTrigger;
	}

	public String getUpdateTrigger() {
		return updateTrigger;
	}

	public void setUpdateTrigger(String updateTrigger) {
		this.updateTrigger = updateTrigger;
	}

	public String getDeleteTrigger() {
		return deleteTrigger;
	}

	public void setDeleteTrigger(String deleteTrigger) {
		this.deleteTrigger = deleteTrigger;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}

}
