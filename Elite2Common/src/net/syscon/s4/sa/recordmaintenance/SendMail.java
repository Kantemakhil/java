package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class SendMail extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("fromId")
	private String fromId;
	
	@JsonProperty("toId")
	private String toId;
	
	@JsonProperty("subject")
	private String subject;
	
	@JsonProperty("body")
	private String body;

	@JsonProperty("emailType")
	private String emailType;

	@JsonProperty("emailTemplate")
	private String emailTemplate;
	
	@JsonProperty("offenderBookId")
	private String offenderBookId;
	
	@JsonProperty("eventId")
	private String eventId;
	
	
	@JsonProperty("templateInput")
	private String templateInput;
	
	
	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public String getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(String offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getTemplateInput() {
		return templateInput;
	}

	public void setTemplateInput(String templateInput) {
		this.templateInput = templateInput;
	}
	
	
}
