package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffSupervisionStsHty extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	@JsonProperty("supStatus")
	private String supStatus;
	@JsonProperty("billableFlag")
	private String billableFlag;
	@JsonProperty("startDatetime")
	private Date startDatetime;
	@JsonProperty("startTime")
	private Date startTime;
	@JsonProperty("endDatetime")
	private Date endDatetime;
	@JsonProperty("endTime")
	private Date endTime;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("errorFlag")
	private String errorFlag;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("createDateTime")
	private Date createDateTime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	@JsonProperty("offenderSupId")
	private Integer offenderSupId;
	@JsonProperty("billableFlagValue")
	private String billableFlagValue;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("trustAccount")
	private Boolean trustAccount;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("offenderId")
	private Long offenderId;
	@JsonProperty("moduleName")
	private String moduleName;

	public OffSupervisionStsHty() {
		// OffSupervisionStsHty
	}

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getSupStatus() {
		return supStatus;
	}

	public void setSupStatus(String supStatus) {
		this.supStatus = supStatus;
	}

	public String getBillableFlag() {
		return billableFlag;
	}

	public void setBillableFlag(String billableFlag) {
		this.billableFlag = billableFlag;
	}

	public Date getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(String errorFlag) {
		this.errorFlag = errorFlag;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public Integer getOffenderSupId() {
		return offenderSupId;
	}

	public void setOffenderSupId(Integer offenderSupId) {
		this.offenderSupId = offenderSupId;
	}

	public String getBillableFlagValue() {
		return billableFlagValue;
	}

	public void setBillableFlagValue(String billableFlagValue) {
		this.billableFlagValue = billableFlagValue;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public Boolean getTrustAccount() {
		return trustAccount;
	}

	public void setTrustAccount(Boolean trustAccount) {
		this.trustAccount = trustAccount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
