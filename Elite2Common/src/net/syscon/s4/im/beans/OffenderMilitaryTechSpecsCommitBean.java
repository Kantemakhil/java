package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderMilitaryTechSpecsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderMilitaryTechSpecs> insertList;

	@JsonProperty("deleteList")
	private List<OffenderMilitaryTechSpecs> deleteList;

	@JsonProperty("updateList")
	private List<OffenderMilitaryTechSpecs> updateList;

	/**
	 * Creates new OffenderMilitaryTechSpecsCommitBean class Object
	 */
	public OffenderMilitaryTechSpecsCommitBean() {
		// OffenderMilitaryTechSpecsCommitBean
	}

	public void setInsertList(final List<OffenderMilitaryTechSpecs> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderMilitaryTechSpecs> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderMilitaryTechSpecs> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderMilitaryTechSpecs> getInsertList() {
		return insertList;
	}

	public List<OffenderMilitaryTechSpecs> getUpdateList() {
		return updateList;
	}

	public List<OffenderMilitaryTechSpecs> getDeleteList() {
		return deleteList;
	}

}
