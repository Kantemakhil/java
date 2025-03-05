package net.syscon.s4.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderProposalConditionsCommitBean extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OrderProposalConditions> insertList;
	@JsonProperty("deleteList")
	private List<OrderProposalConditions> deleteList;
	@JsonProperty("updateList")
	private List<OrderProposalConditions> updateList;
	public List<OrderProposalConditions> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OrderProposalConditions> insertList) {
		this.insertList = insertList;
	}
	public List<OrderProposalConditions> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OrderProposalConditions> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OrderProposalConditions> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OrderProposalConditions> updateList) {
		this.updateList = updateList;
	}
	
	
}
