package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.SuspendDeductions;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class SuspendDeductionsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<SuspendDeductions> insertList;

	@JsonProperty("deleteList")
	private List<SuspendDeductions> deleteList;

	@JsonProperty("updateList")
	private List<SuspendDeductions> updateList;

	/**
	 * Creates new SuspendDeductionsCommitBean class Object
	 */
	public SuspendDeductionsCommitBean() {
		// SuspendDeductionsCommitBean
	}

	public void setInsertList(final List<SuspendDeductions> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<SuspendDeductions> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<SuspendDeductions> deleteList) {
		this.deleteList = deleteList;
	}

	public List<SuspendDeductions> getInsertList() {
		return insertList;
	}

	public List<SuspendDeductions> getUpdateList() {
		return updateList;
	}

	public List<SuspendDeductions> getDeleteList() {
		return deleteList;
	}

}
