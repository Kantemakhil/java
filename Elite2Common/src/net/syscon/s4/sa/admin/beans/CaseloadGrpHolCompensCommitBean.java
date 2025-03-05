package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class CaseloadGrpHolCompensCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;
	private List<CaseloadGrpHolCompens> insertList;
	private List<CaseloadGrpHolCompens> deleteList;
	private List<CaseloadGrpHolCompens> updateList;
	
	public List<CaseloadGrpHolCompens> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<CaseloadGrpHolCompens> insertList) {
		this.insertList = insertList;
	}
	public List<CaseloadGrpHolCompens> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<CaseloadGrpHolCompens> deleteList) {
		this.deleteList = deleteList;
	}
	public List<CaseloadGrpHolCompens> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<CaseloadGrpHolCompens> updateList) {
		this.updateList = updateList;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
