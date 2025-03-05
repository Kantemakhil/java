package net.syscon.s4.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdersCommitBean extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<Orders> insertList;
	@JsonProperty("deleteList")
	private List<Orders> deleteList;
	@JsonProperty("updateList")
	private List<Orders> updateList;
	
	public List<Orders> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<Orders> insertList) {
		this.insertList = insertList;
	}
	public List<Orders> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<Orders> deleteList) {
		this.deleteList = deleteList;
	}
	public List<Orders> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<Orders> updateList) {
		this.updateList = updateList;
	}
	
	

}
