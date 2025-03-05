
package net.syscon.s4.inst.automatedcounts.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VReportingLocations
 */
public class VReportingLocations  extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("countTypeId")
	private Integer countTypeId;
	@JsonProperty("countTypeCode")
	private String countTypeCode;
	@JsonProperty("scheduledTime")
	private String scheduledTime;
	@JsonProperty("agySeq")
	private Integer agySeq;
	@JsonProperty("locationType")
	private String locationType;
	@JsonProperty("location1Id")
	private Integer location1Id;
	@JsonProperty("location2Id")
	private Integer location2Id;
	@JsonProperty("location3Id")
	private Integer location3Id;
	@JsonProperty("listSeq")
	private Integer listSeq;
	@JsonProperty("reportingLocId")
	private Integer reportingLocId;
	@JsonProperty("dateSubmitted")
	private Date dateSubmitted;
	@JsonProperty("actualCount")
	private Integer actualCount;
	@JsonProperty("reportedCount")
	private Integer reportedCount;
	@JsonProperty("conductedByUserid")
	private String conductedByUserid;
	@JsonProperty("enteredByUserid")
	private String enteredByUserid;
	@JsonProperty("conductedDateTime")
	private Date conductedDateTime;
	@JsonProperty("recountRsnCode")
	private String recountRsnCode;
	@JsonProperty("discrepRsnCode")
	private String discrepRsnCode;
	@JsonProperty("rsnCodeUserId")
	private String rsnCodeUserId;
	@JsonProperty("rsnCodeDateTime")
	private Date rsnCodeDateTime;
	@JsonProperty("verifyUserId")
	private String verifyUserId;
	@JsonProperty("verifyDateTime")
	private Date verifyDateTime;
	@JsonProperty("rcntConductedBy")
	private String rcntConductedBy;
	@JsonProperty("rcntDateTime")
	private Date rcntDateTime;
	@JsonProperty("rcntInProgressFlag")
	private String rcntInProgressFlag;
	@JsonProperty("recountTotal")
	private Integer recountTotal;
	@JsonProperty("reportedTotal")
	private Integer reportedTotal;
	@JsonProperty("locationDescription")
	private String locationDescription;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}
	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}
	/**
	 * @return the countTypeId
	 */
	public Integer getCountTypeId() {
		return countTypeId;
	}
	/**
	 * @param countTypeId the countTypeId to set
	 */
	public void setCountTypeId(final Integer countTypeId) {
		this.countTypeId = countTypeId;
	}
	/**
	 * @return the countTypeCode
	 */
	public String getCountTypeCode() {
		return countTypeCode;
	}
	/**
	 * @param countTypeCode the countTypeCode to set
	 */
	public void setCountTypeCode(final String countTypeCode) {
		this.countTypeCode = countTypeCode;
	}
	/**
	 * @return the scheduledTime
	 */
	public String getScheduledTime() {
		return scheduledTime;
	}
	/**
	 * @param scheduledTime the scheduledTime to set
	 */
	public void setScheduledTime(final String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	/**
	 * @return the agySeq
	 */
	public Integer getAgySeq() {
		return agySeq;
	}
	/**
	 * @param agySeq the agySeq to set
	 */
	public void setAgySeq(final Integer agySeq) {
		this.agySeq = agySeq;
	}
	/**
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}
	/**
	 * @param locationType the locationType to set
	 */
	public void setLocationType(final String locationType) {
		this.locationType = locationType;
	}
	/**
	 * @return the location1Id
	 */
	public Integer getLocation1Id() {
		return location1Id;
	}
	/**
	 * @param location1Id the location1Id to set
	 */
	public void setLocation1Id(final Integer location1Id) {
		this.location1Id = location1Id;
	}
	/**
	 * @return the location2Id
	 */
	public Integer getLocation2Id() {
		return location2Id;
	}
	/**
	 * @param location2Id the location2Id to set
	 */
	public void setLocation2Id(final Integer location2Id) {
		this.location2Id = location2Id;
	}
	/**
	 * @return the location3Id
	 */
	public Integer getLocation3Id() {
		return location3Id;
	}
	/**
	 * @param location3Id the location3Id to set
	 */
	public void setLocation3Id(final Integer location3Id) {
		this.location3Id = location3Id;
	}
	/**
	 * @return the listSeq
	 */
	public Integer getListSeq() {
		return listSeq;
	}
	/**
	 * @param listSeq the listSeq to set
	 */
	public void setListSeq(final Integer listSeq) {
		this.listSeq = listSeq;
	}
	/**
	 * @return the reportingLocId
	 */
	public Integer getReportingLocId() {
		return reportingLocId;
	}
	/**
	 * @param reportingLocId the reportingLocId to set
	 */
	public void setReportingLocId(final Integer reportingLocId) {
		this.reportingLocId = reportingLocId;
	}
	/**
	 * @return the dateSubmitted
	 */
	public Date getDateSubmitted() {
		return dateSubmitted;
	}
	/**
	 * @param dateSubmitted the dateSubmitted to set
	 */
	public void setDateSubmitted(final Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	/**
	 * @return the actualCount
	 */
	public Integer getActualCount() {
		return actualCount;
	}
	/**
	 * @param actualCount the actualCount to set
	 */
	public void setActualCount(final Integer actualCount) {
		this.actualCount = actualCount;
	}
	/**
	 * @return the reportedCount
	 */
	public Integer getReportedCount() {
		return reportedCount;
	}
	/**
	 * @param reportedCount the reportedCount to set
	 */
	public void setReportedCount(final Integer reportedCount) {
		this.reportedCount = reportedCount;
	}
	/**
	 * @return the conductedByUserid
	 */
	public String getConductedByUserid() {
		return conductedByUserid;
	}
	/**
	 * @param conductedByUserid the conductedByUserid to set
	 */
	public void setConductedByUserid(final String conductedByUserid) {
		this.conductedByUserid = conductedByUserid;
	}
	/**
	 * @return the enteredByUserid
	 */
	public String getEnteredByUserid() {
		return enteredByUserid;
	}
	/**
	 * @param enteredByUserid the enteredByUserid to set
	 */
	public void setEnteredByUserid(final String enteredByUserid) {
		this.enteredByUserid = enteredByUserid;
	}
	/**
	 * @return the conductedDateTime
	 */
	public Date getConductedDateTime() {
		return conductedDateTime;
	}
	/**
	 * @param conductedDateTime the conductedDateTime to set
	 */
	public void setConductedDateTime(final Date conductedDateTime) {
		this.conductedDateTime = conductedDateTime;
	}
	/**
	 * @return the recountRsnCode
	 */
	public String getRecountRsnCode() {
		return recountRsnCode;
	}
	/**
	 * @param recountRsnCode the recountRsnCode to set
	 */
	public void setRecountRsnCode(final String recountRsnCode) {
		this.recountRsnCode = recountRsnCode;
	}
	/**
	 * @return the discrepRsnCode
	 */
	public String getDiscrepRsnCode() {
		return discrepRsnCode;
	}
	/**
	 * @param discrepRsnCode the discrepRsnCode to set
	 */
	public void setDiscrepRsnCode(final String discrepRsnCode) {
		this.discrepRsnCode = discrepRsnCode;
	}
	/**
	 * @return the rsnCodeUserId
	 */
	public String getRsnCodeUserId() {
		return rsnCodeUserId;
	}
	/**
	 * @param rsnCodeUserId the rsnCodeUserId to set
	 */
	public void setRsnCodeUserId(final String rsnCodeUserId) {
		this.rsnCodeUserId = rsnCodeUserId;
	}
	/**
	 * @return the rsnCodeDateTime
	 */
	public Date getRsnCodeDateTime() {
		return rsnCodeDateTime;
	}
	/**
	 * @param rsnCodeDateTime the rsnCodeDateTime to set
	 */
	public void setRsnCodeDateTime(final Date rsnCodeDateTime) {
		this.rsnCodeDateTime = rsnCodeDateTime;
	}
	/**
	 * @return the verifyUserId
	 */
	public String getVerifyUserId() {
		return verifyUserId;
	}
	/**
	 * @param verifyUserId the verifyUserId to set
	 */
	public void setVerifyUserId(final String verifyUserId) {
		this.verifyUserId = verifyUserId;
	}
	/**
	 * @return the verifyDateTime
	 */
	public Date getVerifyDateTime() {
		return verifyDateTime;
	}
	/**
	 * @param verifyDateTime the verifyDateTime to set
	 */
	public void setVerifyDateTime(final Date verifyDateTime) {
		this.verifyDateTime = verifyDateTime;
	}
	/**
	 * @return the rcntConductedBy
	 */
	public String getRcntConductedBy() {
		return rcntConductedBy;
	}
	/**
	 * @param rcntConductedBy the rcntConductedBy to set
	 */
	public void setRcntConductedBy(final String rcntConductedBy) {
		this.rcntConductedBy = rcntConductedBy;
	}
	/**
	 * @return the rcntDateTime
	 */
	public Date getRcntDateTime() {
		return rcntDateTime;
	}
	/**
	 * @param rcntDateTime the rcntDateTime to set
	 */
	public void setRcntDateTime(final Date rcntDateTime) {
		this.rcntDateTime = rcntDateTime;
	}
	/**
	 * @return the rcntInProgressFlag
	 */
	public String getRcntInProgressFlag() {
		return rcntInProgressFlag;
	}
	/**
	 * @param rcntInProgressFlag the rcntInProgressFlag to set
	 */
	public void setRcntInProgressFlag(final String rcntInProgressFlag) {
		this.rcntInProgressFlag = rcntInProgressFlag;
	}
	/**
	 * @return the recountTotal
	 */
	public Integer getRecountTotal() {
		return recountTotal;
	}
	/**
	 * @param recountTotal the recountTotal to set
	 */
	public void setRecountTotal(final Integer recountTotal) {
		this.recountTotal = recountTotal;
	}
	/**
	 * @return the reportedTotal
	 */
	public Integer getReportedTotal() {
		return reportedTotal;
	}
	/**
	 * @param reportedTotal the reportedTotal to set
	 */
	public void setReportedTotal(final Integer reportedTotal) {
		this.reportedTotal = reportedTotal;
	}
	/**
	 * @return the locationDescription
	 */
	public String getLocationDescription() {
		return locationDescription;
	}
	/**
	 * @param locationDescription the locationDescription to set
	 */
	public void setLocationDescription(final String locationDescription) {
		this.locationDescription = locationDescription;
	}
	
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

}
