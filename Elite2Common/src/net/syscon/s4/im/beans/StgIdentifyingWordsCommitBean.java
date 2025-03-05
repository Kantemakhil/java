package net.syscon.s4.im.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class StgIdentifyingWordsCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<StgIdentifyingWords> insertList;
	private List<StgIdentifyingWords> deleteList;
	private List<StgIdentifyingWords> updateList;

	public void setInsertList(final List<StgIdentifyingWords> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<StgIdentifyingWords> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<StgIdentifyingWords> deleteList) {
		this.deleteList = deleteList;
	}

	public List<StgIdentifyingWords> getInsertList() {
		return insertList;
	}

	public List<StgIdentifyingWords> getUpdateList() {
		return updateList;
	}

	public List<StgIdentifyingWords> getDeleteList() {
		return deleteList;
	}

}
