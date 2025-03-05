package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffObsZoneDetails extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("zoneCode")
	private String zoneCode;

	@JsonProperty("locationCode")
	private String locationCode;

	@JsonProperty("sensorId")
	private Long sensorId;

	@JsonProperty("listSeq")
	private Long listSeq;

	@JsonProperty("internalLocationId")
	private Long internalLocationId;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("expiryDate")
	private Date expiryDate;

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

	@JsonProperty("locationDescription")
	private String locationDescription;
	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(final String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(final String locationCode) {
		this.locationCode = locationCode;
	}

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(final Long sensorId) {
		this.sensorId = sensorId;
	}

	public Long getListSeq() {
		return listSeq;
	}

	public void setListSeq(final Long listSeq) {
		this.listSeq = listSeq;
	}

	public Long getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(final Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}


	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

}
