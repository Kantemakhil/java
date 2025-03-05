package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class AllowancesCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Allowances> insertList;
	private List<Allowances> updateList;
	
	public List<Allowances> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<Allowances> insertList) {
		this.insertList = insertList;
	}
	public List<Allowances> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<Allowances> updateList) {
		this.updateList = updateList;
	}

}
