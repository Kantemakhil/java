package net.syscon.s4.inst.automatedcounts.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class AgencyCounts
 */
public class AgencyCounts extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("reportingLocId")
	private Integer reportingLocId;
	@JsonProperty("countTypeId")
	private Integer countTypeId;
	@JsonProperty("initiatedDate")
	private Date initiatedDate;
	@JsonProperty("completionDate")
	private Date completionDate;
	@JsonProperty("totalActual")
	private Integer totalActual;
	@JsonProperty("totalReported")
	private Integer totalReported;
	@JsonProperty("conductedByUserid")
	private String conductedByUserid;
	@JsonProperty("outcome")
	private String outcome;
	@JsonProperty("recountRsnCode")
	private String recountRsnCode;
	@JsonProperty("discrepRsnCode")
	private String discrepRsnCode;
	@JsonProperty("rsnCodeUserid")
	private String rsnCodeUserid;
	@JsonProperty("rsnCodeDatetime")
	private Date rsnCodeDatetime;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("parentReportingLocId")
	private Integer parentReportingLocId;
	@JsonProperty("countInProgress")
	private String countInProgress;
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
	@JsonProperty("fromDate")
	private Date fromDate;
	@JsonProperty("toDate")
	private Date toDate;
	@JsonProperty("agylocId")
	private String agylocId;
	@JsonProperty("countTypeCode")
	private String countTypeCode;
	@JsonProperty("scheduledTime")
	private String scheduledTime ;
	@JsonProperty("discrep")
	private Integer discrep;
	@JsonProperty("schTime")
	private String schTime;
	@JsonProperty("sessionId")
	private BigDecimal sessionId;
	@JsonProperty("reCountSessionId")
	private BigDecimal reCountSessionId;
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	
	public AgencyCounts() {
		// AgencyCounts
	}

	/**
	 * @return the schTime
	 */
	public String getSchTime() {
		return schTime;
	}

	/**
	 * @param schTime the schTime to set
	 */
	public void setSchTime(String schTime) {
		this.schTime = schTime;
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
	public void setDiscrep(Integer discrep) {
		this.discrep = discrep;
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
	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
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
	public void setCountTypeCode(String countTypeCode) {
		this.countTypeCode = countTypeCode;
	}

	/**
	 * @return the agylocId
	 */
	public String getAgylocId() {
		return agylocId;
	}

	/**
	 * @param agylocId the agylocId to set
	 */
	public void setAgylocId(String agylocId) {
		this.agylocId = agylocId;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
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
	 * @param initiatedDate
	 *            initiatedDate to set
	 */
	public void setInitiatedDate(final Date initiatedDate) {
		this.initiatedDate = initiatedDate;
	}

	/**
	 * return theinitiatedDate
	 */
	public Date getInitiatedDate() {
		return this.initiatedDate;
	}

	/**
	 * @param completionDate
	 *            completionDate to set
	 */
	public void setCompletionDate(final Date completionDate) {
		this.completionDate = completionDate;
	}

	/**
	 * return thecompletionDate
	 */
	public Date getCompletionDate() {
		return this.completionDate;
	}

	/**
	 * @param totalActual
	 *            totalActual to set
	 */
	public void setTotalActual(final Integer totalActual) {
		this.totalActual = totalActual;
	}

	/**
	 * return thetotalActual
	 */
	public Integer getTotalActual() {
		return this.totalActual;
	}

	/**
	 * @param totalReported
	 *            totalReported to set
	 */
	public void setTotalReported(final Integer totalReported) {
		this.totalReported = totalReported;
	}

	/**
	 * return thetotalReported
	 */
	public Integer getTotalReported() {
		return this.totalReported;
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
	 * @param outcome
	 *            outcome to set
	 */
	public void setOutcome(final String outcome) {
		this.outcome = outcome;
	}

	/**
	 * return theoutcome
	 */
	public String getOutcome() {
		return this.outcome;
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
	 * @param parentReportingLocId
	 *            parentReportingLocId to set
	 */
	public void setParentReportingLocId(final Integer parentReportingLocId) {
		this.parentReportingLocId = parentReportingLocId;
	}

	/**
	 * return theparentReportingLocId
	 */
	public Integer getParentReportingLocId() {
		return this.parentReportingLocId;
	}

	/**
	 * @param countInProgress
	 *            countInProgress to set
	 */
	public void setCountInProgress(final String countInProgress) {
		this.countInProgress = countInProgress;
	}

	/**
	 * return thecountInProgress
	 */
	public String getCountInProgress() {
		return this.countInProgress;
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
	 * @return the sessionId
	 */
	public BigDecimal getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(BigDecimal sessionId) {
		this.sessionId = sessionId;
	}

	public BigDecimal getReCountSessionId() {
		return reCountSessionId;
	}

	public void setReCountSessionId(BigDecimal reCountSessionId) {
		this.reCountSessionId = reCountSessionId;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

}