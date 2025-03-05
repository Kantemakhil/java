package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderVisitVisitorsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List< OffenderVisitVisitors> insertList;

	@JsonProperty("deleteList")
	private List< OffenderVisitVisitors> deleteList;

	@JsonProperty("updateList")
	private List< OffenderVisitVisitors> updateList;

	/**
	 * Creates new OffenderVisitVisitorsCommitBean class Object
	 */
	public OffenderVisitVisitorsCommitBean() {
		// TODO: OffenderVisitVisitorsCommitBean
	}

	public void setInsertList(final List< OffenderVisitVisitors> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List< OffenderVisitVisitors> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List< OffenderVisitVisitors> deleteList) {
		this.deleteList = deleteList;
	}

	public List< OffenderVisitVisitors> getInsertList() {
		return insertList;
	}

	public List< OffenderVisitVisitors> getUpdateList() {
		return updateList;
	}

	public List< OffenderVisitVisitors> getDeleteList() {
		return deleteList;
	}

}
