/**
 * 
 */
package net.syscon.s4.inst.transportation.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Gettha T
 *
 */
public class VAssignOffenderTrips  implements Serializable {

	@JsonProperty("offenderLastName")
	private String offenderLastName;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("sexCode")
	private String sexCode;
	
	@JsonProperty("offenderId")
	private Integer offenderId;
	
	@JsonProperty("rootOffenderId")
	private Integer rootOffenderId;
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	
	@JsonProperty("eventStatus")
	private String eventStatus;
	
	@JsonProperty("eventId")
	private Integer eventId;
	
	@JsonProperty("scheduledTripId")
	private Integer scheduledTripId;
	
	@JsonProperty("fromSeq")
	private String fromSeq;
	
	@JsonProperty("recordSource")
	private String recordSource;
	
	@JsonProperty("eventClass")
	private String eventClass;
	
	@JsonProperty("startTime")
	private Date startTime;
	
	@JsonProperty("endTime")
	private Date endTime;

	@JsonProperty("eventDate")
	private Date eventDate;
	
	@JsonProperty("toAgyLocId")
	private String toAgyLocId;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("fromLocation")
	private String fromLocation;
	
	@JsonProperty("luLevel1Code")
	private String luLevel1Code;
	
	@JsonProperty("eventSubType")
	private String eventSubType;
	
	@JsonProperty("eventType")
	private String	eventType;
	
	@JsonProperty("offenderFirstName")
	private String offenderFirstName;
	
	@JsonProperty("selected")
	private String selected;
	
	@JsonProperty("offenderFullName")
	private String offenderFullName;
	
	public String getOffenderFullName() {
		return offenderFullName;
	}
	public void setOffenderFullName(String offenderFullName) {
		this.offenderFullName = offenderFullName;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public String getOffenderLastName() {
		return offenderLastName;
	}
	public void setOffenderLastName(String offenderLastName) {
		this.offenderLastName = offenderLastName;
	}
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}
	public String getSexCode() {
		return sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	public Integer getOffenderId() {
		return offenderId;
	}
	public void setOffenderId(Integer offenderId) {
		this.offenderId = offenderId;
	}
	public Integer getRootOffenderId() {
		return rootOffenderId;
	}
	public void setRootOffenderId(Integer rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}
	public Integer getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Integer getScheduledTripId() {
		return scheduledTripId;
	}
	public void setScheduledTripId(Integer scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}
	public String getFromSeq() {
		return fromSeq;
	}
	public void setFromSeq(String fromSeq) {
		this.fromSeq = fromSeq;
	}
	public String getRecordSource() {
		return recordSource;
	}
	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
	}
	public String getEventClass() {
		return eventClass;
	}
	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getToAgyLocId() {
		return toAgyLocId;
	}
	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}
	public String getAgyLocId() {
		return agyLocId;
	}
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getLuLevel1Code() {
		return luLevel1Code;
	}
	public void setLuLevel1Code(String luLevel1Code) {
		this.luLevel1Code = luLevel1Code;
	}
	public String getEventSubType() {
		return eventSubType;
	}
	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getOffenderFirstName() {
		return offenderFirstName;
	}
	public void setOffenderFirstName(String offenderFirstName) {
		this.offenderFirstName = offenderFirstName;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
}
