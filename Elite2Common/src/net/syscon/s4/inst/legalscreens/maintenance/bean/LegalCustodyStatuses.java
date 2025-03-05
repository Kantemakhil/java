package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class LegalCustodyStatuses extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("statusDescription")
	private String statusDescription;
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
	@JsonProperty("releaseFlag")
	private String releaseFlag;
	@JsonProperty("intakeFlag")
	private String intakeFlag;
	@JsonProperty("alwaysDisplayFlag")
	private String alwaysDisplayFlag;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("statusRank")
	private Long statusRank;
	private Date expiryDate;
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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
	public String getReleaseFlag() {
		return releaseFlag;
	}
	public void setReleaseFlag(String releaseFlag) {
		this.releaseFlag = releaseFlag;
	}
	public String getIntakeFlag() {
		return intakeFlag;
	}
	public void setIntakeFlag(String intakeFlag) {
		this.intakeFlag = intakeFlag;
	}
	public String getAlwaysDisplayFlag() {
		return alwaysDisplayFlag;
	}
	public void setAlwaysDisplayFlag(String alwaysDisplayFlag) {
		this.alwaysDisplayFlag = alwaysDisplayFlag;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Long getStatusRank() {
		return statusRank;
	}
	public void setStatusRank(Long statusRank) {
		this.statusRank = statusRank;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	

}
