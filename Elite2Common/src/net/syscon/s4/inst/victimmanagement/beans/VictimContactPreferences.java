package net.syscon.s4.inst.victimmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VictimContactPreferences extends BaseModel implements Serializable {
	@JsonProperty("victimContactPreferencesId")
	private BigDecimal victimContactPreferencesId;
	@JsonProperty("victimId")
	private Integer victimId;
	@JsonProperty("contactType")
	private String contactType;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("deactivatedDatetime")
	private Date deactivatedDatetime;
	@JsonProperty("comment")
	private String comment;
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

	public BigDecimal getVictimContactPreferencesId() {
		return victimContactPreferencesId;
	}

	public void setVictimContactPreferencesId(BigDecimal victimContactPreferencesId) {
		this.victimContactPreferencesId = victimContactPreferencesId;
	}

	public Integer getVictimId() {
		return victimId;
	}

	public void setVictimId(Integer victimId) {
		this.victimId = victimId;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getDeactivatedDatetime() {
		return deactivatedDatetime;
	}

	public void setDeactivatedDatetime(Date deactivatedDatetime) {
		this.deactivatedDatetime = deactivatedDatetime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	@Override
	public String toString() {
		return "VictimContactPreferences [victimContactPreferencesId=" + victimContactPreferencesId + ", victimId="
				+ victimId + ", contactType=" + contactType + ", activeFlag=" + activeFlag + ", deactivatedDatetime="
				+ deactivatedDatetime + ", comment=" + comment + ", createDatetime=" + createDatetime
				+ ", createUserId=" + createUserId + ", modifyDatetime=" + modifyDatetime + ", modifyUserId="
				+ modifyUserId + ", sealFlag=" + sealFlag + "]";
	}

}