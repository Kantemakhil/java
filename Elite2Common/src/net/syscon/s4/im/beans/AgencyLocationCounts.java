package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class AgencyLocationCounts
 */
public class AgencyLocationCounts implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("reportingLocId")
	private Integer reportingLocId;
	@JsonProperty("countTypeId")
	private Integer countTypeId;
	@JsonProperty("agySeq")
	private Integer agySeq;
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
	@JsonProperty("conductedDatetime")
	private Date conductedDatetime;
	@JsonProperty("recountRsnCode")
	private String recountRsnCode;
	@JsonProperty("discrepRsnCode")
	private String discrepRsnCode;
	@JsonProperty("rsnCodeUserid")
	private String rsnCodeUserid;
	@JsonProperty("rsnCodeDatetime")
	private Date rsnCodeDatetime;
	@JsonProperty("rcntConductedBy")
	private String rcntConductedBy;
	@JsonProperty("rcntDatetime")
	private Date rcntDatetime;
	@JsonProperty("rcntInProgressFlag")
	private String rcntInProgressFlag;
	@JsonProperty("recountTotal")
	private Integer recountTotal;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("verifiedUserId")
	private String verifiedUserId;
	@JsonProperty("verifiedDatetime")
	private Date verifiedDatetime;
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
	@JsonProperty("livingUnitId")
	private Integer livingUnitId;
	@JsonProperty("internalLocationId")
	private Integer internalLocationId;
	@JsonProperty("discrep")
	private Integer discrep;
	@JsonProperty("discrepTemp")
	private Integer discrepTemp;
	@JsonProperty("location")
	private String location;
	@JsonProperty("livingUnitId2")
	private Integer livingUnitId2;
	@JsonProperty("livingUnitId3")
	private Integer livingUnitId3;
	
	
	public AgencyLocationCounts()
	{
		// AgencyLocationCounts
	}
	/**
	 * @return the livingUnitId3
	 */
	public Integer getLivingUnitId3() {
		return livingUnitId3;
	}
	/**
	 * @param livingUnitId3 the livingUnitId3 to set
	 */
	public void setLivingUnitId3(Integer livingUnitId3) {
		this.livingUnitId3 = livingUnitId3;
	}
	/**
	 * @return the livingUnitId2
	 */
	public Integer getLivingUnitId2() {
		return livingUnitId2;
	}
	/**
	 * @param livingUnitId2 the livingUnitId2 to set
	 */
	public void setLivingUnitId2(final Integer livingUnitId2) {
		this.livingUnitId2 = livingUnitId2;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(final String location) {
		this.location = location;
	}
	/**
	 * @return the discrepTemp
	 */
	public Integer getDiscrepTemp() {
		return discrepTemp;
	}
	/**
	 * @param discrepTemp the discrepTemp to set
	 */
	public void setDiscrepTemp(final Integer discrepTemp) {
		this.discrepTemp = discrepTemp;
	}
	/**
	 * @return the discrep
	 */
	public Integer getDiscrep() {
		return discrep;
	}
	/**
	 * @param discrep the discrep to set
	 */
	public void setDiscrep(final Integer discrep) {
		this.discrep = discrep;
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
	 * @param conductedByUserid
	 *            conductedByUserid to set
	 */
	public void setConductedByUserid(final String conductedByUserid) {
		this.conductedByUserid = conductedByUserid;
	}

	/**
	 * return theconductedByUserid
	 */
	public String getConductedByUserid() {
		return this.conductedByUserid;
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
	 * @param conductedDatetime
	 *            conductedDatetime to set
	 */
	public void setConductedDatetime(final Date conductedDatetime) {
		this.conductedDatetime = conductedDatetime;
	}

	/**
	 * return theconductedDatetime
	 */
	public Date getConductedDatetime() {
		return this.conductedDatetime;
	}

	/**
	 * @param recountRsnCode
	 *            recountRsnCode to set
	 */
	public void setRecountRsnCode(final String recountRsnCode) {
		this.recountRsnCode = recountRsnCode;
	}

	/**
	 * return therecountRsnCode
	 */
	public String getRecountRsnCode() {
		return this.recountRsnCode;
	}

	/**
	 * @param discrepRsnCode
	 *            discrepRsnCode to set
	 */
	public void setDiscrepRsnCode(final String discrepRsnCode) {
		this.discrepRsnCode = discrepRsnCode;
	}

	/**
	 * return thediscrepRsnCode
	 */
	public String getDiscrepRsnCode() {
		return this.discrepRsnCode;
	}

	/**
	 * @param rsnCodeUserid
	 *            rsnCodeUserid to set
	 */
	public void setRsnCodeUserid(final String rsnCodeUserid) {
		this.rsnCodeUserid = rsnCodeUserid;
	}

	/**
	 * return thersnCodeUserid
	 */
	public String getRsnCodeUserid() {
		return this.rsnCodeUserid;
	}

	/**
	 * @param rsnCodeDatetime
	 *            rsnCodeDatetime to set
	 */
	public void setRsnCodeDatetime(final Date rsnCodeDatetime) {
		this.rsnCodeDatetime = rsnCodeDatetime;
	}

	/**
	 * return thersnCodeDatetime
	 */
	public Date getRsnCodeDatetime() {
		return this.rsnCodeDatetime;
	}

	/**
	 * @param rcntConductedBy
	 *            rcntConductedBy to set
	 */
	public void setRcntConductedBy(final String rcntConductedBy) {
		this.rcntConductedBy = rcntConductedBy;
	}

	/**
	 * return thercntConductedBy
	 */
	public String getRcntConductedBy() {
		return this.rcntConductedBy;
	}

	/**
	 * @param rcntDatetime
	 *            rcntDatetime to set
	 */
	public void setRcntDatetime(final Date rcntDatetime) {
		this.rcntDatetime = rcntDatetime;
	}

	/**
	 * return thercntDatetime
	 */
	public Date getRcntDatetime() {
		return this.rcntDatetime;
	}

	/**
	 * @param rcntInProgressFlag
	 *            rcntInProgressFlag to set
	 */
	public void setRcntInProgressFlag(final String rcntInProgressFlag) {
		this.rcntInProgressFlag = rcntInProgressFlag;
	}

	/**
	 * return thercntInProgressFlag
	 */
	public String getRcntInProgressFlag() {
		return this.rcntInProgressFlag;
	}

	/**
	 * @param recountTotal
	 *            recountTotal to set
	 */
	public void setRecountTotal(final Integer recountTotal) {
		this.recountTotal = recountTotal;
	}

	/**
	 * return therecountTotal
	 */
	public Integer getRecountTotal() {
		return this.recountTotal;
	}

	/**
	 * @param commentText
	 *            commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * return thecommentText
	 */
	public String getCommentText() {
		return this.commentText;
	}

	/**
	 * @param verifiedUserId
	 *            verifiedUserId to set
	 */
	public void setVerifiedUserId(final String verifiedUserId) {
		this.verifiedUserId = verifiedUserId;
	}

	/**
	 * return theverifiedUserId
	 */
	public String getVerifiedUserId() {
		return this.verifiedUserId;
	}

	/**
	 * @param verifiedDatetime
	 *            verifiedDatetime to set
	 */
	public void setVerifiedDatetime(final Date verifiedDatetime) {
		this.verifiedDatetime = verifiedDatetime;
	}

	/**
	 * return theverifiedDatetime
	 */
	public Date getVerifiedDatetime() {
		return this.verifiedDatetime;
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
	 * @return the livingUnitId
	 */
	public Integer getLivingUnitId() {
		return livingUnitId;
	}

	/**
	 * @param livingUnitId the livingUnitId to set
	 */
	public void setLivingUnitId(Integer livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	/**
	 * @return the internalLocationId
	 */
	public Integer getInternalLocationId() {
		return internalLocationId;
	}

	/**
	 * @param internalLocationId the internalLocationId to set
	 */
	public void setInternalLocationId(Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

}