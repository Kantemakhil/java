package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class AppealsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("updateList")
	private List<Appeals> updateList;
	
	@JsonProperty("insertList")
	private List<Appeals> insertList;

	public List<Appeals> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<Appeals> updateList) {
		this.updateList = updateList;
	}

	public List<Appeals> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<Appeals> insertList) {
		this.insertList = insertList;
	}
	
	
	
	
}
