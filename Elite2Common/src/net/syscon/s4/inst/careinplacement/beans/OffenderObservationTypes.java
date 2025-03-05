package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderObservationTypes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("observationType")
	private String observationType;

	@JsonProperty("frequency")
	private Long frequency;
	
	@JsonProperty("notificationFlag")
	private String notificationFlag;

	@JsonProperty("notificationTiming")
	private Long notificationTiming;
	
	@JsonProperty("linkAssessFlag")
	private String linkAssessFlag;

	@JsonProperty("linkSegDiFlag")
	private String linkSegDiFlag;
	
	@JsonProperty("linkIncidentFlag")
	private String linkIncidentFlag;

	@JsonProperty("linkOicFlag")
	private String linkOicFlag;
	
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

	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(final Long frequency) {
		this.frequency = frequency;
	}

	public String getNotificationFlag() {
		return notificationFlag;
	}

	public void setNotificationFlag(final String notificationFlag) {
		this.notificationFlag = notificationFlag;
	}


	public Long getNotificationTiming() {
		return notificationTiming;
	}

	public void setNotificationTiming(final Long notificationTiming) {
		this.notificationTiming = notificationTiming;
	}

	public String getLinkAssessFlag() {
		return linkAssessFlag;
	}

	public void setLinkAssessFlag(final String linkAssessFlag) {
		this.linkAssessFlag = linkAssessFlag;
	}

	public String getLinkSegDiFlag() {
		return linkSegDiFlag;
	}

	public void setLinkSegDiFlag(final String linkSegDiFlag) {
		this.linkSegDiFlag = linkSegDiFlag;
	}

	public String getLinkIncidentFlag() {
		return linkIncidentFlag;
	}

	public void setLinkIncidentFlag(final String linkIncidentFlag) {
		this.linkIncidentFlag = linkIncidentFlag;
	}

	public String getLinkOicFlag() {
		return linkOicFlag;
	}

	public void setLinkOicFlag(final String linkOicFlag) {
		this.linkOicFlag = linkOicFlag;
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

}
