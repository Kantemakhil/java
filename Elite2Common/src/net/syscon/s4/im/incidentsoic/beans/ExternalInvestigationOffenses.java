package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ExternalInvestigationOffenses extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("agencyIncidentId")
	private Integer agencyIncidentId;
	
	@JsonProperty("chargeSeq")
	private Integer chargeSeq;
	
	@JsonProperty("eidSeq")
	private Integer eidSeq;
	
	@JsonProperty("externalId")
	private String externalId;
	
	@JsonProperty("contactDate")
	private Date contactDate;
	
	@JsonProperty("contactTime")
	private Date contactTime;
	
	@JsonProperty("extInvStatus")
	private String extInvStatus;
	
	@JsonProperty("extInvComment")
	private String extInvComment;
	
	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	
	@JsonProperty("sealFlag")
	private String sealFlag;	
	
	@JsonProperty("charge")
	private String charge;
	
	@JsonProperty("chargeDescription")
	private String chargeDescription;


	public String getCharge() {
		return charge;
	}


	public void setCharge(String charge) {
		this.charge = charge;
	}


	public String getChargeDescription() {
		return chargeDescription;
	}


	public void setChargeDescription(String chargeDescription) {
		this.chargeDescription = chargeDescription;
	}


	public Integer getAgencyIncidentId() {
		return agencyIncidentId;
	}


	public void setAgencyIncidentId(Integer agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}


	public Integer getChargeSeq() {
		return chargeSeq;
	}


	public void setChargeSeq(Integer chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public Integer getEidSeq() {
		return eidSeq;
	}


	public void setEidSeq(Integer eidSeq) {
		this.eidSeq = eidSeq;
	}


	public String getExternalId() {
		return externalId;
	}


	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}


	public Date getContactDate() {
		return contactDate;
	}


	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}


	public Date getContactTime() {
		return contactTime;
	}


	public void setContactTime(Date contactTime) {
		this.contactTime = contactTime;
	}


	public String getExtInvStatus() {
		return extInvStatus;
	}


	public void setExtInvStatus(String extInvStatus) {
		this.extInvStatus = extInvStatus;
	}


	public String getExtInvComment() {
		return extInvComment;
	}


	public void setExtInvComment(String extInvComment) {
		this.extInvComment = extInvComment;
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


	public Date getModifyDateTime() {
		return modifyDateTime;
	}


	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
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


	@Override
	public String toString() {
		return "ExternalInvestigationOffenses [agencyIncidentId=" + agencyIncidentId + ", chargeSeq=" + chargeSeq
				+ ", eidSeq=" + eidSeq + ", externalId=" + externalId + ", contactDate=" + contactDate
				+ ", contactTime=" + contactTime + ", extInvStatus=" + extInvStatus + ", extInvComment=" + extInvComment
				+ ", createDatetime=" + createDatetime + ", createUserId=" + createUserId + ", modifyDateTime="
				+ modifyDateTime + ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag + ", charge=" + charge
				+ ", chargeDescription=" + chargeDescription + "]";
	}

		
}
