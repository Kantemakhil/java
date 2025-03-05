package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffIntakeReviewQueue extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	@JsonProperty("bookingNo")
	private String bookingNo;
	@JsonProperty("queuedDate")
	private Date queuedDate;
	@JsonProperty("agyLocIdFrom")
	private String agyLocIdFrom;
	@JsonProperty("agyLocIdTo")
	private String agyLocIdTo;
	@JsonProperty("intakeReason")
	private String intakeReason;
	@JsonProperty("intakeDate")
	private Date intakeDate;
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	@JsonProperty("supStatus")
	private String supStatus;
	@JsonProperty("supStatusDatetime")
	private Date supStatusDatetime;
	@JsonProperty("acceptedFlag")
	private String acceptedFlag;
	@JsonProperty("acceptedDate")
	private Date acceptedDate;
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
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("middleName")
	private String middleName;
	
	@JsonProperty("birthDate")
	private Date birthDate;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("billableFlag")
	private String billableFlag;
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("offenderId")
	private Long offenderId;

	public OffIntakeReviewQueue() {
		// OffSupervisionStsHty
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public Date getQueuedDate() {
		return queuedDate;
	}

	public void setQueuedDate(Date queuedDate) {
		this.queuedDate = queuedDate;
	}

	public String getAgyLocIdFrom() {
		return agyLocIdFrom;
	}

	public void setAgyLocIdFrom(String agyLocIdFrom) {
		this.agyLocIdFrom = agyLocIdFrom;
	}

	public String getAgyLocIdTo() {
		return agyLocIdTo;
	}

	public void setAgyLocIdTo(String agyLocIdTo) {
		this.agyLocIdTo = agyLocIdTo;
	}

	public String getIntakeReason() {
		return intakeReason;
	}

	public void setIntakeReason(String intakeReason) {
		this.intakeReason = intakeReason;
	}

	public Date getIntakeDate() {
		return intakeDate;
	}

	public void setIntakeDate(Date intakeDate) {
		this.intakeDate = intakeDate;
	}

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getSupStatus() {
		return supStatus;
	}

	public void setSupStatus(String supStatus) {
		this.supStatus = supStatus;
	}

	public Date getSupStatusDatetime() {
		return supStatusDatetime;
	}

	public void setSupStatusDatetime(Date supStatusDatetime) {
		this.supStatusDatetime = supStatusDatetime;
	}

	public String getAcceptedFlag() {
		return acceptedFlag;
	}

	public void setAcceptedFlag(String acceptedFlag) {
		this.acceptedFlag = acceptedFlag;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getBillableFlag() {
		return billableFlag;
	}

	public void setBillableFlag(String billableFlag) {
		this.billableFlag = billableFlag;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

}
