package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.SuspendDeductionTypes;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class SuspendDeductionTypesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<SuspendDeductionTypes> insertList;

	@JsonProperty("deleteList")
	private List<SuspendDeductionTypes> deleteList;

	@JsonProperty("updateList")
	private List<SuspendDeductionTypes> updateList;

	/**
	 * Creates new SuspendDeductionTypesCommitBean class Object
	 */
	public SuspendDeductionTypesCommitBean() {
		// SuspendDeductionTypesCommitBean
	}

	public void setInsertList(final List<SuspendDeductionTypes> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<SuspendDeductionTypes> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<SuspendDeductionTypes> deleteList) {
		this.deleteList = deleteList;
	}

	public List<SuspendDeductionTypes> getInsertList() {
		return insertList;
	}

	public List<SuspendDeductionTypes> getUpdateList() {
		return updateList;
	}

	public List<SuspendDeductionTypes> getDeleteList() {
		return deleteList;
	}

}
