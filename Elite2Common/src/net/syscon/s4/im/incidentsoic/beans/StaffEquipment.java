package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class StaffEquipment extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@JsonProperty("agencyIncidentId")
	@NotNull
	private Integer agencyIncidentId ;
	
	@JsonProperty("partySeq")
	@NotNull
	private Integer partySeq ;
	
	@JsonProperty("staffId")
	@NotNull
	private Integer staffId  ;
	
	@JsonProperty("equipmentUsed")
	@NotNull
	private String equipmentUsed;
	
	@JsonProperty("equipmentDetail")
	@NotNull
	private String equipmentDetail ; 
	
	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime; 
	
	@JsonProperty("createUserId")
	@NotNull
	private String createUserId;
	
	@JsonProperty("modifyUserId")
	@NotNull
	private String modifyUserId ; 
	
	@JsonProperty("modifyDateTime")
	@NotNull
	private Date modifyDateTime ; 
	
	@JsonProperty("sealFlag")
	@NotNull
	private String sealFlag;
	
	@JsonProperty("equipmentUsedTemp")
	private String equipmentUsedTemp;
	

	@JsonProperty("incidentReportId")
	private Integer incidentReportId ;

	public Integer getAgencyIncidentId() {
		return agencyIncidentId;
	}

	public void setAgencyIncidentId(Integer agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}

	public Integer getPartySeq() {
		return partySeq;
	}

	public void setPartySeq(Integer partySeq) {
		this.partySeq = partySeq;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
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

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public String getEquipmentUsed() {
		return equipmentUsed;
	}

	public void setEquipmentUsed(String equipmentUsed) {
		this.equipmentUsed = equipmentUsed;
	}

	public String getEquipmentDetail() {
		return equipmentDetail;
	}

	public void setEquipmentDetail(String equipmentDetail) {
		this.equipmentDetail = equipmentDetail;
	}
	public String getEquipmentUsedTemp() {
		return equipmentUsedTemp;
	}

	public void setEquipmentUsedTemp(String equipmentUsedTemp) {
		this.equipmentUsedTemp = equipmentUsedTemp;
	}

	public Integer getIncidentReportId() {
		return incidentReportId;
	}

	public void setIncidentReportId(Integer incidentReportId) {
		this.incidentReportId = incidentReportId;
	}
	
	
}
