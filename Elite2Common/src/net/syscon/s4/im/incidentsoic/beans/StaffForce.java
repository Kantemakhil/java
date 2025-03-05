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
public class StaffForce extends BaseModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("sequenceNumber")
	@NotNull
	 private Integer sequenceNumber;
	
	@JsonProperty("agencyIncidentId")
	@NotNull
	private Integer agencyIncidentId ;
	
	@JsonProperty("partySeq")
	@NotNull
	private Integer partySeq ;
	
	@JsonProperty("staffId")
	@NotNull
	private Integer staffId  ;
	
	@JsonProperty("forceUsed")
	@NotNull
	private String forceUsed;
	
	@JsonProperty("forceDetail")
	@NotNull
	private String forceDetail ; 
	
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
	
	@JsonProperty("staffSequnce")
	private Integer staffSequnce;
	
	@JsonProperty("notification")
	private String notification;
	
	@JsonProperty("reasonForceUsed")
	private String reasonForceUsed;
	
	@JsonProperty("sequenceNumberTemp")
	private Integer sequenceNumberTemp;
	
	@JsonProperty("incidentReportId")
	private Integer incidentReportId ;

	
	@JsonProperty("listSeq")
	private Integer listSeq;
	
	
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

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

	public String getForceUsed() {
		return forceUsed;
	}

	public void setForceUsed(String forceUsed) {
		this.forceUsed = forceUsed;
	}

	public String getForceDetail() {
		return forceDetail;
	}

	public void setForceDetail(String forceDetail) {
		this.forceDetail = forceDetail;
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

	public Integer getStaffSequnce() {
		return staffSequnce;
	}

	public void setStaffSequnce(Integer staffSequnce) {
		this.staffSequnce = staffSequnce;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getReasonForceUsed() {
		return reasonForceUsed;
	}

	public void setReasonForceUsed(String reasonForceUsed) {
		this.reasonForceUsed = reasonForceUsed;
	}
	
	public Integer getSequenceNumberTemp() {
		return sequenceNumberTemp;
	}

	public void setSequenceNumberTemp(Integer sequenceNumberTemp) {
		this.sequenceNumberTemp = sequenceNumberTemp;
	}

	public Integer getIncidentReportId() {
		return incidentReportId;
	}

	public void setIncidentReportId(Integer incidentReportId) {
		this.incidentReportId = incidentReportId;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}	

}
