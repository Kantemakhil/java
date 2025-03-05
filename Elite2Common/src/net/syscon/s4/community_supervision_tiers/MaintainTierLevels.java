package net.syscon.s4.community_supervision_tiers;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MaintainTierLevels implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("code")
	private String code;
	@JsonProperty("description")
	private String description;
	@JsonProperty("workloadValue")
	private Long workloadValue;
	@JsonProperty("reviewDays")
	private Long reviewDays;
	@JsonProperty("defaultIntakeTierFlag")
	private String defaultIntakeTierFlag;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("listSequence")
	private Long listSequence;
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
	@JsonProperty("editableBtn")
	private Integer editableBtn;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getWorkloadValue() {
		return workloadValue;
	}
	public void setWorkloadValue(Long workloadValue) {
		this.workloadValue = workloadValue;
	}
	public Long getReviewDays() {
		return reviewDays;
	}
	public void setReviewDays(Long reviewDays) {
		this.reviewDays = reviewDays;
	}
	public String getDefaultIntakeTierFlag() {
		return defaultIntakeTierFlag;
	}
	public void setDefaultIntakeTierFlag(String defaultIntakeTierFlag) {
		this.defaultIntakeTierFlag = defaultIntakeTierFlag;
	}
	public String getCaseloadId() {
		return caseloadId;
	}
	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}
	public Long getListSequence() {
		return listSequence;
	}
	public void setSequence(Long listSequence) {
		this.listSequence = listSequence;
	}
	public String getActive() {
		return activeFlag;
	}
	public void setActive(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public void setListSequence(Long listSequence) {
		this.listSequence = listSequence;
	}
	public Integer getEditableBtn() {
		return editableBtn;
	}
	public void setEditableBtn(Integer editableBtn) {
		this.editableBtn = editableBtn;
	}
	
	
	
	
	
	
	
}
