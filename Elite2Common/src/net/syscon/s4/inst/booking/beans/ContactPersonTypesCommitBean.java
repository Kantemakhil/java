package net.syscon.s4.inst.booking.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class ContactPersonTypesCommitBean  extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ContactPersonTypes> insertList;
	private List<ContactPersonTypes> deleteList;
	private List<ContactPersonTypes> updateList;

	public void setInsertList(List<ContactPersonTypes> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<ContactPersonTypes> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<ContactPersonTypes> deleteList) {
		this.deleteList = deleteList;
	}

	public List<ContactPersonTypes> getInsertList() {
		return insertList;
	}

	public List<ContactPersonTypes> getUpdateList() {
		return updateList;
	}

	public List<ContactPersonTypes> getDeleteList() {
		return deleteList;
	}

}
