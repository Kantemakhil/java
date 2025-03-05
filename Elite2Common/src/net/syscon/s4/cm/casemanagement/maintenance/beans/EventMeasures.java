package net.syscon.s4.cm.casemanagement.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class EventMeasures extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("eventMeasureId")
	private Long eventMeasureId;
	
	@JsonProperty("eventType")
	private String eventType;
	
	@JsonProperty("eventSubType")
	private String eventSubType;
	
	@JsonProperty("measuresStandardFlag")
	private String measuresStandardFlag;
	
	@JsonProperty("listSeq")
	private Long listSeq;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;
	
	@JsonProperty("createDate")
	private Date createDate;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("emailFlag")
	private String emailFlag;
	
	@JsonProperty("smsFlag")
	private String smsFlag;
	
	@JsonProperty("emailAddressCount")
	private Integer emailAddressCount;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;	
	
	@JsonProperty("phoneNumberCount")
	private Integer phoneNumberCount;
	
	@JsonProperty("nonAssociationFlag")
	private String nonAssociationFlag;
	
	@JsonProperty("sanctionsFlag")
	private String sanctionsFlag;
	

	public Long getEventMeasureId() {
		return eventMeasureId;
	}

	public void setEventMeasureId(final Long eventMeasureId) {
		this.eventMeasureId = eventMeasureId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(final String eventType) {
		this.eventType = eventType;
	}

	public String getEventSubType() {
		return eventSubType;
	}

	public void setEventSubType(final String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public String getMeasuresStandardFlag() {
		return measuresStandardFlag;
	}

	public void setMeasuresStandardFlag(final String measuresStandardFlag) {
		this.measuresStandardFlag = measuresStandardFlag;
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

	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
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

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public Integer getEmailAddressCount() {
		return emailAddressCount;
	}

	public void setEmailAddressCount(Integer emailAddressCount) {
		this.emailAddressCount = emailAddressCount;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getPhoneNumberCount() {
		return phoneNumberCount;
	}

	public void setPhoneNumberCount(Integer phoneNumberCount) {
		this.phoneNumberCount = phoneNumberCount;
	}

	public String getNonAssociationFlag() {
		return nonAssociationFlag;
	}

	public void setNonAssociationFlag(String nonAssociationFlag) {
		this.nonAssociationFlag = nonAssociationFlag;
	}

	public String getSanctionsFlag() {
		return sanctionsFlag;
	}

	public void setSanctionsFlag(String sanctionsFlag) {
		this.sanctionsFlag = sanctionsFlag;
	}
	
}
