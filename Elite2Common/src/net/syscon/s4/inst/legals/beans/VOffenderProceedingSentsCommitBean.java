package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class VOffenderProceedingSentsCommitBean  extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private List<VOffenderProceedingSents> insertList;
	private List<VOffenderProceedingSents> deleteList;
	private List<VOffenderProceedingSents> updateList;
	
	
	public List<VOffenderProceedingSents> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VOffenderProceedingSents> insertList) {
		this.insertList = insertList;
	}
	public List<VOffenderProceedingSents> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VOffenderProceedingSents> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VOffenderProceedingSents> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VOffenderProceedingSents> updateList) {
		this.updateList = updateList;
	}
	

}
