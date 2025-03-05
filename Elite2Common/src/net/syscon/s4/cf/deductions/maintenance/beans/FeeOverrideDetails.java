package net.syscon.s4.cf.deductions.maintenance.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class FeeOverrideDetails extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderFeeId")
	private Long offenderFeeId;

	@JsonProperty("overrideType")
	private String overrideType;

	@JsonProperty("overrideAmount")
	private Long overrideAmount;

	@JsonProperty("overrideStartDate")
	private Date overrideStartDate;

	@JsonProperty("overrideEndDate")
	private Date overrideEndDate;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("priorityIndicator")
	private Integer priorityIndicator;

	@JsonProperty("addedBy")
	private String addedBy;

	@JsonProperty("addedDate")
	private Date addedDate;

	@JsonProperty("offFeeOverrideId")
	private Long offFeeOverrideId;
	
	@JsonProperty("addedByStaffId")
	private Long addedByStaffId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("nonBillableStatus")
	private String nonBillableStatus;
	
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("feeCode")
	private String feeCode;
	
	@JsonProperty("billGeneratedFlag")
	private String billGeneratedFlag;
	
	@JsonProperty("recordDatetime")
	private String recordDatetime;
	
	@JsonProperty("operation")
	private String operation;

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getNonBillableStatus() {
		return nonBillableStatus;
	}

	public void setNonBillableStatus(String nonBillableStatus) {
		this.nonBillableStatus = nonBillableStatus;
	}

	public String getOverrideType() {
		return overrideType;
	}

	public void setOverrideType(String overrideType) {
		this.overrideType = overrideType;
	}

	public Date getOverrideStartDate() {
		return overrideStartDate;
	}

	public void setOverrideStartDate(Date overrideStartDate) {
		this.overrideStartDate = overrideStartDate;
	}

	public Date getOverrideEndDate() {
		return overrideEndDate;
	}

	public void setOverrideEndDate(Date overrideEndDate) {
		this.overrideEndDate = overrideEndDate;
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

	public Long getOverrideAmount() {
		return overrideAmount;
	}

	public void setOverrideAmount(Long overrideAmount) {
		this.overrideAmount = overrideAmount;
	}

	public Integer getPriorityIndicator() {
		return priorityIndicator;
	}

	public void setPriorityIndicator(Integer priorityIndicator) {
		this.priorityIndicator = priorityIndicator;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Long getOffenderFeeId() {
		return offenderFeeId;
	}

	public void setOffenderFeeId(Long offenderFeeId) {
		this.offenderFeeId = offenderFeeId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Long getOffFeeOverrideId() {
		return offFeeOverrideId;
	}

	public void setOffFeeOverrideId(Long offFeeOverrideId) {
		this.offFeeOverrideId = offFeeOverrideId;
	}

	public Long getAddedByStaffId() {
		return addedByStaffId;
	}

	public void setAddedByStaffId(Long addedByStaffId) {
		this.addedByStaffId = addedByStaffId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getBillGeneratedFlag() {
		return billGeneratedFlag;
	}

	public void setBillGeneratedFlag(String billGeneratedFlag) {
		this.billGeneratedFlag = billGeneratedFlag;
	}

	public String getRecordDatetime() {
		return recordDatetime;
	}

	public void setRecordDatetime(String recordDatetime) {
		this.recordDatetime = recordDatetime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	
}
