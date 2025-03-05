package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemSettingsBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("settingType")
	private String settingType;
	
	@JsonProperty("settingProviderCode")
	private String settingProviderCode;
	
	@JsonProperty("settingValue")
	private String settingValue;
	
	@JsonProperty("settingValueBlob")
	private byte[] settingValueBlob;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("selectedProvider")
	private String selectedProvider;

	public String getSettingType() {
		return settingType;
	}

	public void setSettingType(String settingType) {
		this.settingType = settingType;
	}

	public String getSettingProviderCode() {
		return settingProviderCode;
	}

	public void setSettingProviderCode(String settingProviderCode) {
		this.settingProviderCode = settingProviderCode;
	}

	public String getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}

	public byte[] getSettingValueBlob() {
		return settingValueBlob;
	}

	public void setSettingValueBlob(byte[] settingValueBlob) {
		this.settingValueBlob = settingValueBlob;
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
	
	public String getSelectedProvider() {
		return selectedProvider;
	}

	public void setSelectedProvider(String selectedProvider) {
		this.selectedProvider = selectedProvider;
	}
	
}
