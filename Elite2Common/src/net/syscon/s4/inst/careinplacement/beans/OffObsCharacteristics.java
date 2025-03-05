package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffObsCharacteristics extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("observationType")
	private String observationType;

	@JsonProperty("frequency")
	private Long frequency;
	
	@JsonProperty("characteristicsCode")
	private String characteristicsCode;
	
	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("expiryDate")
	private Date expiryDate;

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


	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(final Long frequency) {
		this.frequency = frequency;
	}

	public String getCharacteristicsCode() {
		return characteristicsCode;
	}

	public void setCharacteristicsCode(final String characteristicsCode) {
		this.characteristicsCode = characteristicsCode;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
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

	public String getObservationType() {
		return observationType;
	}

	public void setObservationType(final String observationType) {
		this.observationType = observationType;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

}
