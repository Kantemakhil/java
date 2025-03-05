package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RemittersCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<Remitters> insertList;

	@JsonProperty("deleteList")
	private List<Remitters> deleteList;

	@JsonProperty("updateList")
	private List<Remitters> updateList;

	/**
	 * Creates new RemittersubAccountsCommitBean class Object
	 */
	public RemittersCommitBean() {
		// TODO: RemittersCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<Remitters> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<Remitters> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<Remitters> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<Remitters> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<Remitters> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<Remitters> updateList) {
		this.updateList = updateList;
	}

}
