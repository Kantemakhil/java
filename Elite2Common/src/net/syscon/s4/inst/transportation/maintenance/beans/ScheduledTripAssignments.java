package net.syscon.s4.inst.transportation.maintenance.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ScheduledTripAssignments extends BaseModel implements Serializable {

	@JsonProperty("scheduledTripId")
	private Long scheduledTripId;

	@JsonProperty("assignmentType")
	private String assignmentType;

	@JsonProperty("assignedId")
	private Long assignedId;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("assignedIdTemp")
	private Long assignedIdTemp;

	@JsonProperty("type")
	private String type;

	@JsonProperty("make")
	private String make;

	@JsonProperty("modelNo")
	private String modelNo;

	@JsonProperty("description")
	private String description;

	@JsonProperty("optimumCapacity")
	private Long optimumCapacity;

	@JsonProperty("physicalCapacity")
	private Long physicalCapacity;

	@JsonProperty("departureDate")
	private Date departureDate;

	@JsonProperty("completionDate")
	private Date completionDate;

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOptimumCapacity() {
		return optimumCapacity;
	}

	public void setOptimumCapacity(Long optimumCapacity) {
		this.optimumCapacity = optimumCapacity;
	}

	public Long getPhysicalCapacity() {
		return physicalCapacity;
	}

	public void setPhysicalCapacity(Long physicalCapacity) {
		this.physicalCapacity = physicalCapacity;
	}

	public Long getAssignedIdTemp() {
		return assignedIdTemp;
	}

	public void setAssignedIdTemp(Long assignedIdTemp) {
		this.assignedIdTemp = assignedIdTemp;
	}

	public ScheduledTripAssignments() {

	}

	public Long getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Long scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public String getAssignmentType() {
		return assignmentType;
	}

	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}

	public Long getAssignedId() {
		return assignedId;
	}

	public void setAssignedId(Long assignedId) {
		this.assignedId = assignedId;
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

	@Override
	public String toString() {
		return "ScheduledTripAssignments [scheduledTripId=" + scheduledTripId + ", assignmentType=" + assignmentType
				+ ", assignedId=" + assignedId + ", createUserId=" + createUserId + ", createDatetime=" + createDatetime
				+ ", sealFlag=" + sealFlag + ", modifyDatetime=" + modifyDatetime + ", modifyUserId=" + modifyUserId
				+ ", assignedIdTemp=" + assignedIdTemp + ", type=" + type + ", make=" + make + ", modelNo=" + modelNo
				+ ", description=" + description + ", optimumCapacity=" + optimumCapacity + ", physicalCapacity="
				+ physicalCapacity + ", departureDate=" + departureDate + ", completionDate=" + completionDate + "]";
	}

}
