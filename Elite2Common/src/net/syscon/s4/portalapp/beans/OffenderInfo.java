package net.syscon.s4.portalapp.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8152378981577600604L;
	private String firstName;
	private String lastName;
	private String middleName;
	private String raceCode;
	private String sexCode;
	private String personId;
	private String offenderBookId;
	private String offenderId;
	private BigDecimal requestId;
	private String requestStatus;
	private Date dateOfBirth;
	private String bookingNumber;
	private Long numberOfExactMatches;
	private Long numberOfMaybeMatches;
	private List<String> numberOfExactMatcheOffenders;
	private List<String> numberOfMaybeMatchesOffenders;
	private Date createDate;
	private Date modifyDate;
	private String createUserId;
	private String inputPayload;
	
	
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
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getRaceCode() {
		return raceCode;
	}
	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}
	public String getSexCode() {
		return sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	public String getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(String offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public BigDecimal getRequestId() {
		return requestId;
	}
	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getInputPayload() {
		return inputPayload;
	}
	public void setInputPayload(String inputPayload) {
		this.inputPayload = inputPayload;
	}
	
	public Long getNumberOfExactMatches() {
		return numberOfExactMatches;
	}
	public void setNumberOfExactMatches(Long numberOfExactMatches) {
		this.numberOfExactMatches = numberOfExactMatches;
	}
	public Long getNumberOfMaybeMatches() {
		return numberOfMaybeMatches;
	}
	public void setNumberOfMaybeMatches(Long numberOfMaybeMatches) {
		this.numberOfMaybeMatches = numberOfMaybeMatches;
	}
	public List<String> getNumberOfExactMatcheOffenders() {
		return numberOfExactMatcheOffenders;
	}
	public void setNumberOfExactMatcheOffenders(List<String> numberOfExactMatcheOffenders) {
		this.numberOfExactMatcheOffenders = numberOfExactMatcheOffenders;
	}
	public List<String> getNumberOfMaybeMatchesOffenders() {
		return numberOfMaybeMatchesOffenders;
	}
	public void setNumberOfMaybeMatchesOffenders(List<String> numberOfMaybeMatchesOffenders) {
		this.numberOfMaybeMatchesOffenders = numberOfMaybeMatchesOffenders;
	}
	public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	public String getOffenderId() {
		return offenderId;
	}
	public void setOffenderId(String offenderId) {
		this.offenderId = offenderId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	

}
