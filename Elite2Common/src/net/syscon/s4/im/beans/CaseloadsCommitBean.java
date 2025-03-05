package net.syscon.s4.im.beans;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.Caseloads;

/**
 * Class CaseloadCommitBean extends BaseModel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseloadsCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<Caseloads> insertList;
	@JsonProperty("deleteList")
	private List<Caseloads> deleteList;
	@JsonProperty("updateList")
	private List<Caseloads> updateList;

	
	/**
	 * Creates new CaseloadsCommitBean class Object
	 */
	public CaseloadsCommitBean() {
		// TODO: CaseloadsCommitBean
	}
	
	public void setInsertList(final List<Caseloads> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<Caseloads> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<Caseloads> deleteList) {
		this.deleteList = deleteList;
	}

	public List<Caseloads> getInsertList() {
		return insertList;
	}

	public List<Caseloads> getUpdateList() {
		return updateList;
	}

	public List<Caseloads> getDeleteList() {
		return deleteList;
	}

}
