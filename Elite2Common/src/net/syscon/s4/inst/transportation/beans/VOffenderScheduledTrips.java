package net.syscon.s4.inst.transportation.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VOffenderScheduledTrips extends BaseModel implements Serializable {

	@JsonProperty("offenderLastName")
	private String offenderLastName;
	
	@JsonProperty("offenderFirstName")
	private String offenderFirstName;
	
	@JsonProperty("eventType")
	private String eventType;
	
	@JsonProperty("eventSubType")
	private String eventSubType;
	
	@JsonProperty("fromLocationDesc")
	private String fromLocationDesc;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("toAgyLocId")
	private String toAgyLocId;
	
	@JsonProperty("eventDate")
	private Timestamp eventDate;
	
	@JsonProperty("startTime")
	private Timestamp startTime;
	
	@JsonProperty("nonAsso")
	private String nonAsso;
	
	@JsonProperty("scheduledTripId")
	private Long scheduledTripId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("butAddOff")
	private String butAddOff;
	
	@JsonProperty("butRemOff")
	private String butRemOff;
	
	@JsonProperty("butNonAss")
	private String butNonAss;
	
	@JsonProperty("eventStatus")
	private String eventStatus;
	
	@JsonProperty("eventId")
	private String eventId;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getOffenderLastName() {
		return offenderLastName;
	}

	public void setOffenderLastName(String offenderLastName) {
		this.offenderLastName = offenderLastName;
	}

	public String getOffenderFirstName() {
		return offenderFirstName;
	}

	public void setOffenderFirstName(String offenderFirstName) {
		this.offenderFirstName = offenderFirstName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventSubType() {
		return eventSubType;
	}

	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public String getFromLocationDesc() {
		return fromLocationDesc;
	}

	public void setFromLocationDesc(String fromLocationDesc) {
		this.fromLocationDesc = fromLocationDesc;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getToAgyLocId() {
		return toAgyLocId;
	}

	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}

	public Timestamp getEventDate() {
		return eventDate;
	}

	public void setEventDate(Timestamp eventDate) {
		this.eventDate = eventDate;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public String getNonAsso() {
		return nonAsso;
	}

	public void setNonAsso(String nonAsso) {
		this.nonAsso = nonAsso;
	}

	public Long getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Long scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getButAddOff() {
		return butAddOff;
	}

	public void setButAddOff(String butAddOff) {
		this.butAddOff = butAddOff;
	}

	public String getButRemOff() {
		return butRemOff;
	}

	public void setButRemOff(String butRemOff) {
		this.butRemOff = butRemOff;
	}

	public String getButNonAss() {
		return butNonAss;
	}

	public void setButNonAss(String butNonAss) {
		this.butNonAss = butNonAss;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
}
