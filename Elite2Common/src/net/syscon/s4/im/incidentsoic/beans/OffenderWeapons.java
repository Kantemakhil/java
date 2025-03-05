package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderWeapons extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("agencyIncidentId")
	@NotNull
	private Integer agencyIncidentId;
	
	@JsonProperty("partySeq")
	@NotNull
	private Integer partySeq;
	
	@JsonProperty("offenderBookId")
	@NotNull
	private Integer offenderBookId;
	
	@JsonProperty("weaponsUsed")
	@NotNull
	private String weaponsUsed;

	
	
	@JsonProperty("weaponsDetail")
	@NotNull
	private String weaponsDetail;
	
	
	
	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;
	

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
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

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	

	public String getWeaponsDetail() {
		return weaponsDetail;
	}

	public void setWeaponsDetail(String weaponsDetail) {
		this.weaponsDetail = weaponsDetail;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getWeaponsUsed() {
		return weaponsUsed;
	}

	public void setWeaponsUsed(String weaponeUsed) {
		this.weaponsUsed = weaponeUsed;
	}


	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
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

	

	
	
}
