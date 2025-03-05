package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OffenderStgAffiliationsCommitBean extends BaseBean
 */

public class OffenderStgAffiliationsCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderStgAffiliations> insertList;
	private List<OffenderStgAffiliations> deleteList;
	private List<OffenderStgAffiliations> updateList;

	public void setInsertList(final List<OffenderStgAffiliations> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderStgAffiliations> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderStgAffiliations> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderStgAffiliations> getInsertList() {
		return insertList;
	}

	public List<OffenderStgAffiliations> getUpdateList() {
		return updateList;
	}

	public List<OffenderStgAffiliations> getDeleteList() {
		return deleteList;
	}

}
