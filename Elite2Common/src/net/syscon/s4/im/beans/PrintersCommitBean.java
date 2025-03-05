package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inmate.beans.Printers;

public class PrintersCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Printers> insertList;
	private List<Printers> deleteList;
	private List<Printers> updateList;

	public List<Printers> getInsertList() {
		return insertList;
	}

	public void setInsertList(final List<Printers> insertList) {
		this.insertList = insertList;
	}

	public List<Printers> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(final List<Printers> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Printers> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(final List<Printers> updateList) {
		this.updateList = updateList;
	}

}
