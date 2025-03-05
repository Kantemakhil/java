package net.syscon.s4.im.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OffenderInterestsCommitBean extends BaseBean
 */
@XmlRootElement
public class OffenderInterestsCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderInterests> insertList;
	private List<OffenderInterests> deleteList;
	private List<OffenderInterests> updateList;

	public void setInsertList(final List<OffenderInterests> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderInterests> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderInterests> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderInterests> getInsertList() {
		return insertList;
	}

	public List<OffenderInterests> getUpdateList() {
		return updateList;
	}

	public List<OffenderInterests> getDeleteList() {
		return deleteList;
	}

}
