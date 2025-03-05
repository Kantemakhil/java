package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OffenderIdentifier;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderIdentifiersCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderIdentifier> insertList;

	@JsonProperty("deleteList")
	private List<OffenderIdentifier> deleteList;

	@JsonProperty("updateList")
	private List<OffenderIdentifier> updateList;

	/**
	 * Creates new OffenderIdentifiersCommitBean class Object
	 */
	public OffenderIdentifiersCommitBean() {
		// TODO: OffenderIdentifiersCommitBean
	}

	public void setInsertList(final List<OffenderIdentifier> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderIdentifier> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderIdentifier> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderIdentifier> getInsertList() {
		return insertList;
	}

	public List<OffenderIdentifier> getUpdateList() {
		return updateList;
	}

	public List<OffenderIdentifier> getDeleteList() {
		return deleteList;
	}

}
