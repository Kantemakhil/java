package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderResidenceCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderResidence> insertList;
	@JsonProperty("deleteList")
	private List<OffenderResidence> deleteList;
	@JsonProperty("updateList")
	private List<OffenderResidence> updateList;

	/**
	 * Creates new OffenderResidenceCommitBean class Object
	 */
	public OffenderResidenceCommitBean() {
		// OffenderResidenceCommitBean
	}
	
	public void setInsertList(final List<OffenderResidence> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderResidence> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderResidence> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderResidence> getInsertList() {
		return insertList;
	}

	public List<OffenderResidence> getUpdateList() {
		return updateList;
	}

	public List<OffenderResidence> getDeleteList() {
		return deleteList;
	}

}
