package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class CreateAdhocEmail extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pWorkflowType;
	private long pWorkId;
	private Integer pOffenderBookId;
	private String pEmailSubject;
	private String pEmailBody;
	private String pEmailSender;
	private String pEmailFrom;
	private List<EmailRecipients> emailRecipientsList;
	private List<EmailRecipients> pToRecipientsList;
	private List<EmailRecipients> pCcRecipientsList;
	private List<EmailRecipients> pBccRecipientsList;
	private String toList;
	private String ccList;
	private String bccList;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String sealFlag;
	private long emailId;
	
	
	public long getEmailId() {
		return emailId;
	}
	public void setEmailId(long emailId) {
		this.emailId = emailId;
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
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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
	public String getpWorkflowType() {
		return pWorkflowType;
	}
	public void setpWorkflowType(String pWorkflowType) {
		this.pWorkflowType = pWorkflowType;
	}
	public long getpWorkId() {
		return pWorkId;
	}
	public void setpWorkId(long pWorkId) {
		this.pWorkId = pWorkId;
	}
	public Integer getpOffenderBookId() {
		return pOffenderBookId;
	}
	public void setpOffenderBookId(Integer pOffenderBookId) {
		this.pOffenderBookId = pOffenderBookId;
	}
	public String getpEmailSubject() {
		return pEmailSubject;
	}
	public void setpEmailSubject(String pEmailSubject) {
		this.pEmailSubject = pEmailSubject;
	}
	public String getpEmailBody() {
		return pEmailBody;
	}
	public void setpEmailBody(String pEmailBody) {
		this.pEmailBody = pEmailBody;
	}
	public String getpEmailSender() {
		return pEmailSender;
	}
	public void setpEmailSender(String pEmailSender) {
		this.pEmailSender = pEmailSender;
	}
	public String getpEmailFrom() {
		return pEmailFrom;
	}
	public void setpEmailFrom(String pEmailFrom) {
		this.pEmailFrom = pEmailFrom;
	}
	
	
	public List<EmailRecipients> getpToRecipientsList() {
		return pToRecipientsList;
	}
	public void setpToRecipientsList(List<EmailRecipients> pToRecipientsList) {
		this.pToRecipientsList = pToRecipientsList;
	}
	public List<EmailRecipients> getpCcRecipientsList() {
		return pCcRecipientsList;
	}
	public void setpCcRecipientsList(List<EmailRecipients> pCcRecipientsList) {
		this.pCcRecipientsList = pCcRecipientsList;
	}
	public List<EmailRecipients> getpBccRecipientsList() {
		return pBccRecipientsList;
	}
	public void setpBccRecipientsList(List<EmailRecipients> pBccRecipientsList) {
		this.pBccRecipientsList = pBccRecipientsList;
	}
	
	public List<EmailRecipients> getEmailRecipientsList() {
		return emailRecipientsList;
	}
	public void setEmailRecipientsList(List<EmailRecipients> emailRecipientsList) {
		this.emailRecipientsList = emailRecipientsList;
	}
	@Override
	public String toString() {
		return "CreateAdhocEmail [pWorkflowType=" + pWorkflowType + ", pWorkId=" + pWorkId + ", pOffenderBookId="
				+ pOffenderBookId + ", pEmailSubject=" + pEmailSubject + ", pEmailBody=" + pEmailBody
				+ ", pEmailSender=" + pEmailSender + ", pEmailFrom=" + pEmailFrom + ", emailRecipientsList="
				+ emailRecipientsList + ", toList=" + toList + ", ccList=" + ccList + ", bccList=" + bccList
				+ ", createDatetime=" + createDatetime + ", createUserId=" + createUserId + ", modifyDatetime="
				+ modifyDatetime + ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag + ", emailId=" + emailId
				+ "]";
	}
	
	
	

}
