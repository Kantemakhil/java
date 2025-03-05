package net.syscon.s4.inst.institutionalactivities.maintenance.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class ProgramPaySettingsCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProgramPaySettingsBean> insertList;
	private List<ProgramPaySettingsBean> deleteList;
	private List<ProgramPaySettingsBean> updateList;

	public List<ProgramPaySettingsBean> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<ProgramPaySettingsBean> insertList) {
		this.insertList = insertList;
	}

	public List<ProgramPaySettingsBean> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<ProgramPaySettingsBean> deleteList) {
		this.deleteList = deleteList;
	}

	public List<ProgramPaySettingsBean> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<ProgramPaySettingsBean> updateList) {
		this.updateList = updateList;
	}

}
