package net.syscon.s4.im.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class StgIdentifiersCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<StgIdentifiers> insertList;
	private List<StgIdentifiers> deleteList;
	private List<StgIdentifiers> updateList;

	public void setInsertList(final List<StgIdentifiers> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<StgIdentifiers> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<StgIdentifiers> deleteList) {
		this.deleteList = deleteList;
	}

	public List<StgIdentifiers> getInsertList() {
		return insertList;
	}

	public List<StgIdentifiers> getUpdateList() {
		return updateList;
	}

	public List<StgIdentifiers> getDeleteList() {
		return deleteList;
	}

}
