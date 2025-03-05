package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.validators.FieldMatch;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@FieldMatch(first = "alertDate", second = "expiryDate", message = "alertDate.should.not.be.greater.than.expiryDate")
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderAlerts extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("alertCode")
	@NotNull
	@Size(max = 12)
	private String alertCode;

	@JsonProperty("alertDate")
	@NotNull
	private Date alertDate;

	@JsonProperty("alertStatus")
	@NotNull
	@Size(max = 12)
	private String alertStatus;

	@JsonProperty("alertType")
	@NotNull
	@Size(max = 12)
	private String alertType;

	@JsonProperty("authorizePersonText")
	private String authorizePersonText;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDate")
	private Date createDate;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("verifiedFlag")
	@NotNull
	@Size(max = 1)
	private String verifiedFlag;

	@JsonProperty("offenderBooking")
	private OffenderBookings offenderBooking;

	@JsonProperty("offenderBookId")
	@NotNull
	private Long offenderBookId;

	@JsonProperty("alertSeq")
	@NotNull
	private Long alertSeq;

	@JsonProperty("alertTypeDes")
	private String alertTypeDes;

	@JsonProperty("alertCodeDes")
	private String alertCodeDes;

	@JsonProperty("workFlowId")
	private Long workFlowId;

	@JsonProperty("workFlowSeq")
	private Long workFlowSeq;

	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("alertTypeDescription")
	private String alertTypeDescription;
	
	@JsonProperty("alertCodeDescription")
	private String alertCodeDescription;
	
	@JsonProperty("seqValue")
	private Integer seqValue;
	/**
	 * Creates new OffenderAlerts class Object
	 */
	public OffenderAlerts() {
		// OffenderAlerts
	}

	/**
	 * @return the alertCode
	 */
	public String getAlertCode() {
		return alertCode;
	}

	/**
	 * @param alertCode
	 *            the alertCode to set
	 */
	public void setAlertCode(final String alertCode) {
		this.alertCode = alertCode;
	}

	/**
	 * @return the alertDate
	 */
	public Date getAlertDate() {
		return alertDate;
	}

	/**
	 * @param alertDate
	 *            the alertDate to set
	 */
	public void setAlertDate(final Date alertDate) {
		this.alertDate = alertDate;
	}

	/**
	 * @return the alertStatus
	 */
	public String getAlertStatus() {
		return alertStatus;
	}

	/**
	 * @param alertStatus
	 *            the alertStatus to set
	 */
	public void setAlertStatus(final String alertStatus) {
		this.alertStatus = alertStatus;
	}

	/**
	 * @return the alertType
	 */
	public String getAlertType() {
		return alertType;
	}

	/**
	 * @param alertType
	 *            the alertType to set
	 */
	public void setAlertType(final String alertType) {
		this.alertType = alertType;
	}

	/**
	 * @return the authorizePersonText
	 */
	public String getAuthorizePersonText() {
		return authorizePersonText;
	}

	/**
	 * @param authorizePersonText
	 *            the authorizePersonText to set
	 */
	public void setAuthorizePersonText(final String authorizePersonText) {
		this.authorizePersonText = authorizePersonText;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId
	 *            the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType
	 *            the caseloadType to set
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the rootOffenderId
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId
	 *            the rootOffenderId to set
	 */
	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the verifiedFlag
	 */
	public String getVerifiedFlag() {
		return verifiedFlag;
	}

	/**
	 * @param verifiedFlag
	 *            the verifiedFlag to set
	 */
	public void setVerifiedFlag(final String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
	}

	/**
	 * @return the offenderBooking
	 */
	public OffenderBookings getOffenderBooking() {
		return offenderBooking;
	}

	/**
	 * @param offenderBooking
	 *            the offenderBooking to set
	 */
	public void setOffenderBooking(final OffenderBookings offenderBooking) {
		this.offenderBooking = offenderBooking;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the alertSeq
	 */
	public Long getAlertSeq() {
		return alertSeq;
	}

	/**
	 * @param alertSeq
	 *            the alertSeq to set
	 */
	public void setAlertSeq(final Long alertSeq) {
		this.alertSeq = alertSeq;
	}

	/**
	 * @return the alertTypeDes
	 */
	public String getAlertTypeDes() {
		return alertTypeDes;
	}

	/**
	 * @param alertTypeDes
	 *            the alertTypeDes to set
	 */
	public void setAlertTypeDes(final String alertTypeDes) {
		this.alertTypeDes = alertTypeDes;
	}

	/**
	 * @return the alertCodeDes
	 */
	public String getAlertCodeDes() {
		return alertCodeDes;
	}

	/**
	 * @param alertCodeDes
	 *            the alertCodeDes to set
	 */
	public void setAlertCodeDes(final String alertCodeDes) {
		this.alertCodeDes = alertCodeDes;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the workFlowId
	 */
	public Long getWorkFlowId() {
		return workFlowId;
	}

	/**
	 * @param workFlowId
	 *            the workFlowId to set
	 */
	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
	}

	/**
	 * @return the workFlowSeq
	 */
	public Long getWorkFlowSeq() {
		return workFlowSeq;
	}

	/**
	 * @param workFlowSeq
	 *            the workFlowSeq to set
	 */
	public void setWorkFlowSeq(Long workFlowSeq) {
		this.workFlowSeq = workFlowSeq;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAlertTypeDescription() {
		return alertTypeDescription;
	}

	public void setAlertTypeDescription(String alertTypeDescription) {
		this.alertTypeDescription = alertTypeDescription;
	}

	public String getAlertCodeDescription() {
		return alertCodeDescription;
	}

	public void setAlertCodeDescription(String alertCodeDescription) {
		this.alertCodeDescription = alertCodeDescription;
	}

	public Integer getSeqValue() {
		return seqValue;
	}

	public void setSeqValue(Integer seqValue) {
		this.seqValue = seqValue;
	}

}