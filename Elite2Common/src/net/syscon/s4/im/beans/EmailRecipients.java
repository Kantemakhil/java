package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class EmailRecipients extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String internetAddress;
	private String internetAddressClass;
	private String toAddress;
	private String ccAddress;
	private String bccAddress;
	private Boolean nbtEmailTo;
	private Boolean nbtEmailCc;
	private Boolean nbtEmailBcc;
	private String emailBody;
	private String emailSubject;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
    private long emailId;
    private long ownerId;
	
	
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public long getEmailId() {
		return emailId;
	}
	public void setEmailId(long emailId) {
		this.emailId = emailId;
	}
	public String getInternetAddress() {
		return internetAddress;
	}
	
	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}
	public String getInternetAddressClass() {
		return internetAddressClass;
	}
	public void setInternetAddressClass(String internetAddressClass) {
		this.internetAddressClass = internetAddressClass;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getCcAddress() {
		return ccAddress;
	}
	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}
	public String getBccAddress() {
		return bccAddress;
	}
	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}
	public Boolean getNbtEmailTo() {
		return nbtEmailTo;
	}
	public void setNbtEmailTo(Boolean nbtEmailTo) {
		this.nbtEmailTo = nbtEmailTo;
	}
	public Boolean getNbtEmailCc() {
		return nbtEmailCc;
	}
	public void setNbtEmailCc(Boolean nbtEmailCc) {
		this.nbtEmailCc = nbtEmailCc;
	}
	public Boolean getNbtEmailBcc() {
		return nbtEmailBcc;
	}
	public void setNbtEmailBcc(Boolean nbtEmailBcc) {
		this.nbtEmailBcc = nbtEmailBcc;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	@Override
	public String toString() {
		return "EmailRecipients [internetAddress=" + internetAddress + ", internetAddressClass=" + internetAddressClass
				+ ", toAddress=" + toAddress + ", ccAddress=" + ccAddress + ", bccAddress=" + bccAddress
				+ ", nbtEmailTo=" + nbtEmailTo + ", nbtEmailCc=" + nbtEmailCc + ", nbtEmailBcc=" + nbtEmailBcc + "]";
	}

	
}
