package net.syscon.s4.im.beans;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;

import java.io.Serializable;
import java.util.List;

/**
 * Class CaseloadAgencyLocationsCommitBean extends BaseModel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseloadAgencyLocationsCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<CaseloadAgencyLocations> insertList;
	@JsonProperty("deleteList")
	private List<CaseloadAgencyLocations> deleteList;
	@JsonProperty("updateList")
	private List<CaseloadAgencyLocations> updateList;

	/**
	 * Creates new CaseloadAgencyLocationsCommitBean class Object
	 */
	public CaseloadAgencyLocationsCommitBean() {
		// TODO: CaseloadAgencyLocationsCommitBean
	}

	
	public void setInsertList(final List<CaseloadAgencyLocations> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<CaseloadAgencyLocations> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<CaseloadAgencyLocations> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CaseloadAgencyLocations> getInsertList() {
		return insertList;
	}

	public List<CaseloadAgencyLocations> getUpdateList() {
		return updateList;
	}

	public List<CaseloadAgencyLocations> getDeleteList() {
		return deleteList;
	}

}
