package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class AddressUsagesCommitBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressUsagesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AddressUsages> insertList;

	@JsonProperty("deleteList")
	private List<AddressUsages> deleteList;

	@JsonProperty("updateList")
	private List<AddressUsages> updateList;

	/**
	 *
	 * @return
	 */
	public List<AddressUsages> getInsertList() {
		return insertList;
	}

	/**
	 *
	 * @param insertList
	 */
	public void setInsertList(List<AddressUsages> insertList) {
		this.insertList = insertList;
	}

	/**
	 *
	 * @return
	 */
	public List<AddressUsages> getDeleteList() {
		return deleteList;
	}

	/**
	 *
	 * @param deleteList
	 */
	public void setDeleteList(List<AddressUsages> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 *
	 * @return
	 */
	public List<AddressUsages> getUpdateList() {
		return updateList;
	}

	/**
	 *
	 * @param updateList
	 */
	public void setUpdateList(List<AddressUsages> updateList) {
		this.updateList = updateList;
	}

}