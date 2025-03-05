package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffObsAddDetails extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("observationType")
	private String observationType;
	
	@JsonProperty("profileType")
	private String profileType;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("formatType")
	private String formatType;
	
	@JsonProperty("listSeq")
	private Long listSeq;
	
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

	
	
	public String getObservationType() {
		return observationType;
	}

	public void setObservationType(final String observationType) {
		this.observationType = observationType;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(final String profileType) {
		this.profileType = profileType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getFormatType() {
		return formatType;
	}

	public void setFormatType(final String formatType) {
		this.formatType = formatType;
	}

	public Long getListSeq() {
		return listSeq;
	}

	public void setListSeq(final Long listSeq) {
		this.listSeq = listSeq;
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

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
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


}
