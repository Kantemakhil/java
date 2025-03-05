package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VAssOffNeeds extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("assessmentId")
	private Long assessmentId;
	@JsonProperty("offAssNeedId")
	private Long offAssNeedId;
	@JsonProperty("assessedNeedCode")
	private String assessedNeedCode;
	@JsonProperty("objective")
	private String objective;
	@JsonProperty("targetDate")
	private Date targetDate;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("assOffCodeDesc")
	private String assOffCodeDesc;
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
	public Long getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}
	public Long getOffAssNeedId() {
		return offAssNeedId;
	}
	public void setOffAssNeedId(Long offAssNeedId) {
		this.offAssNeedId = offAssNeedId;
	}
	public String getAssessedNeedCode() {
		return assessedNeedCode;
	}
	public void setAssessedNeedCode(String assessedNeedCode) {
		this.assessedNeedCode = assessedNeedCode;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
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
	public String getAssOffCodeDesc() {
		return assOffCodeDesc;
	}
	public void setAssOffCodeDesc(String assOffCodeDesc) {
		this.assOffCodeDesc = assOffCodeDesc;
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
