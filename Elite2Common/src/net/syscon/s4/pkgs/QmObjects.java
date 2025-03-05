package net.syscon.s4.pkgs;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class QmObjects extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long objectId;
	private String name;
	private String description;
	private Long processId;
	private String qmDataTypeDomain;
	private String qmDataTypeCode;
	private String purpose;
	private String sealFlag;
	private String createUserId;
	private Date createDatetime;
	private String modifyUserId;
	private Date modifyDatetime;

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getQmDataTypeDomain() {
		return qmDataTypeDomain;
	}

	public void setQmDataTypeDomain(String qmDataTypeDomain) {
		this.qmDataTypeDomain = qmDataTypeDomain;
	}

	public String getQmDataTypeCode() {
		return qmDataTypeCode;
	}

	public void setQmDataTypeCode(String qmDataTypeCode) {
		this.qmDataTypeCode = qmDataTypeCode;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
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

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

}
