package net.syscon.s4.inst.legals.beans;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class PriorHistoryCommitBean extends BaseModel implements Serializable{


	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<PriorHistory> insertList;

	@JsonProperty("updateList")
	private List<PriorHistory> updateList;

	@JsonProperty("deleteList")
	private List<PriorHistory> deleteList;
	/**
	 * Creates new PriorHistoryCommitBean class Object
	 */
	public PriorHistoryCommitBean() {
		// PriorHistoryCommitBean
	}

	public void setInsertList(final List<PriorHistory> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<PriorHistory> updateList) {
		this.updateList = updateList;
	}

	public List<PriorHistory> getInsertList() {
		return insertList;
	}

	public List<PriorHistory> getUpdateList() {
		return updateList;
	}
	public void setDeleteList(final List<PriorHistory> deleteList) {
		this.deleteList = deleteList;
	}

	public List<PriorHistory> getDeleteList() {
		return deleteList;
	}



}
