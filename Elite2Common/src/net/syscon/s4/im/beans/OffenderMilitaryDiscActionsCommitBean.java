package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderMilitaryDiscActionsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderMilitaryDiscActions> insertList;

	@JsonProperty("deleteList")
	private List<OffenderMilitaryDiscActions> deleteList;

	@JsonProperty("updateList")
	private List<OffenderMilitaryDiscActions> updateList;

	/**
	 * Creates new OffenderMilitaryDiscActionsCommitBean class Object
	 */
	public OffenderMilitaryDiscActionsCommitBean() {
		// OffenderMilitaryDiscActionsCommitBean
	}

	public void setInsertList(final List<OffenderMilitaryDiscActions> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderMilitaryDiscActions> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderMilitaryDiscActions> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderMilitaryDiscActions> getInsertList() {
		return insertList;
	}

	public List<OffenderMilitaryDiscActions> getUpdateList() {
		return updateList;
	}

	public List<OffenderMilitaryDiscActions> getDeleteList() {
		return deleteList;
	}

}
