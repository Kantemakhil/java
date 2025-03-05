package net.syscon.s4.im.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class GroupedObligationsCommitBean extends BaseBean
 */
@XmlRootElement
public class GroupedObligationsCommitBean extends BaseModel {
	private List<GroupedObligations> insertList;
	private List<GroupedObligations> deleteList;
	private List<GroupedObligations> updateList;

	public void setInsertList(final List<GroupedObligations> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<GroupedObligations> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<GroupedObligations> deleteList) {
		this.deleteList = deleteList;
	}

	public List<GroupedObligations> getInsertList() {
		return insertList;
	}

	public List<GroupedObligations> getUpdateList() {
		return updateList;
	}

	public List<GroupedObligations> getDeleteList() {
		return deleteList;
	}

}
