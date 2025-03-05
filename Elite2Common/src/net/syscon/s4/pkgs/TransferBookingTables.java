package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TransferBookingTables extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("tableName")
	private String tableName;
	@JsonProperty("applicationCode")
	private String applicationCode;
	@JsonProperty("pkType")
	private String pkType;
	@JsonProperty("processMethod")
	private String processMethod;
	@JsonProperty("rootFlag")
	private String rootFlag;
	@JsonProperty("completedFlag")
	private String completedFlag;
	@JsonProperty("seqColumn")
	private String seqColumn;
	@JsonProperty("objectClass")
	private String objectClass;
	@JsonProperty("transferDateColumn")
	private String transferDateColumn;
	@JsonProperty("bookingIdColumn")
	private String bookingIdColumn;
	@JsonProperty("offenderIdColumn")
	private String offenderIdColumn;
	@JsonProperty("parentOffIdColumn")
	private String parentOffIdColumn;
	@JsonProperty("parentSeqColumn")
	private String parentSeqColumn;
	@JsonProperty("surrogateKeyColumn")
	private String surrogateKeyColumn;
	@JsonProperty("filterColumn")
	private String filterColumn;
	@JsonProperty("transferSeq")
	private Integer transferSeq;
	@JsonProperty("updateClause")
	private String updateClause;
	@JsonProperty("pkFirstColumn")
	private String pkFirstColumn;
	@JsonProperty("pkSecondColumn")
	private String pkSecondColumn;
	@JsonProperty("bookingWhereClause")
	private String bookingWhereClause;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("preProcessingRule")
	private String preProcessingRule;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getPkType() {
		return pkType;
	}
	public void setPkType(String pkType) {
		this.pkType = pkType;
	}
	public String getProcessMethod() {
		return processMethod;
	}
	public void setProcessMethod(String processMethod) {
		this.processMethod = processMethod;
	}
	public String getRootFlag() {
		return rootFlag;
	}
	public void setRootFlag(String rootFlag) {
		this.rootFlag = rootFlag;
	}
	public String getCompletedFlag() {
		return completedFlag;
	}
	public void setCompletedFlag(String completedFlag) {
		this.completedFlag = completedFlag;
	}
	public String getSeqColumn() {
		return seqColumn;
	}
	public void setSeqColumn(String seqColumn) {
		this.seqColumn = seqColumn;
	}
	public String getObjectClass() {
		return objectClass;
	}
	public void setObjectClass(String objectClass) {
		this.objectClass = objectClass;
	}
	public String getTransferDateColumn() {
		return transferDateColumn;
	}
	public void setTransferDateColumn(String transferDateColumn) {
		this.transferDateColumn = transferDateColumn;
	}
	public String getBookingIdColumn() {
		return bookingIdColumn;
	}
	public void setBookingIdColumn(String bookingIdColumn) {
		this.bookingIdColumn = bookingIdColumn;
	}
	public String getOffenderIdColumn() {
		return offenderIdColumn;
	}
	public void setOffenderIdColumn(String offenderIdColumn) {
		this.offenderIdColumn = offenderIdColumn;
	}
	public String getParentOffIdColumn() {
		return parentOffIdColumn;
	}
	public void setParentOffIdColumn(String parentOffIdColumn) {
		this.parentOffIdColumn = parentOffIdColumn;
	}
	public String getParentSeqColumn() {
		return parentSeqColumn;
	}
	public void setParentSeqColumn(String parentSeqColumn) {
		this.parentSeqColumn = parentSeqColumn;
	}
	public String getSurrogateKeyColumn() {
		return surrogateKeyColumn;
	}
	public void setSurrogateKeyColumn(String surrogateKeyColumn) {
		this.surrogateKeyColumn = surrogateKeyColumn;
	}
	public String getFilterColumn() {
		return filterColumn;
	}
	public void setFilterColumn(String filterColumn) {
		this.filterColumn = filterColumn;
	}
	public Integer getTransferSeq() {
		return transferSeq;
	}
	public void setTransferSeq(Integer transferSeq) {
		this.transferSeq = transferSeq;
	}
	public String getUpdateClause() {
		return updateClause;
	}
	public void setUpdateClause(String updateClause) {
		this.updateClause = updateClause;
	}
	public String getPkFirstColumn() {
		return pkFirstColumn;
	}
	public void setPkFirstColumn(String pkFirstColumn) {
		this.pkFirstColumn = pkFirstColumn;
	}
	public String getPkSecondColumn() {
		return pkSecondColumn;
	}
	public void setPkSecondColumn(String pkSecondColumn) {
		this.pkSecondColumn = pkSecondColumn;
	}
	public String getBookingWhereClause() {
		return bookingWhereClause;
	}
	public void setBookingWhereClause(String bookingWhereClause) {
		this.bookingWhereClause = bookingWhereClause;
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

	public String getPreProcessingRule() {
		return preProcessingRule;
	}
	public void setPreProcessingRule(String preProcessingRule) {
		this.preProcessingRule = preProcessingRule;
	}
	
}



