package net.syscon.s4.cf.deductions.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffFeeBillsCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffFeeBills> insertList;
	@JsonProperty("deleteList")
	private List<OffFeeBills> deleteList;
	@JsonProperty("updateList")
	private List<OffFeeBills> updateList;
	public List<OffFeeBills> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffFeeBills> insertList) {
		this.insertList = insertList;
	}
	public List<OffFeeBills> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffFeeBills> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffFeeBills> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffFeeBills> updateList) {
		this.updateList = updateList;
	}
	
	
}
