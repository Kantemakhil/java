package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class PropertyBundleCommitBean extends BaseModel {

	private List<PropertyBundles> insertList;
	private List<PropertyBundles> deleteList;
	private List<PropertyBundles> updateList;
	public List<PropertyBundles> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<PropertyBundles> insertList) {
		this.insertList = insertList;
	}
	public List<PropertyBundles> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<PropertyBundles> deleteList) {
		this.deleteList = deleteList;
	}
	public List<PropertyBundles> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<PropertyBundles> updateList) {
		this.updateList = updateList;
	}
	 	

}
