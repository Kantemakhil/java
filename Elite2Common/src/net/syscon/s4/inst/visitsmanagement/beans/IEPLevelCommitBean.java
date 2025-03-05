package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class IEPLevelCommitBean extends BaseModel implements Serializable {
	
	@JsonProperty("insertList")
	private List<IepLevelBean> insertList;

	@JsonProperty("deleteList")
	private List<IepLevelBean> deleteList;

	@JsonProperty("updateList")
	private List<IepLevelBean> updateList;

	public List<IepLevelBean> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<IepLevelBean> insertList) {
		this.insertList = insertList;
	}

	public List<IepLevelBean> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<IepLevelBean> deleteList) {
		this.deleteList = deleteList;
	}

	public List<IepLevelBean> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<IepLevelBean> updateList) {
		this.updateList = updateList;
	}
	
	@Override
	public String toString() {
		return "IEPLevelCommitBean [insertList=" + insertList + ", deleteList=" + deleteList + ", updateList="
				+ updateList + "]";
	}

}
