package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class BpmnProcess extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("processId")
	private Long processId;
	
	@JsonProperty("processKey")
	private String processKey;
	
	@JsonProperty("processDesc")
	private String processDesc;
	
	@JsonProperty("bpmnFile")
	private byte[] bpmnFile;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("deployeId")
	private String deployeId;
	
	@JsonProperty("defVersion")
	private Long defVersion;
	
	@JsonProperty("bpmn")
	private String bpmn;
	
	@JsonProperty("bpmnByte")
	private byte[] bpmnByte;
	
	@JsonProperty("deployFlag")
	private String deployFlag;
	
	@JsonProperty("historyFlag")
	private String historyFlag;
	
	@JsonProperty("procDefId")
	private String procDefId;
	
	@JsonProperty("module")
	private String module;
	
	@JsonProperty("triggerId")
	private String triggerId;
	
	@JsonProperty("deployDatetime")
	private Date deployDatetime;
	
	@JsonProperty("deployUserId")
	private String deployUserId;

	@JsonProperty("xmlFileName")
	private String xmlFileName;
	
	@JsonProperty("commonProcess")
	private String commonProcess;
	
	@JsonProperty("category")
	private String category;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("timerProcess")
	private String timerProcess;
	
	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public String getProcessDesc() {
		return processDesc;
	}

	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}

	public byte[] getBpmnFile() {
		return bpmnFile;
	}

	public void setBpmnFile(byte[] bpmnFile) {
		this.bpmnFile = bpmnFile;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBpmn() {
		return bpmn;
	}

	public void setBpmn(String bpmn) {
		this.bpmn = bpmn;
	}

	public String getDeployeId() {
		return deployeId;
	}

	public void setDeployeId(String deployeId) {
		this.deployeId = deployeId;
	}

	public Long getDefVersion() {
		return defVersion;
	}

	public void setDefVersion(Long defVersion) {
		this.defVersion = defVersion;
	}

	public byte[] getBpmnByte() {
		return bpmnByte;
	}

	public void setBpmnByte(byte[] bpmnByte) {
		this.bpmnByte = bpmnByte;
	}

	public String getDeployFlag() {
		return deployFlag;
	}

	public void setDeployFlag(String deployFlag) {
		this.deployFlag = deployFlag;
	}

	public String getHistoryFlag() {
		return historyFlag;
	}

	public void setHistoryFlag(String historyFlag) {
		this.historyFlag = historyFlag;
	}

	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}

	public Date getDeployDatetime() {
		return deployDatetime;
	}

	public void setDeployDatetime(Date deployDatetime) {
		this.deployDatetime = deployDatetime;
	}

	public String getDeployUserId() {
		return deployUserId;
	}

	public void setDeployUserId(String deployUserId) {
		this.deployUserId = deployUserId;
	}
	
	public String getCommonProcess() {
		return commonProcess;
	}

	public void setCommonProcess(String commonProcess) {
		this.commonProcess = commonProcess;
	}

	@Override
	public String toString() {
		return "BpmnProcess [processId=" + processId + ", processKey=" + processKey + ", processDesc=" + processDesc
				+ ", bpmnFile=" + Arrays.toString(bpmnFile) + ", createDatetime=" + createDatetime + ", createUserId="
				+ createUserId + ", modifyDatetime=" + modifyDatetime + ", modifyUserId=" + modifyUserId
				+ ", description=" + description + ", code=" + code + ", deployeId=" + deployeId + ", defVersion="
				+ defVersion + ", bpmn=" + bpmn + ", bpmnByte=" + Arrays.toString(bpmnByte) + ", deployFlag="
				+ deployFlag + ", historyFlag=" + historyFlag + ", procDefId=" + procDefId + ", module=" + module
				+ ", triggerId=" + triggerId + ", deployDatetime=" + deployDatetime + ", deployUserId=" + deployUserId
				+ "]";
	}

	public String getXmlFileName() {
		return xmlFileName;
	}

	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTimerProcess() {
		return timerProcess;
	}

	public void setTimerProcess(String timerProcess) {
		this.timerProcess = timerProcess;
	}
}
