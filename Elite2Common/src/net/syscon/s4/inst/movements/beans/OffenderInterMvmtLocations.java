package net.syscon.s4.inst.movements.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the OFFENDER_INTER_MVMT_LOCATIONS database table.
 * 
 */
public class OffenderInterMvmtLocations implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal agencyImlId;

	private String agencyLocationId;

	private String commentText;

	private Object createDatetime;

	private String createUserId;

	private BigDecimal eventId;

	private BigDecimal fromAgencyImlId;

	private Object modifyDatetime;

	private String modifyUserId;

	private Object movementDate;

	private String movementReasonCode;

	private Object movementTime;

	private String movementType;

	private BigDecimal offenderBookId;

	private BigDecimal offenderImlId;

	private String sealFlag;
	private BigDecimal offenderId;
	private String offenderIdDisplay;
	private BigDecimal internalLocationId;
	private String intLocProfileCode;
	private BigDecimal offenderBookIdOne;
    private BigDecimal offenderIdOne;
    private BigDecimal toInternalLocationIdOne;
    private String firstName;
    private String lastName;
    @JsonProperty("warningMsg")
	private String warningMsg;
	@JsonProperty("warningPrompt")
	private String warningPrompt;
	private Date eventDate;
	private BigDecimal referenceId;
	private Integer returnValue;
	
	public OffenderInterMvmtLocations() {
	}

	public BigDecimal getAgencyImlId() {
		return this.agencyImlId;
	}

	public void setAgencyImlId(BigDecimal agencyImlId) {
		this.agencyImlId = agencyImlId;
	}

	public String getAgencyLocationId() {
		return this.agencyLocationId;
	}

	public void setAgencyLocationId(String agencyLocationId) {
		this.agencyLocationId = agencyLocationId;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getEventId() {
		return this.eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public BigDecimal getFromAgencyImlId() {
		return this.fromAgencyImlId;
	}

	public void setFromAgencyImlId(BigDecimal fromAgencyImlId) {
		this.fromAgencyImlId = fromAgencyImlId;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Object getMovementDate() {
		return this.movementDate;
	}

	public void setMovementDate(Object movementDate) {
		this.movementDate = movementDate;
	}

	public String getMovementReasonCode() {
		return this.movementReasonCode;
	}

	public void setMovementReasonCode(String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	public Object getMovementTime() {
		return this.movementTime;
	}

	public void setMovementTime(Object movementTime) {
		this.movementTime = movementTime;
	}

	public String getMovementType() {
		return this.movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderImlId() {
		return this.offenderImlId;
	}

	public void setOffenderImlId(BigDecimal offenderImlId) {
		this.offenderImlId = offenderImlId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public BigDecimal getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(BigDecimal internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public String getIntLocProfileCode() {
		return intLocProfileCode;
	}

	public void setIntLocProfileCode(String intLocProfileCode) {
		this.intLocProfileCode = intLocProfileCode;
	}

	public BigDecimal getOffenderBookIdOne() {
		return offenderBookIdOne;
	}

	public void setOffenderBookIdOne(BigDecimal offenderBookIdOne) {
		this.offenderBookIdOne = offenderBookIdOne;
	}

	public BigDecimal getOffenderIdOne() {
		return offenderIdOne;
	}

	public void setOffenderIdOne(BigDecimal offenderIdOne) {
		this.offenderIdOne = offenderIdOne;
	}

	public BigDecimal getToInternalLocationIdOne() {
		return toInternalLocationIdOne;
	}

	public void setToInternalLocationIdOne(BigDecimal toInternalLocationIdOne) {
		this.toInternalLocationIdOne = toInternalLocationIdOne;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWarningMsg() {
		return warningMsg;
	}

	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	public String getWarningPrompt() {
		return warningPrompt;
	}

	public void setWarningPrompt(String warningPrompt) {
		this.warningPrompt = warningPrompt;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public BigDecimal getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(BigDecimal referenceId) {
		this.referenceId = referenceId;
	}

	public Integer getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(Integer returnValue) {
		this.returnValue = returnValue;
	}

	
}
