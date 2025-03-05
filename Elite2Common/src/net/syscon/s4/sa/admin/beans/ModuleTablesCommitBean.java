package net.syscon.s4.sa.admin.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.AuditLog;
import net.syscon.s4.common.beans.BaseModel;


public class ModuleTablesCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<ModuleTables> insertList;
	private List<ModuleTables> deleteList;
	private List<ModuleTables> updateList;
	
	@JsonProperty("auditLog")
	private AuditLog auditLog;
	
	@JsonProperty("continueFlag")
	private String continueFlag;
	
	
	public List<ModuleTables> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<ModuleTables> insertList) {
		this.insertList = insertList;
	}
	public List<ModuleTables> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<ModuleTables> deleteList) {
		this.deleteList = deleteList;
	}
	public List<ModuleTables> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<ModuleTables> updateList) {
		this.updateList = updateList;
	}
	public String getContinueFlag() {
		return continueFlag;
	}
	public void setContinueFlag(String continueFlag) {
		this.continueFlag = continueFlag;
	}
	public AuditLog getAuditLog() {
		return auditLog;
	}
	public void setAuditLog(AuditLog auditLog) {
		this.auditLog = auditLog;
	}
	
}
