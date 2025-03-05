package net.syscon.s4.inst.automatedcounts.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class TempOidcount
 */
public class TempOidcount extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("sessionId")
	private Integer sessionId;
	@JsonProperty("reportingLocId")
	private Integer reportingLocId;
	@JsonProperty("countTypeId")
	private Integer countTypeId;
	@JsonProperty("agySeq")
	private Integer agySeq;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("countTypeCode")
	private String countTypeCode;
	@JsonProperty("scheduledTime")
	private String scheduledTime;
	@JsonProperty("locationDescription")
	private String locationDescription;
	@JsonProperty("locationType")
	private String locationType;
	@JsonProperty("listSeq")
	private Integer listSeq;
	@JsonProperty("actualCount")
	private Integer actualCount;
	@JsonProperty("reportedCount")
	private Integer reportedCount;
	@JsonProperty("discrepancyCount")
	private Integer discrepancyCount;
	@JsonProperty("dateSubmitted")
	private Date dateSubmitted;
	@JsonProperty("enteredByUserid")
	private String enteredByUserid;
	@JsonProperty("lowestLocationId")
	private Integer lowestLocationId;
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
	private boolean inserted;
	@JsonProperty("countTemp")
	private Integer countTemp;
	@JsonProperty("actualCountTemp")
	private Integer actualCountTemp;
	@JsonProperty("reportedCountTemp")
	private Integer reportedCountTemp;
	@JsonProperty("rowId")
	private Long rowId;
	@JsonProperty("userId")
	private String userId;
	
	/**
	 * @return the rowId
	 */
	public Long getRowId() {
		return rowId;
	}

	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(final Long rowId) {
		this.rowId = rowId;
	}

	/**
	 * @return the countTemp
	 */
	public Integer getCountTemp() {
		return countTemp;
	}

	/**
	 * @param countTemp the countTemp to set
	 */
	public void setCountTemp(Integer countTemp) {
		this.countTemp = countTemp;
	}

	/**
	 * @return the actualCountTemp
	 */
	public Integer getActualCountTemp() {
		return actualCountTemp;
	}

	/**
	 * @param actualCountTemp the actualCountTemp to set
	 */
	public void setActualCountTemp(Integer actualCountTemp) {
		this.actualCountTemp = actualCountTemp;
	}

	/**
	 * @return the reportedCountTemp
	 */
	public Integer getReportedCountTemp() {
		return reportedCountTemp;
	}

	/**
	 * @param reportedCountTemp the reportedCountTemp to set
	 */
	public void setReportedCountTemp(Integer reportedCountTemp) {
		this.reportedCountTemp = reportedCountTemp;
	}

	public TempOidcount() {
		// TempOidcount
	}

	/**
	 * @param sessionId
	 *            sessionId to set
	 */
	public void setSessionId(final Integer sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * return thesessionId
	 */
	public Integer getSessionId() {
		return this.sessionId;
	}

	/**
	 * @param reportingLocId
	 *            reportingLocId to set
	 */
	public void setReportingLocId(final Integer reportingLocId) {
		this.reportingLocId = reportingLocId;
	}

	/**
	 * return thereportingLocId
	 */
	public Integer getReportingLocId() {
		return this.reportingLocId;
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
	 * @param agySeq
	 *            agySeq to set
	 */
	public void setAgySeq(final Integer agySeq) {
		this.agySeq = agySeq;
	}

	/**
	 * return theagySeq
	 */
	public Integer getAgySeq() {
		return this.agySeq;
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
	 * @param locationDescription
	 *            locationDescription to set
	 */
	public void setLocationDescription(final String locationDescription) {
		this.locationDescription = locationDescription;
	}

	/**
	 * return thelocationDescription
	 */
	public String getLocationDescription() {
		return this.locationDescription;
	}

	/**
	 * @param locationType
	 *            locationType to set
	 */
	public void setLocationType(final String locationType) {
		this.locationType = locationType;
	}

	/**
	 * return thelocationType
	 */
	public String getLocationType() {
		return this.locationType;
	}

	/**
	 * @param listSeq
	 *            listSeq to set
	 */
	public void setListSeq(final Integer listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * return thelistSeq
	 */
	public Integer getListSeq() {
		return this.listSeq;
	}

	/**
	 * @param actualCount
	 *            actualCount to set
	 */
	public void setActualCount(final Integer actualCount) {
		this.actualCount = actualCount;
	}

	/**
	 * return theactualCount
	 */
	public Integer getActualCount() {
		return this.actualCount;
	}

	/**
	 * @param reportedCount
	 *            reportedCount to set
	 */
	public void setReportedCount(final Integer reportedCount) {
		this.reportedCount = reportedCount;
	}

	/**
	 * return thereportedCount
	 */
	public Integer getReportedCount() {
		return this.reportedCount;
	}

	/**
	 * @param discrepancyCount
	 *            discrepancyCount to set
	 */
	public void setDiscrepancyCount(final Integer discrepancyCount) {
		this.discrepancyCount = discrepancyCount;
	}

	/**
	 * return thediscrepancyCount
	 */
	public Integer getDiscrepancyCount() {
		return this.discrepancyCount;
	}

	/**
	 * @param dateSubmitted
	 *            dateSubmitted to set
	 */
	public void setDateSubmitted(final Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	/**
	 * return thedateSubmitted
	 */
	public Date getDateSubmitted() {
		return this.dateSubmitted;
	}

	/**
	 * @param enteredByUserid
	 *            enteredByUserid to set
	 */
	public void setEnteredByUserid(final String enteredByUserid) {
		this.enteredByUserid = enteredByUserid;
	}

	/**
	 * return theenteredByUserid
	 */
	public String getEnteredByUserid() {
		return this.enteredByUserid;
	}

	/**
	 * @param lowestLocationId
	 *            lowestLocationId to set
	 */
	public void setLowestLocationId(final Integer lowestLocationId) {
		this.lowestLocationId = lowestLocationId;
	}

	/**
	 * return thelowestLocationId
	 */
	public Integer getLowestLocationId() {
		return this.lowestLocationId;
	}

	/**
	 * @param outTotal
	 *            outTotal to set
	 */
	public void setOutTotal(final Integer outTotal) {
		this.outTotal = outTotal;
	}

	/**
	 * return theoutTotal
	 */
	public Integer getOutTotal() {
		return this.outTotal;
	}

	/**
	 * @param totalMale
	 *            totalMale to set
	 */
	public void setTotalMale(final Integer totalMale) {
		this.totalMale = totalMale;
	}

	/**
	 * return thetotalMale
	 */
	public Integer getTotalMale() {
		return this.totalMale;
	}

	/**
	 * @param totalFemale
	 *            totalFemale to set
	 */
	public void setTotalFemale(final Integer totalFemale) {
		this.totalFemale = totalFemale;
	}

	/**
	 * return thetotalFemale
	 */
	public Integer getTotalFemale() {
		return this.totalFemale;
	}

	/**
	 * @param totalOther
	 *            totalOther to set
	 */
	public void setTotalOther(final Integer totalOther) {
		this.totalOther = totalOther;
	}

	/**
	 * return thetotalOther
	 */
	public Integer getTotalOther() {
		return this.totalOther;
	}

	/**
	 * @param totalMaleOut
	 *            totalMaleOut to set
	 */
	public void setTotalMaleOut(final Integer totalMaleOut) {
		this.totalMaleOut = totalMaleOut;
	}

	/**
	 * return thetotalMaleOut
	 */
	public Integer getTotalMaleOut() {
		return this.totalMaleOut;
	}

	/**
	 * @param totalFemaleOut
	 *            totalFemaleOut to set
	 */
	public void setTotalFemaleOut(final Integer totalFemaleOut) {
		this.totalFemaleOut = totalFemaleOut;
	}

	/**
	 * return thetotalFemaleOut
	 */
	public Integer getTotalFemaleOut() {
		return this.totalFemaleOut;
	}

	/**
	 * @param totalOtherOut
	 *            totalOtherOut to set
	 */
	public void setTotalOtherOut(final Integer totalOtherOut) {
		this.totalOtherOut = totalOtherOut;
	}

	/**
	 * return thetotalOtherOut
	 */
	public Integer getTotalOtherOut() {
		return this.totalOtherOut;
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

}