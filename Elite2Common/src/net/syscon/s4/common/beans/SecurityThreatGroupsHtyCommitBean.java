package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityThreatGroupsHtyCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<SecurityThreatGroupsHty> insertList;

	@JsonProperty("deleteList")
	private List<SecurityThreatGroupsHty> deleteList;

	@JsonProperty("updateList")
	private List<SecurityThreatGroupsHty> updateList;

	/**
	 * Creates new TransactionTypesCommitBean class Object
	 */
	public SecurityThreatGroupsHtyCommitBean() {
		// TransactionTypesCommitBean
	}

	public void setInsertList(final List<SecurityThreatGroupsHty> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<SecurityThreatGroupsHty> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<SecurityThreatGroupsHty> deleteList) {
		this.deleteList = deleteList;
	}

	public List<SecurityThreatGroupsHty> getInsertList() {
		return insertList;
	}

	public List<SecurityThreatGroupsHty> getUpdateList() {
		return updateList;
	}

	public List<SecurityThreatGroupsHty> getDeleteList() {
		return deleteList;
	}

}
