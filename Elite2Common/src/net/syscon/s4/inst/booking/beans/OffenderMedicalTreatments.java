package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderMedicalTreatments extends BaseModel implements Serializable {
	private long offenderMedicaltreatmentId;
	private long offenderHealthProblemId;
	private Date startDate;
	private Date endDate;
	private String caseloadType;
	private String treatmentCode;
	private String commentText;
	private String description;
	private String treatmentProviderCode;
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

	public OffenderMedicalTreatments() {
	}

	public long getOffenderMedicaltreatmentId() {
		return offenderMedicaltreatmentId;
	}

	public void setOffenderMedicaltreatmentId(long offenderMedicaltreatmentId) {
		this.offenderMedicaltreatmentId = offenderMedicaltreatmentId;
	}

	public long getOffenderHealthProblemId() {
		return offenderHealthProblemId;
	}

	public void setOffenderHealthProblemId(long offenderHealthProblemId) {
		this.offenderHealthProblemId = offenderHealthProblemId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCaseloadType() {
		return caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getTreatmentCode() {
		return treatmentCode;
	}

	public void setTreatmentCode(String treatmentCode) {
		this.treatmentCode = treatmentCode;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTreatmentProviderCode() {
		return treatmentProviderCode;
	}

	public void setTreatmentProviderCode(String treatmentProviderCode) {
		this.treatmentProviderCode = treatmentProviderCode;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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
	
}
