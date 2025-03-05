package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class AdhocEmail extends BaseModel implements Serializable {

	private Long emailId;

	private Long offenderBookId;

	private Long workId;

	private String emailSubject;

	private Clob emailBody;// clob

	private String emailSender;

	private String emailForm;

	private String toList;// NT_EMAIL_ADDRESS

	private String ccList;// NT_EMAIL_ADDRESS

	private String bccList;// NT_EMAIL_ADDRESS

	private Date createDateTime;

	private String createUserId;

	private Date modifieddateTime;

	private String modifieduserId;

	private String sealFlag;

	public Long getEmailId() {
		return emailId;
	}

	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public Clob getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(Clob emailBody) {
		this.emailBody = emailBody;
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public String getEmailForm() {
		return emailForm;
	}

	public void setEmailForm(String emailForm) {
		this.emailForm = emailForm;
	}

	public String getToList() {
		return toList;
	}

	public void setToList(String toList) {
		this.toList = toList;
	}

	public String getCcList() {
		return ccList;
	}

	public void setCcList(String ccList) {
		this.ccList = ccList;
	}

	public String getBccList() {
		return bccList;
	}

	public void setBccList(String bccList) {
		this.bccList = bccList;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifieddateTime() {
		return modifieddateTime;
	}

	public void setModifieddateTime(Date modifieddateTime) {
		this.modifieddateTime = modifieddateTime;
	}

	public String getModifieduserId() {
		return modifieduserId;
	}

	public void setModifieduserId(String modifieduserId) {
		this.modifieduserId = modifieduserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
