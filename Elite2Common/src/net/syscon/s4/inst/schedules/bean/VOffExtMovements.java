package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_OFF_EXT_MOVEMENTS database table.
 * 
 */
public class VOffExtMovements implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private String arrestAgencyLocId;

	private String commentText;

	private String directionCode;

	private String escortCode;

	private String escortText;

	private String firstName;

	private String fromAddressDesc;

	private BigDecimal fromAddressId;

	private String fromAgyLocDesc;

	private String fromAgyLocId;

	private String fromCity;

	private String fromCityDesc;

	private String internalScheduleReasonCode;

	private String internalScheduleType;

	private String lastName;

	private Date movementDate;

	private String movementReasonCode;

	private BigDecimal movementSeq;

	private Date movementTime;

	private String movementType;

	private BigDecimal offenderBookId;

	private String offenderIdDisplay;

	private Date reportingDate;

	private Date reportingTime;

	private String toAddressDesc;

	private BigDecimal toAddressId;

	private String toAgyLocDesc;

	private String toAgyLocId;

	private String toCity;

	private String toCityDesc;

	private String toProvStatCode;
	
	private String createUserId;
	
	private String sealFlag;
	
	private Date fromMovementDate;
	
	private Date toMovementDate;

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public VOffExtMovements() {
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getArrestAgencyLocId() {
		return this.arrestAgencyLocId;
	}

	public void setArrestAgencyLocId(String arrestAgencyLocId) {
		this.arrestAgencyLocId = arrestAgencyLocId;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getDirectionCode() {
		return this.directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	public String getEscortCode() {
		return this.escortCode;
	}

	public void setEscortCode(String escortCode) {
		this.escortCode = escortCode;
	}

	public String getEscortText() {
		return this.escortText;
	}

	public void setEscortText(String escortText) {
		this.escortText = escortText;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFromAddressDesc() {
		return this.fromAddressDesc;
	}

	public void setFromAddressDesc(String fromAddressDesc) {
		this.fromAddressDesc = fromAddressDesc;
	}

	public BigDecimal getFromAddressId() {
		return this.fromAddressId;
	}

	public void setFromAddressId(BigDecimal fromAddressId) {
		this.fromAddressId = fromAddressId;
	}

	public String getFromAgyLocDesc() {
		return this.fromAgyLocDesc;
	}

	public void setFromAgyLocDesc(String fromAgyLocDesc) {
		this.fromAgyLocDesc = fromAgyLocDesc;
	}

	public String getFromAgyLocId() {
		return this.fromAgyLocId;
	}

	public void setFromAgyLocId(String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
	}

	public String getFromCity() {
		return this.fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getFromCityDesc() {
		return this.fromCityDesc;
	}

	public void setFromCityDesc(String fromCityDesc) {
		this.fromCityDesc = fromCityDesc;
	}

	public String getInternalScheduleReasonCode() {
		return this.internalScheduleReasonCode;
	}

	public void setInternalScheduleReasonCode(String internalScheduleReasonCode) {
		this.internalScheduleReasonCode = internalScheduleReasonCode;
	}

	public String getInternalScheduleType() {
		return this.internalScheduleType;
	}

	public void setInternalScheduleType(String internalScheduleType) {
		this.internalScheduleType = internalScheduleType;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getMovementDate() {
		return this.movementDate;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	public String getMovementReasonCode() {
		return this.movementReasonCode;
	}

	public void setMovementReasonCode(String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	public BigDecimal getMovementSeq() {
		return this.movementSeq;
	}

	public void setMovementSeq(BigDecimal movementSeq) {
		this.movementSeq = movementSeq;
	}

	public Date getMovementTime() {
		return this.movementTime;
	}

	public void setMovementTime(Date movementTime) {
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

	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public Date getReportingDate() {
		return this.reportingDate;
	}

	public void setReportingDate(Date reportingDate) {
		this.reportingDate = reportingDate;
	}

	public Date getReportingTime() {
		return this.reportingTime;
	}

	public void setReportingTime(Date reportingTime) {
		this.reportingTime = reportingTime;
	}

	public String getToAddressDesc() {
		return this.toAddressDesc;
	}

	public void setToAddressDesc(String toAddressDesc) {
		this.toAddressDesc = toAddressDesc;
	}

	public BigDecimal getToAddressId() {
		return this.toAddressId;
	}

	public void setToAddressId(BigDecimal toAddressId) {
		this.toAddressId = toAddressId;
	}

	public String getToAgyLocDesc() {
		return this.toAgyLocDesc;
	}

	public void setToAgyLocDesc(String toAgyLocDesc) {
		this.toAgyLocDesc = toAgyLocDesc;
	}

	public String getToAgyLocId() {
		return this.toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public String getToCity() {
		return this.toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getToCityDesc() {
		return this.toCityDesc;
	}

	public void setToCityDesc(String toCityDesc) {
		this.toCityDesc = toCityDesc;
	}

	public String getToProvStatCode() {
		return this.toProvStatCode;
	}

	public void setToProvStatCode(String toProvStatCode) {
		this.toProvStatCode = toProvStatCode;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getFromMovementDate() {
		return fromMovementDate;
	}

	public void setFromMovementDate(Date fromMovementDate) {
		this.fromMovementDate = fromMovementDate;
	}

	public Date getToMovementDate() {
		return toMovementDate;
	}

	public void setToMovementDate(Date toMovementDate) {
		this.toMovementDate = toMovementDate;
	}
}
