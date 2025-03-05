package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddingOffenseOutcome  extends BaseModel implements Serializable {
	
	private static final Long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	@JsonProperty("eventId")
	private Integer eventId; 
	@JsonProperty("offenderChargeId")
	private Integer offenderChargeId;
	@JsonProperty("caseId")
	private Integer caseId;
	@JsonProperty("offenceCode")
	private String offenceCode;
	@JsonProperty("statuteCode")
	private String statuteCode;
	@JsonProperty("mostseriousflag")
	private String mostseriousflag;
	@JsonProperty("plea")
	private String plea;
	@JsonProperty("propertyValue")
	private Float propertyValue;
	@JsonProperty("offenseDate")
	private Date offenseDate;
	@JsonProperty("range")
	private Date range;
	@JsonProperty("resultcode1")
	private String resultcode1;
	@JsonProperty("resultcode1indicator")
	private String resultcode1indicator;
	@JsonProperty("noofoffences")
	private Integer noofoffences;
	@JsonProperty("totalpropertyvalue")
	private Float totalpropertyvalue;
	@JsonProperty("chargeStatus")
	private String chargeStatus;
	@JsonProperty("applyflag")
	private String applyflag;
	@JsonProperty("checksum")
	private Integer checksum;
	@JsonProperty("offenseType")
	private String offenseType;
	
	public Integer getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Integer getOffenderChargeId() {
		return offenderChargeId;
	}
	public void setOffenderChargeId(Integer offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
	}
	public Integer getCaseId() {
		return caseId;
	}
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	public String getOffenceCode() {
		return offenceCode;
	}
	public void setOffenceCode(String offenceCode) {
		this.offenceCode = offenceCode;
	}
	public String getStatuteCode() {
		return statuteCode;
	}
	public void setStatuteCode(String statuteCode) {
		this.statuteCode = statuteCode;
	}
	public String getMostseriousflag() {
		return mostseriousflag;
	}
	public void setMostseriousflag(String mostseriousflag) {
		this.mostseriousflag = mostseriousflag;
	}
	public String getPlea() {
		return plea;
	}
	public void setPlea(String plea) {
		this.plea = plea;
	}
	public Float getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(Float propertyValue) {
		this.propertyValue = propertyValue;
	}
	public Date getOffenseDate() {
		return offenseDate;
	}
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}
	public Date getRange() {
		return range;
	}
	public void setRange(Date range) {
		this.range = range;
	}
	public String getResultcode1() {
		return resultcode1;
	}
	public void setResultcode1(String resultcode1) {
		this.resultcode1 = resultcode1;
	}
	public String getResultcode1indicator() {
		return resultcode1indicator;
	}
	public void setResultcode1indicator(String resultcode1indicator) {
		this.resultcode1indicator = resultcode1indicator;
	}
	public Integer getNoofoffences() {
		return noofoffences;
	}
	public void setNoofoffences(Integer noofoffences) {
		this.noofoffences = noofoffences;
	}
	public Float getTotalpropertyvalue() {
		return totalpropertyvalue;
	}
	public void setTotalpropertyvalue(Float totalpropertyvalue) {
		this.totalpropertyvalue = totalpropertyvalue;
	}
	public String getChargeStatus() {
		return chargeStatus;
	}
	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}
	public String getApplyflag() {
		return applyflag;
	}
	public void setApplyflag(String applyflag) {
		this.applyflag = applyflag;
	}
	public Integer getChecksum() {
		return checksum;
	}
	public void setChecksum(Integer checksum) {
		this.checksum = checksum;
	}
	public String getOffenseType() {
		return offenseType;
	}
	public void setOffenseType(String offenseType) {
		this.offenseType = offenseType;
	}
	
	
	
	
	

	

}
