package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderAlert extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("alertCode")
	private String alertCode;

	@JsonProperty("alertDate")
	private Date alertDate;

	@JsonProperty("alertStatus")
	private String alertStatus;

	@JsonProperty("alertType")
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
	private Date createDatetime;

	@JsonProperty("createUserId")
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
	private String verifiedFlag;

	@JsonProperty("offenderBooking")
	private OffenderBookings offenderBooking;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("alertSeq")
	private long alertSeq;

	/**
	 *
	 * @return
	 */
	public String getAlertCode() {
		return alertCode;
	}

	/**
	 *
	 * @param alertCode
	 */
	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}

	/**
	 *
	 * @return
	 */
	public Date getAlertDate() {
		return alertDate;
	}

	/**
	 *
	 * @param alertDate
	 */
	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}

	/**
	 *
	 * @return
	 */
	public String getAlertStatus() {
		return alertStatus;
	}

	/**
	 *
	 * @param alertStatus
	 */
	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getAlertType() {
		return alertType;
	}

	/**
	 *
	 * @param alertType
	 */
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	/**
	 *
	 * @return
	 */
	public String getAuthorizePersonText() {
		return authorizePersonText;
	}

	/**
	 *
	 * @param authorizePersonText
	 */
	public void setAuthorizePersonText(String authorizePersonText) {
		this.authorizePersonText = authorizePersonText;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 *
	 * @param caseloadId
	 */
	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 *
	 * @param caseloadType
	 */
	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 *
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 *
	 * @param expiryDate
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 *
	 * @param rootOffenderId
	 */
	public void setRootOffenderId(BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
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
	public String getVerifiedFlag() {
		return verifiedFlag;
	}

	/**
	 *
	 * @param verifiedFlag
	 */
	public void setVerifiedFlag(String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBooking() {
		return offenderBooking;
	}

	/**
	 *
	 * @param offenderBooking
	 */
	public void setOffenderBooking(OffenderBookings offenderBooking) {
		this.offenderBooking = offenderBooking;
	}

	/**
	 *
	 * @return
	 */
	public long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public long getAlertSeq() {
		return alertSeq;
	}

	/**
	 *
	 * @param alertSeq
	 */
	public void setAlertSeq(long alertSeq) {
		this.alertSeq = alertSeq;
	}

}