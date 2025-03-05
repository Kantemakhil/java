package net.syscon.s4.inst.schedules.maintenance.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class FinalSubmitBeanReleaseScheduleSetting extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("keyDatesData")
	private ReleaseSchedulesSettingsBean keyDatesData;
	
	@JsonProperty("alertsGridData")
	private ReleaseSchedulesSettingsBean alertsGridData;

	@JsonProperty("finalChargeIndData")
	private ReleaseSchedulesSettingsBean finalChargeIndData;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	public ReleaseSchedulesSettingsBean getKeyDatesData() {
		return keyDatesData;
	}

	public void setKeyDatesData(ReleaseSchedulesSettingsBean keyDatesData) {
		this.keyDatesData = keyDatesData;
	}

	public ReleaseSchedulesSettingsBean getAlertsGridData() {
		return alertsGridData;
	}

	public void setAlertsGridData(ReleaseSchedulesSettingsBean alertsGridData) {
		this.alertsGridData = alertsGridData;
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

	public ReleaseSchedulesSettingsBean getFinalChargeIndData() {
		return finalChargeIndData;
	}

	public void setFinalChargeIndData(ReleaseSchedulesSettingsBean finalChargeIndData) {
		this.finalChargeIndData = finalChargeIndData;
	}
	

}
