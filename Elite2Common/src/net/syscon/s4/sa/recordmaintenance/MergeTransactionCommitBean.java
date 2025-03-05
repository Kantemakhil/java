package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
public class MergeTransactionCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<MergeTransactionBean> insertList;
	@JsonProperty("deleteList")
	private List<MergeTransactionBean> deleteList;
	@JsonProperty("updateList")
	private List<MergeTransactionBean> updateList;
	
	public List<MergeTransactionBean> getInsertList() {
		return insertList;
	}
	public List<MergeTransactionBean> getDeleteList() {
		return deleteList;
	}
	public List<MergeTransactionBean> getUpdateList() {
		return updateList;
	}
	public void setInsertList(List<MergeTransactionBean> insertList) {
		this.insertList = insertList;
	}
	public void setDeleteList(List<MergeTransactionBean> deleteList) {
		this.deleteList = deleteList;
	}
	public void setUpdateList(List<MergeTransactionBean> updateList) {
		this.updateList = updateList;
	}
	
	
	
}
