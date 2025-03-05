package net.syscon.s4.inst.property.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the INTERNAL_LOCATION_USAGES database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InternalLocationUsages extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("internalLocationUsageId")
	private Long internalLocationUsageId;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("eventSubType")
	private String eventSubType;
	
	@JsonProperty("internalLocationUsage")
	private String internalLocationUsage;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("chkPermMov")
	private String chkPermMov; 
	
//	//bi-directional many-to-one association to IntLocUsageLocation
//	@JsonProperty("intLocUsageLocations")
//	private List<IntLocUsageLocation> intLocUsageLocations;

	public InternalLocationUsages() {
		// InternalLocationUsages
	}

	public long getInternalLocationUsageId() {
		return this.internalLocationUsageId;
	}

	public void setInternalLocationUsageId(final Long internalLocationUsageId) {
		this.internalLocationUsageId = internalLocationUsageId;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
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

	public String getEventSubType() {
		return this.eventSubType;
	}

	public void setEventSubType(final String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public String getInternalLocationUsage() {
		return this.internalLocationUsage;
	}

	public void setInternalLocationUsage(final String internalLocationUsage) {
		this.internalLocationUsage = internalLocationUsage;
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

//	public List<IntLocUsageLocation> getIntLocUsageLocations() {
//		return this.intLocUsageLocations;
//	}
//
//	public void setIntLocUsageLocations(List<IntLocUsageLocation> intLocUsageLocations) {
//		this.intLocUsageLocations = intLocUsageLocations;
//	}
//
//	public IntLocUsageLocation addIntLocUsageLocation(IntLocUsageLocation intLocUsageLocation) {
//		getIntLocUsageLocations().add(intLocUsageLocation);
//		intLocUsageLocation.setInternalLocationUsage(this);
//
//		return intLocUsageLocation;
//	}
//
//	public IntLocUsageLocation removeIntLocUsageLocation(IntLocUsageLocation intLocUsageLocation) {
//		getIntLocUsageLocations().remove(intLocUsageLocation);
//		intLocUsageLocation.setInternalLocationUsage(null);
//
//		return intLocUsageLocation;
//	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	public String getChkPermMov() {
		return chkPermMov;
	}

	public void setChkPermMov(final String chkPermMov) {
		this.chkPermMov = chkPermMov;
	}
}
