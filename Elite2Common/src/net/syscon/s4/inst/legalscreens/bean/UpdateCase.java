package net.syscon.s4.inst.legalscreens.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class UpdateCase extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String calledFrom;
	private String updateReason;
	private Date updateDate;
	private Integer staffId;
	private String userName;
	private String comment;
	private String rowId;
	private Long offenderBookId;
	private String lastName;
	private String firstName;
	private String caseStatus;
	private Long caseId;
	private Long sentConditionId;
	private Integer returnValue;
	private String sentenceCalcType;
	private String category;
	
	public String getSentenceCalcType() {
		return sentenceCalcType;
	}
	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(Integer returnValue) {
		this.returnValue = returnValue;
	}
	@JsonProperty("sentenceSeq")
	private Integer sentenceSeq;
	
	@JsonProperty("conditionCode")
	private String conditionCode;
	
	@JsonProperty("conditionTypeCode")
	private String conditionTypeCode;
	
	
	public Long getSentConditionId() {
		return sentConditionId;
	}
	public void setSentConditionId(Long sentConditionId) {
		this.sentConditionId = sentConditionId;
	}
	public Integer getSentenceSeq() {
		return sentenceSeq;
	}
	public void setSentenceSeq(Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}
	public String getConditionCode() {
		return conditionCode;
	}
	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}
	public String getConditionTypeCode() {
		return conditionTypeCode;
	}
	public void setConditionTypeCode(String conditionTypeCode) {
		this.conditionTypeCode = conditionTypeCode;
	}
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getCalledFrom() {
		return calledFrom;
	}
	public void setCalledFrom(String calledFrom) {
		this.calledFrom = calledFrom;
	}
	public String getUpdateReason() {
		return updateReason;
	}
	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public Long getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
}
