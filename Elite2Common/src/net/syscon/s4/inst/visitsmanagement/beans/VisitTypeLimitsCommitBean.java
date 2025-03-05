package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VisitTypeLimitsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VisitTypeLimits> insertList;

	@JsonProperty("deleteList")
	private List<VisitTypeLimits> deleteList;

	@JsonProperty("updateList")
	private List<VisitTypeLimits> updateList;

	/**
	 * Creates new VisitTypeLimitsCommitBean class Object
	 */
	public VisitTypeLimitsCommitBean() {
		// VisitTypeLimitsCommitBean
	}

	public void setInsertList(final List<VisitTypeLimits> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VisitTypeLimits> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VisitTypeLimits> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VisitTypeLimits> getInsertList() {
		return insertList;
	}

	public List<VisitTypeLimits> getUpdateList() {
		return updateList;
	}

	public List<VisitTypeLimits> getDeleteList() {
		return deleteList;
	}

}
