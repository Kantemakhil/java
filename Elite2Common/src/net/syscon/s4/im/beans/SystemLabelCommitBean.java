package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemLabelCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SystemLables> insertList;
	private List<SystemLables> deleteList;
	private List<SystemLables> updateList;
	public List<SystemLables> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<SystemLables> insertList) {
		this.insertList = insertList;
	}
	public List<SystemLables> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<SystemLables> deleteList) {
		this.deleteList = deleteList;
	}
	public List<SystemLables> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<SystemLables> updateList) {
		this.updateList = updateList;
	}
	
	
}
