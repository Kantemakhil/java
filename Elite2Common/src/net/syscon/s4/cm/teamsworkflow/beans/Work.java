package net.syscon.s4.cm.teamsworkflow.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the WORKS database table.
 * 
 */
public class Work implements Serializable {
	private static final long serialVersionUID = 1L;

	private long workId;

	private String activeFlag;

	private String caseloadType;

	private Date createDatetime;

	private String createUserId;

	private String emailBody;

	private String emailSubject;

	private Date expiryDate;

	private String manualCloseFlag;

	private String manualSelectFlag;

	private Date modifyDatetime;

	private String modifyUserId;

	private String moduleName;

	private String sealFlag;

	private String workSubType;
	
	private String code;
	
	private String workType;

	private String workflowType;
	
	private String description;
	
	private Integer casenoteFlag;
	
	private String templateCount;
	
	private String triggerCount;
	
	private String functionCount;
	
	private String emailRecipientsCount;
	
	private String emailReturnCount;
	
	private String caseNoteText;
	
    private Integer roleId;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	public String getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}

	private String createFlag;
	
	private String viewFlag;

	public Work() {
		// Work
	}

	public long getWorkId() {
		return this.workId;
	}

	public void setWorkId(long workId) {
		this.workId = workId;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getEmailBody() {
		return this.emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getEmailSubject() {
		return this.emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getManualCloseFlag() {
		return this.manualCloseFlag;
	}

	public void setManualCloseFlag(String manualCloseFlag) {
		this.manualCloseFlag = manualCloseFlag;
	}

	public String getManualSelectFlag() {
		return this.manualSelectFlag;
	}

	public void setManualSelectFlag(String manualSelectFlag) {
		this.manualSelectFlag = manualSelectFlag;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getWorkSubType() {
		return this.workSubType;
	}

	public void setWorkSubType(String workSubType) {
		this.workSubType = workSubType;
	}

	public String getWorkType() {
		return this.workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getWorkflowType() {
		return this.workflowType;
	}

	public void setWorkflowType(String workflowType) {
		this.workflowType = workflowType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCasenoteFlag() {
		return casenoteFlag;
	}

	public void setCasenoteFlag(Integer casenoteFlag) {
		this.casenoteFlag = casenoteFlag;
	}

	public String getTemplateCount() {
		return templateCount;
	}

	public void setTemplateCount(String templateCount) {
		this.templateCount = templateCount;
	}

	public String getTriggerCount() {
		return triggerCount;
	}

	public void setTriggerCount(String triggerCount) {
		this.triggerCount = triggerCount;
	}

	public String getFunctionCount() {
		return functionCount;
	}

	public void setFunctionCount(String functionCount) {
		this.functionCount = functionCount;
	}

	public String getEmailRecipientsCount() {
		return emailRecipientsCount;
	}

	public void setEmailRecipientsCount(String emailRecipientsCount) {
		this.emailRecipientsCount = emailRecipientsCount;
	}

	public String getEmailReturnCount() {
		return emailReturnCount;
	}

	public void setEmailReturnCount(String emailReturnCount) {
		this.emailReturnCount = emailReturnCount;
	}

	public String getCaseNoteText() {
		return caseNoteText;
	}

	public void setCaseNoteText(String caseNoteText) {
		this.caseNoteText = caseNoteText;
	}

	

	
}
