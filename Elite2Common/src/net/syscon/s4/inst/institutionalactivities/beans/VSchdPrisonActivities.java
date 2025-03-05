package net.syscon.s4.inst.institutionalactivities.beans;
import java.math.BigDecimal;
import java.util.Date;

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

	private Date endTime;

	private String internalLocationDesc;

	private BigDecimal internalLocationId;

	private BigDecimal programId;

	private Date scheduleDate;

	private Date scheduleEndDate;

	private Date scheduleStartDate;

	private String service;

	private Date startTime;

	

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

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



	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}


	

}
