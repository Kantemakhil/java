package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OdynfrmSubmitDataCommitBean extends BaseModel implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<OdynfrmSubmitDataBean> insertList;
	@JsonProperty("deleteList")
	private List<OdynfrmSubmitDataBean> deleteList;
	@JsonProperty("updateList")
	private List<OdynfrmSubmitDataBean> updateList;
	
	public List<OdynfrmSubmitDataBean> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OdynfrmSubmitDataBean> insertList) {
		this.insertList = insertList;
	}
	public List<OdynfrmSubmitDataBean> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OdynfrmSubmitDataBean> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OdynfrmSubmitDataBean> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OdynfrmSubmitDataBean> updateList) {
		this.updateList = updateList;
	}

}
