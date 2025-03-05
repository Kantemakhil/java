package net.syscon.s4.portalapp.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputBookingPayloadInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4013033491786070523L;
	private String errorMessage;
	private String bookingNumber;
	private String offenderBookId;
	private String offenderId;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	public String getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(String offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getOffenderId() {
		return offenderId;
	}
	public void setOffenderId(String offenderId) {
		this.offenderId = offenderId;
	}
	
	
}
