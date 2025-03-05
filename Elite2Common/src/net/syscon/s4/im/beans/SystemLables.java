package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class SystemLables extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("labelId")
	Integer labelId;
	@JsonProperty("moduleName")
	String moduleName;
	@JsonProperty("msgKey")
	String msgKey;
	@JsonProperty("msgValue")
	String msgValue;
	@JsonProperty("msgType")
	String msgType;
	@JsonProperty("createDateTime")
	Date createDateTime;
	@JsonProperty("createUserId")
	String createUserId;
	@JsonProperty("modifyDateTime")
	Date modifyDateTime;
	@JsonProperty("ModifyuserId")
	String  modifyUserId;
	
	public Integer getLabelId() {
		return labelId;
	}
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getMsgKey() {
		return msgKey;
	}
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	public String getMsgValue() {
		return msgValue;
	}
	public void setMsgValue(String msgValue) {
		this.msgValue = msgValue;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getModifyDateTime() {
		return modifyDateTime;
	}
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
	
	
	

}
