package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderEscape extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("escapeId")
	private long escapeId;

	@JsonProperty("adjustSentenceFlag")
	private String adjustSentenceFlag;

	@JsonProperty("arrestAgyCode")
	private String arrestAgyCode;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("escapeAgyLocId")
	private String escapeAgyLocId;

	@JsonProperty("escapeCircumstanceCode")
	private String escapeCircumstanceCode;

	@JsonProperty("escapeCommentText")
	private String escapeCommentText;

	@JsonProperty("escapeDate")
	private Date escapeDate;

	@JsonProperty("escapeEscortCode")
	private String escapeEscortCode;

	@JsonProperty("escapeMovementReason")
	private String escapeMovementReason;

	@JsonProperty("escapeRegistrationRef")
	private String escapeRegistrationRef;

	@JsonProperty("escapeTime")
	private Date escapeTime;

	@JsonProperty("inCompanyFlag")
	private String inCompanyFlag;

	@JsonProperty("incidentNumber")
	private String incidentNumber;

	@JsonProperty("lastSeenDate")
	private Date lastSeenDate;

	@JsonProperty("lastSeenTime")
	private Date lastSeenTime;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("offenderAdjustId")
	private BigDecimal offenderAdjustId;

	@JsonProperty("readmissAgyLocId")
	private String readmissAgyLocId;

	@JsonProperty("readmissCommentText")
	private String readmissCommentText;

	@JsonProperty("readmissionDate")
	private Date readmissionDate;

	@JsonProperty("readmissionTime")
	private Date readmissionTime;

	@JsonProperty("recaptureDate")
	private Date recaptureDate;

	@JsonProperty("recaptureMovementReason")
	private String recaptureMovementReason;

	@JsonProperty("recpatureCommentText")
	private String recpatureCommentText;

	@JsonProperty("recpatureTime")
	private Date recpatureTime;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("securityBreachCode")
	private String securityBreachCode;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	/**
	 *
	 * @return
	 */
	public long getEscapeId() {
		return escapeId;
	}

	/**
	 *
	 * @param escapeId
	 */
	public void setEscapeId(long escapeId) {
		this.escapeId = escapeId;
	}

	/**
	 *
	 * @return
	 */
	public String getAdjustSentenceFlag() {
		return adjustSentenceFlag;
	}

	/**
	 *
	 * @param adjustSentenceFlag
	 */
	public void setAdjustSentenceFlag(String adjustSentenceFlag) {
		this.adjustSentenceFlag = adjustSentenceFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getArrestAgyCode() {
		return arrestAgyCode;
	}

	/**
	 *
	 * @param arrestAgyCode
	 */
	public void setArrestAgyCode(String arrestAgyCode) {
		this.arrestAgyCode = arrestAgyCode;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getEscapeAgyLocId() {
		return escapeAgyLocId;
	}

	/**
	 *
	 * @param escapeAgyLocId
	 */
	public void setEscapeAgyLocId(String escapeAgyLocId) {
		this.escapeAgyLocId = escapeAgyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getEscapeCircumstanceCode() {
		return escapeCircumstanceCode;
	}

	/**
	 *
	 * @param escapeCircumstanceCode
	 */
	public void setEscapeCircumstanceCode(String escapeCircumstanceCode) {
		this.escapeCircumstanceCode = escapeCircumstanceCode;
	}

	/**
	 *
	 * @return
	 */
	public String getEscapeCommentText() {
		return escapeCommentText;
	}

	/**
	 *
	 * @param escapeCommentText
	 */
	public void setEscapeCommentText(String escapeCommentText) {
		this.escapeCommentText = escapeCommentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getEscapeDate() {
		return escapeDate;
	}

	/**
	 *
	 * @param escapeDate
	 */
	public void setEscapeDate(Date escapeDate) {
		this.escapeDate = escapeDate;
	}

	/**
	 *
	 * @return
	 */
	public String getEscapeEscortCode() {
		return escapeEscortCode;
	}

	/**
	 *
	 * @param escapeEscortCode
	 */
	public void setEscapeEscortCode(String escapeEscortCode) {
		this.escapeEscortCode = escapeEscortCode;
	}

	/**
	 *
	 * @return
	 */
	public String getEscapeMovementReason() {
		return escapeMovementReason;
	}

	/**
	 *
	 * @param escapeMovementReason
	 */
	public void setEscapeMovementReason(String escapeMovementReason) {
		this.escapeMovementReason = escapeMovementReason;
	}

	/**
	 *
	 * @return
	 */
	public String getEscapeRegistrationRef() {
		return escapeRegistrationRef;
	}

	/**
	 *
	 * @param escapeRegistrationRef
	 */
	public void setEscapeRegistrationRef(String escapeRegistrationRef) {
		this.escapeRegistrationRef = escapeRegistrationRef;
	}

	/**
	 *
	 * @return
	 */
	public Date getEscapeTime() {
		return escapeTime;
	}

	/**
	 *
	 * @param escapeTime
	 */
	public void setEscapeTime(Date escapeTime) {
		this.escapeTime = escapeTime;
	}

	/**
	 *
	 * @return
	 */
	public String getInCompanyFlag() {
		return inCompanyFlag;
	}

	/**
	 *
	 * @param inCompanyFlag
	 */
	public void setInCompanyFlag(String inCompanyFlag) {
		this.inCompanyFlag = inCompanyFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getIncidentNumber() {
		return incidentNumber;
	}

	/**
	 *
	 * @param incidentNumber
	 */
	public void setIncidentNumber(String incidentNumber) {
		this.incidentNumber = incidentNumber;
	}

	/**
	 *
	 * @return
	 */
	public Date getLastSeenDate() {
		return lastSeenDate;
	}

	/**
	 *
	 * @param lastSeenDate
	 */
	public void setLastSeenDate(Date lastSeenDate) {
		this.lastSeenDate = lastSeenDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getLastSeenTime() {
		return lastSeenTime;
	}

	/**
	 *
	 * @param lastSeenTime
	 */
	public void setLastSeenTime(Date lastSeenTime) {
		this.lastSeenTime = lastSeenTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOffenderAdjustId() {
		return offenderAdjustId;
	}

	/**
	 *
	 * @param offenderAdjustId
	 */
	public void setOffenderAdjustId(BigDecimal offenderAdjustId) {
		this.offenderAdjustId = offenderAdjustId;
	}

	/**
	 *
	 * @return
	 */
	public String getReadmissAgyLocId() {
		return readmissAgyLocId;
	}

	/**
	 *
	 * @param readmissAgyLocId
	 */
	public void setReadmissAgyLocId(String readmissAgyLocId) {
		this.readmissAgyLocId = readmissAgyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getReadmissCommentText() {
		return readmissCommentText;
	}

	/**
	 *
	 * @param readmissCommentText
	 */
	public void setReadmissCommentText(String readmissCommentText) {
		this.readmissCommentText = readmissCommentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getReadmissionDate() {
		return readmissionDate;
	}

	/**
	 *
	 * @param readmissionDate
	 */
	public void setReadmissionDate(Date readmissionDate) {
		this.readmissionDate = readmissionDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getReadmissionTime() {
		return readmissionTime;
	}

	/**
	 *
	 * @param readmissionTime
	 */
	public void setReadmissionTime(Date readmissionTime) {
		this.readmissionTime = readmissionTime;
	}

	/**
	 *
	 * @return
	 */
	public Date getRecaptureDate() {
		return recaptureDate;
	}

	/**
	 *
	 * @param recaptureDate
	 */
	public void setRecaptureDate(Date recaptureDate) {
		this.recaptureDate = recaptureDate;
	}

	/**
	 *
	 * @return
	 */
	public String getRecaptureMovementReason() {
		return recaptureMovementReason;
	}

	/**
	 *
	 * @param recaptureMovementReason
	 */
	public void setRecaptureMovementReason(String recaptureMovementReason) {
		this.recaptureMovementReason = recaptureMovementReason;
	}

	/**
	 *
	 * @return
	 */
	public String getRecpatureCommentText() {
		return recpatureCommentText;
	}

	/**
	 *
	 * @param recpatureCommentText
	 */
	public void setRecpatureCommentText(String recpatureCommentText) {
		this.recpatureCommentText = recpatureCommentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getRecpatureTime() {
		return recpatureTime;
	}

	/**
	 *
	 * @param recpatureTime
	 */
	public void setRecpatureTime(Date recpatureTime) {
		this.recpatureTime = recpatureTime;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getSecurityBreachCode() {
		return securityBreachCode;
	}

	/**
	 *
	 * @param securityBreachCode
	 */
	public void setSecurityBreachCode(String securityBreachCode) {
		this.securityBreachCode = securityBreachCode;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

	/**
	 * @return the offenderBookId
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	
	
}