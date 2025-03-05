package net.syscon.s4.cf.deductions.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffFeeBillTransactionsCommitBean extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<OffFeeBillTransactions> insertList;
	@JsonProperty("deleteList")
	private List<OffFeeBillTransactions> deleteList;
	@JsonProperty("updateList")
	private List<OffFeeBillTransactions> updateList;
	@JsonProperty("stmtInsertList")
	private List<offBillingStatements> stmtInsertList;
	
	public List<OffFeeBillTransactions> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffFeeBillTransactions> insertList) {
		this.insertList = insertList;
	}
	public List<OffFeeBillTransactions> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffFeeBillTransactions> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffFeeBillTransactions> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffFeeBillTransactions> updateList) {
		this.updateList = updateList;
	}
	public List<offBillingStatements> getStmtInsertList() {
		return stmtInsertList;
	}
	public void setStmtInsertList(List<offBillingStatements> stmtInsertList) {
		this.stmtInsertList = stmtInsertList;
	}
	
	

}
