package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffObsPrdCharacteristics extends BaseModel implements Serializable {
private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	
	@JsonProperty("obsPeriodId")
	private long obsPeriodId;	
	
	@JsonProperty("observationType")
	private String observationType;	
	
	@JsonProperty("characteristicsCode")
	private String characteristicsCode;
	
	@JsonProperty("startDate")
	private Date startDate;		
	
	@JsonProperty("endReasonCode")
	private String endReasonCode;
	
	@JsonProperty("endDate")
	private Date endDate;		
	
	@JsonProperty("statusCode")
	private String statusCode;
	
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
	
	@JsonProperty("startTime") 
	private Date startTime;
	
	@JsonProperty("endTime") 
	private Date endTime;
	
	@JsonProperty("frequency")
	private long frequency;
	

	@JsonProperty("obsPrdCharId")
	private long obsPrdCharId;
	
	
	
	public OffObsPrdCharacteristics(){
		
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getObsPeriodId() {
		return obsPeriodId;
	}

	public void setObsPeriodId(final long obsPeriodId) {
		this.obsPeriodId = obsPeriodId;
	}

	public String getObservationType() {
		return observationType;
	}

	public void setObservationType(final String observationType) {
		this.observationType = observationType;
	}

	public String getCharacteristicsCode() {
		return characteristicsCode;
	}

	public void setCharacteristicsCode(final String characteristicsCode) {
		this.characteristicsCode = characteristicsCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public String getEndReasonCode() {
		return endReasonCode;
	}

	public void setEndReasonCode(final String endReasonCode) {
		this.endReasonCode = endReasonCode;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(final String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}
	
	public long getFrequency() {
		return frequency;
	}

	public void setFrequency(final long frequency) {
		this.frequency = frequency;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public long getObsPrdCharId() {
		return obsPrdCharId;
	}

	public void setObsPrdCharId(long obsPrdCharId) {
		this.obsPrdCharId = obsPrdCharId;
	}


}
