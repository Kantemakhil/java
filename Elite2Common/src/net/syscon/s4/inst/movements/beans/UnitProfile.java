package net.syscon.s4.inst.movements.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class UnitProfile extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("internalLocationId")
	private Integer internalLocationId;

	@JsonProperty("profileType")
	private String profileType;

	@JsonProperty("locCode")
	private String locCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	public Integer getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
