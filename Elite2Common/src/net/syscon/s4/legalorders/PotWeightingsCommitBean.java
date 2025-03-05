package net.syscon.s4.legalorders;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class PotWeightingsCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private List<PotWeightings> insertList;
	private List<PotWeightings> deleteList;
	private List<PotWeightings> updateList;

	public List<PotWeightings> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<PotWeightings> insertList) {
		this.insertList = insertList;
	}

	public List<PotWeightings> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<PotWeightings> deleteList) {
		this.deleteList = deleteList;
	}

	public List<PotWeightings> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<PotWeightings> updateList) {
		this.updateList = updateList;
	}

}
