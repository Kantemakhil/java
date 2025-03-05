package net.syscon.s4.inst.institutionalactivities.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the V_PRISON_ACTIVITIES database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VPrisonActivities extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private String activity;

	private String agyLocDesc;

	private String agyLocId;

	private BigDecimal crsActyId;

	private String internalLocationDesc;

	private BigDecimal internalLocationId;
	
	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("programCode")
	private String programCode;
	
	@JsonProperty("programId")
	private BigDecimal programId;

	private Date  scheduleEndDate;

	private Date scheduleStartDate;

	@JsonProperty("service")
	private String service;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("description")
	private String description;

	@JsonProperty("checkFlag")
	private String checkFlag;
	
	@JsonProperty("canDisplay")
	 private Boolean canDisplay = true;
	
	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
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

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public String getProgramCode() {
		return this.programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
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
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

}
