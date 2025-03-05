package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CaseloadAdmAlertProfiles extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("alertType")
	private String alertType;
	@JsonProperty("alertCode")
	private String alertCode;
	@JsonProperty("messageNumber")
	private Integer messageNumber;
	@JsonProperty("applnCode")
	private String applnCode;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;
	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonProperty("messNum")
	private String messNum;
	@JsonProperty("rowId")
	private Integer rowId;	
	@JsonProperty("messageText")
	private String messageText;
	
	

	/**
	 * Creates new StaffMembers class Object
	 */
	public CaseloadAdmAlertProfiles() {
		// CaseloadAdmAlertProfiles
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId
	 *            the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the alertType
	 */
	public String getAlertType() {
		return alertType;
	}

	/**
	 * @param alertType
	 *            the alertType to set
	 */
	public void setAlertType(final String alertType) {
		this.alertType = alertType;
	}

	/**
	 * @return the alertCode
	 */
	public String getAlertCode() {
		return alertCode;
	}

	/**
	 * @param alertCode
	 *            the alertCode to set
	 */
	public void setAlertCode(final String alertCode) {
		this.alertCode = alertCode;
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
	 * @return the modifyDateTime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
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
	 * @return the rootOffenderId
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId
	 *            the rootOffenderId to set
	 */
	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(final Integer rowId) {
		this.rowId = rowId;
	}

	public String getMessNum() {
		return messNum;
	}

	public void setMessNum(final String messNum) {
		this.messNum = messNum;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

}