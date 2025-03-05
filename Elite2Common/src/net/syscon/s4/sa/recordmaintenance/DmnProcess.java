package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class DmnProcess extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("decisionId")
	private Long decisionId;
	
	@JsonProperty("definitionKey")
	private String definitionKey;
	
	@JsonProperty("definitionDesc")
	private String definitionDesc;
	
	@JsonProperty("dmnFile")
	private byte[] dmnFile;
	
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
	
	@JsonProperty("dmn")
	private String dmn;
	
	@JsonProperty("dmnByte")
	private byte[] dmnByte;
	
	@JsonProperty("deployFlag")
	private String deployFlag;
	
	@JsonProperty("historyFlag")
	private String historyFlag;
	
	@JsonProperty("definitionId")
	private String definitionId;
	
	@JsonProperty("deployDatetime")
	private Date deployDatetime;
	
	@JsonProperty("deployUserId")
	private String deployUserId;
	
	@JsonProperty("XmlFileName")
	private String XmlFileName;

	@JsonProperty("category")
	private String category;

	public String getDefinitionKey() {
		return definitionKey;
	}

	public void setDefinitionKey(String definitionKey) {
		this.definitionKey = definitionKey;
	}

	public String getDefinitionDesc() {
		return definitionDesc;
	}

	public void setDefinitionDesc(String definitionDesc) {
		this.definitionDesc = definitionDesc;
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

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
	
	public Date getDeployDatetime() {
		return deployDatetime;
	}

	public void setDeployDatetime(Date deployDatetime) {
		this.deployDatetime = deployDatetime;
	}

	public Long getDecisionId() {
		return decisionId;
	}

	public void setDecisionId(Long decisionId) {
		this.decisionId = decisionId;
	}

	public String getDeployUserId() {
		return deployUserId;
	}

	public void setDeployUserId(String deployUserId) {
		this.deployUserId = deployUserId;
	}

	public byte[] getDmnFile() {
		return dmnFile;
	}

	public void setDmnFile(byte[] dmnFile) {
		this.dmnFile = dmnFile;
	}

	public String getDmn() {
		return dmn;
	}

	public void setDmn(String dmn) {
		this.dmn = dmn;
	}

	public byte[] getDmnByte() {
		return dmnByte;
	}

	public void setDmnByte(byte[] dmnByte) {
		this.dmnByte = dmnByte;
	}

	public String getXmlFileName() {
		return XmlFileName;
	}

	public void setXmlFileName(String xmlFileName) {
		XmlFileName = xmlFileName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
