package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VLivingUnitOffendersCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<VLivingUnitOffenders> insertList;

	@JsonProperty("deleteList")
	private List<VLivingUnitOffenders> deleteList;

	@JsonProperty("updateList")
	private List<VLivingUnitOffenders> updateList;

	/**
	 * Creates new VLivingUnitOffendersCommitBean class Object
	 */
	public VLivingUnitOffendersCommitBean() {
		// VLivingUnitOffendersCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<VLivingUnitOffenders> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */

	public void setInsertList(List<VLivingUnitOffenders> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<VLivingUnitOffenders> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */

	public void setDeleteList(List<VLivingUnitOffenders> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<VLivingUnitOffenders> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */

	public void setUpdateList(List<VLivingUnitOffenders> updateList) {
		this.updateList = updateList;
	}

}
