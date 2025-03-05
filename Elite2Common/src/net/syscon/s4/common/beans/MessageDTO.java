package net.syscon.s4.common.beans;

import java.io.Serializable;

public class MessageDTO extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String message;
	private MessageType type;

	public MessageDTO() {
		super();
	}

	public MessageDTO(MessageType type, String message) {
		super();
		this.message = message;
		this.type = type;
	}
	

	public MessageDTO(String errorCode, String message, MessageType type) {
        super();
        this.errorCode = errorCode;
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
	
}
