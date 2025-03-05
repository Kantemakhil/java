package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VTrustHeaderCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VTrustHeader> insertList;

	@JsonProperty("deleteList")
	private List<VTrustHeader> deleteList;

	@JsonProperty("updateList")
	private List<VTrustHeader> updateList;

	/**
	 * Creates new VTrustHeaderCommitBean class Object
	 */
	public VTrustHeaderCommitBean() {
		// VTrustHeaderCommitBean
	}

	public void setInsertList(final List<VTrustHeader> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VTrustHeader> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VTrustHeader> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VTrustHeader> getInsertList() {
		return insertList;
	}

	public List<VTrustHeader> getUpdateList() {
		return updateList;
	}

	public List<VTrustHeader> getDeleteList() {
		return deleteList;
	}

}
