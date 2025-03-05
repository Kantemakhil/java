package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderStgAssociationsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderStgAssociations> insertList;

	@JsonProperty("deleteList")
	private List<OffenderStgAssociations> deleteList;

	@JsonProperty("updateList")
	private List<OffenderStgAssociations> updateList;

	/**
	 * Creates new OffenderStgAssociationsCommitBean class Object
	 */
	public OffenderStgAssociationsCommitBean() {
		// OffenderStgAssociationsCommitBean
	}

	public void setInsertList(final List<OffenderStgAssociations> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderStgAssociations> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderStgAssociations> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderStgAssociations> getInsertList() {
		return insertList;
	}

	public List<OffenderStgAssociations> getUpdateList() {
		return updateList;
	}

	public List<OffenderStgAssociations> getDeleteList() {
		return deleteList;
	}

}
