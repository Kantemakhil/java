package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VClearAccountPayablesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VClearAccountPayables> insertList;

	@JsonProperty("deleteList")
	private List<VClearAccountPayables> deleteList;

	@JsonProperty("updateList")
	private List<VClearAccountPayables> updateList;

	/**
	 * Creates new VClearAccountPayablesCommitBean class Object
	 */
	public VClearAccountPayablesCommitBean() {
		// VClearAccountPayablesCommitBean
	}

	public void setInsertList(final List<VClearAccountPayables> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VClearAccountPayables> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VClearAccountPayables> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VClearAccountPayables> getInsertList() {
		return insertList;
	}

	public List<VClearAccountPayables> getUpdateList() {
		return updateList;
	}

	public List<VClearAccountPayables> getDeleteList() {
		return deleteList;
	}

}
