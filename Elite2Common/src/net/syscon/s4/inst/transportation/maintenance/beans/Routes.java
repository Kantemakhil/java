package net.syscon.s4.inst.transportation.maintenance.beans;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

import java.io.Serializable;
import java.math.*;
public class Routes extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("routeName")
	private String routeName;
	
	@JsonProperty("travelTime")
	private String travelTime;
	
	@JsonProperty("remarks")
	private String remarks;
	
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
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("noStops")
	private BigDecimal noStops;
	
	@JsonProperty("startAgyLocId")
	private String startAgyLocId;
	
	@JsonProperty("stopAgyLocId")
	private String stopAgyLocId;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;

	

	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getNoStops() {
		return noStops;
	}
	public void setNoStops(BigDecimal noStops) {
		this.noStops = noStops;
	}
	public String getStartAgyLocId() {
		return startAgyLocId;
	}
	public void setStartAgyLocId(String startAgyLocId) {
		this.startAgyLocId = startAgyLocId;
	}
	public String getStopAgyLocId() {
		return stopAgyLocId;
	}
	public void setStopAgyLocId(String stopAgyLocId) {
		this.stopAgyLocId = stopAgyLocId;
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

	


}