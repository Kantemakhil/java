package net.syscon.s4.inst.offenderobservations.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffObsZoneDetails extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("zoneCode")
	private String zoneCode;
	@JsonProperty("internalLocationId")
	private BigDecimal internalLocationId;
	@JsonProperty("locationCode")
	private String locationCode;
	@JsonProperty("sensorId")
	private String sensorId;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
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
	@JsonProperty("returnedOutput")
	private BigDecimal returnedOutput;
	
	@JsonProperty("locationDescription")
	private String locationDescription;
	
	@JsonProperty("otherLocExist")
	private String otherLocExist;
	public String getAgyLocId() {
		return agyLocId;
	}
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public BigDecimal getInternalLocationId() {
		return internalLocationId;
	}
	public void setInternalLocationId(BigDecimal internalLocationId) {
		this.internalLocationId = internalLocationId;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
	public BigDecimal getListSeq() {
		return listSeq;
	}
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
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
	public BigDecimal getReturnedOutput() {
		return returnedOutput;
	}
	public void setReturnedOutput(BigDecimal returnedOutput) {
		this.returnedOutput = returnedOutput;
	}
	public String getLocationDescription() {
		return locationDescription;
	}
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
	public String getOtherLocExist() {
		return otherLocExist;
	}
	public void setOtherLocExist(String otherLocExist) {
		this.otherLocExist = otherLocExist;
	}
	
	
}
