package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VHeaderBlock2CommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VHeaderBlock2> insertList;

	@JsonProperty("deleteList")
	private List<VHeaderBlock2> deleteList;

	@JsonProperty("updateList")
	private List<VHeaderBlock2> updateList;

	/**
	 * Creates new VHeaderBlock2CommitBean class Object
	 */
	public VHeaderBlock2CommitBean() {
		// VHeaderBlock2CommitBean
	}

	public void setInsertList(final List<VHeaderBlock2> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VHeaderBlock2> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VHeaderBlock2> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VHeaderBlock2> getInsertList() {
		return insertList;
	}

	public List<VHeaderBlock2> getUpdateList() {
		return updateList;
	}

	public List<VHeaderBlock2> getDeleteList() {
		return deleteList;
	}

}
