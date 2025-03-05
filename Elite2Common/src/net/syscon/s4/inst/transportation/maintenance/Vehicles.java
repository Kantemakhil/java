package net.syscon.s4.inst.transportation.maintenance;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

import java.io.Serializable;
import java.math.*;

public class Vehicles extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("assetId")
	private Long assetId;

	@JsonProperty("vehicleId")
	private Long vehicleId;

	@JsonProperty("plateNo")
	private String plateNo;

	@JsonProperty("fuelType")
	private String fuelType;

	@JsonProperty("licenceClass")
	private String licenceClass;

	@JsonProperty("remarks")
	private String remarks;

	@JsonProperty("capacity")
	private Long capacity;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("optimalCapacity")
	private Long optimalCapacity;
	
	@JsonProperty("lvExist")
	private Long lvExist;


	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getLicenceClass() {
		return licenceClass;
	}

	public void setLicenceClass(String licenceClass) {
		this.licenceClass = licenceClass;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Long getOptimalCapacity() {
		return optimalCapacity;
	}

	public void setOptimalCapacity(Long optimalCapacity) {
		this.optimalCapacity = optimalCapacity;
	}
	
	public Long getLvExist() {
		return lvExist;
	}

	public void setLvExist(Long lvExist) {
		this.lvExist = lvExist;
	}
	
	@Override
	public String toString() {
		return "Vehicles [assetId=" + assetId + ", vehicleId=" + vehicleId + ", plateNo=" + plateNo + ", fuelType="
				+ fuelType + ", licenceClass=" + licenceClass + ", remarks=" + remarks + ", capacity=" + capacity
				+ ", createDatetime=" + createDatetime + ", createUserId=" + createUserId + ", modifyDatetime="
				+ modifyDatetime + ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag + ", optimalCapacity="
				+ optimalCapacity + ", lvExist=" + lvExist + "]";
	}


	
}