package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OicHearingResultsCommitBean extends BaseModel implements Serializable {

	@JsonProperty("insertList")
	private List<OicHearingResults> insertList;

	@JsonProperty("deleteList")
	private List<OicHearingResults> deleteList;
	
	@JsonProperty("updateList")
	private List<OicHearingResults> updateList;

	/**
	 * Creates new OicHearingResultsCommitBean class Object
	 */
	public OicHearingResultsCommitBean() {

		// OicHearingResultsCommitBean
	}

	public void setInsertList(List<OicHearingResults> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OicHearingResults> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OicHearingResults> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OicHearingResults> getInsertList() {
		return insertList;
	}

	public List<OicHearingResults> getUpdateList() {
		return updateList;
	}

	public List<OicHearingResults> getDeleteList() {
		return deleteList;
	}

}
