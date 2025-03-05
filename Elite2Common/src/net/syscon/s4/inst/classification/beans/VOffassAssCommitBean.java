package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VOffassAssCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VOffassAss> insertList;
	private List<VOffassAss> deleteList;
	private List<VOffassAss> updateList;

	public void setInsertList(List<VOffassAss> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<VOffassAss> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<VOffassAss> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VOffassAss> getInsertList() {
		return insertList;
	}

	public List<VOffassAss> getUpdateList() {
		return updateList;
	}

	public List<VOffassAss> getDeleteList() {
		return deleteList;
	}

}
