package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class StgCaseNotesCommitBean extends BaseBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StgCaseNotesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<StgCaseNotes> insertList;
	private List<StgCaseNotes> deleteList;
	private List<StgCaseNotes> updateList;

	public void setInsertList(final List<StgCaseNotes> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<StgCaseNotes> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<StgCaseNotes> deleteList) {
		this.deleteList = deleteList;
	}

	public List<StgCaseNotes> getInsertList() {
		return insertList;
	}

	public List<StgCaseNotes> getUpdateList() {
		return updateList;
	}

	public List<StgCaseNotes> getDeleteList() {
		return deleteList;
	}

}
