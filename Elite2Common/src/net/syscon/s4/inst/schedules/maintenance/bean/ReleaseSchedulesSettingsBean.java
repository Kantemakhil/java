package net.syscon.s4.inst.schedules.maintenance.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ReleaseSchedulesSettingsBean extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("relSchSettingType")
	private String relSchSettingType;
	
	@JsonProperty("relSchSettingValue")
	private String relSchSettingValue;
	
	@JsonProperty("relSchSettingValueBlob")
	private byte[] relSchSettingValueBlob;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	public String getRelSchSettingType() {
		return relSchSettingType;
	}

	public void setRelSchSettingType(String relSchSettingType) {
		this.relSchSettingType = relSchSettingType;
	}

	public String getRelSchSettingValue() {
		return relSchSettingValue;
	}

	public void setRelSchSettingValue(String relSchSettingValue) {
		this.relSchSettingValue = relSchSettingValue;
	}

	public byte[] getRelSchSettingValueBlob() {
		return relSchSettingValueBlob;
	}

	public void setRelSchSettingValueBlob(byte[] relSchSettingValueBlob) {
		this.relSchSettingValueBlob = relSchSettingValueBlob;
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
