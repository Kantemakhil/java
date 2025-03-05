package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class MergeOffenderTables extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tableName;
	private String parentTable;
	private String applicationCode;
	private String pkType;
	private String additionalUpdateRule;
	private String callingProcedure;
	private String processMethod;
	private String rootFlag;
	private String completedFlag;
	private String offenderIdColumn;
	private String seqColumn;
	private String objectClass;
	private String modifyUserId;
	private String createUserId;
	private Date createDatetime;
	private Date modifyDatetime;
	private String sealFlag;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getParentTable() {
		return parentTable;
	}
	public void setParentTable(String parentTable) {
		this.parentTable = parentTable;
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
	public String getAdditionalUpdateRule() {
		return additionalUpdateRule;
	}
	public void setAdditionalUpdateRule(String additionalUpdateRule) {
		this.additionalUpdateRule = additionalUpdateRule;
	}
	public String getCallingProcedure() {
		return callingProcedure;
	}
	public void setCallingProcedure(String callingProcedure) {
		this.callingProcedure = callingProcedure;
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
	public String getOffenderIdColumn() {
		return offenderIdColumn;
	}
	public void setOffenderIdColumn(String offenderIdColumn) {
		this.offenderIdColumn = offenderIdColumn;
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
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	
}
