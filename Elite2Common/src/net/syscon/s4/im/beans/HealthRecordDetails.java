package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthRecordDetails extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offHealthRecDtlId")
	private Long offHealthRecDtlId;

	@JsonProperty("offHealthRecId")
	private Long offHealthRecId;

	@JsonProperty("healthTreatType")
	private String healthTreatType;

	@JsonProperty("healthProvider")
	private String healthProvider;

	@JsonProperty("description")
	private String description;

	@JsonProperty("fromDate")
	private Date fromDate;

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

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("returnedOutput")
	private Integer returnedOutput;

	@JsonProperty("recordCreateDatetime")
	private Date recordCreateDatetime;
	@JsonProperty("toDate")
	private Date toDate;
	// def const
	public HealthRecordDetails() {

	}

	

	public Long getOffHealthRecId() {
		return offHealthRecId;
	}

	public void setOffHealthRecId(Long offHealthRecId) {
		this.offHealthRecId = offHealthRecId;
	}

	public String getHealthTreatType() {
		return healthTreatType;
	}

	public void setHealthTreatType(String healthTreatType) {
		this.healthTreatType = healthTreatType;
	}

	public String getHealthProvider() {
		return healthProvider;
	}

	public void setHealthProvider(String healthProvider) {
		this.healthProvider = healthProvider;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
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




	public Long getOffHealthRecDtlId() {
		return offHealthRecDtlId;
	}



	public void setOffHealthRecDtlId(Long offHealthRecDtlId) {
		this.offHealthRecDtlId = offHealthRecDtlId;
	}



	public Integer getReturnedOutput() {
		return returnedOutput;
	}



	public void setReturnedOutput(Integer returnedOutput) {
		this.returnedOutput = returnedOutput;
	}



	public Date getRecordCreateDatetime() {
		return recordCreateDatetime;
	}



	public void setRecordCreateDatetime(Date recordCreateDatetime) {
		this.recordCreateDatetime = recordCreateDatetime;
	}



	public Date getToDate() {
		return toDate;
	}



	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	

}
