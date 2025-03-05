package net.syscon.s4.inst.transportation.maintenance.beans;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

import java.io.Serializable;
import java.math.*;
public class AgyLocFeedDetails extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("feedAgyLocId")
	private String feedAgyLocId;
	
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

	
	@JsonProperty("description")
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getFeedAgyLocId() {
		return feedAgyLocId;
	}

	public void setFeedAgyLocId(String feedAgyLocId) {
		this.feedAgyLocId = feedAgyLocId;
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
	


}