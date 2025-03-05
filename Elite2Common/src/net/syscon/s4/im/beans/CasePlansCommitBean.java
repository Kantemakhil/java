package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CasePlansCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<CasePlans> insertList;

	@JsonProperty("deleteList")
	private List<CasePlans> deleteList;

	@JsonProperty("updateList")
	private List<CasePlans> updateList;

	/**
	 * Creates new CasePlansCommitBean class Object
	 */
	public CasePlansCommitBean() {
		// CasePlansCommitBean
	}

	public void setInsertList(final List<CasePlans> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<CasePlans> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<CasePlans> deleteList) {
		this.deleteList = deleteList;
	}

	public List<CasePlans> getInsertList() {
		return insertList;
	}

	public List<CasePlans> getUpdateList() {
		return updateList;
	}

	public List<CasePlans> getDeleteList() {
		return deleteList;
	}

}
