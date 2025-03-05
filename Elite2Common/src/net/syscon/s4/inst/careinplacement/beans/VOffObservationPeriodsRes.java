package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VOffObservationPeriodsRes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("birthDate")
	private Date birthDate;
	
	@JsonProperty("checkId")
	private Long checkId;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("frequency")
	private Long frequency;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("livingUnitDescription")
	private String livingUnitDescription;
	
	@JsonProperty("livingUnitId")
	private Long livingUnitId;
	
	@JsonProperty("livUnitDesc")
	private String livUnitDesc;
	
	@JsonProperty("observationType")
	private String observationType;
	
	@JsonProperty("observationTypeDesc")
	private String observationTypeDesc;
	
	@JsonProperty("obsPeriodId")
	private Long obsPeriodId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("offenderId")
	private Long offenderId;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("rootOffenderId")
	private Long rootOffenderId;
	
	@JsonProperty("scheduleDate")
	private Date scheduleDate;

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Long getCheckId() {
		return checkId;
	}

	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLivingUnitDescription() {
		return livingUnitDescription;
	}

	public void setLivingUnitDescription(String livingUnitDescription) {
		this.livingUnitDescription = livingUnitDescription;
	}

	public Long getLivingUnitId() {
		return livingUnitId;
	}

	public void setLivingUnitId(Long livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	public String getLivUnitDesc() {
		return livUnitDesc;
	}

	public void setLivUnitDesc(String livUnitDesc) {
		this.livUnitDesc = livUnitDesc;
	}

	public String getObservationType() {
		return observationType;
	}

	public void setObservationType(String observationType) {
		this.observationType = observationType;
	}

	public String getObservationTypeDesc() {
		return observationTypeDesc;
	}

	public void setObservationTypeDesc(String observationTypeDesc) {
		this.observationTypeDesc = observationTypeDesc;
	}

	public Long getObsPeriodId() {
		return obsPeriodId;
	}

	public void setObsPeriodId(Long obsPeriodId) {
		this.obsPeriodId = obsPeriodId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public Long getRootOffenderId() {
		return rootOffenderId;
	}

	public void setRootOffenderId(Long rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	
	
	
	
}
