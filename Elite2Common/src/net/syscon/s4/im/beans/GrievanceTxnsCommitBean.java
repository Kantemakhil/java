package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GrievanceTxnsCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<GrievanceTxns> insertList;

	@JsonProperty("deleteList")
	private List<GrievanceTxns> deleteList;

	@JsonProperty("updateList")
	private List<GrievanceTxns> updateList;

	public List<GrievanceTxns> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<GrievanceTxns> insertList) {
		this.insertList = insertList;
	}

	public List<GrievanceTxns> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<GrievanceTxns> deleteList) {
		this.deleteList = deleteList;
	}

	public List<GrievanceTxns> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<GrievanceTxns> updateList) {
		this.updateList = updateList;
	}
	
}
