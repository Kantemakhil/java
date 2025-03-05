package net.syscon.s4.triggers;

import java.io.Serializable;
import java.sql.Date;

import net.syscon.s4.common.beans.BaseModel;

public class AgyIntLocAmendments extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long agyIntLocAmendmentId;
	private Long internalLocationId;
	private Date amendDate;
	private String columnName;
	private String oldValue;
	private String newValue;
	private String deactivateReasonCode;
	private String intLocProfileCode;
	private String actionCode;
	private String amendUserId;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String sealFlag;
	private String intLocProfileType;

	public Long getAgyIntLocAmendmentId() {
		return agyIntLocAmendmentId;
	}

	public void setAgyIntLocAmendmentId(Long agyIntLocAmendmentId) {
		this.agyIntLocAmendmentId = agyIntLocAmendmentId;
	}

	public Long getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public Date getAmendDate() {
		return amendDate;
	}

	public void setAmendDate(Date amendDate) {
		this.amendDate = amendDate;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getDeactivateReasonCode() {
		return deactivateReasonCode;
	}

	public void setDeactivateReasonCode(String deactivateReasonCode) {
		this.deactivateReasonCode = deactivateReasonCode;
	}

	public String getIntLocProfileCode() {
		return intLocProfileCode;
	}

	public void setIntLocProfileCode(String intLocProfileCode) {
		this.intLocProfileCode = intLocProfileCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getAmendUserId() {
		return amendUserId;
	}

	public void setAmendUserId(String amendUserId) {
		this.amendUserId = amendUserId;
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

	public String getIntLocProfileType() {
		return intLocProfileType;
	}

	public void setIntLocProfileType(String intLocProfileType) {
		this.intLocProfileType = intLocProfileType;
	}

}
