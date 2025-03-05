package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class DeductionTypesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<DeductionTypes> insertList;

	@JsonProperty("deleteList")
	private List<DeductionTypes> deleteList;

	@JsonProperty("updateList")
	private List<DeductionTypes> updateList;

	/**
	 * Creates new DeductionTypesCommitBean class Object
	 */
	public DeductionTypesCommitBean() {
		// DeductionTypesCommitBean
	}

	public void setInsertList(final List<DeductionTypes> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<DeductionTypes> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<DeductionTypes> deleteList) {
		this.deleteList = deleteList;
	}

	public List<DeductionTypes> getInsertList() {
		return insertList;
	}

	public List<DeductionTypes> getUpdateList() {
		return updateList;
	}

	public List<DeductionTypes> getDeleteList() {
		return deleteList;
	}

}
