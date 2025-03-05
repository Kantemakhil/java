package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class SignificantIncident extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("agencyIncidentId")
	@NotNull
	private Integer agencyIncidentId;
	@JsonProperty("significanceType")
	@NotNull
    private String significanceType;
	@JsonProperty("modifyUserId")
	@NotNull
	private String modifyUserId;
	@JsonProperty("createUserId")
	@NotNull
    private String createUserId;
	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;
	@JsonProperty("modifyDateTime")
	@NotNull
	private Date modifyDateTime;
	@JsonProperty("sealFlag")
	@NotNull
	private String sealFlag;
	@JsonProperty("entryDate")
	@NotNull
	private Date entryDate;
	@JsonProperty("modifiedStaffId")
	@NotNull
	private String modifiedStaffId;
	
	@JsonProperty("recordedByStaffId")
	private BigDecimal recordedByStaffId;
	
	public String getModifiedStaffId() {
		return modifiedStaffId;
	}
	public void setModifiedStaffId(String modifiedStaffId) {
		this.modifiedStaffId = modifiedStaffId;
	}
	public Integer getAgencyIncidentId() {
		return agencyIncidentId;
	}
	public void setAgencyIncidentId(Integer agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}
	public String getSignificanceType() {
		return significanceType;
	}
	public void setSignificanceType(String significanceType) {
		this.significanceType = significanceType;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getModifyDateTime() {
		return modifyDateTime;
	}
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public BigDecimal getRecordedByStaffId() {
		return recordedByStaffId;
	}
	public void setRecordedByStaffId(BigDecimal recordedByStaffId) {
		this.recordedByStaffId = recordedByStaffId;
	} 
	

}
