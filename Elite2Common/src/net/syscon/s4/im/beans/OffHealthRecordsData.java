package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)

public class OffHealthRecordsData extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offHealthRecId")
	private Long offHealthRecId;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("healthType")
	private String healthType;

	@JsonProperty("healthSubType")
	private String healthSubType;

	@JsonProperty("description")
	private String description;

	@JsonProperty("fromDate")
	private Date fromDate;

	@JsonProperty("toDate")
	private Date toDate;

	@JsonProperty("healthStatus")
	private String healthStatus;

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
	private BigDecimal returnedOutput;

	@JsonProperty("recordCreateDatetime")
	private Date recordCreateDatetime;
//def const
	public OffHealthRecordsData() {

	}

	public Long getOffHealthRecId() {
		return offHealthRecId;
	}

	public void setOffHealthRecId(Long offHealthRecId) {
		this.offHealthRecId = offHealthRecId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getHealthType() {
		return healthType;
	}

	public void setHealthType(String healthType) {
		this.healthType = healthType;
	}

	public String getHealthSubType() {
		return healthSubType;
	}

	public void setHealthSubType(String healthSubType) {
		this.healthSubType = healthSubType;
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

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
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

	public BigDecimal getReturnedOutput() {
		return returnedOutput;
	}

	public void setReturnedOutput(BigDecimal returnedOutput) {
		this.returnedOutput = returnedOutput;
	}

	public Date getRecordCreateDatetime() {
		return recordCreateDatetime;
	}

	public void setRecordCreateDatetime(Date recordCreateDatetime) {
		this.recordCreateDatetime = recordCreateDatetime;
	}
	

}
