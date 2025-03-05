package net.syscon.s4.inst.classification.beans;
import java.math.BigDecimal;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the V_SCHD_PRISON_ACTIVITIES database table.
 * 
 */
public class VSchdPrisonActivities extends BaseModel{
	private static final long serialVersionUID = 1L;
	

	private String activity;

	private String agyLocDesc;

	private String agyLocId;

	private BigDecimal crsActyId;

	private BigDecimal crsSchId;

	private Object endTime;

	private String internalLocationDesc;

	private BigDecimal internalLocationId;

	private BigDecimal programId;

	private Object scheduleDate;

	private Object scheduleEndDate;

	private Object scheduleStartDate;

	private String service;

	private Object startTime;

	

	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getAgyLocDesc() {
		return this.agyLocDesc;
	}

	public void setAgyLocDesc(String agyLocDesc) {
		this.agyLocDesc = agyLocDesc;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public BigDecimal getCrsActyId() {
		return this.crsActyId;
	}

	public void setCrsActyId(BigDecimal crsActyId) {
		this.crsActyId = crsActyId;
	}

	public BigDecimal getCrsSchId() {
		return this.crsSchId;
	}

	public void setCrsSchId(BigDecimal crsSchId) {
		this.crsSchId = crsSchId;
	}

	public Object getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Object endTime) {
		this.endTime = endTime;
	}

	public String getInternalLocationDesc() {
		return this.internalLocationDesc;
	}

	public void setInternalLocationDesc(String internalLocationDesc) {
		this.internalLocationDesc = internalLocationDesc;
	}

	public BigDecimal getInternalLocationId() {
		return this.internalLocationId;
	}

	public void setInternalLocationId(BigDecimal internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public BigDecimal getProgramId() {
		return this.programId;
	}

	public void setProgramId(BigDecimal programId) {
		this.programId = programId;
	}

	public Object getScheduleDate() {
		return this.scheduleDate;
	}

	public void setScheduleDate(Object scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Object getScheduleEndDate() {
		return this.scheduleEndDate;
	}

	public void setScheduleEndDate(Object scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public Object getScheduleStartDate() {
		return this.scheduleStartDate;
	}

	public void setScheduleStartDate(Object scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Object getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Object startTime) {
		this.startTime = startTime;
	}

}
