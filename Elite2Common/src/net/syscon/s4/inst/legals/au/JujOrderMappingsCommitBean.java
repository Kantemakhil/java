package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class JujOrderMappingsCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<JujOrderMappings> insertList;
	@JsonProperty("deleteList ")
	private List<JujOrderMappings> deleteList;
	@JsonProperty("updateList")
	private List<JujOrderMappings> updateList;

	public List<JujOrderMappings> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<JujOrderMappings> insertList) {
		this.insertList = insertList;
	}

	public List<JujOrderMappings> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<JujOrderMappings> deleteList) {
		this.deleteList = deleteList;
	}

	public List<JujOrderMappings> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<JujOrderMappings> updateList) {
		this.updateList = updateList;
	}

}
