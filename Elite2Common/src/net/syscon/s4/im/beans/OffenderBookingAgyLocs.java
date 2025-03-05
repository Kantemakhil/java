package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderBookingAgyLocs extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("additionComment")
	private String additionComment;
	@JsonProperty("additionTime")
	private Date additionTime;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("offenderStatus")
	private String offenderStatus;
	@JsonProperty("ptrId")
	private BigDecimal ptrId;
	@JsonProperty("reasonCode")
	private String reasonCode;
	@JsonProperty("removalComment")
	private String removalComment;
	@JsonProperty("removedDate")
	private Date removedDate;
	@JsonProperty("removedReasonCode")
	private String removedReasonCode;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("additionDate")
	private Date additionDate;

	/**
	 * Creates new OffenderBookingAgyLocs class Object
	 */
	public OffenderBookingAgyLocs() {
		// OffenderAlerts
	}

	/**
	 * @return the additionComment
	 */
	public String getAdditionComment() {
		return additionComment;
	}

	/**
	 * @param additionComment
	 *            the additionComment to set
	 */
	public void setAdditionComment(final String additionComment) {
		this.additionComment = additionComment;
	}

	/**
	 * @return the additionTime
	 */
	public Date getAdditionTime() {
		return additionTime;
	}

	/**
	 * @param additionTime
	 *            the additionTime to set
	 */
	public void setAdditionTime(final Date additionTime) {
		this.additionTime = additionTime;
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
	 * @return the offenderStatus
	 */
	public String getOffenderStatus() {
		return offenderStatus;
	}

	/**
	 * @param offenderStatus
	 *            the offenderStatus to set
	 */
	public void setOffenderStatus(final String offenderStatus) {
		this.offenderStatus = offenderStatus;
	}

	/**
	 * @return the ptrId
	 */
	public BigDecimal getPtrId() {
		return ptrId;
	}

	/**
	 * @param ptrId
	 *            the ptrId to set
	 */
	public void setPtrId(final BigDecimal ptrId) {
		this.ptrId = ptrId;
	}

	/**
	 * @return the reasonCode
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * @param reasonCode
	 *            the reasonCode to set
	 */
	public void setReasonCode(final String reasonCode) {
		this.reasonCode = reasonCode;
	}

	/**
	 * @return the removalComment
	 */
	public String getRemovalComment() {
		return removalComment;
	}

	/**
	 * @param removalComment
	 *            the removalComment to set
	 */
	public void setRemovalComment(final String removalComment) {
		this.removalComment = removalComment;
	}

	/**
	 * @return the removedDate
	 */
	public Date getRemovedDate() {
		return removedDate;
	}

	/**
	 * @param removedDate
	 *            the removedDate to set
	 */
	public void setRemovedDate(final Date removedDate) {
		this.removedDate = removedDate;
	}

	/**
	 * @return the removedReasonCode
	 */
	public String getRemovedReasonCode() {
		return removedReasonCode;
	}

	/**
	 * @param removedReasonCode
	 *            the removedReasonCode to set
	 */
	public void setRemovedReasonCode(final String removedReasonCode) {
		this.removedReasonCode = removedReasonCode;
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
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the additionDate
	 */
	public Date getAdditionDate() {
		return additionDate;
	}

	/**
	 * @param additionDate
	 *            the additionDate to set
	 */
	public void setAdditionDate(final Date additionDate) {
		this.additionDate = additionDate;
	}

}
