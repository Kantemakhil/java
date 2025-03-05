package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class SystemMessages extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("applnCode")
	private String applnCode;
	@JsonProperty("messageNumber")
	private Integer messageNumber;
	@JsonProperty("messageType")
	private String messageType;
	@JsonProperty("messageText")
	private String messageText;
	@JsonProperty("actionText")
	private String actionText;
	@JsonProperty("responseList")
	private String responseList;
	@JsonProperty("defaultResponse")
	private Integer defaultResponse;
	@JsonProperty("systemRemarksText")
	private String systemRemarksText;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("createDateTime")
	private Timestamp createDateTime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDateTime")
	private Timestamp modifyDateTime;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonProperty("code")
	private String code;
	@JsonProperty("description")
	private String description;

	/**
	 * Creates new SystemMessages class Object
	 */
	public SystemMessages() {
		// SystemMessages;
	}

	/**
	 * @return the applnCode
	 */
	public String getApplnCode() {
		return applnCode;
	}

	/**
	 * @param applnCode
	 *            the applnCode to set
	 */
	public void setApplnCode(final String applnCode) {
		this.applnCode = applnCode;
	}

	/**
	 * @return the messageNumber
	 */
	public Integer getMessageNumber() {
		return messageNumber;
	}

	/**
	 * @param messageNumber
	 *            the messageNumber to set
	 */
	public void setMessageNumber(final Integer messageNumber) {
		this.messageNumber = messageNumber;
	}

	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType
	 *            the messageType to set
	 */
	public void setMessageType(final String messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * @param messageText
	 *            the messageText to set
	 */
	public void setMessageText(final String messageText) {
		this.messageText = messageText;
	}

	/**
	 * @return the actionText
	 */
	public String getActionText() {
		return actionText;
	}

	/**
	 * @param actionText
	 *            the actionText to set
	 */
	public void setActionText(final String actionText) {
		this.actionText = actionText;
	}

	/**
	 * @return the responseList
	 */
	public String getResponseList() {
		return responseList;
	}

	/**
	 * @param responseList
	 *            the responseList to set
	 */
	public void setResponseList(final String responseList) {
		this.responseList = responseList;
	}

	/**
	 * @return the defaultResponse
	 */
	public Integer getDefaultResponse() {
		return defaultResponse;
	}

	/**
	 * @param defaultResponse
	 *            the defaultResponse to set
	 */
	public void setDefaultResponse(final Integer defaultResponse) {
		this.defaultResponse = defaultResponse;
	}

	/**
	 * @return the systemRemarksText
	 */
	public String getSystemRemarksText() {
		return systemRemarksText;
	}

	/**
	 * @param systemRemarksText
	 *            the systemRemarksText to set
	 */
	public void setSystemRemarksText(final String systemRemarksText) {
		this.systemRemarksText = systemRemarksText;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the createDateTime
	 */
	public Timestamp getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(final Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDateTime
	 */
	public Timestamp getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(final Timestamp modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}