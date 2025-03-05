package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class CaseloadGrpHolCompens extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("caseloadId")
	String caseloadId;
	
	@JsonProperty("compensationCode")
	String compensationCode;
	
	@JsonProperty("createDatetime")
	Date createDatetime;
	
	@JsonProperty("createUserId")
	String createUserId;
	
	@JsonProperty("holidayEventId")
	long holidayEventId;
	
	@JsonProperty("modifyDatetime")
	Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	String modifyUserId;
	
	@JsonProperty("sealFlag")
	String sealFlag;
	
	@JsonProperty("workGroupId")
	String workGroupId;
	
	@JsonProperty("rowId")
	Integer rowId;

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getCompensationCode() {
		return compensationCode;
	}

	public void setCompensationCode(String compensationCode) {
		this.compensationCode = compensationCode;
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

	public long getHolidayEventId() {
		return holidayEventId;
	}

	public void setHolidayEventId(long holidayEventId) {
		this.holidayEventId = holidayEventId;
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

	public String getWorkGroupId() {
		return workGroupId;
	}

	public void setWorkGroupId(String workGroupId) {
		this.workGroupId = workGroupId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	
}
