package net.syscon.s4.inst.transportation.maintenance.beans;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

import java.io.Serializable;
import java.math.*;
import java.sql.Timestamp;


/**
 * Class AgencySegmentLengths
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencySegmentLengths extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("segmentLength")
	private Double segmentLength;
	
	
	@JsonProperty("fromAgyLocId")
	private String fromAgyLocId;
	
	@JsonProperty("toAgyLocId")
	private String toAgyLocId;
	
	@JsonProperty("fromDescription")
	private String  fromDescription;
	
	@JsonProperty("toDescription")
	private String  toDescription;
	
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
	
	@JsonProperty("nbtSegmentLength")
	private Date nbtSegmentLength;

	

	public Double getSegmentLength() {
		return segmentLength;
	}

	public void setSegmentLength(Double segmentLength) {
		this.segmentLength = segmentLength;
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

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Timestamp createDatetime) {
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

	public void setModifyDatetime(Timestamp modifyDatetime) {
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
	public String getFromDescription() {
		return fromDescription;
	}

	public void setFromDescription(String fromDescription) {
		this.fromDescription = fromDescription;
	}

	public String getToDescription() {
		return toDescription;
	}

	public void setToDescription(String toDescription) {
		this.toDescription = toDescription;
	}
	
	public Date getNbtSegmentLength() {
		return nbtSegmentLength;
	}

	public void setNbtSegmentLength(Date nbtSegmentLength) {
		this.nbtSegmentLength = nbtSegmentLength;
	}
}