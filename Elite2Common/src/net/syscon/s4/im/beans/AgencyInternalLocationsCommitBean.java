package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyInternalLocationsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AgencyInternalLocations> insertList;

	@JsonProperty("deleteList")
	private List<AgencyInternalLocations> deleteList;

	@JsonProperty("updateList")
	private List<AgencyInternalLocations> updateList;

	/**
	 * Creates new AgencyInternalLocationsCommitBean class Object
	 */
	public AgencyInternalLocationsCommitBean() {
		// AgencyInternalLocationsCommitBean
	}

	public void setInsertList(final List<AgencyInternalLocations> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<AgencyInternalLocations> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<AgencyInternalLocations> deleteList) {
		this.deleteList = deleteList;
	}

	public List<AgencyInternalLocations> getInsertList() {
		return insertList;
	}

	public List<AgencyInternalLocations> getUpdateList() {
		return updateList;
	}

	public List<AgencyInternalLocations> getDeleteList() {
		return deleteList;
	}

}
