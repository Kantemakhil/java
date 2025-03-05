package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VOffBalCalsCommitBean extends BaseModel  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List<VOffBalCals> insertList;
	private List<VOffBalCals> updateList;
	private List<VOffBalCals> deleteList;
	
	public List<VOffBalCals> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VOffBalCals> insertList) {
		this.insertList = insertList;
	}
	public List<VOffBalCals> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VOffBalCals> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VOffBalCals> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VOffBalCals> updateList) {
		this.updateList = updateList;
	}
	
	
}
