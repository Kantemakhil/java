package net.syscon.s4.inst.automatedcounts.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class AgencyLocationCountsCommitBean extends BaseBean
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyLocationCountsCommitBean extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AgencyLocationCounts> insertList;
	@JsonProperty("deleteList")
	private List<AgencyLocationCounts> deleteList;
	@JsonProperty("updateList")
	private List<AgencyLocationCounts> updateList;

	public void setInsertList(List<AgencyLocationCounts> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<AgencyLocationCounts> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<AgencyLocationCounts> deleteList) {
		this.deleteList = deleteList;
	}

	public List<AgencyLocationCounts> getInsertList() {
		return insertList;
	}

	public List<AgencyLocationCounts> getUpdateList() {
		return updateList;
	}

	public List<AgencyLocationCounts> getDeleteList() {
		return deleteList;
	}

}
