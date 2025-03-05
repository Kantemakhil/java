package net.syscon.s4.inst.automatedcounts.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class AgencyCountTypes
 */
public class AgencyCountTypes extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("countTypeId")
	private Integer countTypeId;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("countTypeCode")
	private String countTypeCode;
	@JsonProperty("scheduledTime")
	private String scheduledTime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	private boolean inserted;
	@JsonProperty("code")
	private String code;
	@JsonProperty("description")
	private String description;
	@JsonProperty("sessionId")
	private Long sessionId;
	@JsonProperty("caseLoadId")
	private String caseLoadId;
	@JsonProperty("reportingLocId")
	private Integer reportingLocId;
	@JsonProperty("schTime")
	private String schTime;
	@JsonProperty("checkInitiate")
	private String checkInitiate;
	@JsonProperty("totalActual")
	private Integer totalActual;
	@JsonProperty("totalReported")
	private Integer totalReported;
	@JsonProperty("outTotal")
	private Integer outTotal;
	@JsonProperty("totalMale")
	private Integer totalMale;
	@JsonProperty("totalFemale")
	private Integer totalFemale;
	@JsonProperty("totalOther")
	private Integer totalOther;
	@JsonProperty("totalMaleOut")
	private Integer totalMaleOut;
	@JsonProperty("totalFemaleOut")
	private Integer totalFemaleOut;
	@JsonProperty("totalOtherOut")
	private Integer totalOtherOut;
	@JsonProperty("copyFlag")
	private Boolean copyFlag;
	private Integer houseLoc;
	private Integer intLoc;
	@JsonProperty("agencyCounts")
	private AgencyCounts agencyCounts;
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;

	public AgencyCountTypes() {
		
	}

	/**
	 * @return the schTime
	 */
	public String getSchTime() {
		return schTime;
	}

	/**
	 * @param schTime
	 *            the schTime to set
	 */
	public void setSchTime(String schTime) {
		this.schTime = schTime;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param countTypeId
	 *            countTypeId to set
	 */
	public void setCountTypeId(final Integer countTypeId) {
		this.countTypeId = countTypeId;
	}

	/**
	 * return thecountTypeId
	 */
	public Integer getCountTypeId() {
		return this.countTypeId;
	}

	/**
	 * @param agyLocId
	 *            agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * return theagyLocId
	 */
	public String getAgyLocId() {
		return this.agyLocId;
	}

	/**
	 * @param countTypeCode
	 *            countTypeCode to set
	 */
	public void setCountTypeCode(final String countTypeCode) {
		this.countTypeCode = countTypeCode;
	}

	/**
	 * return thecountTypeCode
	 */
	public String getCountTypeCode() {
		return this.countTypeCode;
	}

	/**
	 * @param scheduledTime
	 *            scheduledTime to set
	 */
	public void setScheduledTime(final String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	/**
	 * return thescheduledTime
	 */
	public String getScheduledTime() {
		return this.scheduledTime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param activeFlag
	 *            activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * return theactiveFlag
	 */
	public String getActiveFlag() {
		return this.activeFlag;
	}

	/**
	 * @param expiryDate
	 *            expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * return theexpiryDate
	 */
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the sessionId
	 */
	public Long getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            the sessionId to set
	 */
	public void setSessionId(final Long sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the caseLoadId
	 */
	public String getCaseLoadId() {
		return caseLoadId;
	}

	/**
	 * @param caseLoadId
	 *            the caseLoadId to set
	 */
	public void setCaseLoadId(final String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	/**
	 * @return the reportingLocId
	 */
	public Integer getReportingLocId() {
		return reportingLocId;
	}

	/**
	 * @param reportingLocId
	 *            the reportingLocId to set
	 */
	public void setReportingLocId(final Integer reportingLocId) {
		this.reportingLocId = reportingLocId;
	}

	/**
	 * @return the checkInitiate
	 */
	public String getCheckInitiate() {
		return checkInitiate;
	}

	/**
	 * @param checkInitiate
	 *            the checkInitiate to set
	 */
	public void setCheckInitiate(final String checkInitiate) {
		this.checkInitiate = checkInitiate;
	}

	/**
	 * @return the totalActual
	 */
	public Integer getTotalActual() {
		return totalActual;
	}

	/**
	 * @param totalActual
	 *            the totalActual to set
	 */
	public void setTotalActual(final Integer totalActual) {
		this.totalActual = totalActual;
	}

	/**
	 * @return the totalReported
	 */
	public Integer getTotalReported() {
		return totalReported;
	}

	/**
	 * @param totalReported
	 *            the totalReported to set
	 */
	public void setTotalReported(final Integer totalReported) {
		this.totalReported = totalReported;
	}

	/**
	 * @return the outTotal
	 */
	public Integer getOutTotal() {
		return outTotal;
	}

	/**
	 * @param outTotal
	 *            the outTotal to set
	 */
	public void setOutTotal(final Integer outTotal) {
		this.outTotal = outTotal;
	}

	/**
	 * @return the totalMale
	 */
	public Integer getTotalMale() {
		return totalMale;
	}

	/**
	 * @param totalMale
	 *            the totalMale to set
	 */
	public void setTotalMale(final Integer totalMale) {
		this.totalMale = totalMale;
	}

	/**
	 * @return the totalFemale
	 */
	public Integer getTotalFemale() {
		return totalFemale;
	}

	/**
	 * @param totalFemale
	 *            the totalFemale to set
	 */
	public void setTotalFemale(final Integer totalFemale) {
		this.totalFemale = totalFemale;
	}

	/**
	 * @return the totalOther
	 */
	public Integer getTotalOther() {
		return totalOther;
	}

	/**
	 * @param totalOther
	 *            the totalOther to set
	 */
	public void setTotalOther(final Integer totalOther) {
		this.totalOther = totalOther;
	}

	/**
	 * @return the totalMaleOut
	 */
	public Integer getTotalMaleOut() {
		return totalMaleOut;
	}

	/**
	 * @param totalMaleOut
	 *            the totalMaleOut to set
	 */
	public void setTotalMaleOut(final Integer totalMaleOut) {
		this.totalMaleOut = totalMaleOut;
	}

	/**
	 * @return the totalFemaleOut
	 */
	public Integer getTotalFemaleOut() {
		return totalFemaleOut;
	}

	/**
	 * @param totalFemaleOut
	 *            the totalFemaleOut to set
	 */
	public void setTotalFemaleOut(final Integer totalFemaleOut) {
		this.totalFemaleOut = totalFemaleOut;
	}

	/**
	 * @return the totalOtherOut
	 */
	public Integer getTotalOtherOut() {
		return totalOtherOut;
	}

	/**
	 * @param totalOtherOut
	 *            the totalOtherOut to set
	 */
	public void setTotalOtherOut(final Integer totalOtherOut) {
		this.totalOtherOut = totalOtherOut;
	}

	public Boolean getCopyFlag() {
		return copyFlag;
	}

	public void setCopyFlag(final Boolean copyFlag) {
		this.copyFlag = copyFlag;
	}

	public Integer getHouseLoc() {
		return houseLoc;
	}

	public void setHouseLoc(Integer houseLoc) {
		this.houseLoc = houseLoc;
	}

	public Integer getIntLoc() {
		return intLoc;
	}

	public void setIntLoc(Integer intLoc) {
		this.intLoc = intLoc;
	}
	

	public AgencyCounts getAgencyCounts() {
		return agencyCounts;
	}

	public void setAgencyCounts(AgencyCounts agencyCounts) {
		this.agencyCounts = agencyCounts;
	}
	
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}


}