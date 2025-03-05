package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderBookingEvent extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("bookingEventCode")
	private String bookingEventCode;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("creationDate")
	private Date creationDate;

	@JsonProperty("creationUser")
	private String creationUser;

	@JsonProperty("eventDate")
	private Date eventDate;

	@JsonProperty("eventTime")
	private Date eventTime;

	@JsonProperty("eventUser")
	private String eventUser;

	@JsonProperty("fromAgyLocId")
	private String fromAgyLocId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("reasonCode")
	private String reasonCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("toAgyLocId")
	private String toAgyLocId;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("eventSeq")
	private long eventSeq;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("offenderBookIdCheck")
	private String offenderBookIdCheck;

	@JsonProperty("offenderId")
	private long offenderId;

	@JsonProperty("createBookingFlag")
	private String createBookingFlag;

	@JsonProperty("dspDescription4")
	private String dspDescription4;

	@JsonProperty("nbtOffenderBookId")
	private String nbtOffenderBookId;

	@JsonProperty("nbtOffenderBookId2")
	private String nbtOffenderBookId2;

	@JsonProperty("dspOffenderBookId")
	private String dspOffenderBookId;

	@JsonProperty("tempValue")
	private String tempValue;

	@JsonProperty("checkFlag")
	private String checkFlag;

	@JsonProperty("dspDescription")
	private String dspDescription;

	@JsonProperty("createTrustAccountFlag")
	private String createTrustAccountFlag;

	@JsonProperty("rootOffenderId")
	private Long rootOffenderId;

	@JsonProperty("pCommStatus")
	private String pCommStatus;

	@JsonProperty("staffId")
	private Long staffId;

	@JsonProperty("pBookIdOld")
	private Integer pBookIdOld;

	@JsonProperty("intakefrom")
	private String intakefrom;
	@JsonProperty("intaketo")
	private String intaketo;

	@JsonProperty("intakeReason")
	private String intakeReason;

	@JsonProperty("inOutStatus")
	private String inOutStatus;

	@JsonProperty("bookingType")
	private String bookingType;

	private Integer pTxnSeq;
	
	private String pTriggerBlk;
	
	private Integer pTxnId;
	
	private String pReceiptNo;
	
	private String pTxnPtgType;
	
	private String pSubacType;
	
	private String pTxnDesc;

	private String vReceiptNo;
	
	@JsonProperty("intCorrelationId")
	private Long intCorrelationId;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	public String getvReceiptNo() {
		return vReceiptNo;
	}

	public void setvReceiptNo(String vReceiptNo) {
		this.vReceiptNo = vReceiptNo;
	}

	public Integer getpTxnSeq() {
		return pTxnSeq;
	}

	public void setpTxnSeq(Integer pTxnSeq) {
		this.pTxnSeq = pTxnSeq;
	}

	public String getpTriggerBlk() {
		return pTriggerBlk;
	}

	public void setpTriggerBlk(String pTriggerBlk) {
		this.pTriggerBlk = pTriggerBlk;
	}

	public Integer getpTxnId() {
		return pTxnId;
	}

	public void setpTxnId(Integer pTxnId) {
		this.pTxnId = pTxnId;
	}

	public String getpReceiptNo() {
		return pReceiptNo;
	}

	public void setpReceiptNo(String pReceiptNo) {
		this.pReceiptNo = pReceiptNo;
	}

	public String getpTxnPtgType() {
		return pTxnPtgType;
	}

	public void setpTxnPtgType(String pTxnPtgType) {
		this.pTxnPtgType = pTxnPtgType;
	}

	public String getpSubacType() {
		return pSubacType;
	}

	public void setpSubacType(String pSubacType) {
		this.pSubacType = pSubacType;
	}

	public String getpTxnDesc() {
		return pTxnDesc;
	}

	public void setpTxnDesc(String pTxnDesc) {
		this.pTxnDesc = pTxnDesc;
	}

	public String getInOutStatus() {
		return inOutStatus;
	}

	public void setInOutStatus(String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public String getTempValue() {
		return tempValue;
	}

	public void setTempValue(final String tempValue) {
		this.tempValue = tempValue;
	}

	public String getDspDescription4() {
		return dspDescription4;
	}

	public void setDspDescription4(final String dspDescription4) {
		this.dspDescription4 = dspDescription4;
	}

	/**
	 * @return the offenderBookIdCheck
	 */
	public String getOffenderBookIdCheck() {
		return offenderBookIdCheck;
	}

	/**
	 * @param offenderBookIdCheck the offenderBookIdCheck to set
	 */
	public void setOffenderBookIdCheck(final String offenderBookIdCheck) {
		this.offenderBookIdCheck = offenderBookIdCheck;
	}

	/**
	 *
	 * @return
	 */
	public String getBookingEventCode() {
		return bookingEventCode;
	}

	/**
	 *
	 * @param bookingEventCode
	 */
	public void setBookingEventCode(final String bookingEventCode) {
		this.bookingEventCode = bookingEventCode;
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
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
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
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 *
	 * @param creationDate
	 */
	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 *
	 * @return
	 */
	public String getCreationUser() {
		return creationUser;
	}

	/**
	 *
	 * @param creationUser
	 */
	public void setCreationUser(final String creationUser) {
		this.creationUser = creationUser;
	}

	/**
	 *
	 * @return
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 *
	 * @param eventDate
	 */
	public void setEventDate(final Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getEventTime() {
		return eventTime;
	}

	/**
	 *
	 * @param eventTime
	 */
	public void setEventTime(final Date eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 *
	 * @return
	 */
	public String getEventUser() {
		return eventUser;
	}

	/**
	 *
	 * @param eventUser
	 */
	public void setEventUser(final String eventUser) {
		this.eventUser = eventUser;
	}

	/**
	 *
	 * @return
	 */
	public String getFromAgyLocId() {
		return fromAgyLocId;
	}

	/**
	 *
	 * @param fromAgyLocId
	 */
	public void setFromAgyLocId(final String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
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
	public String getToAgyLocId() {
		return toAgyLocId;
	}

	/**
	 *
	 * @param toAgyLocId
	 */
	public void setToAgyLocId(final String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
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
	public long getEventSeq() {
		return eventSeq;
	}

	/**
	 *
	 * @param eventSeq
	 */
	public void setEventSeq(final long eventSeq) {
		this.eventSeq = eventSeq;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final long offenderId) {
		this.offenderId = offenderId;
	}

	public String getCreateBookingFlag() {
		return createBookingFlag;
	}

	public void setCreateBookingFlag(final String createBookingFlag) {
		this.createBookingFlag = createBookingFlag;
	}

	public String getNbtOffenderBookId() {
		return nbtOffenderBookId;
	}

	public void setNbtOffenderBookId(final String nbtOffenderBookId) {
		this.nbtOffenderBookId = nbtOffenderBookId;
	}

	public String getNbtOffenderBookId2() {
		return nbtOffenderBookId2;
	}

	public void setNbtOffenderBookId2(final String nbtOffenderBookId2) {
		this.nbtOffenderBookId2 = nbtOffenderBookId2;
	}

	public String getDspOffenderBookId() {
		return dspOffenderBookId;
	}

	public void setDspOffenderBookId(final String dspOffenderBookId) {
		this.dspOffenderBookId = dspOffenderBookId;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(final String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getDspDescription() {
		return dspDescription;
	}

	public void setDspDescription(final String dspDescription) {
		this.dspDescription = dspDescription;
	}

	public String getCreateTrustAccountFlag() {
		return createTrustAccountFlag;
	}

	public void setCreateTrustAccountFlag(final String createTrustAccountFlag) {
		this.createTrustAccountFlag = createTrustAccountFlag;
	}

	public void setRootOffenderId(final Long rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getpCommStatus() {
		return pCommStatus;
	}

	public void setpCommStatus(final String pCommStatus) {
		this.pCommStatus = pCommStatus;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(final Long staffId) {
		this.staffId = staffId;
	}

	public Integer getpBookIdOld() {
		return pBookIdOld;
	}

	public void setpBookIdOld(final Integer pBookIdOld) {
		this.pBookIdOld = pBookIdOld;
	}

	public String getIntakefrom() {
		return intakefrom;
	}

	public void setIntakefrom(String intakefrom) {
		this.intakefrom = intakefrom;
	}

	public String getIntaketo() {
		return intaketo;
	}

	public void setIntaketo(String intaketo) {
		this.intaketo = intaketo;
	}

	public String getIntakeReason() {
		return intakeReason;
	}

	public void setIntakeReason(String intakeReason) {
		this.intakeReason = intakeReason;
	}

	public Long getRootOffenderId() {
		return rootOffenderId;
	}

	public void setRootOffenderId(long rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public Long getIntCorrelationId() {
		return intCorrelationId;
	}

	public void setIntCorrelationId(Long intCorrelationId) {
		this.intCorrelationId = intCorrelationId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}