package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class VOffExm
 * 
 */
@XmlRootElement
public class VOffExm extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	@JsonProperty("movementSeq")
	private Integer movementSeq;

	@JsonProperty("movementDate")
	private Date movementDate;

	@JsonProperty("movementTime")
	private Date movementTime;

	@JsonProperty("internalScheduleType")
	private String internalScheduleType;

	@JsonProperty("internalScheduleReasonCode")
	private String internalScheduleReasonCode;

	@JsonProperty("movementType")
	private String movementType;

	@JsonProperty("movementReasonCode")
	private String movementReasonCode;

	@JsonProperty("directionCode")
	private String directionCode;

	@JsonProperty("arrestAgencyLocId")
	private String arrestAgencyLocId;

	@JsonProperty("toProvStatCode")
	private String toProvStatCode;

	@JsonProperty("escortCode")
	private String escortCode;

	@JsonProperty("fromAgyLocId")
	private String fromAgyLocId;

	@JsonProperty("toAgyLocId")
	private String toAgyLocId;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("escortText")
	private String escortText;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("reportingDate")
	private Date reportingDate;

	@JsonProperty("toCity")
	private String toCity;

	@JsonProperty("fromCity")
	private String fromCity;

	@JsonProperty("reportingTime")
	private Date reportingTime;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("dspDescription")
	private String dspDescription;

	@JsonProperty("offenderId")
	private Integer offenderId;
	/**
	 * @return the offenderBookId
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the movementSeq
	 */
	public Integer getMovementSeq() {
		return movementSeq;
	}

	/**
	 * @param movementSeq
	 *            the movementSeq to set
	 */
	public void setMovementSeq(Integer movementSeq) {
		this.movementSeq = movementSeq;
	}

	/**
	 * @return the movementDate
	 */
	public Date getMovementDate() {
		return movementDate;
	}

	/**
	 * @param movementDate
	 *            the movementDate to set
	 */
	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	/**
	 * @return the movementTime
	 */
	public Date getMovementTime() {
		return movementTime;
	}

	/**
	 * @param movementTime
	 *            the movementTime to set
	 */
	public void setMovementTime(Date movementTime) {
		this.movementTime = movementTime;
	}

	/**
	 * @return the internalScheduleType
	 */
	public String getInternalScheduleType() {
		return internalScheduleType;
	}

	/**
	 * @param internalScheduleType
	 *            the internalScheduleType to set
	 */
	public void setInternalScheduleType(String internalScheduleType) {
		this.internalScheduleType = internalScheduleType;
	}

	/**
	 * @return the internalScheduleReasonCode
	 */
	public String getInternalScheduleReasonCode() {
		return internalScheduleReasonCode;
	}

	/**
	 * @param internalScheduleReasonCode
	 *            the internalScheduleReasonCode to set
	 */
	public void setInternalScheduleReasonCode(String internalScheduleReasonCode) {
		this.internalScheduleReasonCode = internalScheduleReasonCode;
	}

	/**
	 * @return the movementType
	 */
	public String getMovementType() {
		return movementType;
	}

	/**
	 * @param movementType
	 *            the movementType to set
	 */
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	/**
	 * @return the movementReasonCode
	 */
	public String getMovementReasonCode() {
		return movementReasonCode;
	}

	/**
	 * @param movementReasonCode
	 *            the movementReasonCode to set
	 */
	public void setMovementReasonCode(String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	/**
	 * @return the directionCode
	 */
	public String getDirectionCode() {
		return directionCode;
	}

	/**
	 * @param directionCode
	 *            the directionCode to set
	 */
	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	/**
	 * @return the arrestAgencyLocId
	 */
	public String getArrestAgencyLocId() {
		return arrestAgencyLocId;
	}

	/**
	 * @param arrestAgencyLocId
	 *            the arrestAgencyLocId to set
	 */
	public void setArrestAgencyLocId(String arrestAgencyLocId) {
		this.arrestAgencyLocId = arrestAgencyLocId;
	}

	/**
	 * @return the toProvStatCode
	 */
	public String getToProvStatCode() {
		return toProvStatCode;
	}

	/**
	 * @param toProvStatCode
	 *            the toProvStatCode to set
	 */
	public void setToProvStatCode(String toProvStatCode) {
		this.toProvStatCode = toProvStatCode;
	}

	/**
	 * @return the escortCode
	 */
	public String getEscortCode() {
		return escortCode;
	}

	/**
	 * @param escortCode
	 *            the escortCode to set
	 */
	public void setEscortCode(String escortCode) {
		this.escortCode = escortCode;
	}

	/**
	 * @return the fromAgyLocId
	 */
	public String getFromAgyLocId() {
		return fromAgyLocId;
	}

	/**
	 * @param fromAgyLocId
	 *            the fromAgyLocId to set
	 */
	public void setFromAgyLocId(String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
	}

	/**
	 * @return the toAgyLocId
	 */
	public String getToAgyLocId() {
		return toAgyLocId;
	}

	/**
	 * @param toAgyLocId
	 *            the toAgyLocId to set
	 */
	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
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

	/**
	 * @return the escortText
	 */
	public String getEscortText() {
		return escortText;
	}

	/**
	 * @param escortText
	 *            the escortText to set
	 */
	public void setEscortText(String escortText) {
		this.escortText = escortText;
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
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the reportingDate
	 */
	public Date getReportingDate() {
		return reportingDate;
	}

	/**
	 * @param reportingDate
	 *            the reportingDate to set
	 */
	public void setReportingDate(Date reportingDate) {
		this.reportingDate = reportingDate;
	}

	/**
	 * @return the toCity
	 */
	public String getToCity() {
		return toCity;
	}

	/**
	 * @param toCity
	 *            the toCity to set
	 */
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	/**
	 * @return the fromCity
	 */
	public String getFromCity() {
		return fromCity;
	}

	/**
	 * @param fromCity
	 *            the fromCity to set
	 */
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	/**
	 * @return the reportingTime
	 */
	public Date getReportingTime() {
		return reportingTime;
	}

	/**
	 * @param reportingTime
	 *            the reportingTime to set
	 */
	public void setReportingTime(Date reportingTime) {
		this.reportingTime = reportingTime;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getDspDescription() {
		return dspDescription;
	}

	public void setDspDescription(String dspDescription) {
		this.dspDescription = dspDescription;
	}

	public Integer getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Integer offenderId) {
		this.offenderId = offenderId;
	}

}