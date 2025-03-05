package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the MERGE_PROCESSES database table.
 * 
 */
public class MergeProcesses implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("processId")
	private long processId;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("defaultOnFlag")
	private String defaultOnFlag;
	@JsonProperty("description")
	private String description;
	@JsonProperty("endingProcessFlag")
	private String endingProcessFlag;
	@JsonProperty("firstRuleId")
	private BigDecimal firstRuleId;
	@JsonProperty("firstRuleIdOff")
	private BigDecimal firstRuleIdOff;
	@JsonProperty("initProc")
	private String initProc;
	@JsonProperty("mandatoryOnFlag")
	private String mandatoryOnFlag;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("nextCheckProc")
	private String nextCheckProc;
	@JsonProperty("nextCheckProcType")
	private String nextCheckProcType;
	@JsonProperty("processName")
	private String processName;
	@JsonProperty("resultFalseNextId")
	private BigDecimal resultFalseNextId;
	@JsonProperty("resultTrueNextId")
	private BigDecimal resultTrueNextId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("startingProcessFlag")
	private String startingProcessFlag;
	@JsonProperty("termProc")
	private String termProc;
	@JsonProperty("timeframeRequiredFlag")
	private String timeframeRequiredFlag;
	@JsonProperty("transactionType")
	private String transactionType;

	public MergeProcesses() {
		// MergeProcesses
	}

	public long getProcessId() {
		return this.processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
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

	public String getDefaultOnFlag() {
		return this.defaultOnFlag;
	}

	public void setDefaultOnFlag(String defaultOnFlag) {
		this.defaultOnFlag = defaultOnFlag;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndingProcessFlag() {
		return this.endingProcessFlag;
	}

	public void setEndingProcessFlag(String endingProcessFlag) {
		this.endingProcessFlag = endingProcessFlag;
	}

	public BigDecimal getFirstRuleId() {
		return this.firstRuleId;
	}

	public void setFirstRuleId(BigDecimal firstRuleId) {
		this.firstRuleId = firstRuleId;
	}

	public BigDecimal getFirstRuleIdOff() {
		return this.firstRuleIdOff;
	}

	public void setFirstRuleIdOff(BigDecimal firstRuleIdOff) {
		this.firstRuleIdOff = firstRuleIdOff;
	}

	public String getInitProc() {
		return this.initProc;
	}

	public void setInitProc(String initProc) {
		this.initProc = initProc;
	}

	public String getMandatoryOnFlag() {
		return this.mandatoryOnFlag;
	}

	public void setMandatoryOnFlag(String mandatoryOnFlag) {
		this.mandatoryOnFlag = mandatoryOnFlag;
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

	public String getNextCheckProc() {
		return this.nextCheckProc;
	}

	public void setNextCheckProc(String nextCheckProc) {
		this.nextCheckProc = nextCheckProc;
	}

	public String getNextCheckProcType() {
		return this.nextCheckProcType;
	}

	public void setNextCheckProcType(String nextCheckProcType) {
		this.nextCheckProcType = nextCheckProcType;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public BigDecimal getResultFalseNextId() {
		return this.resultFalseNextId;
	}

	public void setResultFalseNextId(BigDecimal resultFalseNextId) {
		this.resultFalseNextId = resultFalseNextId;
	}

	public BigDecimal getResultTrueNextId() {
		return this.resultTrueNextId;
	}

	public void setResultTrueNextId(BigDecimal resultTrueNextId) {
		this.resultTrueNextId = resultTrueNextId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getStartingProcessFlag() {
		return this.startingProcessFlag;
	}

	public void setStartingProcessFlag(String startingProcessFlag) {
		this.startingProcessFlag = startingProcessFlag;
	}

	public String getTermProc() {
		return this.termProc;
	}

	public void setTermProc(String termProc) {
		this.termProc = termProc;
	}

	public String getTimeframeRequiredFlag() {
		return this.timeframeRequiredFlag;
	}

	public void setTimeframeRequiredFlag(String timeframeRequiredFlag) {
		this.timeframeRequiredFlag = timeframeRequiredFlag;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}
