package net.syscon.s4.inst.transportation.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class SelectVehiclesV1 extends BaseModel  implements Serializable{

	@JsonProperty("type")
	private String type;
	@JsonProperty("make")
	private String make;
	@JsonProperty("modelNo")
	private String modelNo;
	@JsonProperty("vehicleId")
	private Long vehicleId;
	@JsonProperty("description")
	private String description;
	@JsonProperty("optimumCapacity")
	private Long optimumCapacity;
	@JsonProperty("physicalCapacity")
	private Long physicalCapacity;
	@JsonProperty("assetId")
	private Long assetId;
	@JsonProperty("butProfileExit")
	private Object butProfileExit;
	@JsonProperty("butSelect")
	private Object butSelect;
	@JsonProperty("scheduledTripId")
	private Long scheduledTripId;
	@JsonProperty("moduleName")
	private Object moduleName;
	@JsonProperty("formModuleName")
	private Object formModuleName;

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(final String make) {
		this.make = make;
	}
	public String getModelNo() {
		return this.modelNo;
	}

	public void setModelNo(final String modelNo) {
		this.modelNo = modelNo;
	}

	public Long getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(final Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	public Long getOptimumCapacity() {
		return this.optimumCapacity;
	}

	public void setOptimumCapacity(final Long optimumCapacity) {
		this.optimumCapacity = optimumCapacity;
	}
	public Long getPhysicalCapacity() {
		return this.physicalCapacity;
	}

	public void setPhysicalCapacity(final Long physicalCapacity) {
		this.physicalCapacity = physicalCapacity;
	}

	public Long getAssetId() {
		return this.assetId;
	}

	public void setAssetId(final Long assetId) {
		this.assetId = assetId;
	}
	public Object getButProfileExit() {
		return this.butProfileExit;
	}

	public void setButProfileExit(final Object butProfileExit) {
		this.butProfileExit = butProfileExit;
	}
	public Long getscheduledTripId() {
		return this.scheduledTripId;
	}

	public void setscheduledTripId(final Long scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}
	public Object getmoduleName() {
		return this.moduleName;
	}

	public void setmoduleName(final Object moduleName) {
		this.moduleName = moduleName;
	}
	
	public Object getformModuleName() {
		return this.formModuleName;
	}

	public void setformModuleName(final Object formModuleName) {
		this.formModuleName = formModuleName;
	}
}
