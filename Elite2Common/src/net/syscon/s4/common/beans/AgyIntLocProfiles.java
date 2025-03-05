package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the AGY_INT_LOC_PROFILES database table.
 * 
 */
public class AgyIntLocProfiles implements Serializable {
	private static final long serialVersionUID = 1L;
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
	
	@JsonProperty("internalLocationId")
	private Long internalLocationId;
	
	@JsonProperty("intLocProfileType")
	private String intLocProfileType;
	
	@JsonProperty("intLocProfileCode")
	private String intLocProfileCode;
	
	@JsonProperty("rowId")
	private Integer rowId;
	
	@JsonProperty("iepLevelCode")
	private String iepLevelCode;
	
	private String iepLeveldescription;

	public AgyIntLocProfiles() {
		// AgyIntLocProfile
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Long getInternalLocationId() {
		return this.internalLocationId;
	}

	public void setInternalLocationId(final Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public String getIntLocProfileType() {
		return this.intLocProfileType;
	}

	public void setIntLocProfileType(final String intLocProfileType) {
		this.intLocProfileType = intLocProfileType;
	}

	public String getIntLocProfileCode() {
		return this.intLocProfileCode;
	}

	public void setIntLocProfileCode(final String intLocProfileCode) {
		this.intLocProfileCode = intLocProfileCode;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(final Integer rowId) {
		this.rowId = rowId;
	}

	public String getIepLevelCode() {
		return iepLevelCode;
	}

	public void setIepLevelCode(String iepLevelCode) {
		this.iepLevelCode = iepLevelCode;
	}

	public String getIepLeveldescription() {
		return iepLeveldescription;
	}

	public void setIepLeveldescription(String iepLeveldescription) {
		this.iepLeveldescription = iepLeveldescription;
	}



}
