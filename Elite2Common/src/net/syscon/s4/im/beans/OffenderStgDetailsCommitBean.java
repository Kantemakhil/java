package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OffenderStgDetailsCommitBean extends BaseBean
 */

public class OffenderStgDetailsCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderStgDetails> insertList;
	private List<OffenderStgDetails> deleteList;
	private List<OffenderStgDetails> updateList;

	public void setInsertList(final List<OffenderStgDetails> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderStgDetails> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderStgDetails> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderStgDetails> getInsertList() {
		return insertList;
	}

	public List<OffenderStgDetails> getUpdateList() {
		return updateList;
	}

	public List<OffenderStgDetails> getDeleteList() {
		return deleteList;
	}

}
