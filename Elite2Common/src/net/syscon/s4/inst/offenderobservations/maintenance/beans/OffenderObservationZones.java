package net.syscon.s4.inst.offenderobservations.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderObservationZones extends BaseModel implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@JsonProperty("agyLocId")
private String agyLocId;
@JsonProperty("zoneCode")
private String zoneCode;
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

}
