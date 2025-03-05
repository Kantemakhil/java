package net.syscon.s4.inst.movementexternal.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.VHeaderBlock;

/**
 * Class AddressUsagesCommitBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VHeaderBlockCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VHeaderBlock> insertList;

	@JsonProperty("deleteList")
	private List<VHeaderBlock> deleteList;

	@JsonProperty("updateList")
	private List<VHeaderBlock> updateList;

	/**
	 *
	 * @return
	 */
	public List<VHeaderBlock> getInsertList() {
		return insertList;
	}

	/**
	 *
	 * @param insertList
	 */
	public void setInsertList(List<VHeaderBlock> insertList) {
		this.insertList = insertList;
	}

	/**
	 *
	 * @return
	 */
	public List<VHeaderBlock> getDeleteList() {
		return deleteList;
	}

	/**
	 *
	 * @param deleteList
	 */
	public void setDeleteList(List<VHeaderBlock> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 *
	 * @return
	 */
	public List<VHeaderBlock> getUpdateList() {
		return updateList;
	}

	/**
	 *
	 * @param updateList
	 */
	public void setUpdateList(List<VHeaderBlock> updateList) {
		this.updateList = updateList;
	}

}