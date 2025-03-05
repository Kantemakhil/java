package net.syscon.s4.inst.transportation.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class NonAdmittedInmateMvmts extends BaseModel implements Serializable {

	@JsonProperty("inmateId")
	private String inmateId;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("mvmtType")
	private String mvmtType;

	@JsonProperty("mvmtReasonCode")
	private String mvmtReasonCode;

	@JsonProperty("fromAgyLocId")
	private String fromAgyLocId;

	@JsonProperty("toAgyLocId")
	private String toAgyLocId;

	@JsonProperty("responsibleAgency")
	private String responsibleAgency;

	@JsonProperty("emergencyContact")
	private String emergencyContact;

	@JsonProperty("scheduledTripId")
	private BigDecimal scheduledTripId;

	@JsonProperty("nonAdmInmateId")
	private BigDecimal nonAdmInmateId;

	@JsonProperty("fromAgySeq")
	private BigDecimal fromAgySeq;

	@JsonProperty("toAgySeq")
	private BigDecimal toAgySeq;

	@JsonProperty("sexCode")
	private String sexCode;

	@JsonProperty("raceCode")
	private String raceCode;

	@JsonProperty("birthDate")
	private Date birthDate;

	@JsonProperty("ssnId")
	private String ssnId;

	@JsonProperty("inmateCommentText")
	private String inmateCommentText;

	@JsonProperty("movementCommentText")
	private String movementCommentText;

	@JsonProperty("alternateAgyLocId")
	private String alternateAgyLocId;

	@JsonProperty("algoComment")
	private String algoComment;

	@JsonProperty("eventDate")
	private Date eventDate;

	@JsonProperty("tmpGroupId")
	private BigDecimal tmpGroupId;

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
	
	@JsonProperty("offenderId")
	private String offenderId;

	@JsonProperty("alerts")
	private Integer alerts;
	
	@JsonProperty("sanctions")
	private String sanctions;
	
	@JsonProperty("imprisonmentStatus")
	private String imprisonmentStatus;
	
	@JsonProperty("securityLevel")
	private String securityLevel;
	
	
	@JsonProperty("nonAssociation")
	private String nonAssociation;
	
	@JsonProperty("affilliation")
	private Integer affilliation;
	
	@JsonProperty("PotentialScheduledconflicts")
	private String PotentialScheduledconflicts;
	@JsonProperty("ethn")
      private String ethn;
	
	@JsonProperty("ethnicity")
	private String ethnicity;
	 
	@JsonProperty("poteSch")
	private String poteSch;
	
	@JsonProperty("NonAss")
	private String NonAss;
	
	@JsonProperty("secLev")
	private String secLev;
	
	@JsonProperty("sanct")
	private String sanct;
	
	
	@JsonProperty("stgAff")
	private String stgAff;
	
	
	@JsonProperty("impSts")
	private String impSts;
	
	@JsonProperty("sancCode")
	private String sancCode;
	
	@JsonProperty("potSchFlag")
	private String potSchFlag;
	
	public String getSancCode() {
		return sancCode;
	}

	public void setSancCode(String sancCode) {
		this.sancCode = sancCode;
	}

	public String getPotSchFlag() {
		return potSchFlag;
	}

	public void setPotSchFlag(String potSchFlag) {
		this.potSchFlag = potSchFlag;
	}

	public Boolean getPotentialSchld() {
		return potentialSchld;
	}

	public void setPotentialSchld(Boolean potentialSchld) {
		this.potentialSchld = potentialSchld;
	}

	@JsonProperty("potentialSchld")
	private Boolean potentialSchld;
	

	public String getNonAssociation() {
		return nonAssociation;
	}

	public void setNonAssociation(String nonAssociation) {
		this.nonAssociation = nonAssociation;
	}

	public Integer getAffilliation() {
		return affilliation;
	}

	public void setAffilliation(Integer affilliation) {
		this.affilliation = affilliation;
	}

	public String getPotentialScheduledconflicts() {
		return PotentialScheduledconflicts;
	}

	public void setPotentialScheduledconflicts(String potentialScheduledconflicts) {
		PotentialScheduledconflicts = potentialScheduledconflicts;
	}

	public Integer getAlerts() {
		return alerts;
	}

	public void setAlerts(Integer alerts) {
		this.alerts = alerts;
	}

	public String getSanctions() {
		return sanctions;
	}

	public void setSanctions(String sanctions) {
		this.sanctions = sanctions;
	}

	public String getImprisonmentStatus() {
		return imprisonmentStatus;
	}

	public void setImprisonmentStatus(String imprisonmentStatus) {
		this.imprisonmentStatus = imprisonmentStatus;
	}

	public String getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}

	public String getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(String offenderId) {
		this.offenderId = offenderId;
	}

	public String getInmateId() {
		return inmateId;
	}

	public void setInmateId(String inmateId) {
		this.inmateId = inmateId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMvmtType() {
		return mvmtType;
	}

	public void setMvmtType(String mvmtType) {
		this.mvmtType = mvmtType;
	}

	public String getMvmtReasonCode() {
		return mvmtReasonCode;
	}

	public void setMvmtReasonCode(String mvmtReasonCode) {
		this.mvmtReasonCode = mvmtReasonCode;
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

	public String getResponsibleAgency() {
		return responsibleAgency;
	}

	public void setResponsibleAgency(String responsibleAgency) {
		this.responsibleAgency = responsibleAgency;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public BigDecimal getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(BigDecimal scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public BigDecimal getNonAdmInmateId() {
		return nonAdmInmateId;
	}

	public void setNonAdmInmateId(BigDecimal nonAdmInmateId) {
		this.nonAdmInmateId = nonAdmInmateId;
	}

	public BigDecimal getFromAgySeq() {
		return fromAgySeq;
	}

	public void setFromAgySeq(BigDecimal fromAgySeq) {
		this.fromAgySeq = fromAgySeq;
	}

	public BigDecimal getToAgySeq() {
		return toAgySeq;
	}

	public void setToAgySeq(BigDecimal toAgySeq) {
		this.toAgySeq = toAgySeq;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getSsnId() {
		return ssnId;
	}

	public void setSsnId(String ssnId) {
		this.ssnId = ssnId;
	}

	public String getInmateCommentText() {
		return inmateCommentText;
	}

	public void setInmateCommentText(String inmateCommentText) {
		this.inmateCommentText = inmateCommentText;
	}

	public String getMovementCommentText() {
		return movementCommentText;
	}

	public void setMovementCommentText(String movementCommentText) {
		this.movementCommentText = movementCommentText;
	}

	public String getAlternateAgyLocId() {
		return alternateAgyLocId;
	}

	public void setAlternateAgyLocId(String alternateAgyLocId) {
		this.alternateAgyLocId = alternateAgyLocId;
	}

	public String getAlgoComment() {
		return algoComment;
	}

	public void setAlgoComment(String algoComment) {
		this.algoComment = algoComment;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public BigDecimal getTmpGroupId() {
		return tmpGroupId;
	}

	public void setTmpGroupId(BigDecimal tmpGroupId) {
		this.tmpGroupId = tmpGroupId;
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

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
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
	
	public String getImpSts() {
		return impSts;
	}

	public void setImpSts(String impSts) {
		this.impSts = impSts;
	}

	public String getPoteSch() {
		return poteSch;
	}

	public void setPoteSch(String poteSch) {
		this.poteSch = poteSch;
	}

	public String getNonAss() {
		return NonAss;
	}

	public void setNonAss(String nonAss) {
		NonAss = nonAss;
	}

	public String getSecLev() {
		return secLev;
	}

	public void setSecLev(String secLev) {
		this.secLev = secLev;
	}

	public String getEthn() {
		return ethn;
	}

	public void setEthn(String ethn) {
		this.ethn = ethn;
	}

	public String getSanct() {
		return sanct;
	}

	public void setSanct(String sanct) {
		this.sanct = sanct;
	}

	public String getStgAff() {
		return stgAff;
	}

	public void setStgAff(String stgAff) {
		this.stgAff = stgAff;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}


}
