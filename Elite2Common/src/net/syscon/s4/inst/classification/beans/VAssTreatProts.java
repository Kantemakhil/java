package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VAssTreatProts extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("offAssNeedId")
	private Long offAssNeedId;
	@JsonProperty("treatmentId")
	private Long treatmentId;
	@JsonProperty("caseworkTypeDesc")
	private String caseworkTypeDesc;
	@JsonProperty("caseworkType")
	private String caseworkType;
	@JsonProperty("programId")
	private BigDecimal programId;
	@JsonProperty("programDesc")
	private String programDesc;
	@JsonProperty("maxScore")
	private Integer maxScore;
	@JsonProperty("minScore")
	private Integer minScore;
	@JsonProperty("referralFlag")
	private String referralFlag;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("programCategory")
	private String programCategory;
	@JsonProperty("prgCategoryDesc")
	private String prgCategoryDesc;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("waitListCount")
	private Integer waitListCount;
	public Integer getWaitListCount() {
		return waitListCount;
	}
	public void setWaitListCount(Integer waitListCount) {
		this.waitListCount = waitListCount;
	}
	public Long getOffAssNeedId() {
		return offAssNeedId;
	}
	public void setOffAssNeedId(Long offAssNeedId) {
		this.offAssNeedId = offAssNeedId;
	}
	public Long getTreatmentId() {
		return treatmentId;
	}
	public void setTreatmentId(Long treatmentId) {
		this.treatmentId = treatmentId;
	}
	public String getCaseworkTypeDesc() {
		return caseworkTypeDesc;
	}
	public void setCaseworkTypeDesc(String caseworkTypeDesc) {
		this.caseworkTypeDesc = caseworkTypeDesc;
	}
	public String getCaseworkType() {
		return caseworkType;
	}
	public void setCaseworkType(String caseworkType) {
		this.caseworkType = caseworkType;
	}
	public BigDecimal getProgramId() {
		return programId;
	}
	public void setProgramId(BigDecimal programId) {
		this.programId = programId;
	}
	public String getProgramDesc() {
		return programDesc;
	}
	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}
	public Integer getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}
	public Integer getMinScore() {
		return minScore;
	}
	public void setMinScore(Integer minScore) {
		this.minScore = minScore;
	}
	public String getReferralFlag() {
		return referralFlag;
	}
	public void setReferralFlag(String referralFlag) {
		this.referralFlag = referralFlag;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getProgramCategory() {
		return programCategory;
	}
	public void setProgramCategory(String programCategory) {
		this.programCategory = programCategory;
	}
	public String getPrgCategoryDesc() {
		return prgCategoryDesc;
	}
	public void setPrgCategoryDesc(String prgCategoryDesc) {
		this.prgCategoryDesc = prgCategoryDesc;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
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
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	

}
