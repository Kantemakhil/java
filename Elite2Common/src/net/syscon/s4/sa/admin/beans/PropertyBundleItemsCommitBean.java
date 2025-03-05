package net.syscon.s4.sa.admin.beans;

import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class PropertyBundleItemsCommitBean extends BaseModel {

	private List<PropertyBundleItems> insertList;
	private List<PropertyBundleItems> deleteList;
	private List<PropertyBundleItems> updateList;
	
	public List<PropertyBundleItems> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<PropertyBundleItems> insertList) {
		this.insertList = insertList;
	}
	public List<PropertyBundleItems> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<PropertyBundleItems> deleteList) {
		this.deleteList = deleteList;
	}
	public List<PropertyBundleItems> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<PropertyBundleItems> updateList) {
		this.updateList = updateList;
	}
	 	

}
