package net.syscon.s4.inst.workflow.maintenance.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.AgencyLocations;


/**
 * The persistent class for the AGY_LOC_TEAM_FUNCTIONS database table.
 * 
 */
public class AgyLocTeamFunctions extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("agyLocTeamFunctionId")
	private long agyLocTeamFunctionId;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("effectiveDate")
	private Date effectiveDate;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;
	
	@JsonProperty("functionType")
	private String functionType;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("overwrittenFlag")
	private String overwrittenFlag;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("teamId")
	private java.math.BigDecimal teamId;
	
	@JsonProperty("agyLocId")
	@NotNull
	@Size(max = 6)
	private String agyLocId;
	
	@JsonProperty("agencyLocationType")
	@Size(max = 12)
	private String agencyLocationType;

	//bi-directional many-to-one association to AgencyLocation
	private AgencyLocations agencyLocation;

	private String teamIdDesc;
	
	public AgyLocTeamFunctions() {
		//AgyLocTeamFunctions
	}

	public long getAgyLocTeamFunctionId() {
		return this.agyLocTeamFunctionId;
	}

	public void setAgyLocTeamFunctionId(final long agyLocTeamFunctionId) {
		this.agyLocTeamFunctionId = agyLocTeamFunctionId;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFunctionType() {
		return this.functionType;
	}

	public void setFunctionType(final String functionType) {
		this.functionType = functionType;
	}

	

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getTeamIdDesc() {
		return teamIdDesc;
	}

	public void setTeamIdDesc(final String teamIdDesc) {
		this.teamIdDesc = teamIdDesc;
	}

	public String getAgencyLocationType() {
		return agencyLocationType;
	}

	public void setAgencyLocationType(final String agencyLocationType) {
		this.agencyLocationType = agencyLocationType;
	}

	public String getOverwrittenFlag() {
		return this.overwrittenFlag;
	}

	public void setOverwrittenFlag(final String overwrittenFlag) {
		this.overwrittenFlag = overwrittenFlag;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public java.math.BigDecimal getTeamId() {
		return this.teamId;
	}

	public void setTeamId(final java.math.BigDecimal teamId) {
		this.teamId = teamId;
	}

	public AgencyLocations getAgencyLocation() {
		return agencyLocation;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public void setAgencyLocation(final AgencyLocations agencyLocation) {
		this.agencyLocation = agencyLocation;
	}

	

}
