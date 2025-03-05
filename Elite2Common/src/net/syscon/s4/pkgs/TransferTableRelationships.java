package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TransferTableRelationships extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("parentTable")
	private String parentTable;
	@JsonProperty("childTable")
	private String childTable;
	@JsonProperty("fkOffenderIdColumn")
	private String fkOffenderIdColumn;
	@JsonProperty("fkSeqColumn")
	private String fkSeqColumn;
	@JsonProperty("fkPkType")
	private String fkPkType;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("relationshipSeq")
	private Integer relationshipSeq;
	@JsonProperty("childUpdateClause")
	private String childUpdateClause;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("preProcessingRule")
	private String preProcessingRule;
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	public String getParentTable() {
		return parentTable;
	}
	public void setParentTable(String parentTable) {
		this.parentTable = parentTable;
	}
	public String getChildTable() {
		return childTable;
	}
	public void setChildTable(String childTable) {
		this.childTable = childTable;
	}
	public String getFkOffenderIdColumn() {
		return fkOffenderIdColumn;
	}
	public void setFkOffenderIdColumn(String fkOffenderIdColumn) {
		this.fkOffenderIdColumn = fkOffenderIdColumn;
	}
	public String getFkSeqColumn() {
		return fkSeqColumn;
	}
	public void setFkSeqColumn(String fkSeqColumn) {
		this.fkSeqColumn = fkSeqColumn;
	}
	public String getFkPkType() {
		return fkPkType;
	}
	public void setFkPkType(String fkPkType) {
		this.fkPkType = fkPkType;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Integer getRelationshipSeq() {
		return relationshipSeq;
	}
	public void setRelationshipSeq(Integer relationshipSeq) {
		this.relationshipSeq = relationshipSeq;
	}
	public String getChildUpdateClause() {
		return childUpdateClause;
	}
	public void setChildUpdateClause(String childUpdateClause) {
		this.childUpdateClause = childUpdateClause;
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
	public String getPreProcessingRule() {
		return preProcessingRule;
	}
	public void setPreProcessingRule(String preProcessingRule) {
		this.preProcessingRule = preProcessingRule;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
