package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ReleasePlansCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<ReleasePlans> insertList;

	@JsonProperty("deleteList")
	private List<ReleasePlans> deleteList;

	@JsonProperty("updateList")
	private List<ReleasePlans> updateList;

	/**
	 * Creates new ReleasePlansCommitBean class Object
	 */
	public ReleasePlansCommitBean() {
		// ReleasePlansCommitBean
	}

	public void setInsertList(final List<ReleasePlans> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<ReleasePlans> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<ReleasePlans> deleteList) {
		this.deleteList = deleteList;
	}

	public List<ReleasePlans> getInsertList() {
		return insertList;
	}

	public List<ReleasePlans> getUpdateList() {
		return updateList;
	}

	public List<ReleasePlans> getDeleteList() {
		return deleteList;
	}

}
