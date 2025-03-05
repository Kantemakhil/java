package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.Phones;

/**
 * Class PhonesCommitBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhonesCommitBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<Phones> insertList;

	@JsonProperty("deleteList")
	private List<Phones> deleteList;

	@JsonProperty("updateList")
	private List<Phones> updateList;

	/**
	 *
	 * @return
	 */
	public List<Phones> getInsertList() {
		return insertList;
	}

	/**
	 *
	 * @param insertList
	 */
	public void setInsertList(List<Phones> insertList) {
		this.insertList = insertList;
	}

	/**
	 *
	 * @return
	 */
	public List<Phones> getDeleteList() {
		return deleteList;
	}

	/**
	 *
	 * @param deleteList
	 */
	public void setDeleteList(List<Phones> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 *
	 * @return
	 */
	public List<Phones> getUpdateList() {
		return updateList;
	}

	/**
	 *
	 * @param updateList
	 */
	public void setUpdateList(List<Phones> updateList) {
		this.updateList = updateList;
	}

}