package net.syscon.s4.inst.transportation.maintenance.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class FixedAssets extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("assetId")
	private Long assetId;

	@JsonProperty("assetClass")
	private String assetClass;

	@JsonProperty("assetLocationId")
	private Long assetLocationId;

	@JsonProperty("assetType")
	private String assetType;

	@JsonProperty("description")
	private String description;

	@JsonProperty("make")
	private String make;

	@JsonProperty("model")
	private String model;

	@JsonProperty("status")
	private String status;

	@JsonProperty("serialNo")
	private String serialNo;

	@JsonProperty("color")
	private String color;

	@JsonProperty("lifeSpanCode")
	private String lifeSpanCode;

	@JsonProperty("cost")
	private Long cost;

	@JsonProperty("year")
	private Date year;

	@JsonProperty("manufactureDate")
	private Date manufactureDate;

	@JsonProperty("acquisitionDate")
	private Date acquisitionDate;

	@JsonProperty("dispositionDate")
	private Date dispositionDate;

	@JsonProperty("acquisitionCorporationId")
	private Long acquisitionCorporationId;

	@JsonProperty("acquisitionPersonId")
	private Long acquisitionPersonId;

	@JsonProperty("dispositionPrice")
	private Long dispositionPrice;

	@JsonProperty("dispositionType")
	private Long dispositionType;

	@JsonProperty("dispositionCorporateId")
	private Long dispositionCorporateId;

	@JsonProperty("dispositionPersonId")
	private Long dispositionPersonId;

	@JsonProperty("dispositionCommentText")
	private String dispositionCommentText;

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
	
	@JsonProperty("lvExist")
	private Long lvExist;
	
	@JsonProperty("assignCount")
	private Long assignCount;

	
	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public String getAssetClass() {
		return assetClass;
	}

	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
	}

	public Long getAssetLocationId() {
		return assetLocationId;
	}

	public void setAssetLocationId(Long assetLocationId) {
		this.assetLocationId = assetLocationId;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLifeSpanCode() {
		return lifeSpanCode;
	}

	public void setLifeSpanCode(String lifeSpanCode) {
		this.lifeSpanCode = lifeSpanCode;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public Date getAcquisitionDate() {
		return acquisitionDate;
	}

	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	public Date getDispositionDate() {
		return dispositionDate;
	}

	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}

	public Long getAcquisitionCorporationId() {
		return acquisitionCorporationId;
	}

	public void setAcquisitionCorporationId(Long acquisitionCorporationId) {
		this.acquisitionCorporationId = acquisitionCorporationId;
	}

	public Long getAcquisitionPersonId() {
		return acquisitionPersonId;
	}

	public void setAcquisitionPersonId(Long acquisitionPersonId) {
		this.acquisitionPersonId = acquisitionPersonId;
	}

	public Long getDispositionPrice() {
		return dispositionPrice;
	}

	public void setDispositionPrice(Long dispositionPrice) {
		this.dispositionPrice = dispositionPrice;
	}

	public Long getDispositionType() {
		return dispositionType;
	}

	public void setDispositionType(Long dispositionType) {
		this.dispositionType = dispositionType;
	}

	public Long getDispositionCorporateId() {
		return dispositionCorporateId;
	}

	public void setDispositionCorporateId(Long dispositionCorporateId) {
		this.dispositionCorporateId = dispositionCorporateId;
	}

	public Long getDispositionPersonId() {
		return dispositionPersonId;
	}

	public void setDispositionPersonId(Long dispositionPersonId) {
		this.dispositionPersonId = dispositionPersonId;
	}

	public String getDispositionCommentText() {
		return dispositionCommentText;
	}

	public void setDispositionCommentText(String dispositionCommentText) {
		this.dispositionCommentText = dispositionCommentText;
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
	
	public Long getLvExist() {
		return lvExist;
	}

	public void setLvExist(Long lvExist) {
		this.lvExist = lvExist;
	}
	public Long getAssignCount() {
		return assignCount;
	}

	public void setAssignCount(Long assignCount) {
		this.assignCount = assignCount;
	}

	@Override
	public String toString() {
		return "FixedAssets [assetId=" + assetId + ", assetClass=" + assetClass + ", assetLocationId=" + assetLocationId
				+ ", assetType=" + assetType + ", description=" + description + ", make=" + make + ", model=" + model
				+ ", status=" + status + ", serialNo=" + serialNo + ", color=" + color + ", lifeSpanCode="
				+ lifeSpanCode + ", cost=" + cost + ", year=" + year + ", manufactureDate=" + manufactureDate
				+ ", acquisitionDate=" + acquisitionDate + ", dispositionDate=" + dispositionDate
				+ ", acquisitionCorporationId=" + acquisitionCorporationId + ", acquisitionPersonId="
				+ acquisitionPersonId + ", dispositionPrice=" + dispositionPrice + ", dispositionType="
				+ dispositionType + ", dispositionCorporateId=" + dispositionCorporateId + ", dispositionPersonId="
				+ dispositionPersonId + ", dispositionCommentText=" + dispositionCommentText + ", createDatetime="
				+ createDatetime + ", createUserId=" + createUserId + ", modifyDatetime=" + modifyDatetime
				+ ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag + ", lvExist=" + lvExist
				+ ", assignCount=" + assignCount + "]";
	}

	

}