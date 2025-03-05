package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MergeProcessRules implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("ruleId")
	private Long ruleId;
	@JsonProperty("ruleProc")
	private String ruleProc;
	@JsonProperty("ruleProcType")
	private String ruleProcType;
	@JsonProperty("resultTrueNextId")
	private Long resultTrueNextId;
	@JsonProperty("resultFalseNextId")
	private Long resultFalseNextId;
	@JsonProperty("description")
	private String description;
	@JsonProperty("initProc")
	private String initProc;
	@JsonProperty("termProc")
	private String termProc;
	@JsonProperty("processName")
	private String processName;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("endingRuleFlag")
	private String endingRuleFlag;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("createDateTime")
	private Date createDateTime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("processId")
	private Long processId;
	@JsonProperty("mandatoryOnFlag")
	private String mandatoryOnFlag;
	@JsonProperty("defaultOnFlag")
	private String defaultOnFlag;
	@JsonProperty("ruleId")
	private String timeframeRequiredFlag;
	@JsonProperty("ruleId")
	private String applicationCode;
	
	
	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleProc() {
		return ruleProc;
	}

	public void setRuleProc(String ruleProc) {
		this.ruleProc = ruleProc;
	}

	public String getRuleProcType() {
		return ruleProcType;
	}

	public void setRuleProcType(String ruleProcType) {
		this.ruleProcType = ruleProcType;
	}

	public Long getResultTrueNextId() {
		return resultTrueNextId;
	}

	public void setResultTrueNextId(Long resultTrueNextId) {
		this.resultTrueNextId = resultTrueNextId;
	}

	public Long getResultFalseNextId() {
		return resultFalseNextId;
	}

	public void setResultFalseNextId(Long resultFalseNextId) {
		this.resultFalseNextId = resultFalseNextId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInitProc() {
		return initProc;
	}

	public void setInitProc(String initProc) {
		this.initProc = initProc;
	}

	public String getTermProc() {
		return termProc;
	}

	public void setTermProc(String termProc) {
		this.termProc = termProc;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getEndingRuleFlag() {
		return endingRuleFlag;
	}

	public void setEndingRuleFlag(String endingRuleFlag) {
		this.endingRuleFlag = endingRuleFlag;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
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

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getMandatoryOnFlag() {
		return mandatoryOnFlag;
	}

	public void setMandatoryOnFlag(String mandatoryOnFlag) {
		this.mandatoryOnFlag = mandatoryOnFlag;
	}

	public String getDefaultOnFlag() {
		return defaultOnFlag;
	}

	public void setDefaultOnFlag(String defaultOnFlag) {
		this.defaultOnFlag = defaultOnFlag;
	}

	public String getTimeframeRequiredFlag() {
		return timeframeRequiredFlag;
	}

	public void setTimeframeRequiredFlag(String timeframeRequiredFlag) {
		this.timeframeRequiredFlag = timeframeRequiredFlag;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

}

