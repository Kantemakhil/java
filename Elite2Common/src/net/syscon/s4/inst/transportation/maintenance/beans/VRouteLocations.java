package net.syscon.s4.inst.transportation.maintenance.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VRouteLocations extends BaseModel implements Serializable {

	@JsonProperty("optimumCapacity")
	private Integer optimumCapacity;

	@JsonProperty("physicalCapacity")
	private Integer physicalCapacity;

	@JsonProperty("fromAgyLocId")
	private String fromAgyLocId;

	@JsonProperty("toAgyLocId")
	private String toAgyLocId;

	@JsonProperty("scheduledTripId")
	private Long scheduledTripId;

	public Integer getOptimumCapacity() {
		return optimumCapacity;
	}

	public void setOptimumCapacity(Integer optimumCapacity) {
		this.optimumCapacity = optimumCapacity;
	}

	public Integer getPhysicalCapacity() {
		return physicalCapacity;
	}

	public void setPhysicalCapacity(Integer physicalCapacity) {
		this.physicalCapacity = physicalCapacity;
	}

	public String getFromAgyLocId() {
		return fromAgyLocId;
	}

	public void setFromAgyLocId(String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public Long getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Long scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	@Override
	public String toString() {
		return "VRouteLocations [optimumCapacity=" + optimumCapacity + ", physicalCapacity=" + physicalCapacity
				+ ", fromAgyLocId=" + fromAgyLocId + ", toAgyLocId=" + toAgyLocId + ", scheduledTripId="
				+ scheduledTripId + "]";
	}

	
}