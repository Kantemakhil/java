package net.syscon.s4.inst.institutionalactivities.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class prgPayBatchOffCrsCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<prgPayBatchOffCrsBean> insertList;
	private List<prgPayBatchOffCrsBean> deleteList;
	private List<prgPayBatchOffCrsBean> updateList;

	public List<prgPayBatchOffCrsBean> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<prgPayBatchOffCrsBean> insertList) {
		this.insertList = insertList;
	}

	public List<prgPayBatchOffCrsBean> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<prgPayBatchOffCrsBean> deleteList) {
		this.deleteList = deleteList;
	}

	public List<prgPayBatchOffCrsBean> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<prgPayBatchOffCrsBean> updateList) {
		this.updateList = updateList;
	}

}
