package net.syscon.s4.inst.property.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class PropertySettings extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("settingCode")
	private String settingCode;

	@JsonProperty("settingDescription")
	private String settingDescription;

	@JsonProperty("settingValue")
	private byte[] settingValue;

	private String settingValueString;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	public String getSettingCode() {
		return settingCode;
	}

	public void setSettingCode(String settingCode) {
		this.settingCode = settingCode;
	}

	public String getSettingDescription() {
		return settingDescription;
	}

	public void setSettingDescription(String settingDescription) {
		this.settingDescription = settingDescription;
	}

	public byte[] getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(byte[] settingValue) {
		this.settingValue = settingValue;
	}

	public String getSettingValueString() {
		return settingValueString;
	}

	public void setSettingValueString(String settingValueString) {
		this.settingValueString = settingValueString;
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
