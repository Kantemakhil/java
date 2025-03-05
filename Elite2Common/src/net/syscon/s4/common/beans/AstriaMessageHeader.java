package net.syscon.s4.common.beans;

public class AstriaMessageHeader {
	public String messageId;
    public String correlationId;
    public String sourceSystem;
    public String messageType;
    public String messageTypeCode;
    public String messageTypeVersion;
    public String messageCreationTimestamp;
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getSourceSystem() {
		return sourceSystem;
	}
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageTypeCode() {
		return messageTypeCode;
	}
	public void setMessageTypeCode(String messageTypeCode) {
		this.messageTypeCode = messageTypeCode;
	}
	public String getMessageTypeVersion() {
		return messageTypeVersion;
	}
	public void setMessageTypeVersion(String messageTypeVersion) {
		this.messageTypeVersion = messageTypeVersion;
	}
	public String getMessageCreationTimestamp() {
		return messageCreationTimestamp;
	}
	public void setMessageCreationTimestamp(String messageCreationTimestamp) {
		this.messageCreationTimestamp = messageCreationTimestamp;
	}
}
