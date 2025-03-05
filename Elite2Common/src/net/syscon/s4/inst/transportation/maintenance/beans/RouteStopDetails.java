package net.syscon.s4.inst.transportation.maintenance.beans;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

import java.io.Serializable;
import java.math.*;
public class RouteStopDetails extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("routeName")
	private String routeName;
	
	@JsonProperty("tempLegid")
	private BigDecimal tempLegid;
	
	@JsonProperty("tempLegseq")
	private BigDecimal tempLegseq;
	
	@JsonProperty("tempAgylocid")
	private String tempAgylocid;
	
	@JsonProperty("legId")
	private BigDecimal legId;
	
	@JsonProperty("legSeq")
	private BigDecimal legSeq;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("countFlag")
	private String countFlag;
	
	@JsonProperty("intakeLocFlag")
	private String intakeLocFlag;
	
	@JsonProperty("overnightFlag")
	private String overnightFlag;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;
	
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

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public BigDecimal getLegId() {
		return legId;
	}

	public void setLegId(BigDecimal legId) {
		this.legId = legId;
	}

	public BigDecimal getLegSeq() {
		return legSeq;
	}

	public void setLegSeq(BigDecimal legSeq) {
		this.legSeq = legSeq;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getCountFlag() {
		return countFlag;
	}

	public void setCountFlag(String countFlag) {
		this.countFlag = countFlag;
	}

	public String getIntakeLocFlag() {
		return intakeLocFlag;
	}

	public void setIntakeLocFlag(String intakeLocFlag) {
		this.intakeLocFlag = intakeLocFlag;
	}

	public String getOvernightFlag() {
		return overnightFlag;
	}

	public void setOvernightFlag(String overnightFlag) {
		this.overnightFlag = overnightFlag;
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
	public BigDecimal getTempLegid() {
		return tempLegid;
	}

	public void setTempLegid(BigDecimal tempLegid) {
		this.tempLegid = tempLegid;
	}

	public BigDecimal getTempLegseq() {
		return tempLegseq;
	}

	public void setTempLegseq(BigDecimal tempLegseq) {
		this.tempLegseq = tempLegseq;
	}

	public String getTempAgylocid() {
		return tempAgylocid;
	}

	public void setTempAgylocid(String tempAgylocid) {
		this.tempAgylocid = tempAgylocid;
	}

	


}