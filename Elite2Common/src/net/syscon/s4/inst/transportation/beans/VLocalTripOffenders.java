/**
 * 
 */
package net.syscon.s4.inst.transportation.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * @author Gettha T
 *
 */
public class VLocalTripOffenders extends BaseModel implements Serializable {

	@JsonProperty("fromSeq")
	private String fromSeq;
	
	@JsonProperty("ifExistMov")
	private Integer ifExistMov;
	
	@JsonProperty("selected")
	private String selected;
	
	@JsonProperty("toSeq")
	private String toSeq;
	
	@JsonProperty("offenderToLoc")
	private String offenderToLoc;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("recordSource")
	private String recordSource;
	
	@JsonProperty("scheduledTripId")
	private Integer scheduledTripId;
	
	@JsonProperty("routeName")
	private String routeName;
	
	@JsonProperty("inmateId")
	private Integer inmateId;
	
	@JsonProperty("eventId")
	private Integer eventId;
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("offenderLastName")
	private String offenderLastName;
	
	@JsonProperty("offenderFirstName")
	private String offenderFirstName;
	
	@JsonProperty("sexCode")
	private String sexCode;
	
	@JsonProperty("raceCode")
	private String raceCode;
	
	@JsonProperty("eventType")
	private String eventType;
	
	@JsonProperty("eventSubType")
	private String eventSubType;
	
	@JsonProperty("offenderFromLoc")
	private String offenderFromLoc;
	
	@JsonProperty("housingLocation")
	private String housingLocation;
	
	public Integer getIfExistMov() {
		return ifExistMov;
	}
	public void setIfExistMov(Integer ifExistMov) {
		this.ifExistMov = ifExistMov;
	}
	
	public String getFromSeq() {
		return fromSeq;
	}
	public void setFromSeq(String fromSeq) {
		this.fromSeq = fromSeq;
	}
	
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public String getToSeq() {
		return toSeq;
	}
	public void setToSeq(String toSeq) {
		this.toSeq = toSeq;
	}
	public String getOffenderToLoc() {
		return offenderToLoc;
	}
	public void setOffenderToLoc(String offenderToLoc) {
		this.offenderToLoc = offenderToLoc;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getRecordSource() {
		return recordSource;
	}
	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
	}
	public Integer getScheduledTripId() {
		return scheduledTripId;
	}
	public void setScheduledTripId(Integer scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public Integer getInmateId() {
		return inmateId;
	}
	public void setInmateId(Integer inmateId) {
		this.inmateId = inmateId;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Integer getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
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
	public String getSexCode() {
		return sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	public String getRaceCode() {
		return raceCode;
	}
	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
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
	public String getOffenderFromLoc() {
		return offenderFromLoc;
	}
	public void setOffenderFromLoc(String offenderFromLoc) {
		this.offenderFromLoc = offenderFromLoc;
	}
	public String getHousingLocation() {
		return housingLocation;
	}
	public void setHousingLocation(String housingLocation) {
		this.housingLocation = housingLocation;
	}
	
	
}
