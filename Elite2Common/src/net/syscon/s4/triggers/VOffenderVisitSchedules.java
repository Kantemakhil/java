package net.syscon.s4.triggers;

import java.io.Serializable;

public class VOffenderVisitSchedules implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String eventStatus;
	private String eventOutcome;
	private String visitorCommentText;
	private String outcomeReasonCode;
	private Long eventId;

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getEventOutcome() {
		return eventOutcome;
	}

	public void setEventOutcome(String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	public String getVisitorCommentText() {
		return visitorCommentText;
	}

	public void setVisitorCommentText(String visitorCommentText) {
		this.visitorCommentText = visitorCommentText;
	}

	public String getOutcomeReasonCode() {
		return outcomeReasonCode;
	}

	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

}
