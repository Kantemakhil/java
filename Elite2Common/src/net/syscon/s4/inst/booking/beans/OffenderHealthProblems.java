package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderHealthProblems extends BaseModel implements Serializable{
	private long offenderHealthProblemId;
	private long offenderBookId;
	private String problemType;
	private String problemCode;
	private Date startDate;
	private Date endDate;
	private String caseloadType;
	private String description;
	private String problemStatus;
	private String sealFlag;
	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;
	private Integer returnValue;
	
	public Integer getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(Integer returnValue) {
		this.returnValue = returnValue;
	}
	public OffenderHealthProblems() {
	}
	public long getOffenderHealthProblemId() {
		return offenderHealthProblemId;
	}
	public void setOffenderHealthProblemId(final long offenderHealthProblemId) {
		this.offenderHealthProblemId = offenderHealthProblemId;
	}
	public long getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(final long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getProblemType() {
		return problemType;
	}
	public void setProblemType(final String problemType) {
		this.problemType = problemType;
	}
	public String getProblemCode() {
		return problemCode;
	}
	public void setProblemCode(final String problemCode) {
		this.problemCode = problemCode;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	public String getCaseloadType() {
		return caseloadType;
	}
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public String getProblemStatus() {
		return problemStatus;
	}
	public void setProblemStatus(final String problemStatus) {
		this.problemStatus = problemStatus;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
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

	
}
