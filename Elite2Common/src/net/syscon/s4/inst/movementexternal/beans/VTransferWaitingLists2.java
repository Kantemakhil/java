package net.syscon.s4.inst.movementexternal.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;


/**
 * The persistent class for the V_TRANSFER_WAITING_LISTS_2 database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VTransferWaitingLists2 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("agencyLocationTo")
	private String agencyLocationTo;
	
	@JsonProperty("approvedFlag")
	private String approvedFlag;
	
	@JsonProperty("checkSum")
	private BigDecimal checkSum;

	@JsonProperty("eventId")
	private BigDecimal eventId;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("outcomeReasonCode")
	private String outcomeReasonCode;
	
	@JsonProperty("requestedDate")
	private Object requestedDate;
	
	@JsonProperty("transferPriority")
	private String transferPriority;
	
	@JsonProperty("waitListStatus")
	private String waitListStatus;
	
	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	
	public VTransferWaitingLists2() {
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAgencyLocationTo() {
		return this.agencyLocationTo;
	}

	public void setAgencyLocationTo(String agencyLocationTo) {
		this.agencyLocationTo = agencyLocationTo;
	}

	public String getApprovedFlag() {
		return this.approvedFlag;
	}

	public void setApprovedFlag(String approvedFlag) {
		this.approvedFlag = approvedFlag;
	}

	public BigDecimal getCheckSum() {
		return this.checkSum;
	}

	public void setCheckSum(BigDecimal checkSum) {
		this.checkSum = checkSum;
	}

	public BigDecimal getEventId() {
		return this.eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getOutcomeReasonCode() {
		return this.outcomeReasonCode;
	}

	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public Object getRequestedDate() {
		return this.requestedDate;
	}

	public void setRequestedDate(Object requestedDate) {
		this.requestedDate = requestedDate;
	}

	public String getTransferPriority() {
		return this.transferPriority;
	}

	public void setTransferPriority(String transferPriority) {
		this.transferPriority = transferPriority;
	}

	public String getWaitListStatus() {
		return this.waitListStatus;
	}

	public void setWaitListStatus(String waitListStatus) {
		this.waitListStatus = waitListStatus;
	}

	/**
	 * @return the rootOffenderId
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId the rootOffenderId to set
	 */
	public void setRootOffenderId(BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
