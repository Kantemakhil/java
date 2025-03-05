package net.syscon.s4.im.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class ObligationGroupsCommitBean extends BaseBean
 */
@XmlRootElement
public class ObligationGroupsCommitBean extends BaseModel {
	private List<ObligationGroups> insertList;
	private List<ObligationGroups> deleteList;
	private List<ObligationGroups> updateList;

	public void setInsertList(final List<ObligationGroups> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<ObligationGroups> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<ObligationGroups> deleteList) {
		this.deleteList = deleteList;
	}

	public List<ObligationGroups> getInsertList() {
		return insertList;
	}

	public List<ObligationGroups> getUpdateList() {
		return updateList;
	}

	public List<ObligationGroups> getDeleteList() {
		return deleteList;
	}

}
