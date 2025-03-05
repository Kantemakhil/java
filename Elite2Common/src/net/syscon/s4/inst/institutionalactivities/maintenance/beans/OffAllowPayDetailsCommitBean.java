package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class OffAllowPayDetailsCommitBean extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<OffAllowPayDetails> insertList;
	private List<OffAllowPayDetails> updateList;
	private List<OffAllowPayDetails> deleteList;
	
	public List<OffAllowPayDetails> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffAllowPayDetails> insertList) {
		this.insertList = insertList;
	}
	public List<OffAllowPayDetails> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffAllowPayDetails> updateList) {
		this.updateList = updateList;
	}
	public List<OffAllowPayDetails> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffAllowPayDetails> deleteList) {
		this.deleteList = deleteList;
	}
	
	
}
