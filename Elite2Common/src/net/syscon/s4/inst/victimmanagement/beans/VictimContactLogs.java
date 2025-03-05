package net.syscon.s4.inst.victimmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VictimContactLogs extends BaseModel implements Serializable {
	@JsonProperty("victimContactLogId")
	private BigDecimal victimContactLogId;
	@JsonProperty("victimId")
	private Integer victimId;
	@JsonProperty("logDatetime")
	private Date logDatetime;
	@JsonProperty("category")
	private String category;
	@JsonProperty("type")
	private String type;
	@JsonProperty("createdBy")
	private Integer createdBy;
	@JsonProperty("contactMethod")
	private String contactMethod;
	@JsonProperty("note")
	private String note;
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
	@JsonProperty("staffName")
	private String staffName;
	@JsonProperty("staffId")
	private String staffId;

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public BigDecimal getVictimContactLogId() {
		return victimContactLogId;
	}

	public void setVictimContactLogId(BigDecimal victimContactLogId) {
		this.victimContactLogId = victimContactLogId;
	}

	public Integer getVictimId() {
		return victimId;
	}

	public void setVictimId(Integer victimId) {
		this.victimId = victimId;
	}

	public Date getLogDatetime() {
		return logDatetime;
	}

	public void setLogDatetime(Date logDatetime) {
		this.logDatetime = logDatetime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getContactMethod() {
		return contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	@Override
	public String toString() {
		return "VictimContactLogs [victimContactLogId=" + victimContactLogId + ", victimId=" + victimId
				+ ", logDatetime=" + logDatetime + ", category=" + category + ", type=" + type + ", createdBy="
				+ createdBy + ", contactMethod=" + contactMethod + ", note=" + note + ", createDatetime="
				+ createDatetime + ", createUserId=" + createUserId + ", modifyDatetime=" + modifyDatetime
				+ ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag + ", staffName=" + staffName
				+ ", staffId=" + staffId + "]";
	}

}