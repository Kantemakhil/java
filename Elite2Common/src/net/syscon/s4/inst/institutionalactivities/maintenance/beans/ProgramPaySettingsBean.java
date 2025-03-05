package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramPaySettingsBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("payFlag")
	private String payFlag;

	@JsonProperty("paySystemCode")
	private String paySystemCode;

	@JsonProperty("payCycleStartDay")
	private String payCycleStartDay;

	@JsonProperty("instActDefaultAttCode")
	private String instActDefaultAttCode;

	@JsonProperty("instActMaxScheduledHours")
	private Integer instActMaxScheduledHours;

	@JsonProperty("acpAttCode")
	private String acpAttCode;

	@JsonProperty("instActAttCode")
	private String instActAttCode;

	@JsonProperty("iepLevelCode")
	private String iepLevelCode;

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
	
	@JsonProperty("acpAttCodeVal")
	private byte[] acpAttCodeVal;

	@JsonProperty("instActAttCodeVal")
	private byte[] instActAttCodeVal;
	
	@JsonProperty("instActDelFlag")
	private String instActDelFlag;

	public String getInstActDelFlag() {
		return instActDelFlag;
	}

	public void setInstActDelFlag(String instActDelFlag) {
		this.instActDelFlag = instActDelFlag;
	}

	public ProgramPaySettingsBean() {
		// ProgramPaySettingsBean
	}

	public String getPaySystemCode() {
		return paySystemCode;
	}

	public void setPaySystemCode(String paySystemCode) {
		this.paySystemCode = paySystemCode;
	}

	public Integer getInstActMaxScheduledHours() {
		return instActMaxScheduledHours;
	}

	public void setInstActMaxScheduledHours(Integer instActMaxScheduledHours) {
		this.instActMaxScheduledHours = instActMaxScheduledHours;
	}

	public String getIepLevelCode() {
		return iepLevelCode;
	}

	public void setIepLevelCode(String iepLevelCode) {
		this.iepLevelCode = iepLevelCode;
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

	public String getPayCycleStartDay() {
		return payCycleStartDay;
	}

	public void setPayCycleStartDay(String payCycleStartDay) {
		this.payCycleStartDay = payCycleStartDay;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public String getInstActDefaultAttCode() {
		return instActDefaultAttCode;
	}

	public void setInstActDefaultAttCode(String instActDefaultAttCode) {
		this.instActDefaultAttCode = instActDefaultAttCode;
	}

	public String getAcpAttCode() {
		return acpAttCode;
	}

	public void setAcpAttCode(String acpAttCode) {
		this.acpAttCode = acpAttCode;
	}

	public String getInstActAttCode() {
		return instActAttCode;
	}

	public void setInstActAttCode(String instActAttCode) {
		this.instActAttCode = instActAttCode;
	}

	public byte[] getAcpAttCodeVal() {
		return acpAttCodeVal;
	}

	public byte[] getInstActAttCodeVal() {
		return instActAttCodeVal;
	}

	public void setAcpAttCodeVal(byte[] acpAttCodeVal) {
		this.acpAttCodeVal = acpAttCodeVal;
	}

	public void setInstActAttCodeVal(byte[] instActAttCodeVal) {
		this.instActAttCodeVal = instActAttCodeVal;
	}

}
