package net.syscon.s4.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderProposalsCommitBean extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OrderProposals> insertList;
	@JsonProperty("deleteList")
	private List<OrderProposals> deleteList;
	@JsonProperty("updateList")
	private List<OrderProposals> updateList;
	
	public List<OrderProposals> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OrderProposals> insertList) {
		this.insertList = insertList;
	}
	public List<OrderProposals> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OrderProposals> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OrderProposals> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OrderProposals> updateList) {
		this.updateList = updateList;
	}
	
	
}
