package net.syscon.s4.inst.offenderobservations.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderObservationInquiry extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("checkDate")
	private Date checkDate;
	@JsonProperty("checkTime")
	private Date checkTime;
	@JsonProperty("livingUnitDescription")
	private String livingUnitDescription;
	@JsonProperty("observationType")
	private String observationType;
	@JsonProperty("frequency")
	private BigDecimal frequency;
	@JsonProperty("nextCheckTime")
	private Date nextCheckTime;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("overDueFlag")
	private String overDueFlag;
	@JsonProperty("zoneId")
	private String zoneId;
	
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	
	@JsonProperty("checkId")
	private long checkId;	
	
	@JsonProperty("obsPeriodId")
	private long obsPeriodId;
	
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
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getLivingUnitDescription() {
		return livingUnitDescription;
	}
	public void setLivingUnitDescription(String livingUnitDescription) {
		this.livingUnitDescription = livingUnitDescription;
	}
	public String getObservationType() {
		return observationType;
	}
	public void setObservationType(String observationType) {
		this.observationType = observationType;
	}
	public BigDecimal getFrequency() {
		return frequency;
	}
	public void setFrequency(BigDecimal frequency) {
		this.frequency = frequency;
	}
	public Date getNextCheckTime() {
		return nextCheckTime;
	}
	public void setNextCheckTime(Date nextCheckTime) {
		this.nextCheckTime = nextCheckTime;
	}
	public String getAgyLocId() {
		return agyLocId;
	}
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	public String getOverDueFlag() {
		return overDueFlag;
	}
	public void setOverDueFlag(String overDueFlag) {
		this.overDueFlag = overDueFlag;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public BigDecimal getOffenderId() {
		return offenderId;
	}
	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}
	public long getCheckId() {
		return checkId;
	}
	public void setCheckId(long checkId) {
		this.checkId = checkId;
	}
	public long getObsPeriodId() {
		return obsPeriodId;
	}
	public void setObsPeriodId(long obsPeriodId) {
		this.obsPeriodId = obsPeriodId;
	}

}
