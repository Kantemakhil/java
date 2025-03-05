package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
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
	private java.math.BigDecimal ptrId;

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

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("additionDate")
	private Date additionDate;
	
	@JsonProperty("agyLocDescription")
	private String agyLocDescription;
	
	@JsonProperty("eventSeq")
	private Long eventSeq;
	
	@JsonProperty("make")
	private String make;
	

	/**
	 *
	 * @return
	 */
	public String getAdditionComment() {
		return additionComment;
	}

	/**
	 *
	 * @param additionComment
	 */
	public void setAdditionComment(final String additionComment) {
		this.additionComment = additionComment;
	}

	/**
	 *
	 * @return
	 */
	public Date getAdditionTime() {
		return additionTime;
	}

	/**
	 *
	 * @param additionTime
	 */
	public void setAdditionTime(final Date additionTime) {
		this.additionTime = additionTime;
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
	public String getOffenderStatus() {
		return offenderStatus;
	}

	/**
	 *
	 * @param offenderStatus
	 */
	public void setOffenderStatus(final String offenderStatus) {
		this.offenderStatus = offenderStatus;
	}

	/**
	 *
	 * @return
	 */
	public java.math.BigDecimal getPtrId() {
		return ptrId;
	}

	/**
	 *
	 * @param ptrId
	 */
	public void setPtrId(final BigDecimal ptrId) {
		this.ptrId = ptrId;
	}

	/**
	 *
	 * @return
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 *
	 * @param reasonCode
	 */
	public void setReasonCode(final String reasonCode) {
		this.reasonCode = reasonCode;
	}

	/**
	 *
	 * @return
	 */
	public String getRemovalComment() {
		return removalComment;
	}

	/**
	 *
	 * @param removalComment
	 */
	public void setRemovalComment(final String removalComment) {
		this.removalComment = removalComment;
	}

	/**
	 *
	 * @return
	 */
	public Date getRemovedDate() {
		return removedDate;
	}

	/**
	 *
	 * @param removedDate
	 */
	public void setRemovedDate(final Date removedDate) {
		this.removedDate = removedDate;
	}

	/**
	 *
	 * @return
	 */
	public String getRemovedReasonCode() {
		return removedReasonCode;
	}

	/**
	 *
	 * @param removedReasonCode
	 */
	public void setRemovedReasonCode(final String removedReasonCode) {
		this.removedReasonCode = removedReasonCode;
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
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(final OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
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
	public void setOffenderBookId(final long offenderBookId) {
		this.offenderBookId = offenderBookId;
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
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 *
	 * @return
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 *
	 * @param agyLocId
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 *
	 * @return
	 */
	public Date getAdditionDate() {
		return additionDate;
	}

	/**
	 *
	 * @param additionDate
	 */
	public void setAdditionDate(final Date additionDate) {
		this.additionDate = additionDate;
	}

	/**
	 * @return the agyLocDescription
	 */
	public String getAgyLocDescription() {
		return agyLocDescription;
	}

	/**
	 * @param agyLocDescription the agyLocDescription to set
	 */
	public void setAgyLocDescription(final String agyLocDescription) {
		this.agyLocDescription = agyLocDescription;
	}

	/**
	 * @return the eventSeq
	 */
	public Long getEventSeq() {
		return eventSeq;
	}

	/**
	 * @param eventSeq the eventSeq to set
	 */
	public void setEventSeq(final Long eventSeq) {
		this.eventSeq = eventSeq;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make the make to set
	 */
	public void setMake(final String make) {
		this.make = make;
	}

	
}