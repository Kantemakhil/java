
package net.syscon.s4.inst.movementexternal.beans;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderEscapes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("escapeId")
	private Integer escapeId;

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
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("bookNo")
	private String bookNo;
	
	@JsonProperty("nbtOffenderId")
	private BigDecimal nbtOffenderId;
	
	@JsonProperty("returnFlag")
    private Boolean returnFlag;
	
	@JsonProperty("dspEscapeReason")
	private String dspEscapeReason;
	
	@JsonProperty("dspRecaptureReason")
	private String dspRecaptureReason;
	
	
	

	public String getDspRecaptureReason() {
		return dspRecaptureReason;
	}

	public void setDspRecaptureReason(String dspRecaptureReason) {
		this.dspRecaptureReason = dspRecaptureReason;
	}

	/**
	 * @return the nbtOffenderId
	 */
	public BigDecimal getNbtOffenderId() {
		return nbtOffenderId;
	}

	/**
	 * @param nbtOffenderId the nbtOffenderId to set
	 */
	public void setNbtOffenderId(BigDecimal nbtOffenderId) {
		this.nbtOffenderId = nbtOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public Integer getEscapeId() {
		return escapeId;
	}

	/**
	 *
	 * @param escapeId
	 */
	public void setEscapeId(final Integer escapeId) {
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
	public void setAdjustSentenceFlag(final String adjustSentenceFlag) {
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
	public void setArrestAgyCode(final String arrestAgyCode) {
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
	public void setCreateDatetime(final Date createDatetime) {
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
	public void setCreateUserId(final String createUserId) {
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
	public void setEscapeAgyLocId(final String escapeAgyLocId) {
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
	public void setEscapeCircumstanceCode(final String escapeCircumstanceCode) {
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
	public void setEscapeCommentText(final String escapeCommentText) {
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
	public void setEscapeDate(final Date escapeDate) {
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
	public void setEscapeEscortCode(final String escapeEscortCode) {
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
	public void setEscapeMovementReason(final String escapeMovementReason) {
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
	public void setEscapeRegistrationRef(final String escapeRegistrationRef) {
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
	public void setEscapeTime(final Date escapeTime) {
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
	public void setInCompanyFlag(final String inCompanyFlag) {
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
	public void setIncidentNumber(final String incidentNumber) {
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
	public void setLastSeenDate(final Date lastSeenDate) {
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
	public void setLastSeenTime(final Date lastSeenTime) {
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
	public void setModifyDatetime(final Date modifyDatetime) {
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
	public void setModifyUserId(final String modifyUserId) {
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
	public void setOffenderAdjustId(final BigDecimal offenderAdjustId) {
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
	public void setReadmissAgyLocId(final String readmissAgyLocId) {
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
	public void setReadmissCommentText(final String readmissCommentText) {
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
	public void setReadmissionDate(final Date readmissionDate) {
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
	public void setReadmissionTime(final Date readmissionTime) {
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
	public void setRecaptureDate(final Date recaptureDate) {
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
	public void setRecaptureMovementReason(final String recaptureMovementReason) {
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
	public void setRecpatureCommentText(final String recpatureCommentText) {
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
	public void setRecpatureTime(final Date recpatureTime) {
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
	public void setSealFlag(final String sealFlag) {
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
	public void setSecurityBreachCode(final String securityBreachCode) {
		this.securityBreachCode = securityBreachCode;
	}
	
	/**
	 * @param offenderBookId
	 *            offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * return theoffenderBookId
	 */
	public Integer getOffenderBookId() {
		return this.offenderBookId;
	}

	/**
	 * return offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay
	 *            offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}
	/**
	 * return bookNo
	 */
	public String getBookNo() {
		return bookNo;
	}

	/**
	 * @param bookNo
	 *            bookNo to set
	 */
	public void setBookNo(final String bookNo) {
		this.bookNo = bookNo;
	}

	/**
	 * @return the returnFlag
	 */
	public Boolean getReturnFlag() {
		return returnFlag;
	}

	/**
	 * @param returnFlag the returnFlag to set
	 */
	public void setReturnFlag(final Boolean returnFlag) {
		this.returnFlag = returnFlag;
	}

	public String getDspEscapeReason() {
		return dspEscapeReason;
	}

	public void setDspEscapeReason(String dspEscapeReason) {
		this.dspEscapeReason = dspEscapeReason;
	}

	@Override
	public String toString() {
		return "OffenderEscapes [escapeId=" + escapeId + ", adjustSentenceFlag=" + adjustSentenceFlag
				+ ", arrestAgyCode=" + arrestAgyCode + ", createDatetime=" + createDatetime + ", createUserId="
				+ createUserId + ", escapeAgyLocId=" + escapeAgyLocId + ", escapeCircumstanceCode="
				+ escapeCircumstanceCode + ", escapeCommentText=" + escapeCommentText + ", escapeDate=" + escapeDate
				+ ", escapeEscortCode=" + escapeEscortCode + ", escapeMovementReason=" + escapeMovementReason
				+ ", escapeRegistrationRef=" + escapeRegistrationRef + ", escapeTime=" + escapeTime + ", inCompanyFlag="
				+ inCompanyFlag + ", incidentNumber=" + incidentNumber + ", lastSeenDate=" + lastSeenDate
				+ ", lastSeenTime=" + lastSeenTime + ", modifyDatetime=" + modifyDatetime + ", modifyUserId="
				+ modifyUserId + ", offenderAdjustId=" + offenderAdjustId + ", readmissAgyLocId=" + readmissAgyLocId
				+ ", readmissCommentText=" + readmissCommentText + ", readmissionDate=" + readmissionDate
				+ ", readmissionTime=" + readmissionTime + ", recaptureDate=" + recaptureDate
				+ ", recaptureMovementReason=" + recaptureMovementReason + ", recpatureCommentText="
				+ recpatureCommentText + ", recpatureTime=" + recpatureTime + ", sealFlag=" + sealFlag
				+ ", securityBreachCode=" + securityBreachCode + ", offenderBookId=" + offenderBookId
				+ ", offenderIdDisplay=" + offenderIdDisplay + ", bookNo=" + bookNo + ", nbtOffenderId=" + nbtOffenderId
				+ ", returnFlag=" + returnFlag + ", dspEscapeReason=" + dspEscapeReason + ", dspRecaptureReason="
				+ dspRecaptureReason + "]";
	}

}